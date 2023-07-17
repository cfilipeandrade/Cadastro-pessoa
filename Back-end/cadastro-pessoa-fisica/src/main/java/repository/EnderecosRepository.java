package repository;

import model.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
public interface EnderecosRepository extends JpaRepository<Endereco, Long> {
    void delete(Endereco e);
}
