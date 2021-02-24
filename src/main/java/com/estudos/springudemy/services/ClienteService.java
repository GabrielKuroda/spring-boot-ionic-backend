package com.estudos.springudemy.services;

import com.estudos.springudemy.domain.Categoria;
import com.estudos.springudemy.domain.Cidade;
import com.estudos.springudemy.domain.Cliente;
import com.estudos.springudemy.domain.Endereco;
import com.estudos.springudemy.domain.enums.TipoCliente;
import com.estudos.springudemy.dto.CategoriaDTO;
import com.estudos.springudemy.dto.ClienteDTO;
import com.estudos.springudemy.dto.ClienteNewDTO;
import com.estudos.springudemy.repositories.ClienteRepository;
import com.estudos.springudemy.repositories.EnderecoRepository;
import com.estudos.springudemy.services.execptions.DataIntegrityExecption;
import com.estudos.springudemy.services.execptions.ObjectNotFoundExecption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private BCryptPasswordEncoder pe;

    public Cliente find(Integer id){
        Optional<Cliente> obj = clienteRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundExecption(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
    }

    @Transactional
    public Cliente insert(Cliente obj) {
        obj.setId(null);
        obj = clienteRepository.save(obj);
        enderecoRepository.saveAll(obj.getEnderecos());
        return obj;
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
        return new Cliente(dto.getId(),dto.getNome(),dto.getEmail(),null,null,null);
    }

    public Cliente fromDTO(ClienteNewDTO dto){
        Cliente cli = new Cliente(null,dto.getNome(),dto.getEmail(),dto.getCpfOuCnpj(), TipoCliente.toEnum(dto.getTipo()),pe.encode(dto.getSenha()));
        Cidade cid = new Cidade(dto.getCidadeId(), null,null);
        Endereco end = new Endereco(null,dto.getLogradouro(),dto.getNumero(),dto.getComplemento(),dto.getBairro(),dto.getCep(),cli,cid);
        cli.getEnderecos().add(end);
        cli.getTelefones().add(dto.getTelefone1());
        if(dto.getTelefone2() != null){
            cli.getTelefones().add(dto.getTelefone2());
        }
        if(dto.getTelefone3() != null){
            cli.getTelefones().add(dto.getTelefone3());
        }
        return cli;
    }

    private void updateData(Cliente newObj, Cliente obj) {
        newObj.setNome(obj.getNome());
        newObj.setEmail(obj.getEmail());
    }


}
