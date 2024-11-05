package com.github.Danielfp13.emailsender.resource;

import com.github.Danielfp13.emailsender.dtos.UserDTO;
import com.github.Danielfp13.emailsender.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/user")
@AllArgsConstructor
public class UserResource {

    private final UserService service;

    @PostMapping
    public ResponseEntity<UserDTO> save(@Valid @RequestBody UserDTO userDTO){
        userDTO = service.save(userDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(userDTO.getId()).toUri();
        return ResponseEntity.created(uri).body(userDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> findById(@PathVariable Long id){
        return ResponseEntity.ok(service.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> save(@Valid @RequestBody UserDTO userDTO, @PathVariable Long id){
        userDTO = service.update(userDTO, id);
        return ResponseEntity.ok(userDTO);
    }
}
