package repository;

import model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
    Optional<Pessoa> findByNome(String nome);
    void deleteByCpf(String cpf);
    Optional<Pessoa> findByCpf(String cpf);
}
