package be.nabu.libs.validator;

import java.util.ArrayList;
import java.util.List;

import be.nabu.libs.validator.api.ValidationMessage;
import be.nabu.libs.validator.api.Validator;

public class MultipleValidator<T> implements Validator<T> {

	private Validator<T> [] validators;
	
	public MultipleValidator(Validator<T>...validators) {
		this.validators = validators;
	}

	@Override
	public List<ValidationMessage> validate(T instance) {
		List<ValidationMessage> messages = new ArrayList<ValidationMessage>();
		for (Validator<T> validator : validators)
			messages.addAll(validator.validate(instance));
		return messages;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Class<T> getValueClass() {
		if (validators.length > 0)
			return validators[0].getValueClass();
		else
			return (Class<T>) Void.class;
	}
	
}
