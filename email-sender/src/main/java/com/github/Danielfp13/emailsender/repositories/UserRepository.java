package com.github.Danielfp13.emailsender.repositories;

import com.github.Danielfp13.emailsender.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    public Optional<User> findByUsername(String username);
    public Optional<User> findByCpf(String cpf);
    public Optional<User> findByPhoneNumber(String phoneNumber);
}
