/**
 * 
 */
package com.zhou.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.zhou.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * zhousecurity
 * 2019/10/31 13:56
 * for test
 *
 * @author ChristChou
 * @since
 **/
public class MyConstraintValidator implements ConstraintValidator<MyConstraint, Object> {

	@Autowired
	private HelloService helloService;
	
	@Override
	public void initialize(MyConstraint constraintAnnotation) {
		System.out.println("my validator init");
	}

	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		helloService.greeting("tom");
		System.out.println(value);
		return false;
	}

}
