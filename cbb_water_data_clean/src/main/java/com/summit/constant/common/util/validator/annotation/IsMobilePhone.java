package com.summit.constant.common.util.validator.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.summit.constant.common.util.validator.MobilePhoneValidator;
@Target({ ElementType.FIELD, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = MobilePhoneValidator.class)
public @interface IsMobilePhone {

    String message() default "不是合法的11位电话号码";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
