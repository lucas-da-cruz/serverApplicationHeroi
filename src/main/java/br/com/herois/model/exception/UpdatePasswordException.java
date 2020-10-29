package br.com.herois.model.exception;

public class UpdatePasswordException extends RuntimeException  {
    private static final long serialVersionUID = 1L;

    public UpdatePasswordException(String msg){
        super(msg);
    }
}