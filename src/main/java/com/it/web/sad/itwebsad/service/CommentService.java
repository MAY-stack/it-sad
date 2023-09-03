package com.it.web.sad.itwebsad.service;

import com.it.web.sad.itwebsad.dto.CommentDTO;
import com.it.web.sad.itwebsad.entity.CommentEntity;

import java.util.List;
import java.util.Optional;

public interface CommentService {

    /* all */
    public List<CommentDTO> getComments();

    /* post comment */
    public CommentDTO addComment(CommentDTO commentDTO);

    /* get by id */
    public CommentDTO getCommentById(String id);

    /*ID duplicate check*/
    public Optional<CommentEntity> checkCommentId(String id);

    /* update */
    public void updateComment(String id, CommentDTO commentDTO);

    /* delete */
    public void deleteComment(String id);

    /* get by storyId */
    public List<CommentDTO> getCommentsByStoryId(String storyId);

    /* CommentEntity to DTO */
    public CommentDTO commentEntityToDTO(CommentEntity commentEntity);

}