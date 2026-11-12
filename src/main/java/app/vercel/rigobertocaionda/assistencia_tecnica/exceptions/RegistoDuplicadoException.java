package app.vercel.rigobertocaionda.assistencia_tecnica.exceptions;

public class RegistoDuplicadoException extends RuntimeException {
    public RegistoDuplicadoException(String message) {
        super(message);
    }
}
