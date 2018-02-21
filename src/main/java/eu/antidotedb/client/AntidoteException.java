package eu.antidotedb.client;

/**
 * The Class AntidoteException.
 */
public class AntidoteException extends RuntimeException {
    
    public enum ErrorCode {
        UNKNOWN(0), TIMEOUT(1), BOUNDED_COUNTER(2);
        
        private int code;
        
        ErrorCode(int code) {
            this.code = code;
        }
        
        public static ErrorCode getErrorCode(int code) {
            for (ErrorCode ec : values())
                if (ec.code == code)
                    return ec;
            return UNKNOWN;
        }
    }

    /**
     * The Constant serialVersionUID.
     */
    private static final long serialVersionUID = 1L;
    
    private ErrorCode errorCode = ErrorCode.UNKNOWN;

    /**
     * Instantiates a new antidote exception.
     *
     * @param message the message
     */
    public AntidoteException(String message) {
        super(message);
    }

    public AntidoteException(String message, Throwable cause) {
        super(message, cause);
    }

    public AntidoteException(Throwable cause) {
        super(cause);
    }
    
    public AntidoteException(String message, int errorCode) {
        super(message);
        this.errorCode = ErrorCode.getErrorCode(errorCode);
    }
    
    public ErrorCode getErrorCode() {
        return errorCode;
    }
}
