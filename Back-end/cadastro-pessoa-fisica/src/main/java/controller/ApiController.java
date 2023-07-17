package controller;

import model.Endereco;
import model.Pessoa;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.EnderecoService;
import service.PessoaService;
import service.PessoaValidation;

import java.util.List;

@RestController
@RequestMapping(path = "/pessoa", produces = MediaType.APPLICATION_JSON_VALUE)
public class ApiController {

    private PessoaService service;
    private PessoaValidation validation;

    private EnderecoService enderecoService;

    @GetMapping //busca todas as pessoas cadastradas
    public List<Pessoa> listaPessoas() {
        return service.listarPessoas();
    }

    @GetMapping("/{id}") //retorna uma pessoa de 1 id em especifico
    public ResponseEntity<Pessoa> buscaPessoa(@PathVariable Long id){
        return ResponseEntity.ok(service.getPessoa(id)); //Volta o valor 200 na tela
    }

    @PostMapping //Grava a pessoa no banco
    public ResponseEntity<Pessoa> salvaPessoa(@RequestBody Pessoa p){

        boolean verificaCamposVazios = validation.validaCampos(p.getNome(), p.getCpf(), p.getEmail());
        boolean validaExistenciaContato = validation.validaExistenciaEndereco(p.getEnderecos());
        boolean validaCpf =  validation.validarCpf(p.getCpf());

        if(validaCpf == true && verificaCamposVazios == false && validaExistenciaContato == true) {
            Pessoa pessoa = service.gravarPessoaBD(p);
            return ResponseEntity.status(HttpStatus.CREATED).body(pessoa);
        }
        else {
            return ResponseEntity.badRequest().body(p);
        }
    }

    @PutMapping("/")
    public Pessoa update(@RequestBody Pessoa pessoa){
        return  service.alterarPessoaBD(pessoa.getId(), pessoa);
    }

    @DeleteMapping("/{id}")
    @CrossOrigin("http://localhost:3000")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        service.deletarPessoaBD(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    @CrossOrigin("http://localhost:3000")
    public ResponseEntity<Void> deleteEndereco(@PathVariable("id") Long id) {
        enderecoService.deletarEnderecoBD(id);
        return ResponseEntity.ok().build();
    }

}
