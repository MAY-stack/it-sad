package com.it.web.sad.itwebsad.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTO {

    @NotNull
    @NotEmpty
    @NotBlank
    private String uuid;

    @NotNull
    @NotEmpty
    @NotBlank
    private String name;

    private String ip;

    private String agent;

}
