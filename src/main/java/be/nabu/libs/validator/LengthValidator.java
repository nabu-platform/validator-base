package be.nabu.libs.validator;

import java.util.ArrayList;
import java.util.List;

import be.nabu.libs.validator.api.ValidationMessage;
import be.nabu.libs.validator.api.ValidationMessage.Severity;
import be.nabu.libs.validator.api.Validator;

public class LengthValidator implements Validator<CharSequence> {
	
	private Integer max, min;

	public LengthValidator(Integer min, Integer max) {
		this.min = min;
		this.max = max;
	}
	
	@Override
	public List<ValidationMessage> validate(CharSequence instance) {
		List<ValidationMessage> messages = new ArrayList<ValidationMessage>();
		if (min != null || max != null) {
			if (instance == null)
				messages.add(new ValidationMessage(Severity.WARNING, "The string is null, its length can not be validated"));
			else {
				Integer length = instance.length();
				if (min != null && length.compareTo(min) < 0)
					messages.add(new ValidationMessage(Severity.ERROR, "The string '" + instance + "' is shorter than allowed: " + instance.length() + " < " + min));
				if (max != null && length.compareTo(max) > 0)
					messages.add(new ValidationMessage(Severity.ERROR, "The string '" + instance + "' is longer than allowed: " + instance.length() + " > " + max));
			}
		}
		return messages;
	}

	public Integer getMax() {
		return max;
	}

	public Integer getMin() {
		return min;
	}

	@Override
	public Class<CharSequence> getValueClass() {
		return CharSequence.class;
	}
}
