package com.estudos.springudemy.dto;

import com.estudos.springudemy.domain.Cliente;
import com.estudos.springudemy.services.validation.ClienteUpdate;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@ClienteUpdate
public class ClienteDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;

    @NotEmpty(message = "Preenchimento Obrigatorio")
    @Length(min = 5,max = 120,message = "O tamanho de ser entro 5 e 120 caracteres")
    private String nome;

    @Email(message = "Email Invalido")
    @NotEmpty(message = "Preenchimento Obrigatorio")
    private String email;

    public ClienteDTO() {
    }

    public ClienteDTO(Cliente cliente) {
        id = cliente.getId();
        nome = cliente.getNome();
        email = cliente.getEmail();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
