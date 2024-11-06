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

import java.util.List;

import be.nabu.libs.validator.api.Validation;
import be.nabu.libs.validator.api.Validator;
import junit.framework.TestCase;

public class TestValidators extends TestCase {
	
	public void testLength() {
		testCorrect(new LengthValidator(1, null), "long enough");
		testInCorrect(new LengthValidator(10, null), "too short");
		testCorrect(new LengthValidator(null, 6), "short");
		testInCorrect(new LengthValidator(null, 6), "not short enough");
		testCorrect(new LengthValidator(2, 4), "yup");
	}
	
	protected <T> void testCorrect(Validator<T> validator, T instance) {
		List<? extends Validation<?>> messages = validator.validate(instance);
		assertTrue(messages.size() == 0);
	}
	protected <T> void testInCorrect(Validator<T> validator, T instance) {
		List<? extends Validation<?>> messages = validator.validate(instance);
		assertFalse(messages.size() == 0);
	}
	
}
