package app.vercel.rigobertocaionda.assistencia_tecnica.exceptions;

public class BusinessException extends RuntimeException {
    public BusinessException(String message) {
        super(message);
    }
}
