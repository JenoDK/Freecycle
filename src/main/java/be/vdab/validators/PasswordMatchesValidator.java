package be.vdab.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import be.vdab.constraints.PasswordMatches;
import be.vdab.entities.User;

public class PasswordMatchesValidator implements
		ConstraintValidator<PasswordMatches, Object> {
	@Override
	public void initialize(PasswordMatches constraintAnnotation) {
	}

	@Override
	public boolean isValid(Object obj, ConstraintValidatorContext context) {
		User user = (User) obj;
		return user.getPaswoord().equals(user.getMatchingPaswoord());
	}
}
