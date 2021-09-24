package com.bezkoder.spring.files.excel.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "beroepen")
public class Beroep {

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

    public Beroep() {

    }
}

