package be.nabu.libs.validator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import be.nabu.libs.validator.api.ValidationMessage;
import be.nabu.libs.validator.api.ValidationMessage.Severity;
import be.nabu.libs.validator.api.Validator;

@SuppressWarnings("rawtypes")
public class OccurenceValidator<T extends Collection> implements Validator<T> {
	
	private Integer minOccurs, maxOccurs;

	public OccurenceValidator(Integer minOccurs, Integer maxOccurs) {
		this.minOccurs = minOccurs;
		this.maxOccurs = maxOccurs;
	}
	
	@Override
	public List<ValidationMessage> validate(T instance) {
		List<ValidationMessage> messages = new ArrayList<ValidationMessage>();
		if (instance.size() < getMinOccurs())
			messages.add(new ValidationMessage(Severity.ERROR, "Collection has " + instance.size() + " values, but expects at least " + getMinOccurs()));
		if (getMaxOccurs() != 0 && instance.size() > getMaxOccurs())
			messages.add(new ValidationMessage(Severity.ERROR, "Collection has " + instance.size() + " values, but only " + getMaxOccurs() + " are allowed"));
		return messages;
	}

	public int getMinOccurs() {
		return minOccurs == null ? 1 : minOccurs;
	}

	public int getMaxOccurs() {
		return maxOccurs == null ? 1 : maxOccurs;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Class<T> getValueClass() {
		return (Class<T>) Collection.class;
	}
}
