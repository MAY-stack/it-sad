package com.it.web.sad.itwebsad.repository;


import com.it.web.sad.itwebsad.entity.CommentEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CommentRepository extends MongoRepository<CommentEntity, String> {

    List<CommentEntity> findAllByStoryId(String storyId);
}
