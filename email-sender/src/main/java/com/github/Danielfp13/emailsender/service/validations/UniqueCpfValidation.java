package com.github.Danielfp13.emailsender.service.validations;

import com.github.Danielfp13.emailsender.entities.User;
import com.github.Danielfp13.emailsender.repositories.UserRepository;
import com.github.Danielfp13.emailsender.service.exception.ObjectNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import java.util.Map;
import java.util.Optional;

public class UniqueCpfValidation implements ConstraintValidator<UniqueCpf, String> {

    @Autowired
    private UserRepository repository;

    @Autowired
    private HttpServletRequest request;

    @Override
    public boolean isValid(String cpf, ConstraintValidatorContext constraintValidatorContext) {

        Long uriId = getUriIdFromRequest();

        if (uriId != null) {
            checkRegisteredUserId(uriId);
        }

        Optional<User> user = repository.findByCpf(cpf);
        if (user.isPresent() && !user.get().getId().equals(uriId)) {
            return false;
        }
        return true;
    }

    private Long getUriIdFromRequest() {// Método para capturar o ID da URL
        @SuppressWarnings("unchecked")
        Map<String, String> map = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
        return map != null && map.containsKey("id") ? Long.parseLong(map.get("id")) : null;
    }

    private void checkRegisteredUserId(Long uriId) {
        repository.findById(uriId).orElseThrow(() -> new ObjectNotFoundException("There is no registered user with id = " + uriId + "."));
    }
}
