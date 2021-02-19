package com.estudos.springudemy.services;

import com.estudos.springudemy.domain.Categoria;
import com.estudos.springudemy.domain.Pedido;
import com.estudos.springudemy.repositories.CategoriaRepository;
import com.estudos.springudemy.repositories.PedidoRepository;
import com.estudos.springudemy.services.execptions.ObjectNotFoundExecption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    public Pedido find(Integer id){
        Optional<Pedido> obj = pedidoRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundExecption(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName()));
    }

}
