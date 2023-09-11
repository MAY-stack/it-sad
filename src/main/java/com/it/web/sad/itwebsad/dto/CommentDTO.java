package com.it.web.sad.itwebsad.dto;

import com.it.web.sad.itwebsad.entity.CommentEntity;
import com.it.web.sad.itwebsad.entity.UserEntity;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentDTO {

    private String id;
    private String storyId;
    private String type;
    private String version;
    private String message;
    private String image;
    private String isSend;
    private String time;
    private List<String> news;
    private UserEntity user;


    public CommentDTO (CommentEntity commentEntity) {
        this.id = commentEntity.getId();
        this.storyId = commentEntity.getStoryId();
        this.type = commentEntity.getType();
        this.version = commentEntity.getVersion();
        this.message = commentEntity.getMessage();
        this.image = commentEntity.getImage();
        this.isSend = commentEntity.getIsSend();
        this.time = commentEntity.getTime();
        this.news = commentEntity.getNews();
        this.user = commentEntity.getUser();
    }

    public CommentEntity dtoToEntity(CommentDTO commentDTO){
        return CommentEntity.builder()
                .id(commentDTO.getId())
                .storyId(commentDTO.getStoryId())
                .type(commentDTO.getType())
                .version(commentDTO.getVersion())
                .message(commentDTO.getMessage())
                .image(commentDTO.getImage())
                .isSend(commentDTO.getIsSend())
                .time(commentDTO.getTime())
                .news(commentDTO.getNews())
                .user(commentDTO.getUser())
                .build();
    }

}
