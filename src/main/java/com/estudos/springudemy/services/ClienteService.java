package com.estudos.springudemy.services;

import com.estudos.springudemy.domain.Categoria;
import com.estudos.springudemy.domain.Cliente;
import com.estudos.springudemy.dto.CategoriaDTO;
import com.estudos.springudemy.dto.ClienteDTO;
import com.estudos.springudemy.repositories.ClienteRepository;
import com.estudos.springudemy.services.execptions.DataIntegrityExecption;
import com.estudos.springudemy.services.execptions.ObjectNotFoundExecption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public Cliente find(Integer id){
        Optional<Cliente> obj = clienteRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundExecption(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
    }

    public Cliente update(Cliente obj) {
        Cliente newObj = find(obj.getId());
        updateData(newObj,obj);
        return clienteRepository.save(newObj);
    }

    public void delete(Integer id) {
        find(id);
        try{
            clienteRepository.deleteById(id);
        }catch (DataIntegrityViolationException e){
            throw new DataIntegrityExecption("Não é possivel excluir porque ha entidades relacionadas!");
        }
    }

    public List<Cliente> findAll() {
        return clienteRepository.findAll();
    }

    public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
        PageRequest pageRequest =  PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        return clienteRepository.findAll(pageRequest);
    }

    public Cliente fromDTO(ClienteDTO dto){
        return new Cliente(dto.getId(),dto.getNome(),dto.getEmail(),null,null);
    }

    private void updateData(Cliente newObj, Cliente obj) {
        newObj.setNome(obj.getNome());
        newObj.setEmail(obj.getEmail());
    }
}
