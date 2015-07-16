package be.nabu.libs.validator;

import java.util.List;

import be.nabu.libs.validator.api.Validation;
import be.nabu.libs.validator.api.Validator;
import junit.framework.TestCase;

public class TestValidators extends TestCase {
	
	public void testLength() {
		testCorrect(new LengthValidator(1, null), "long enough");
		testInCorrect(new LengthValidator(10, null), "too short");
		testCorrect(new LengthValidator(null, 6), "short");
		testInCorrect(new LengthValidator(null, 6), "not short enough");
		testCorrect(new LengthValidator(2, 4), "yup");
	}
	
	protected <T> void testCorrect(Validator<T> validator, T instance) {
		List<? extends Validation<?>> messages = validator.validate(instance);
		assertTrue(messages.size() == 0);
	}
	protected <T> void testInCorrect(Validator<T> validator, T instance) {
		List<? extends Validation<?>> messages = validator.validate(instance);
		assertFalse(messages.size() == 0);
	}
	
}
