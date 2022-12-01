package com.chemaplus.milempresas.controllers;

import com.chemaplus.milempresas.config.Mapper;
import com.chemaplus.milempresas.repository.FavouriteRepository;
import org.openapitools.api.FavouritesApi;
import org.openapitools.model.Favourite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/auth")
public class FavouriteController implements FavouritesApi {

    @Autowired
    Mapper mapper;

    @Autowired
    FavouriteRepository favouriteRepository;

    @Override
    public ResponseEntity<Favourite> patchFavourite(Favourite favourite) {
        try {
            return new ResponseEntity<>(mapper.entityToApiFavourite(favouriteRepository.save(mapper.apiToEntityFavourite(favourite))), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Favourite> postFavourite(Favourite favourite) {
        try {
            return new ResponseEntity<>(mapper.entityToApiFavourite(favouriteRepository.save(mapper.apiToEntityFavourite(favourite))), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<String> removeFavourite(String favouriteId) {
        try {
            favouriteRepository.deleteById(Long.parseLong(favouriteId));
            return new ResponseEntity<>("Eliminado correctamente", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
