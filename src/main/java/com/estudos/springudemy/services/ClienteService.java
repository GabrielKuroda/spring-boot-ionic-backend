package com.estudos.springudemy.services;

import com.estudos.springudemy.domain.Cliente;
import com.estudos.springudemy.repositories.ClienteRepository;
import com.estudos.springudemy.services.execptions.ObjectNotFoundExecption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public Cliente buscar(Integer id){
        Optional<Cliente> obj = clienteRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundExecption(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
    }

}
