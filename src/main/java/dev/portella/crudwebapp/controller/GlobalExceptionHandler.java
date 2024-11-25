package dev.portella.crudwebapp.controller;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final String ERROR = "error";
    private static final String MESSAGE = "message";

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String handleGeneralException(Exception ex, Model model) {
        model.addAttribute(ERROR, "Erro Interno");
        model.addAttribute(MESSAGE, ex.getMessage());
        return ERROR;
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleNotFound(NoHandlerFoundException ex, Model model) {
        model.addAttribute(ERROR, "Página Não Encontrada");
        model.addAttribute(MESSAGE, "O recurso solicitado não pôde ser encontrado.");
        return ERROR;
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleIllegalArgumentException(IllegalArgumentException ex, Model model) {
        model.addAttribute(ERROR, "Requisição Inválida");
        model.addAttribute(MESSAGE, ex.getMessage());
        return ERROR;
    }
}
