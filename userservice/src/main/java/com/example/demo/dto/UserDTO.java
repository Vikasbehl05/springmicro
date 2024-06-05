package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTO {

    @NotBlank(message = "User ID is required")
    private String userId;

    @NotBlank(message = "Name is required")
    @Size(max = 20, message = "Name can have at most 20 characters")
    private String name;

    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    private String email;

    @Size(max = 500, message = "About can have at most 500 characters")
    private String about;

    @NotNull(message = "Address is required")
    private AddressDTO address;
    
}
