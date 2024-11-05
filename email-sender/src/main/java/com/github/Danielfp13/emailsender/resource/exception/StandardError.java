package com.github.Danielfp13.emailsender.resource.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
public class StandardError implements Serializable {
    private LocalDateTime timestamp;
    private String error;
    private Integer status;
    private String path;
}
