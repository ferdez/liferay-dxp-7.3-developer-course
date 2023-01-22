package com.liferay.training.gradebook.validator;

import com.liferay.training.gradebook.exception.AssignmentValidationException;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

public interface AssignmentValidator {
	/**
	 * Validates an Assignment
	 *
	 * @param title
	 * @param description
	 * @param dueDate
	 * @throws AssignmentValidationException
	 */
	public void validate(String title, String description, Date dueDate) throws AssignmentValidationException;
	public void validate(Map<Locale, String> titleMap, String description, Date dueDate) throws AssignmentValidationException;
	public void validate(Map<Locale, String> titleMap, Map<Locale, String> descriptionMap, Date dueDate) throws AssignmentValidationException;
}
