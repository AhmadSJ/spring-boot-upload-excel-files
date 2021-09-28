package com.bezkoder.spring.files.excel.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@NoArgsConstructor
public class BeroepDto {

    private long id;
    private int beroepsCode;
    private String omschrijvingBeroep;
    private String beroepType;
    private String beroepStatus;
    private int code_5e_laag;
    private String beroepen_5e_laag;
    private int isco_code_UG;
    private String nl_unit_group_4e_laag;
    private int isco_code_mig;
    private String nl_minor_group_3e_laag;
    private int isco_code_sub_mg;
    private String isco_nl_sub_major_group_2e_laag;
    private int isco_code_mg;
    private String isco_nl_major_group_1e_laag;

    public boolean equalsItself(BeroepDto o) {
        if(this.getBeroepsCode()!=o.getBeroepsCode()) return false;
        if(this.getCode_5e_laag()!=o.getCode_5e_laag()) return false;
        if(!this.getBeroepStatus().equals(o.getBeroepStatus())) return false;
        if(!this.getBeroepType().equals(o.getBeroepType())) return false;
        if(!this.getOmschrijvingBeroep().equals(o.getOmschrijvingBeroep())) return false;
        if(!this.getBeroepen_5e_laag().equals(o.getBeroepen_5e_laag())) return false;
        if(this.getIsco_code_UG()!=o.getIsco_code_UG()) return false;
        if(!this.getNl_unit_group_4e_laag().equals(o.getNl_unit_group_4e_laag())) return false;
        if(this.getIsco_code_mig()!=o.getIsco_code_mig()) return false;
        if(!this.getNl_minor_group_3e_laag().equals(o.getNl_minor_group_3e_laag())) return false;
        if(this.getIsco_code_sub_mg()!=o.getIsco_code_sub_mg()) return false;
        if(!this.getIsco_nl_sub_major_group_2e_laag().equals(o.getIsco_nl_sub_major_group_2e_laag())) return false;
        if(this.getIsco_code_mg()!=o.getIsco_code_mg()) return false;
        if(!this.getIsco_nl_major_group_1e_laag().equals(o.getIsco_nl_major_group_1e_laag())) return false;
        return true;
    }
}
