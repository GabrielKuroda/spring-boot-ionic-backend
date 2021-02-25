package com.estudos.springudemy.services;

import com.estudos.springudemy.domain.Cliente;
import com.estudos.springudemy.domain.Pedido;
import org.springframework.mail.SimpleMailMessage;

import javax.mail.internet.MimeMessage;

public interface EmailService {

    void sendOrderConfirmationEmail(Pedido obj);

    void sendEmail(SimpleMailMessage msg);

    void sendOrderConfirmationHtmlEmail(Pedido obj);

    void sendHtmlEmail(MimeMessage msg);

    void sendNewPasswordEmail(Cliente cliente, String newPass);
}
