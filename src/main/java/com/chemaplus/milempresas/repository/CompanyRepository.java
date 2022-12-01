package com.chemaplus.milempresas.repository;

import com.chemaplus.milempresas.entity.Company;
import com.chemaplus.milempresas.entity.Province;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
    @Query(value = "SELECT c.* FROM company c WHERE c.province_id = :provinceId", nativeQuery = true)
    List<Company> getCompaniesByProvinceId(String provinceId);

    @Query(value = "SELECT c.* FROM company c WHERE c.city_id = :cityId", nativeQuery = true)
    List<Company> getCompaniesByCityId(String cityId);
}
