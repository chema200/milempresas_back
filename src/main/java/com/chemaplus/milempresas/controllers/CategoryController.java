package com.chemaplus.milempresas.controllers;

import com.chemaplus.milempresas.config.Mapper;
import com.chemaplus.milempresas.repository.CategoryRepository;
import org.openapitools.api.CategoriesApi;
import org.openapitools.model.Category;
import org.openapitools.model.Company;
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
public class CategoryController implements CategoriesApi {

    @Autowired
    Mapper mapper;

    @Autowired
    CategoryRepository categoryRepository;


    @Override
    public ResponseEntity<List<Category>> getAllCategories() {
        try {
            List<org.openapitools.model.Category> categories = new ArrayList<Category>();
            categoryRepository.findAll().forEach(a -> categories.add(mapper.entityToApiCategory(a)));
            if (categories.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(categories, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Category> getCategoryId(String categoryId) {
        Optional<Category> categoryReturn = Optional.of(mapper.entityToApiCategory(categoryRepository.findById(Long.parseLong(categoryId)).get()));

        if (categoryReturn.isPresent()) {
            return new ResponseEntity<>(categoryReturn.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<List<Category>> getPopularCategories() {
        try {
            List<org.openapitools.model.Category> categories = new ArrayList<Category>();
            List<org.openapitools.model.Category> popularCategories = new ArrayList<Category>();
            categoryRepository.findAll().forEach(a -> categories.add(mapper.entityToApiCategory(a)));
            if (categories.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            for(int i=0;i<6;i++){
                popularCategories.add(categories.get(i));
            }
            return new ResponseEntity<>(popularCategories, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Category> patchCategory(Category category) {
        try {
            return new ResponseEntity<>(mapper.entityToApiCategory(categoryRepository.save(mapper.apiToEntityCategory(category))), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Category> postCategory(Category category) {
        try {
            return new ResponseEntity<>(mapper.entityToApiCategory(categoryRepository.save(mapper.apiToEntityCategory(category))), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<String> removeCategory(String categoryId) {
        try {
            categoryRepository.deleteById(Long.parseLong(categoryId));
            return new ResponseEntity<>("Eliminado correctamente", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
