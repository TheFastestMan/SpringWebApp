package ru.rail.springwebapp.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.rail.springwebapp.dto.CompanyReadDto;
import ru.rail.springwebapp.entity.Company;
import ru.rail.springwebapp.mapper.CompanyReadMapper;
import ru.rail.springwebapp.repository.CompanyRepository;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CompanyServiceTest {

    @Mock
    private CompanyRepository companyRepository;

    @Mock
    private CompanyReadMapper companyReadMapper;

    @InjectMocks
    private CompanyService companyService;

    @Test
    public void getAll_ReturnsListOfCompanyReadDtos() {

        List<Company> companies = Arrays.asList(new Company());
        when(companyRepository.findAll()).thenReturn(companies);
        when(companyReadMapper.mapFrom(companies.get(0))).thenReturn(new CompanyReadDto());


        List<CompanyReadDto> result = companyService.getAll();


        assertEquals(1, result.size());

    }
}
