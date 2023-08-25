package com.it.web.sad.itwebsad.repository;
import com.it.web.sad.itwebsad.entity.UserEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface UserRepository extends MongoRepository<UserEntity, String> {
}
