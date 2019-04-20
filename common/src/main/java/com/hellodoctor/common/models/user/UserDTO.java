package com.hellodoctor.common.models.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Khoa
 * @created 4/19/2019
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private Long id;
    private String password;
    private String email;
    private String status;
    private List<String> roles = new ArrayList<>();
}
