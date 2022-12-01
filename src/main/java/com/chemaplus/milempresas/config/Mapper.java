package com.chemaplus.milempresas.config;

import com.chemaplus.milempresas.entity.*;
import com.chemaplus.milempresas.entity.Category;
import com.chemaplus.milempresas.entity.User;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@org.mapstruct.Mapper(componentModel = "spring")
public interface Mapper {

    Mapper INSTANCE = Mappers.getMapper(Mapper.class);

    Company apiToEntityCompany(org.openapitools.model.Company company);

    org.openapitools.model.Company entityToApiCompany(Company company);

    Favourite apiToEntityFavourite(org.openapitools.model.Favourite favourite);

    org.openapitools.model.Favourite entityToApiFavourite(Favourite favourite);

    Province apiToEntityProvince(org.openapitools.model.Province province);

    org.openapitools.model.Province entityToApiProvince(Province province);

    City apiToEntityCity(org.openapitools.model.City city);

    org.openapitools.model.City entityToApiCity(City city);

    Category apiToEntityCategory(org.openapitools.model.Category category);

    org.openapitools.model.Category entityToApiCategory(Category category);
    @Mapping(target = "roles", ignore = true)
    User apiToEntityUser(org.openapitools.model.User user);
    @Mapping(target = "roles", ignore = true)
    org.openapitools.model.User entityToApiUser(User user);

}
