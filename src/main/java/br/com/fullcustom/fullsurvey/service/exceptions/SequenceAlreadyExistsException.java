package br.com.fullcustom.fullsurvey.service.exceptions;

public class SequenceAlreadyExistsException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public SequenceAlreadyExistsException(String msg) {
        super(msg);
    }

    public SequenceAlreadyExistsException(String msg, Throwable cause) {
        super(msg, cause);
    }

}