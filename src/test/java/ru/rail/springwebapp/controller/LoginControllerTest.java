package ru.rail.springwebapp.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import ru.rail.springwebapp.dto.LoginDto;
import ru.rail.springwebapp.entity.Role;
import ru.rail.springwebapp.service.UserService;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class LoginControllerTest {

    private MockMvc mockMvc;

    @Mock
    private UserService userService;

    @InjectMocks
    private LoginController loginController;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(loginController).build();
    }

    @Test
    public void login_ShouldReturnLoginView() throws Exception {
        mockMvc.perform(get("/web/login"))
                .andExpect(status().isOk())
                .andExpect(view().name("web/login"));
    }

    @Test
    public void login_PostWithUserRole_ShouldRedirectToUsers() throws Exception {
        LoginDto userDto = new LoginDto();
        userDto.setRole(Role.USER);
        userDto.setId(1L);
        when(userService.login(anyString(), anyString())).thenReturn(Optional.of(userDto));

        mockMvc.perform(post("/web/login")
                        .param("email", "user@example.com")
                        .param("password", "password"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/users"));
    }

    @Test
    public void login_PostWithAdminRole_ShouldRedirectToUserDetail() throws Exception {

        LoginDto adminDto = new LoginDto();
        adminDto.setRole(Role.ADMIN);
        adminDto.setId(2L);
        when(userService.login(anyString(), anyString())).thenReturn(Optional.of(adminDto));

        mockMvc.perform(post("/web/login")
                        .param("email", "admin@example.com")
                        .param("password", "password"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/user/2"));
    }

    @Test
    public void login_PostWithInvalidCredentials_ShouldReturnLoginView() throws Exception {
        when(userService.login(anyString(), anyString())).thenReturn(Optional.empty());

        mockMvc.perform(post("/web/login")
                        .param("email", "invalid@example.com")
                        .param("password", "wrongpassword"))
                .andExpect(status().isOk())
                .andExpect(view().name("web/login"));
    }
}
