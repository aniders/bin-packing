package com.maersk.fse.knpsack.exception;

/**
 * 
 * @author Aniruddh
 *
 */
public class KnapsackServiceException extends Exception {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;

    public KnapsackServiceException() {
        super();
    }

    public KnapsackServiceException(String message) {
        super(message);
    }

    public KnapsackServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public KnapsackServiceException(Throwable cause) {
        super(cause);
    }
}
