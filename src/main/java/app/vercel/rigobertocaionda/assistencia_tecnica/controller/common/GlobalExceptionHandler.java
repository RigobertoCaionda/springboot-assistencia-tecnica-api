package app.vercel.rigobertocaionda.assistencia_tecnica.controller.common;

import app.vercel.rigobertocaionda.assistencia_tecnica.controller.dto.ErroCampo;
import app.vercel.rigobertocaionda.assistencia_tecnica.controller.dto.ErroResposta;
import app.vercel.rigobertocaionda.assistencia_tecnica.exceptions.RegistoDuplicadoException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_CONTENT)
    public ErroResposta handleMethodArgumentNotValidException(MethodArgumentNotValidException e) { // Exceção para trabalhar com o @Valid
        List<FieldError> fieldErrors = e.getFieldErrors();
        var listaErros = fieldErrors
                .stream()
                .map(fe -> new ErroCampo(fe.getField(), fe.getDefaultMessage()))
                .collect(Collectors.toList());

        return new ErroResposta(HttpStatus.UNPROCESSABLE_CONTENT.value(), "Erro de validação", listaErros);
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErroResposta handleErrosNaoTratados(RuntimeException e) {
        System.out.println(e.getMessage());
        return new ErroResposta(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Ocorreu um erro inesperado, entre em contato com a administração.", List.of());
    }

    @ExceptionHandler(RegistoDuplicadoException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErroResposta handleRegistoDuplicadoException(RegistoDuplicadoException e) {
        return ErroResposta.conflito(e.getMessage());
    }
}
