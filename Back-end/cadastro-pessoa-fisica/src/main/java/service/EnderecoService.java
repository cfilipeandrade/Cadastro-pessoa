package service;

import dto.EnderecoDTO;
import mapper.EnderecoMapper;
import model.Endereco;
import model.Pessoa;
import model.exception.RegraNegocioException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.EnderecosRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EnderecoService implements EnderecoValidation{

    EnderecosRepository repository;
    EnderecoMapper mapper;

    @Override
    public boolean validarCEP(Integer cep) {
        return false;
    }

    @Override
    public boolean validaCampos(String rua, String cep, String estado) {
        return false;
    }

    public EnderecoDTO createEndereco(EnderecoDTO enderecoDTO) throws RegraNegocioException {
        Endereco endereco = mapper.toModel(enderecoDTO);
        Endereco savedEndereco = repository.save(endereco);
        return mapper.toDTO(savedEndereco);
    }

    public void deletarEnderecoBD(Long id) {
        Endereco e = findOrFail(id);
        repository.delete(e);
    }

    private Endereco findOrFail(Long id) {
    }


}
