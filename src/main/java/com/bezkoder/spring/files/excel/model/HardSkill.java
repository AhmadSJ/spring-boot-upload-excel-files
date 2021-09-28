package com.bezkoder.spring.files.excel.model;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "hardskill")
public class HardSkill {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "Code_5e_laag")
    private int code_5e_laag;

    @Column(name = "Omschrijving_5e_laag")
    private String omschrijving_5e_laag;

    @Column(name = "Skill_Code")
    private String skillCode;

    @Column(name = "Skill_Omschrijving")
    private String skillOmschrijving;

    @Column(name = "Essentieel_Optioneel")
    private String essentieelOptioneel;

    public boolean equalsItself(HardSkill o) {
        if(this.getCode_5e_laag()!=o.getCode_5e_laag()) return false;
        if(!this.getOmschrijving_5e_laag().equals(o.getOmschrijving_5e_laag())) return false;
        if(!this.getSkillCode().equals(o.getSkillCode())) return false;
        if(!this.getSkillOmschrijving().equals(o.getSkillOmschrijving())) return false;
        if(!this.getEssentieelOptioneel().equals(o.getEssentieelOptioneel())) return false;
        return true;
    }

}
