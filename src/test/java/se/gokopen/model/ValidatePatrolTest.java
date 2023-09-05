package se.gokopen.model;

import org.junit.Test;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class ValidatePatrolTest {

    @Test
    public void validateAllFieldsEmpty() {
        Patrol patrol = new Patrol();

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        Set validations = validator.validate(patrol);
        assertThat(validations.size(),equalTo(6));


    }
}
