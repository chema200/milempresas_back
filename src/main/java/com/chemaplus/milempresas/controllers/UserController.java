package com.chemaplus.milempresas.controllers;

import com.chemaplus.milempresas.config.Mapper;
import com.chemaplus.milempresas.repository.CityRepository;
import com.chemaplus.milempresas.repository.UserRepository;
import org.openapitools.api.CitiesApi;
import org.openapitools.api.UsersApi;
import org.openapitools.model.City;
import org.openapitools.model.Favourite;
import org.openapitools.model.User;
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
public class UserController implements UsersApi {

    @Autowired
    Mapper mapper;

    @Autowired
    UserRepository userRepository;


    @Override
    public ResponseEntity<User> getUserId(String userId) {
        Optional<User> userReturn = Optional.of(mapper.entityToApiUser(userRepository.findById(Long.parseLong(userId)).get()));

        if (userReturn.isPresent()) {
            return new ResponseEntity<>(userReturn.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<User> patchUser(User user) {
        try {
            return new ResponseEntity<>(mapper.entityToApiUser(userRepository.save(mapper.apiToEntityUser(user))), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<String> removeUser(String userId) {
        try {
            userRepository.deleteById(Long.parseLong(userId));
            return new ResponseEntity<>("Eliminado correctamente", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
