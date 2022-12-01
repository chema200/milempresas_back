package com.chemaplus.milempresas.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Entity
@Data
@Builder
@AllArgsConstructor
@Table(name = "company")
public class Company {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String address;
    private String phone;
    private String description;

    @OneToOne
    @JoinColumn(name = "province_id")
    private Province province;

    @OneToOne
    @JoinColumn(name = "city_id")
    private City city;

    @OneToOne
    @JoinColumn(name = "image_id")
    private Image image;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "company_category",
            joinColumns = @JoinColumn(name = "id_company"),
            inverseJoinColumns = @JoinColumn(name = "id_category"))
    private Set<Category> categories = new HashSet<>();


    public Company() {

    }
}

