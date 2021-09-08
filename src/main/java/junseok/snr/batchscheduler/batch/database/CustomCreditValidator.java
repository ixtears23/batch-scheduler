package junseok.snr.batchscheduler.batch.database;


import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class CustomCreditValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.isAssignableFrom(CustomerCredit.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        CustomerCredit customerCredit = (CustomerCredit) target;

        if (customerCredit.getName().isEmpty()) {
            errors.rejectValue("name", "name is empty!!");
        }
    }
}
