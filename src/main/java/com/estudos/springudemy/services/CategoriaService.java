package com.estudos.springudemy.services;

import com.estudos.springudemy.domain.Categoria;
import com.estudos.springudemy.repositories.CategoriaRepository;
import com.estudos.springudemy.services.execptions.ObjectNotFoundExecption;
import org.hibernate.ObjectDeletedException;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public Categoria buscar(Integer id){
        Optional<Categoria> obj = categoriaRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundExecption(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName()));
    }

}
