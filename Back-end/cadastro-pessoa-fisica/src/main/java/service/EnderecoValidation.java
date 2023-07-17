package service;

public interface EnderecoValidation {
    boolean validarCEP(Integer cep);

    boolean validaCampos(String rua, String cep, String estado);
}
