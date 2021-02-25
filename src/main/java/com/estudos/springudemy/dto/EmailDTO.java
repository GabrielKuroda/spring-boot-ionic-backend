package com.estudos.springudemy.dto;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

public class EmailDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @Email(message = "Email Invalido")
    @NotEmpty(message = "Preenchimento Obrigatorio")
    private String email;

    public EmailDTO() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
