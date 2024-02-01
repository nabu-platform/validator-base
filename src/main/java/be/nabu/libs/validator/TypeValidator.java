package be.nabu.libs.validator;

import java.util.ArrayList;
import java.util.List;

import be.nabu.libs.validator.api.ValidationMessage;
import be.nabu.libs.validator.api.Validator;
import be.nabu.libs.validator.api.ValidationMessage.Severity;

public class TypeValidator<T> implements Validator<T> {

	private Class<T> type;
	
	public TypeValidator(Class<T> type) {
		this.type = type;
	}
	
	@Override
	public List<ValidationMessage> validate(T instance) {
		List<ValidationMessage> messages = new ArrayList<ValidationMessage>();
		if (instance == null)
			messages.add(new ValidationMessage(Severity.WARNING, "The object is null, the class can not be validated"));
		else if (!type.isAssignableFrom(instance.getClass()))
			messages.add(new ValidationMessage(Severity.ERROR, "The object is of type " + instance.getClass().getName() + " not of the expected type " + type.getName(), "type", null));
		return messages;
	}

	@Override
	public Class<T> getValueClass() {
		return type;
	}

}
