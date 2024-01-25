package ru.rail.springwebapp.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import ru.rail.springwebapp.dto.UserReadDto;
import ru.rail.springwebapp.service.UserService;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

    private MockMvc mockMvc;

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    public void findById_ShouldAddUserToModelAndRenderUserView() throws Exception {
        Long userId = 1L;
        UserReadDto userReadDto = new UserReadDto();
        userReadDto.setId(userId);
        when(userService.getById(anyLong())).thenReturn(Optional.of(userReadDto));

        mockMvc.perform(get("/user/{id}", userId))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("user"))
                .andExpect(model().attribute("user", userReadDto))
                .andExpect(view().name("user/user"));
    }

}
