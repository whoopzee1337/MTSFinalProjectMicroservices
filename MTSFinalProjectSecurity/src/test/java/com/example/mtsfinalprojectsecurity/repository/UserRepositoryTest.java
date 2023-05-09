package com.example.mtsfinalprojectsecurity.repository;

import com.example.mtsfinalprojectsecurity.AbstractTestcontainers;
import com.example.mtsfinalprojectsecurity.entity.UserEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.DirtiesContext;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class UserRepositoryTest extends AbstractTestcontainers {

    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        userRepository = new UserRepository(getJdbcTemplate());
    }

    @Test
    void findBy() {
        String username = "test";
        UserEntity userEntity = new UserEntity();
        userEntity.setId(1L).setRole("ROLE_USER").setUsername(username).setPassword("test");

        userRepository.save(userEntity);

        UserEntity expectedUserEntity = userRepository.findBy(username);

        assertThat(expectedUserEntity).isEqualTo(userEntity);
    }

    @Test
    void save() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        String username = "test2";
        UserEntity userEntity = new UserEntity();
        userEntity.setId(2L).setRole("ROLE_USER").setUsername(username).setPassword("test");

        userRepository.save(userEntity);
        assertThat(userRepository.findBy(username)).isEqualTo(userEntity);
    }
}