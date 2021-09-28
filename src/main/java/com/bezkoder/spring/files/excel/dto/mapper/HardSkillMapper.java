package com.bezkoder.spring.files.excel.dto.mapper;

import com.bezkoder.spring.files.excel.dto.HardSkillDto;
import com.bezkoder.spring.files.excel.model.HardSkill;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface HardSkillMapper {

    HardSkillMapper INSTANCE = Mappers.getMapper(HardSkillMapper.class);

    HardSkillDto hardSkillToHardSkillDto(HardSkill hardSkill);

    HardSkill hardSkillDtoToHardSkill(HardSkillDto hardSkillDto);

    List<HardSkill> getListOfHardSkills(List<HardSkillDto> hardSkillDtos);

    List<HardSkillDto> getListOfHardSkillDtos(List<HardSkill> hardSkills);

}
