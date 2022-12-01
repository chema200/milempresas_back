package com.chemaplus.milempresas.controllers;

import com.chemaplus.milempresas.config.Mapper;
import com.chemaplus.milempresas.entity.Image;
import com.chemaplus.milempresas.entity.Province;
import com.chemaplus.milempresas.repository.CompanyRepository;
import com.chemaplus.milempresas.repository.ProvinceRepository;
import com.chemaplus.milempresas.util.FileUtility;
import org.openapitools.model.Company;
import org.openapitools.api.CompaniesApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/auth")
public class CompanyController implements CompaniesApi {

    @Autowired
    Mapper mapper;

    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    ProvinceRepository provinceRepository;

    @Override
    public ResponseEntity<List<Company>> getAllCompaniesByCityId(String cityId) {
        try {
            List<org.openapitools.model.Company> companies = new ArrayList<org.openapitools.model.Company>();
            companyRepository.getCompaniesByCityId(cityId).forEach(a -> companies.add(mapper.entityToApiCompany(a)));

            List<org.openapitools.model.Company> companiesWithImage = new ArrayList<org.openapitools.model.Company>();
            companies.forEach(company -> companiesWithImage.add(FileUtility.setImageToBase64(company)));

            if (companies.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(companies, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<List<Company>> getAllCompaniesHome() {
        try {
            List<org.openapitools.model.Company> companies = new ArrayList<org.openapitools.model.Company>();
            List<org.openapitools.model.Company> companiesHome = new ArrayList<org.openapitools.model.Company>();
            companyRepository.findAll().forEach(a -> companies.add(mapper.entityToApiCompany(a)));

            List<org.openapitools.model.Company> companiesWithImage = new ArrayList<org.openapitools.model.Company>();
            companies.forEach(company -> companiesWithImage.add(FileUtility.setImageToBase64(company)));

            if (companies.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            for(int i=0;i<6;i++){
                companiesHome.add(companies.get(i));
            }

            return new ResponseEntity<>(companiesHome, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

   /* @Override
    public ResponseEntity<List<Company>> getCompanies() {
        try {
            List<org.openapitools.model.Company> companies = new ArrayList<org.openapitools.model.Company>();
            companyRepository.findAll().forEach(a -> companies.add(mapper.entityToApiCompany(a)));

            List<org.openapitools.model.Company> companiesWithImage = new ArrayList<org.openapitools.model.Company>();
            companies.forEach(company -> companiesWithImage.add(FileUtility.setImageToBase64(company)));

            if (companies.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(companies, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }*/

    @Override
    public ResponseEntity<List<Company>> getCompaniesByProvinceId(String provinceId) {
        try {
            List<org.openapitools.model.Company> companies = new ArrayList<org.openapitools.model.Company>();
            companyRepository.getCompaniesByProvinceId(provinceId).forEach(a -> companies.add(mapper.entityToApiCompany(a)));

            List<org.openapitools.model.Company> companiesWithImage = new ArrayList<org.openapitools.model.Company>();
            companies.forEach(company -> companiesWithImage.add(FileUtility.setImageToBase64(company)));

            if (companies.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(companies, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @Override
    public ResponseEntity<Company> getCompanyId(String companyId) {
        Optional<Company> companyReturn = Optional.of(mapper.entityToApiCompany(companyRepository.findById(Long.parseLong(companyId)).get()));

        FileUtility.setImageToBase64(companyReturn.get());
        if (companyReturn.isPresent()) {
            return new ResponseEntity<>(companyReturn.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<Company> patchCompany(Company company) {
        try {
            return new ResponseEntity<>(mapper.entityToApiCompany(companyRepository.save(mapper.apiToEntityCompany(company))), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Company> postCompany(Company company) {
        try {
           /* if(company.getImage().!=null){
                byte[] file = Base64.getDecoder().decode(company.getImage().getImageBase64().getBytes("UTF-8"));
                Image img = new Image(company.getImage().getName(), company.getImage().getType(),
                        FileUtility.compressBytes(file),company.getImage().getImageBase64());
            }*/
            return new ResponseEntity<>(mapper.entityToApiCompany(companyRepository.save(mapper.apiToEntityCompany(company))), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<String> removeCompany(String companyId) {
        try {
            companyRepository.deleteById(Long.parseLong(companyId));
            return new ResponseEntity<>("Eliminado correctamente", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
