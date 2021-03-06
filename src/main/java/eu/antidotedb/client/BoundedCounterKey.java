package eu.antidotedb.client;

import com.google.protobuf.ByteString;
import eu.antidotedb.antidotepb.AntidotePB;

import java.nio.charset.Charset;

import javax.annotation.CheckReturnValue;

public class BoundedCounterKey extends Key<Integer> {

    BoundedCounterKey(AntidotePB.CRDT_type type, ByteString key) {
        super(type, key);
    }

    @Override
    Integer readResponseToValue(AntidotePB.ApbReadObjectResp resp) {
        return ResponseDecoder.bcounter().readResponseToValue(resp);
    }

    /**
     * Creates an update operation, which increments the counter by the given amount.
     * <p>
     * Use the methods on {@link Bucket} to execute the update.
     */
    @CheckReturnValue
    public UpdateOp increment(long inc) {
        AntidotePB.ApbBCounterUpdate.Builder bcounterUpdateInstruction = AntidotePB.ApbBCounterUpdate.newBuilder(); 
        bcounterUpdateInstruction.setInc(inc); // Set increment
        bcounterUpdateInstruction.setId(ByteString.copyFrom("myid", Charset.defaultCharset())); // TODO
        AntidotePB.ApbUpdateOperation.Builder updateOperation = AntidotePB.ApbUpdateOperation.newBuilder();
        updateOperation.setBcounterop(bcounterUpdateInstruction);
        return new UpdateOpDefaultImpl(this, updateOperation);
    }

    /**
     * Creates an update operation, which decrements the counter by the given amount.
     * <p>
     * Use the methods on {@link Bucket} to execute the update.
     */
    @CheckReturnValue
    public UpdateOp decrement(long dec) {
        AntidotePB.ApbBCounterUpdate.Builder bcounterUpdateInstruction = AntidotePB.ApbBCounterUpdate.newBuilder();
        bcounterUpdateInstruction.setDec(dec); // Set decrement
        bcounterUpdateInstruction.setId(ByteString.copyFrom("myid", Charset.defaultCharset())); // TODO
        AntidotePB.ApbUpdateOperation.Builder updateOperation = AntidotePB.ApbUpdateOperation.newBuilder();
        updateOperation.setBcounterop(bcounterUpdateInstruction);
        return new UpdateOpDefaultImpl(this, updateOperation);
    }

}
