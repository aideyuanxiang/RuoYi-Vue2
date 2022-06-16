package com.ruoyi.system.annotation;

import com.ruoyi.system.validate.MyXssPriceValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author 26324
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(value = { ElementType.METHOD, ElementType.FIELD, ElementType.CONSTRUCTOR, ElementType.PARAMETER })
@Constraint(validatedBy = {MyXssPriceValidator.class})
public @interface MyXssPrice {

    String message()

            default "价格请输入非负数值";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
