package ru.rail.springwebapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.rail.springwebapp.dto.CompanyReadDto;
import ru.rail.springwebapp.entity.Company;
import ru.rail.springwebapp.mapper.CompanyReadMapper;
import ru.rail.springwebapp.repository.CompanyRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class CompanyService {

    @Autowired
    private final CompanyRepository companyRepository;
    @Autowired
    private final CompanyReadMapper companyReadMapper;

    public CompanyService(CompanyRepository companyRepository, CompanyReadMapper companyReadMapper) {
        this.companyRepository = companyRepository;
        this.companyReadMapper = companyReadMapper;
    }

    public List<CompanyReadDto> getAll() {
        List<Company> companies = companyRepository.findAll();
        return companies.stream().map(companyReadMapper::mapFrom)
                .collect(Collectors.toList());
    }

}
