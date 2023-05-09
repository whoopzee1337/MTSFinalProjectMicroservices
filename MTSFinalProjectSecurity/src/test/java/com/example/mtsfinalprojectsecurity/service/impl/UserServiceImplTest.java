package com.example.mtsfinalprojectsecurity.service.impl;

import com.example.mtsfinalprojectsecurity.dto.UserDto;
import com.example.mtsfinalprojectsecurity.entity.UserEntity;
import com.example.mtsfinalprojectsecurity.mapper.UserMapper;
import com.example.mtsfinalprojectsecurity.mapper.UserMapperImpl;
import com.example.mtsfinalprojectsecurity.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    UserRepository userRepository;

    @Spy
    UserMapper userMapper = new UserMapperImpl();

    @InjectMocks
    UserServiceImpl userService;

    @Test
    public void saveUserTest() {
        UserDto userDto = new UserDto();
        userDto.setUsername("testUser");
        userDto.setPassword("testPassword");

        userService.saveUser(userDto);

        verify(userRepository).save(Mockito.any());
    }

    @Test
    public void testGetUserByUsername() {
        String username = "testUser";
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(username);
        userEntity.setPassword("password");
        userEntity.setRole("ROLE_USER");
        when(userRepository.findBy(username)).thenReturn(userEntity);

        UserDto result = userService.getUserByUsername(username);

        assertNotNull(result);
        assertEquals(username, result.getUsername());
        assertEquals("password", result.getPassword());

        verify(userRepository).findBy(username);
    }

}