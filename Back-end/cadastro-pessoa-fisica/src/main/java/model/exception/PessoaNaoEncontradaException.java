package model.exception;

public class PessoaNaoEncontradaException extends RegraNegocioException{
    private static final long serialVersionUID = 1L;

    public PessoaNaoEncontradaException(String mensagem) {
        super(mensagem);
    }
}
