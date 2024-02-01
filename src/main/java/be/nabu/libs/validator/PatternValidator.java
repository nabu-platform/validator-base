package be.nabu.libs.validator;

import java.util.ArrayList;
import java.util.List;

import be.nabu.libs.validator.api.ValidationMessage;
import be.nabu.libs.validator.api.ValidationMessage.Severity;
import be.nabu.libs.validator.api.Validator;

public class PatternValidator implements Validator<CharSequence> {

	private String pattern;
	
	public PatternValidator(String pattern) {
		this.pattern = pattern;
	}
	
	@Override
	public List<ValidationMessage> validate(CharSequence instance) {
		List<ValidationMessage> messages = new ArrayList<ValidationMessage>();
		if (getPattern() != null) {
			if (instance == null)
				messages.add(new ValidationMessage(Severity.WARNING, "The string is null, pattern can not be validated"));
			else if (!instance.toString().matches(getPattern()))
				messages.add(new ValidationMessage(Severity.ERROR, "The string '" + instance + "' does not match the pattern '" + getPattern() + "'", "pattern", null));
		}
		return messages;
	}

	public String getPattern() {
		return pattern;
	}

	@Override
	public Class<CharSequence> getValueClass() {
		return CharSequence.class;
	}
}
