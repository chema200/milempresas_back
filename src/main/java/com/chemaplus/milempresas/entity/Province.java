package com.chemaplus.milempresas.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Builder
@AllArgsConstructor
@Table(name = "province")
public class Province {

    @Id
    private Long provinceId;
    private String provinceName;


    public Province() {

    }
}

