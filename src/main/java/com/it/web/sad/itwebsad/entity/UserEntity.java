package com.it.web.sad.itwebsad.entity;

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
