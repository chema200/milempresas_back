package com.chemaplus.milempresas.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Builder
@AllArgsConstructor
@Table(name = "favourite")
public class Favourite {
    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "id_user_fav")
    private Long idUser;
    @Column(name = "id_company_fav")
    private Long idCompany;

    public Favourite() {

    }
}

