package intrusii.core.model.validators;


public class ContractException extends RuntimeException {
    public ContractException(String message) {
        super(message);
    }

    public ContractException(String message, Throwable cause) {
        super(message, cause);
    }

    public ContractException(Throwable cause) {
        super(cause);
    }
}
