package com.it.web.sad.itwebsad.service;

import com.it.web.sad.itwebsad.dto.CommentDTO;
import com.it.web.sad.itwebsad.entity.CommentEntity;

import java.util.List;

public interface CommentService {

    /* all */
    public List<CommentDTO> getComments();

    /* post comment */
    public CommentDTO addComment(CommentDTO commentDTO);

    /* get by id */
    public CommentDTO getCommentById(String id);

    /* update */
    public CommentDTO updateComment(String id, CommentDTO commentDTO) throws Exception;

    /* delete */
    public void deleteComment(String id);

    /* get by storyId */
    public List<CommentDTO> getCommentsByStoryId(String storyId);

    /* CommentEntity to DTO */
    public CommentDTO commentEntityToDTO(CommentEntity commentEntity);

}