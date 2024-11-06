/*
* Copyright (C) 2014 Alexander Verbruggen
*
* This program is free software: you can redistribute it and/or modify
* it under the terms of the GNU Lesser General Public License as published by
* the Free Software Foundation, either version 3 of the License, or
* (at your option) any later version.
*
* This program is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
* GNU Lesser General Public License for more details.
*
* You should have received a copy of the GNU Lesser General Public License
* along with this program. If not, see <https://www.gnu.org/licenses/>.
*/

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
				if (min != null && max != null && min == max && length != min) {
					messages.add(new ValidationMessage(Severity.ERROR, "The string '" + instance + "' is not the allowed length", "length", null));
				}
				else {
					if (min != null && length.compareTo(min) < 0)
						messages.add(new ValidationMessage(Severity.ERROR, "The string '" + instance + "' is shorter than allowed: " + instance.length() + " < " + min, "minLength", null));
					if (max != null && length.compareTo(max) > 0)
						messages.add(new ValidationMessage(Severity.ERROR, "The string '" + instance + "' is longer than allowed: " + instance.length() + " > " + max, "maxLength", null));
				}
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
