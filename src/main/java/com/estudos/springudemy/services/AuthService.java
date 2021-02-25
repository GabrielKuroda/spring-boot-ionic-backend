package com.estudos.springudemy.services;

import com.estudos.springudemy.domain.Cliente;
import com.estudos.springudemy.repositories.ClienteRepository;
import com.estudos.springudemy.services.execptions.ObjectNotFoundExecption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class AuthService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private BCryptPasswordEncoder pe;

    @Autowired
    private EmailService emailService;

    private Random random = new Random();

    public void sendNewPassword(String email){
        Cliente cliente = clienteRepository.findByEmail(email);
        if(cliente == null){
            throw new ObjectNotFoundExecption("Email não encontrado!");
        }
        String newPass = newPassword();
        cliente.setSenha(pe.encode(newPass));
        clienteRepository.save(cliente);
        emailService.sendNewPasswordEmail(cliente,newPass);
    }

    private String newPassword() {
        char[] vet = new char[10];
        for(int i = 0;i<10;i++){
            vet[i] = randomChar();
        }
        return new String(vet);
    }

    private char randomChar() {
        int opt = random.nextInt(3);
        if(opt == 0){//gera Digito
            return (char) (random.nextInt(10) + 48);
        }else if(opt == 1){//Letra Maiuscula
            return (char) (random.nextInt(26) + 65);
        }else {//Letra Minuscula
            return (char) (random.nextInt(26) + 97);
        }
    }

}
