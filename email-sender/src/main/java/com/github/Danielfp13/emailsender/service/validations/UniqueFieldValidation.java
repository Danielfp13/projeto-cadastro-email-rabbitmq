package com.github.Danielfp13.emailsender.service.validations;

import com.github.Danielfp13.emailsender.dtos.UserDTO;
import com.github.Danielfp13.emailsender.entities.User;
import com.github.Danielfp13.emailsender.repositories.UserRepository;
import com.github.Danielfp13.emailsender.resource.exception.FieldMessage;
import com.github.Danielfp13.emailsender.service.exception.ObjectNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class UniqueFieldValidation implements ConstraintValidator<UniqueField, UserDTO> {

    @Autowired
    private UserRepository repository;

    @Autowired
    private HttpServletRequest request;

    private String[] fields;

    @Override
    public void initialize(UniqueField constraintAnnotation) {
        fields = constraintAnnotation.fields();
    }

    @Override
    public boolean isValid(UserDTO userDTO, ConstraintValidatorContext context) {
        Long uriId = getUriIdFromRequest();

        if (uriId != null && repository.findById(uriId).isEmpty())
            throw new ObjectNotFoundException("User with this ID does not exist: " + uriId + ".");

        List<FieldMessage> list = new ArrayList<>();

        for (String field : fields) {
            Optional<User> user = switch (field) {
                case "username" -> repository.findByUsername(userDTO.getUsername());
                case "cpf" -> repository.findByCpf(userDTO.getCpf());
                case "phoneNumber" -> repository.findByPhoneNumber(userDTO.getPhoneNumber());
                default -> Optional.empty();
            };

            if (user.isPresent() && !user.get().getId().equals(uriId))
                list.add(new FieldMessage(field, "The " + field + " informed already belongs to another user."));
        }

        list.forEach(e -> {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage())
                    .addPropertyNode(e.getField())
                    .addConstraintViolation();
        });
        return list.isEmpty();
    }

    private Long getUriIdFromRequest() {
        Map<String, String> map = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
        return (map != null && map.containsKey("id")) ? Long.parseLong(map.get("id")) : null;
    }
}
