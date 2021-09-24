package com.bezkoder.spring.files.excel.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "softskills")
public class SoftSkill {

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

}