package com.summit.constant.common.util.validator;

import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.summit.constant.common.util.validator.annotation.IsMobilePhone;

/**
 * 
 * 类描述: 校验11位手机号
 */
public class MobilePhoneValidator implements ConstraintValidator<IsMobilePhone, String>{

    private String reg1 = "^1([38][0-9]|4[0-9]|5[0-9]|6[0-9]|7[0-9]|9[0-9])\\d{8}$";   

    private Pattern idCardPattern1 = Pattern.compile(reg1);

    public void initialize(IsMobilePhone phone) {
        
    }

    public boolean isValid(String value, ConstraintValidatorContext arg1) {
        if (value == null || value.trim() == ""){
            return true;
        }else if(value.length()==11) {
            if( "1".equals(value.toString().substring(0, 1))){
                return idCardPattern1.matcher(value).matches();
            }else {
                return true;
            }
        }else {
            return true;
        }
    }
}
