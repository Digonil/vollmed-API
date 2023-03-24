package med.voll.api.infra.exceptions;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class TratadordeErros {

    //Por padrão, os erros tratados pelo spring volta o erro 500.
    //Tratamos para retornar o código do erro condizente com o tipo de exceção.
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity tratarErro404() {
        //Se atentar ao build() para criar o objeto ResponseEntity.
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity tratarErro400(MethodArgumentNotValidException ex) {

        //recebe uma lista com os campos inválidos.
        var erros = ex.getFieldErrors();

        //Como o body retorna o objeto no corpo da requisição, não precisa do build().
        return ResponseEntity
                .badRequest()
                .body(erros.stream().map(DadosErrosValidacao::new).toList());

    }

    private record DadosErrosValidacao(String campo, String mensagem) {
        public DadosErrosValidacao(FieldError erro) {
            this(erro.getField(), erro.getDefaultMessage());

        }

    }
}
