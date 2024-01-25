package ru.rail.springwebapp.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import ru.rail.springwebapp.service.CompanyService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class RegistrationControllerTest {
    private MockMvc mockMvc;

    @Mock
    private CompanyService companyService;

    @InjectMocks
    private RegistrationController registrationController;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(registrationController).build();
    }

    @Test
    public void registration_ShouldAddRolesToModelAndRenderRegistrationView() throws Exception {
        mockMvc.perform(get("/registration"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("roles"))
                .andExpect(model().attributeExists("companies"))
                .andExpect(view().name("web/registration"));
    }
}
