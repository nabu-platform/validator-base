package be.nabu.libs.validator;

import java.util.ArrayList;
import java.util.List;

import be.nabu.libs.validator.api.ValidationMessage;
import be.nabu.libs.validator.api.ValidationMessage.Severity;
import be.nabu.libs.validator.api.Validator;

@SuppressWarnings("rawtypes")
public class RangeValidator<T extends Comparable> implements Validator<T> {
	
	private T min, max;
	private boolean minInclusive = true, maxInclusive = false;
	
	public RangeValidator(T min, Boolean minInclusive, T max, Boolean maxInclusive) {
		this.min = min;
		this.minInclusive = minInclusive == null ? true : minInclusive;
		this.max = max;
		this.maxInclusive = maxInclusive == null ? false : maxInclusive;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ValidationMessage> validate(T instance) {
		List<ValidationMessage> messages = new ArrayList<ValidationMessage>();
		if (min != null || max != null) {
			if (instance == null)
				messages.add(new ValidationMessage(Severity.WARNING, "The object is null, the range can not be validated"));
			else {
				if (min != null) {
					int result = instance.compareTo(min);
					if ((minInclusive && result < 0) || (!minInclusive && result <= 0))
						messages.add(new ValidationMessage(Severity.ERROR, "The object is smaller than allowed", instance + " < " + min));
				}
				if (max != null) {
					int result = instance.compareTo(max);
					if ((maxInclusive && result > 0) || (!maxInclusive && result >= 0))
						messages.add(new ValidationMessage(Severity.ERROR, "The object is larger than allowed", instance + " > " + max));
				}
			}
		}
		return messages;
	}

	public T getMin() {
		return min;
	}

	public T getMax() {
		return max;
	}

	public boolean isMaxInclusive() {
		return maxInclusive;
	}

	public boolean isMinInclusive() {
		return minInclusive;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Class<T> getValueClass() {
		return (Class<T>) Comparable.class;
	}
}
