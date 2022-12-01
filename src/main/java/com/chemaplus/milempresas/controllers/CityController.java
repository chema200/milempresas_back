package com.chemaplus.milempresas.controllers;

import com.chemaplus.milempresas.config.Mapper;
import com.chemaplus.milempresas.repository.CityRepository;
import org.openapitools.api.CitiesApi;
import org.openapitools.model.City;
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
public class CityController implements CitiesApi {

    @Autowired
    Mapper mapper;

    @Autowired
    CityRepository cityRepository;


    @Override
    public ResponseEntity<List<City>> getCitiesByProvinceId(String provinceId) {
        try {
            List<org.openapitools.model.City> cities = new ArrayList<City>();
            cityRepository.getCitiesByProvinceId(Long.parseLong(provinceId)).forEach(a -> cities.add(mapper.entityToApiCity(a)));

            if (cities.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(cities, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
