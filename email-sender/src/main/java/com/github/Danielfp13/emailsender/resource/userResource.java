package com.github.Danielfp13.emailsender.resource;

import com.github.Danielfp13.emailsender.dtos.UserDTO;
import com.github.Danielfp13.emailsender.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/user")
@AllArgsConstructor
public class userResource {

    private final UserService service;

    @PostMapping
    public ResponseEntity<UserDTO> save(@RequestBody UserDTO userDTO){
        userDTO = service.save(userDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(userDTO.getId()).toUri();
        return ResponseEntity.created(uri).body(userDTO);
    }
}
