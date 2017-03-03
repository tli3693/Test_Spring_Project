package com.troy.spring.test.validation;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.troy.spring.test.data.entity.Admin;
import com.troy.spring.test.data.entity.User;

@Component
public class UserCheckBoxValidation implements Validator{

	@Override
	public boolean supports(Class clazz) {
		//just validate the Customer instances
		return Admin.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {

		Admin cust = (Admin)target;

		if(cust.getListUsername()==null||cust.getListUsername().isEmpty()){
			errors.rejectValue("listUsername", "required.users");
		}
	}
}