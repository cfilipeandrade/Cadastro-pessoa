package service;

import model.Endereco;
import model.Pessoa;

import java.util.List;

public interface PessoaValidation {
    boolean validarCpf(String cpf);

    boolean validaCampos(String nome, String cpf, String email);

    boolean validaExistenciaEndereco(List<Endereco> enderecos);

    Pessoa salvarPessoa(Pessoa p);
}
