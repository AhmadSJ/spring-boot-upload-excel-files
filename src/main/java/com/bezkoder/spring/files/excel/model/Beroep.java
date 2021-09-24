package com.bezkoder.spring.files.excel.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "beroepen")
public class Beroep {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "BEROEPS_CODE")
    private int beroepsCode;

    @Column(name = "OMSCHRIJVING_BEROEP")
    private String omschrijvingBeroep;

    @Column(name = "BEROEP_TYPE")
    private String beroepType;

    @Column(name = "BEROEP_STATUS")
    private String beroepStatus;

    @Column(name = "code_5e_laag")
    private int code_5e_laag;

    @Column(name = "beroepen_5e_laag")
    private String beroepen_5e_laag;

    @Column(name = "isco_code_UG")
    private int isco_code_UG;

    @Column(name = "NL_unit_group_4e_laag")
    private String nl_unit_group_4e_laag;

    @Column(name = "isco_code_MiG")
    private int isco_code_mig;

    @Column(name = "NL_minor_group_3e_laag")
    private String nl_minor_group_3e_laag;

    @Column(name = "isco_code_sub_MG")
    private int isco_code_sub_mg;

    @Column(name = "isco_NL_sub_major_group_2e_laag")
    private String isco_nl_sub_major_group_2e_laag;

    @Column(name = "isco_code_MG")
    private int isco_code_mg;

    @Column(name = "isco_NL_major_group_1e_laag")
    private String isco_nl_major_group_1e_laag;

}
