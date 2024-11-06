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
