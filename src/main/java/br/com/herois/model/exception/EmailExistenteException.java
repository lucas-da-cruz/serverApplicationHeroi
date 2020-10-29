package br.com.herois.model.exception;

public class EmailExistenteException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public EmailExistenteException(String msg){
        super(msg);
    }
}
