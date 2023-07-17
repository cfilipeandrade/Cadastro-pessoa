package mapper;

import dto.EnderecoDTO;
import model.Endereco;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface EnderecoMapper {
    EnderecoMapper INSTANCE = Mappers.getMapper(EnderecoMapper.class);
    Endereco toModel(EnderecoDTO enderecoDTO);
    EnderecoDTO toDTO(Endereco endereco);
}
