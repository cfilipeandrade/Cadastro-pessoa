package dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import model.Endereco;
import org.hibernate.validator.constraints.br.CPF;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PessoaDTO {
    @NotNull
    @Size(min = 20, max = 200)
    private String nome;

    @CPF
    @NotNull
    private String cpf;

    @NotNull
    private String telefone;

    private String telefone2;

    @Email
    @NotNull
    private String email;

    @NotNull
    private List<Endereco> enderecos = new ArrayList<>();
}
