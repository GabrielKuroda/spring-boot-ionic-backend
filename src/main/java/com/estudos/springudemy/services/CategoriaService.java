package com.estudos.springudemy.services;

import com.estudos.springudemy.domain.Categoria;
import com.estudos.springudemy.repositories.CategoriaRepository;
import com.estudos.springudemy.services.execptions.DataIntegrityExecption;
import com.estudos.springudemy.services.execptions.ObjectNotFoundExecption;
import org.hibernate.ObjectDeletedException;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public Categoria find(Integer id){
        Optional<Categoria> obj = categoriaRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundExecption(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName()));
    }

    public Categoria insert(Categoria obj) {
        obj.setId(null);
        return categoriaRepository.save(obj);
    }

    public Categoria update(Categoria obj) {
        find(obj.getId());
        return categoriaRepository.save(obj);
    }

    public void delete(Integer id) {
        find(id);
        try{
            categoriaRepository.deleteById(id);
        }catch (DataIntegrityViolationException e){
            throw new DataIntegrityExecption("Não é possivel excluir uma categoria que possua produtos!");
        }
    }
}
