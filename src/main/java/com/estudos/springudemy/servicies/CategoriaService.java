package com.estudos.springudemy.servicies;

import com.estudos.springudemy.domain.Categoria;
import com.estudos.springudemy.repositories.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public Categoria buscar(Integer id){
        Optional<Categoria> obj = categoriaRepository.findById(id);
        return obj.orElse(null);
    }

}
