package ru.rail.springwebapp.dto;

import lombok.*;

@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CompanyReadDto {
    private Long id;
    private String name;

}
