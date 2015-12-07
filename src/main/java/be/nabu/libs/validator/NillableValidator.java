package be.nabu.libs.validator;

import java.util.ArrayList;
import java.util.List;

import be.nabu.libs.validator.api.ValidationMessage;
import be.nabu.libs.validator.api.ValidationMessage.Severity;
import be.nabu.libs.validator.api.Validator;

public class NillableValidator implements Validator<Object> {

	private Boolean isNillable;
	
	public NillableValidator(Boolean isNillable) {
		this.isNillable = isNillable;
	}
	
	@Override
	public List<ValidationMessage> validate(Object instance) {
		List<ValidationMessage> messages = new ArrayList<ValidationMessage>();
		if (isNillable != null && !isNillable && instance == null)
			messages.add(new ValidationMessage(Severity.ERROR, "Object is null, this is not allowed"));
		return messages;
	}

	@Override
	public Class<Object> getValueClass() {
		return Object.class;
	}

}
