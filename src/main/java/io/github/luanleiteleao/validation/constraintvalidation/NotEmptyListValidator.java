package io.github.luanleiteleao.validation.constraintvalidation;

import io.github.luanleiteleao.validation.NotEmptyList;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

//constraintvalidation
public class NotEmptyListValidator implements ConstraintValidator<NotEmptyList, List> {
    @Override
    public void initialize(NotEmptyList constraintAnnotation) {
    }

    @Override
    public boolean isValid(List list, ConstraintValidatorContext constraintValidatorContext) {
        return list != null && !list.isEmpty();
    }
}
