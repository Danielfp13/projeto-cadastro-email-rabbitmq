package com.github.Danielfp13.emailsender.service;

import com.github.Danielfp13.emailsender.dtos.UserDTO;
import com.github.Danielfp13.emailsender.entities.User;
import com.github.Danielfp13.emailsender.repositories.UserRepository;
import com.github.Danielfp13.emailsender.service.exception.ObjectNotFoundException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service

public class UserService {

    @Value("${rabbitmq.exchange.name}")
    private String exchangeName;

    @Value("${rabbitmq.routing.key}")
    private String routingKey;

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
        rabbitTemplate.convertAndSend(exchangeName, routingKey, user);
        return new UserDTO(user);
    }

    public UserDTO findById(Long id) {
        return new UserDTO(repository.findById(id).orElseThrow(() ->
                new ObjectNotFoundException("There is no registered user with id = " + id + ".")));
    }

    public UserDTO update(UserDTO userDTO, Long id) {
        User user = new User();
        BeanUtils.copyProperties(userDTO, user, "id");
        user.setId(id);
        return new UserDTO(repository.save(user));
    }
}