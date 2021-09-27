package io.blocks.integratorsjava.exception;

public class BusinessException extends RuntimeException{
    private int code;

    public BusinessException(int code) {
        this.code = code;
    }

    public BusinessException(String message, int code) {
        super(message);
        this.code = code;
    }

    public BusinessException(String message, Throwable cause, int code) {
        super(message, cause);
        this.code = code;
    }

    public static BusinessException valueOf(int code){
        return new BusinessException(code);
    }

    public int getCode(){
        return code;
    }
}
