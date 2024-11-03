package com.github.Danielfp13.emailsender.service;

import com.github.Danielfp13.emailsender.dtos.UserDTO;
import com.github.Danielfp13.emailsender.entities.User;
import com.github.Danielfp13.emailsender.repositories.UserRepository;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service

public class UserService {

    private final UserRepository repository;
    private final RabbitTemplate rabbitTemplate;

    public UserService(UserRepository repository, RabbitTemplate rabbitTemplate) {
        this.repository = repository;
        this.rabbitTemplate = rabbitTemplate;
    }

    public UserDTO save(UserDTO userDTO) {
        User user = new User();
        BeanUtils.copyProperties(userDTO, user);
        user = repository.save(user);
        return new UserDTO(user);
    }
}
