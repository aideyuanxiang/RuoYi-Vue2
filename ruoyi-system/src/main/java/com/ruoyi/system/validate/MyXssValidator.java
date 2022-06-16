package com.ruoyi.system.validate;

import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.annotation.MyXss;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MyXssValidator implements ConstraintValidator<MyXss,String> {

    private final String HTML_PATTERN = "<(\\S*?)[^>]*>.*?|<.*? />";

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {

        if(StringUtils.isEmpty(s)){
            return true;
        }
        Pattern pattern=Pattern.compile(HTML_PATTERN);
        Matcher matcher= pattern.matcher(s);
        return !matcher.matches();
    }
}
