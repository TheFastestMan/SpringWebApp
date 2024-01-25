package ru.rail.springwebapp.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.rail.springwebapp.dto.UserCreateEditDto;
import ru.rail.springwebapp.dto.UserReadDto;
import ru.rail.springwebapp.entity.Company;
import ru.rail.springwebapp.entity.User;
import ru.rail.springwebapp.mapper.UserCreateEditMapper;
import ru.rail.springwebapp.mapper.UserReadMapper;
import ru.rail.springwebapp.repository.CompanyRepository;
import ru.rail.springwebapp.repository.UserRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private CompanyRepository companyRepository;

    @Mock
    private UserCreateEditMapper userCreateEditMapper;

    @Mock
    private UserReadMapper userReadMapper;

    @InjectMocks
    private UserService userService;

    @Test
    void findAll_ShouldReturnListOfUserReadDtos() {

        User user = new User();
        when(userRepository.findAll()).thenReturn(Collections.singletonList(user));
        UserReadDto userReadDto = new UserReadDto();
        lenient().when(userReadMapper.mapFrom(any(User.class))).thenReturn(userReadDto);

        List<UserReadDto> result = userService.findAll();

        assertFalse(result.isEmpty());
        assertEquals(1, result.size());


        UserReadDto resultDto = result.get(0);
        assertEquals(userReadDto.getId(), resultDto.getId());
        assertEquals(userReadDto.getUsername(), resultDto.getUsername());
        assertEquals(userReadDto.getBirthDate(), resultDto.getBirthDate());
    }


    @Test
    void getById_ShouldReturnUserReadDtoWhenUserExists() {

        Long userId = 1L;
        User user = new User();
        UserReadDto userReadDto = new UserReadDto();
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(userReadMapper.mapFrom(user)).thenReturn(userReadDto);

        Optional<UserReadDto> result = userService.getById(userId);

        assertTrue(result.isPresent());
        assertEquals(userReadDto, result.get());
    }

    @Test
    void register_ShouldSaveUserAndReturnUserCreateEditDto() {

        UserCreateEditDto userCreateEditDto = new UserCreateEditDto();
        User user = new User();
        when(userCreateEditMapper.mapFrom(userCreateEditDto)).thenReturn(user);
        when(userRepository.save(user)).thenReturn(user);
        when(userCreateEditMapper.mapTo(user)).thenReturn(userCreateEditDto);

        UserCreateEditDto result = userService.register(userCreateEditDto);

        assertEquals(userCreateEditDto, result);
        verify(userRepository, times(1)).save(user);
    }


}
