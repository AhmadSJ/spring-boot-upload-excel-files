package com.bezkoder.spring.files.excel.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@NoArgsConstructor
public class SoftSkillDto {

    private long id;
    private int code_5e_laag;
    private String omschrijving_5e_laag;
    private String skillCode;
    private String skillOmschrijving;
    private String essentieelOptioneel;

    public boolean equalsItself(SoftSkillDto o) {
        if(this.getCode_5e_laag()!=o.getCode_5e_laag()) return false;
        if(!this.getOmschrijving_5e_laag().equals(o.getOmschrijving_5e_laag())) return false;
        if(!this.getSkillCode().equals(o.getSkillCode())) return false;
        if(!this.getSkillOmschrijving().equals(o.getSkillOmschrijving())) return false;
        if(!this.getEssentieelOptioneel().equals(o.getEssentieelOptioneel())) return false;
        return true;
    }
}
