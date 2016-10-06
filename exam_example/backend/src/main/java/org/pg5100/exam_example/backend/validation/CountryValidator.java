package org.pg5100.exam_example.backend.validation;

import org.pg5100.exam_example.backend.Countries;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CountryValidator implements ConstraintValidator<Country,String> {
    @Override
    public void initialize(Country constraintAnnotation) {

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        return Countries.getCountries().stream()
                .anyMatch(s -> s.equalsIgnoreCase(value));
    }
}
