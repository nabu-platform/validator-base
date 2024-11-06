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

import be.nabu.libs.validator.api.Validation;
import be.nabu.libs.validator.api.Validator;

public class MultipleValidator<T> implements Validator<T> {

	private Validator<T> [] validators;
	
	public MultipleValidator(Validator<T>...validators) {
		this.validators = validators;
	}

	@Override
	public List<? extends Validation<?>> validate(T instance) {
		List<Validation<?>> messages = new ArrayList<Validation<?>>();
		for (Validator<T> validator : validators)
			messages.addAll(validator.validate(instance));
		return messages;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Class<T> getValueClass() {
		if (validators.length > 0)
			return validators[0].getValueClass();
		else
			return (Class<T>) Void.class;
	}
	
}
