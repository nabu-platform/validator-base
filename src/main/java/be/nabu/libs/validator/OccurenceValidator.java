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
			messages.add(new ValidationMessage(Severity.ERROR, "Collection has " + instance.size() + " values, but expects at least " + getMinOccurs(), "minItems", null));
		if (getMaxOccurs() != 0 && instance.size() > getMaxOccurs())
			messages.add(new ValidationMessage(Severity.ERROR, "Collection has " + instance.size() + " values, but only " + getMaxOccurs() + " are allowed", "maxItems", null));
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
