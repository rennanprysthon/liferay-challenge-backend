package br.com.liferay.services.exceptions;

public class ProjectException extends  RuntimeException{
    public ProjectException(String message) {
        super(message);
    }

    public ProjectException(String message, Throwable cause) {
        super(message, cause);
    }
}
