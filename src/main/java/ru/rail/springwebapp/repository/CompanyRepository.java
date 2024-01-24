package ru.rail.springwebapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.rail.springwebapp.entity.Company;

public interface CompanyRepository extends JpaRepository<Company, Long> {
}
