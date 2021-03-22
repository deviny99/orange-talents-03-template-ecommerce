package br.com.zup.global.web.exception;

import org.springframework.http.HttpStatus;

public class ControllerException  extends RuntimeException {

    private final HttpStatus status;

    public ControllerException(HttpStatus status, String message){
        super(message);
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public static ControllerException notFound(String message){
        return new ControllerException(HttpStatus.NOT_FOUND,message);
    }

    public static ControllerException forbidden(String message) {
        return new ControllerException(HttpStatus.FORBIDDEN, message);
    }

    public static ControllerException notAcceptable(String message) {
        return new ControllerException(HttpStatus.NOT_ACCEPTABLE, message);
    }

    public static ControllerException conflict(String message) {
        return new ControllerException(HttpStatus.CONFLICT, message);
    }

    public static ControllerException badRequest(String message) {
        return new ControllerException(HttpStatus.BAD_REQUEST, message);
    }

    public static ControllerException preconditionFailed(String message) {
        return new ControllerException(HttpStatus.PRECONDITION_FAILED, message);
    }

    public static ControllerException unsupportedMediaType(String message) {
        return new ControllerException(HttpStatus.UNSUPPORTED_MEDIA_TYPE, message);
    }
}
