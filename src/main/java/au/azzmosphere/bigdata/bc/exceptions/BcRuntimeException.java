package au.azzmosphere.bigdata.bc.exceptions;

public abstract class BcRuntimeException extends RuntimeException {
    public BcRuntimeException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
