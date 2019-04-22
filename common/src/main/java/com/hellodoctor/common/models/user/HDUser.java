package com.hellodoctor.common.models.user;

import com.hellodoctor.common.models.UserType;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Khoa
 * @created 3/23/2019
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class HDUser {
    private Long id;
    private String password;
    private String email;
    private String name;
    private String status;
    private UserType type;
    private List<String> roles = new ArrayList<>();
}
