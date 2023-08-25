package com.it.web.sad.itwebsad.service;

import com.it.web.sad.itwebsad.entity.UserEntity;

import java.util.List;

public interface UserService {

    public List<UserEntity> getUser();

    public UserEntity addUser(UserEntity userEntity);

    public UserEntity deleteUser(String uuid);

    public  UserEntity updateUser(String uuid, UserEntity userEntity);

}
