package dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import model.EnderecoType;
import model.Pessoa;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EnderecoDTO {

    @Id
    private Long id;

    @NotNull
    private EnderecoType type;

    @NotNull
    @Size(min = 9, max = 9)
    private Integer cep;

    @NotNull
    @Size(min = 5, max = 200)
    private String logradouro;

    @NotNull
    @Size(min = 5, max = 10)
    private Integer numero;

    @NotNull
    @Size(min = 5, max = 200)
    private String bairro;

    @NotNull
    @Size(min = 5, max = 200)
    private String cidade;

    @NotNull
    @Size(min = 1, max = 200)
    private String estado;

    @NotNull
    @Size(min = 1, max = 200)
    private String complemento;

}
