package com.chemaplus.milempresas.controllers;

import com.chemaplus.milempresas.config.Mapper;
import com.chemaplus.milempresas.repository.ProvinceRepository;
import lombok.RequiredArgsConstructor;
import org.openapitools.api.ProvincesApi;
import org.openapitools.model.Province;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/auth")
public class ProvinceController implements ProvincesApi {

    @Autowired
    Mapper mapper;

    @Autowired
    ProvinceRepository provinceRepository;


    @Override
    public ResponseEntity<List<Province>> getAllProvinces() {
        try {
            List<org.openapitools.model.Province> provinces = new ArrayList<Province>();
            provinceRepository.findAll().forEach(a -> provinces.add(mapper.entityToApiProvince(a)));

            if (provinces.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(provinces, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
