package com.bezkoder.spring.files.excel.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "softskills")
public class SoftSkill {

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

    public SoftSkill() {

    }

    public SoftSkill(String code5elaag, String omschrijving5elaag, boolean published,
                     String skillomschrijving, String essentieeloptioneel) {
        this.code5elaag = code5elaag;
        this.omschrijving5elaag = omschrijving5elaag;
        this.published = published;
        this.skillomschrijving = skillomschrijving;
        this.essentieeloptioneel = essentieeloptioneel;
    }
}