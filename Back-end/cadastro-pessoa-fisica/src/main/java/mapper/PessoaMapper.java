package mapper;

import dto.PessoaDTO;
import model.Pessoa;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PessoaMapper {

    PessoaMapper INSTANCE = Mappers.getMapper(PessoaMapper.class);
    Pessoa toModel(PessoaDTO pessoaDTO);
    PessoaDTO toDTO(Pessoa pessoa);
}
