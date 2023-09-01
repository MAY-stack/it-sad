package com.it.web.sad.itwebsad.service.Impl;

import com.it.web.sad.itwebsad.entity.CommentEntity;
import com.it.web.sad.itwebsad.entity.UserEntity;
import com.it.web.sad.itwebsad.repository.CommentRepository;
import com.it.web.sad.itwebsad.repository.UserRepository;
import com.it.web.sad.itwebsad.service.CommentService;
import com.it.web.sad.itwebsad.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Repository
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<UserEntity> getUser() {
        return userRepository.findAll();
    }

    @Override
    public UserEntity addUser(UserEntity userEntity) {
        return userRepository.save(userEntity);
    }

    @Override
    public UserEntity deleteUser(String uuid) {
        UserEntity targetUser = userRepository.findById(uuid).get();
        userRepository.delete(targetUser);
        return targetUser;
    }

    @Override
    public UserEntity updateUser(String uuid, UserEntity userEntity) {
        UserEntity targetUser = userRepository.findById(uuid).get();
        targetUser.setIp(userEntity.getIp());
        targetUser.setName(userEntity.getName());
        targetUser.setAgent(userEntity.getAgent());
        return userRepository.save(targetUser);
    }
}
