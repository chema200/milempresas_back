package com.chemaplus.milempresas.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;


@Entity
@Data
@Builder
@AllArgsConstructor
@Table(name = "city")
public class City {

    @Id
    private Long cityId;
    private Long provinceId;
    private String cityName;


    public City() {

    }
}

