package com.github.Danielfp13.emailsender.service;

import com.github.Danielfp13.emailsender.entities.User;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component

public class EmailListener {

    private final EmailService emailService;

    @Value("${rabbitmq.queue.name}")
    private String queueName;

    public EmailListener(EmailService emailService) {
        this.emailService = emailService;
    }

    @RabbitListener(queues = "${rabbitmq.queue.name}", messageConverter = "jackson2JsonMessageConverter")
    public void receiveMessage(User user) {
        String subject = "Bem-vindo(a) ao Nosso Sistema, " + user.getFirstName() + "!";
        String message = String.format(
                "Olá, %s %s!\n\n"
                        + "Estamos muito felizes em tê-lo(a) conosco.\n"
                        + "Seu cadastro foi realizado com sucesso! Abaixo estão algumas informações úteis para você começar:\n\n"
                        + "Usuário: %s\n"
                        + "Telefone: %s\n\n"
                        + "Para acessar sua conta, utilize seu nome de usuário e a senha cadastrados. Se precisar de qualquer ajuda, nossa equipe de suporte está à disposição.\n\n"
                        + "Bem-vindo(a) a bordo e esperamos que tenha uma excelente experiência conosco!\n\n"
                        + "Atenciosamente,\nEquipe de Suporte",
                user.getFirstName(), user.getLastName(), user.getUsername(), user.getPhoneNumber()
        );
        emailService.sendWelcomeEmail(user.getUsername(), subject, message);
    }
}
