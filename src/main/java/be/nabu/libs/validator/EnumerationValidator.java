package be.nabu.libs.validator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import be.nabu.libs.validator.api.ValidationMessage;
import be.nabu.libs.validator.api.ValidationMessage.Severity;
import be.nabu.libs.validator.api.Validator;

public class EnumerationValidator<T> implements Validator<T> {

	private boolean whitelist = true;
	private Set<T> values;
	
	public EnumerationValidator(boolean whitelist, T...values) {
		this.whitelist = whitelist;
		this.values = new HashSet<T>(Arrays.asList(values));
	}
	
	public EnumerationValidator(boolean whiteList, List<T> values) {
		this.whitelist = whiteList;
		this.values = new HashSet<T>(values);
	}
	
	@Override
	public List<ValidationMessage> validate(T instance) {
		List<ValidationMessage> messages = new ArrayList<ValidationMessage>();
		if (instance != null) {
			if (!values.isEmpty() && whitelist && !values.contains(instance))
				messages.add(new ValidationMessage(Severity.ERROR, "The value '" + instance + "' did not match any of the enumerated options: " + values));
			else if (!values.isEmpty() && !whitelist && values.contains(instance))
				messages.add(new ValidationMessage(Severity.ERROR, "The value '" + instance + "' matched one of the disallowed options"));
		}
		return messages;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Class<T> getValueClass() {
		if (values.isEmpty())
			return (Class<T>) Void.class;
		else
			return (Class<T>) values.iterator().next().getClass();
	}

}
