package com.rest.Rest.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

public class UserDto {
    @Data
    public class User {
        private Long id;
        private String username;
        private String password;
        private String email;
        private String address;
        private String phone;

    }
}
