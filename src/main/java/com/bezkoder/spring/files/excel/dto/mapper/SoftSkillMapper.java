package com.bezkoder.spring.files.excel.dto.mapper;

import com.bezkoder.spring.files.excel.dto.SoftSkillDto;
import com.bezkoder.spring.files.excel.model.SoftSkill;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface SoftSkillMapper {

    SoftSkillMapper INSTANCE = Mappers.getMapper(SoftSkillMapper.class);

    SoftSkillDto softSkillToSoftSkillDto(SoftSkill softSkill);

    SoftSkill softSkillDtoToSoftSkill(SoftSkillDto softSkillDto);

    List<SoftSkill> getListOfSoftSkills(List<SoftSkillDto> softSkillDtos);

    List<SoftSkillDto> getListOfSoftSkillDtos(List<SoftSkill> softSkills);

}
