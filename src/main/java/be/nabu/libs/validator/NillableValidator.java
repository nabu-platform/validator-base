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

public class NillableValidator implements Validator<Object> {

	private Boolean isNillable;
	
	public NillableValidator(Boolean isNillable) {
		this.isNillable = isNillable;
	}
	
	@Override
	public List<ValidationMessage> validate(Object instance) {
		List<ValidationMessage> messages = new ArrayList<ValidationMessage>();
		if (isNillable != null && !isNillable && instance == null)
			messages.add(new ValidationMessage(Severity.ERROR, "Object is null, this is not allowed", "required", null));
		return messages;
	}

	@Override
	public Class<Object> getValueClass() {
		return Object.class;
	}

}
