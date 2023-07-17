package service;

import dto.PessoaDTO;
import lombok.AllArgsConstructor;
import mapper.PessoaMapper;
import model.Pessoa;
import model.exception.PessoaNaoEncontradaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.PessoaRepository;

import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PessoaService {

    private PessoaRepository pessoaRepository;

    private PessoaMapper pessoaMapper;

    //Método Get para retornar todos os registros
    public List<Pessoa> listarPessoas(){
        return pessoaRepository.findAll();
    }

    //Método Get para retornar uma pessoa em especifico
    public Pessoa getPessoa(Long id) {
        return findOrFail(id);
    }

    //Construção do método para retornar uma pessoa em especifico
    private Pessoa findOrFail(Long id) {
        return pessoaRepository.findById(id).orElseThrow(() -> new PessoaNaoEncontradaException("A Pessoa não foi localizada, tente novamente com outro id."));
    }

    //Método Post
    public Pessoa gravarPessoaBD(Pessoa p) {
        p.getEnderecos().forEach(c -> c.setPessoa(p)); //Para cada endereco vai inserir uma pessoa
        return pessoaRepository.save(p);
    }

    //Método Put
    public Pessoa alterarPessoaBD(Long id, Pessoa p) {
        Pessoa registroPessoaSalva = findOrFail(id);

        registroPessoaSalva.getEnderecos().clear();
        registroPessoaSalva.getEnderecos().addAll(p.getEnderecos());
        BeanUtils.copyProperties(p, registroPessoaSalva, "id", "endereco"); //Vai copiar as propriedades de p para registroPessoaSalva, ele não vai copiar o id e nem a parte de endereço, pois já está tratado
        return gravarPessoaBD(registroPessoaSalva);//PessoaRepositorio.save(registroPessoaSalva);
    }

    //Método Delete
    public void deletarPessoaBD(Long id) {
        Pessoa p = findOrFail(id);
        pessoaRepository.delete(p);
    }

    public PessoaDTO createPessoa(PessoaDTO pessoaDTO) throws PessoaNaoEncontradaException{
        verifyIfIsAlreadyRegistered(pessoaDTO.getNome());
        Pessoa pessoa = pessoaMapper.toModel(pessoaDTO);
        Pessoa savedPessoa = pessoaRepository.save(pessoa);
        return pessoaMapper.toDTO(savedPessoa);
    }

    private void verifyIfIsAlreadyRegistered(String nome) throws PessoaNaoEncontradaException {
        Optional<Pessoa> optSavedPessoa = pessoaRepository.findByNome(nome);
        if (optSavedPessoa.isPresent()) {
            throw new PessoaNaoEncontradaException(nome);
        }
    }

    public PessoaDTO findByNome(String nome) throws PessoaNaoEncontradaException {
        Pessoa foundPessoa = pessoaRepository.findByNome(nome)
                .orElseThrow(() -> new PessoaNaoEncontradaException(nome));
        return pessoaMapper.toDTO(foundPessoa);
    }

    public List<PessoaDTO> listAll() {
        return pessoaRepository.findAll()
                .stream()
                .map(pessoaMapper::toDTO)
                .collect(Collectors.toList());
    }

    public void deleteByCpf(String cpf) throws PessoaNaoEncontradaException {
        verifyIfExists(cpf);
        pessoaRepository.deleteByCpf(cpf);
    }

    private Pessoa verifyIfExists(String cpf) throws PessoaNaoEncontradaException {
        return pessoaRepository.findByCpf(cpf)
                .orElseThrow(() -> new PessoaNaoEncontradaException(cpf));
    }
}
