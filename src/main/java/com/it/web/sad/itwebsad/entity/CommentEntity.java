package com.it.web.sad.itwebsad.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Document(collection = "comments")
public class CommentEntity {
    private String id;
    private String storyId;
    private String type;
    private String version;
    private String message;
    private String image;
    private String isSend;
    private String time;
    private UserEntity user;

}
