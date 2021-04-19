package intrusii.core.model.validators;

import intrusii.core.model.Client;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ClientValidator implements Validator<Client> {
    /**
     * Validates the given client (Client entity)
     *
     * @param entity must be not null.
     * @throws ValidatorException if the given cnp is not a number.
     *                            if the given E-mail is not a valid email address.
     */
    @Override
    public void validate(Client entity) throws ValidatorException {
        try {
            Long.parseLong(entity.getCnp());
        } catch (NumberFormatException e) {
            throw new ValidatorException("Invalid CNP");
        }
        //Optional.ofNullable(entity.getCnp()).filter(cnp -> cnp.length() == 13).orElseThrow(() -> new ValidatorException("Invalid CNP entered"));
        Optional.ofNullable(entity.getEmail()).filter(email -> email.contains("@")).orElseThrow(() -> new ValidatorException("Invalid E-Mail address"));
        //Optional.ofNullable(entity.getName()).filter(String::isBlank).orElseThrow(() -> new ValidatorException("Blank name entered"));
    }
}
