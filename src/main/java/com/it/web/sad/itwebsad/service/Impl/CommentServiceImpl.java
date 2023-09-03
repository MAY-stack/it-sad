package com.it.web.sad.itwebsad.service.Impl;

import com.it.web.sad.itwebsad.dto.CommentDTO;
import com.it.web.sad.itwebsad.entity.CommentEntity;
import com.it.web.sad.itwebsad.repository.CommentRepository;
import com.it.web.sad.itwebsad.service.CommentService;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    public CommentServiceImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    /* get all comments */
    @Override
    public List<CommentDTO> getComments() {
        List<CommentEntity> EntityList = commentRepository.findAll();

        List<CommentDTO> DTOList = new ArrayList<>();

        for(CommentEntity commentEntity : EntityList) {
            DTOList.add(commentEntityToDTO(commentEntity));
        }

        return DTOList;
    }

    /* post comment */
    @Override
    public CommentDTO addComment(CommentDTO commentDTO) {
        commentRepository.save(commentDTO.dtoToEntity(commentDTO));
        return commentDTO;
    }

    /* get by comment id */
    @Override
    public CommentDTO getCommentById(String id){

        CommentEntity targetEntity = new CommentEntity();

        if(commentRepository.findById(id).isPresent()) {
            targetEntity = commentRepository.findById(id).get();
            return commentEntityToDTO(targetEntity);

        } else return commentEntityToDTO(targetEntity);
    }

    /*ID duplicate check*/
    public Optional<CommentEntity> checkCommentId(String id){
        return commentRepository.findById(id);
    }

    /* update */
    @Override
    public void updateComment(String id, CommentDTO commentDTO) {
        if(commentRepository.findById(id).isPresent()){
            CommentEntity targetEntity = commentRepository.findById(id).get();
            targetEntity.setMessage(commentDTO.getMessage());
            targetEntity.setImage(commentDTO.getImage());
            targetEntity.setType(commentDTO.getType());
            targetEntity.setTime(commentDTO.getTime());
            targetEntity.setUser(commentDTO.getUser());
            targetEntity.setIsSend(commentDTO.getIsSend());
           commentRepository.save(targetEntity);
        }
    }

    /* delete */
    @Override
    public void deleteComment(String id) {
        if(commentRepository.findById(id).isPresent()){
            CommentEntity targetEntity = commentRepository.findById(id).get();
            commentRepository.delete(targetEntity);
        }
    }

    /* get by storyId */
    @Override
    public List<CommentDTO> getCommentsByStoryId(String storyId){
        List<CommentEntity> EntityList = commentRepository.findAllByStoryId(storyId);
        List<CommentDTO> DTOList = new ArrayList<>();
        for(CommentEntity commentEntity : EntityList) {
            DTOList.add(commentEntityToDTO(commentEntity));
        }
        return DTOList;
    }

    /* CommentEntity to DTO */
    public CommentDTO commentEntityToDTO(CommentEntity commentEntity){
        return CommentDTO.builder()
                .id(commentEntity.getId())
                .storyId(commentEntity.getStoryId())
                .type(commentEntity.getType())
                .version(commentEntity.getVersion())
                .message(commentEntity.getMessage())
                .image(commentEntity.getImage())
                .isSend(commentEntity.getIsSend())
                .time(commentEntity.getTime())
                .user(commentEntity.getUser())
                .build();
    }

}