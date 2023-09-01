package com.it.web.sad.itwebsad.entity;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserEntity {

    private String uuid;

    private String name;

    private String ip;

    private String agent;

}
