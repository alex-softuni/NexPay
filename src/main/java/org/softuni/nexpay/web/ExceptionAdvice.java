package org.softuni.nexpay.web;

import org.softuni.nexpay.exception.EmailAlreadyExistException;
import org.softuni.nexpay.exception.UsernameAlreadyExistException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@ControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler(UsernameAlreadyExistException.class)
    public String handleUsernameAlreadyExist(RedirectAttributes redirectAttributes) {

        redirectAttributes.addFlashAttribute("usernameAlreadyExistMessage", "This username already exists." );
        return "redirect:/register";
    }

    @ExceptionHandler(EmailAlreadyExistException.class)
    public String handleEmailAlreadyExist(RedirectAttributes redirectAttributes) {

        redirectAttributes.addFlashAttribute("emailAlreadyExistMessage", "This email already exists." );
        return "redirect:/register";
    }
}
