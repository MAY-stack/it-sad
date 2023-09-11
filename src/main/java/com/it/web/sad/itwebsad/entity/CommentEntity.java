package com.it.web.sad.itwebsad.entity;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Document(collection = "comments")
public class CommentEntity {
    @MongoId
    private String id;

    @NotNull
    @NotEmpty
    @NotBlank
    private String storyId;

    @NotNull
    @NotEmpty
    @NotBlank
    private String type;

    @NotNull
    @NotEmpty
    @NotBlank
    private String version;

    @NotNull
    @NotEmpty
    @NotBlank
    private String message;

    private String image;

    private String isSend;

    @NotNull
    @NotEmpty
    @NotBlank
    private String time;

    @NotNull
    private List<String> news;

    @NotNull
    private UserEntity user;

}
