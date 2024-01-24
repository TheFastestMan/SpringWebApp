package ru.rail.springwebapp.mapper;

import org.springframework.stereotype.Component;
import ru.rail.springwebapp.dto.CompanyReadDto;
import ru.rail.springwebapp.entity.Company;
@Component
public class CompanyReadMapper implements Mapper<Company, CompanyReadDto>{

    @Override
    public CompanyReadDto mapFrom(Company company) {
        return CompanyReadDto.builder()
                .id(company.getId())
                .name(company.getName())
                .build();
    }

    @Override
    public Company mapTo(CompanyReadDto companyReadDto) {
        return Company.builder()
                .id(companyReadDto.getId())
                .name(companyReadDto.getName())
                .build();
    }
}