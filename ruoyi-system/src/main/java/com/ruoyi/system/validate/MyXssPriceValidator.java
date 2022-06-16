package com.ruoyi.system.validate;

import com.ruoyi.system.annotation.MyXss;
import com.ruoyi.system.annotation.MyXssPrice;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MyXssPriceValidator implements ConstraintValidator<MyXssPrice,Double> {

    private final String HTML_PATTERN = "^[0-9]+.?[0-9]*$";

    @Override
    public boolean isValid(Double aDouble, ConstraintValidatorContext constraintValidatorContext) {
        Pattern pattern=Pattern.compile(HTML_PATTERN);
        Matcher matcher= pattern.matcher(aDouble.toString());
        System.out.println(matcher.matches());
        return matcher.matches();
    }
}
