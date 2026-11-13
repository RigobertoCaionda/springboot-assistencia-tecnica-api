package app.vercel.rigobertocaionda.assistencia_tecnica.exceptions;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super(message);
    }
}
