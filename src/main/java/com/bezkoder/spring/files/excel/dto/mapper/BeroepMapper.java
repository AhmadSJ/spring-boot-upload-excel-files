package com.bezkoder.spring.files.excel.dto.mapper;

import com.bezkoder.spring.files.excel.dto.BeroepDto;
import com.bezkoder.spring.files.excel.model.Beroep;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface BeroepMapper {

    BeroepMapper INSTANCE = Mappers.getMapper(BeroepMapper.class);

    BeroepDto beroepToBeroepDto(Beroep beroep);

    Beroep beroepDtoToBeroep(BeroepDto beroepDto);

    List<Beroep> getListOfBeroepen(List<BeroepDto> beroepDtos);

    List<BeroepDto> getListOfBeroepDtos(List<Beroep> beroepen);
}
