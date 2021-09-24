package com.bezkoder.spring.files.excel.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "hardskills")
public class HardSkill {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "Code_5e_laag")
    private String code5elaag;

    @Column(name = "Omschrijving_5e_laag")
    private String omschrijving5elaag;

    @Column(name = "Skill_Code")
    private boolean published;

    @Column(name = "Skill_Omschrijving")
    private String skillomschrijving;

    @Column(name = "Essentieel_Optioneel")
    private String essentieeloptioneel;

    public HardSkill() {

    }

    public HardSkill(String code5elaag, String omschrijving5elaag, boolean published,
                     String skillomschrijving, String essentieeloptioneel) {
        this.code5elaag = code5elaag;
        this.omschrijving5elaag = omschrijving5elaag;
        this.published = published;
        this.skillomschrijving = skillomschrijving;
        this.essentieeloptioneel = essentieeloptioneel;
    }
}
