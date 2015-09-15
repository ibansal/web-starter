package com.company.project.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;


@ControllerAdvice
public class ExceptionHandlingController {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ModelAndView exception(Exception exception, HttpServletRequest request) {

        exception.printStackTrace();
        ModelAndView modelAndView = new ModelAndView("common/error");
        modelAndView.addObject("errorMessage", exception.getMessage());
        return modelAndView;
    }
}
