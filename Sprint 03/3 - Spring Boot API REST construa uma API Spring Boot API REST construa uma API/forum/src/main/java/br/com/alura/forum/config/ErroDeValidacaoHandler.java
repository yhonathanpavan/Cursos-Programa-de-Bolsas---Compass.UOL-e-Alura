package br.com.alura.forum.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ErroDeValidacaoHandler {

    /*
    Como eu descubro que erros aconteceram? Por isso o método handle() recebe o tal do (MethodArgumentNotValidException exception).
    Dentro desse objeto tem todos os erros que aconteceram.
    * */

    @Autowired
    private MessageSource messageSource; // Essa classe MessageSource te ajuda a pegar mensagens de erro, de acordo com o idioma que o cliente requisitar.

    @ResponseStatus(code = HttpStatus.BAD_REQUEST) //Quando dá um erro de validação de formulário, usando Bean Validation, que exceção que o Spring lança? Ele manda uma exception chamada MethodArgumentNotValidException
    @ExceptionHandler(MethodArgumentNotValidException.class) // Preciso dizer para o Spring que esse método deve ser chamado quando houver uma exceção dentro de algum Controller. Para falar isso para o Spring, em cima do método, vamos colocar uma anotação @ExceptionHandler, que é do próprio Spring.
    public List<ErroDeFormularioDto> handle(MethodArgumentNotValidException exception){ //é o método handle() que fará o tratamento do erro

        List<ErroDeFormularioDto> dto = new ArrayList<>();

        // Essa variável tem os erros de formulário, para ele descobrir qual o "locale", qual o local atual para pegar a mensagem no idioma correto.
        List<FieldError> fieldError = exception.getBindingResult().getFieldErrors(); //Se pegarmos o parâmetro exception, seguido do método getBindingResult(), com o resultado das validações, e do método getfieldErrors() que contém todos erros "Field", de formulário.
        fieldError.forEach(e -> {
            //Para pegar mensagens de erro
            String mensagem = messageSource.getMessage(e, LocaleContextHolder.getLocale());
            //Neste caso, quero criar um erro de formulário DTO, então,
            ErroDeFormularioDto erro = new ErroDeFormularioDto(e.getField(), mensagem);
            dto.add(erro);
        });

        return dto; //Ele tem que devolver alguma coisa, que, no nosso caso, vai ser uma lista com as mensagens de erro.
    }
}
