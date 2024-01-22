package ru.rail.springwebapp.service;

import org.springframework.stereotype.Service;
import ru.rail.springwebapp.dto.UserCreateEditDto;
import ru.rail.springwebapp.dto.UserReadDto;
import java.util.List;

@Service
public class UserService {


    public UserReadDto createUser(UserCreateEditDto userDto) {
        return null;
    }


    public UserReadDto updateUser(Long id, UserCreateEditDto userDto) {
        return null;
    }


    public UserReadDto getUserById(Long id) {
        return null;
    }


    public void deleteUser(Long id) {
    }


    public List<UserReadDto> getAllUsers() {
        return null;
    }


}
