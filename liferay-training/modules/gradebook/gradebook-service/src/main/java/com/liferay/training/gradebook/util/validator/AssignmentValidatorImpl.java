package com.liferay.training.gradebook.util.validator;

import com.liferay.portal.kernel.util.Validator;
import com.liferay.training.gradebook.exception.AssignmentValidationException;
import com.liferay.training.gradebook.validator.AssignmentValidator;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.osgi.service.component.annotations.Component;

@Component(immediate = true, service = AssignmentValidator.class)
public class AssignmentValidatorImpl implements AssignmentValidator {
	/**
	 * Validates assignment values and throws
	 * {AssignmentValidationExceptionException} if the assignment values are not
	 * valid.
	 *
	 * @param title
	 * @param description
	 * @param dueDate
	 * @throws AssignmentValidationExceptionException
	 */
	public void validate(String title, String description, Date dueDate) throws AssignmentValidationException {
		List<String> errors = new ArrayList<>();

		if (!isAssignmentValid(title, description, dueDate, errors)) {
			throw new AssignmentValidationException(errors);
		}
	}
	
	public void validate(Map<Locale, String> titleMap, String description, Date dueDate) throws AssignmentValidationException {
		List<String> errors = new ArrayList<>();

		if (!isAssignmentValid(titleMap, description, dueDate, errors)) {
			throw new AssignmentValidationException(errors);
		}
	}
	
	public void validate(Map<Locale, String> titleMap, Map<Locale, String> descriptionMap, Date dueDate) throws AssignmentValidationException {
		List<String> errors = new ArrayList<>();

		if (!isAssignmentValid(titleMap, descriptionMap, dueDate, errors)) {
			throw new AssignmentValidationException(errors);
		}
	}



	/**
	 * Returns <code>true</code> if the given fields are valid for an assignment.
	 *
	 * @param title
	 * @param description
	 * @param dueDate
	 * @param errors
	 * @return boolean <code>true</code> if assignment is valid, otherwise
	 *         <code>false</code>
	 */
	private boolean isAssignmentValid(final String title, final String description, final Date dueDate,
			final List<String> errors) {
		boolean result = true;
		result &= isTitleValid(title, errors);
		result &= isDueDateValid(dueDate, errors);
		result &= isDescriptionValid(description, errors);
		return result;
	}
	
	private boolean isAssignmentValid(final Map<Locale, String> titleMap, final String description, final Date dueDate,
			final List<String> errors) {
		boolean result = true;
		result &= isTitleValid(titleMap, errors);
		result &= isDueDateValid(dueDate, errors);
		result &= isDescriptionValid(description, errors);
		return result;
	}
	
	private boolean isAssignmentValid(Map<Locale, String> titleMap, Map<Locale, String> descriptionMap, Date dueDate,
			List<String> errors) {
		boolean result = true;
		result &= isTitleValid(titleMap, errors);
		result &= isDueDateValid(dueDate, errors);
		result &= isDescriptionValid(descriptionMap, errors);
		return result;
	}

	/**
	 * Returns <code>true</code> if description is valid for an assignment. If not
	 * valid, an error message is added to the errors list.
	 *
	 * @param description
	 * @param errors
	 * @return boolean <code>true</code> if description is valid, otherwise
	 *         <code>false</code>
	 */
	private boolean isDescriptionValid(final String description, final List<String> errors) {
		boolean result = true;
		// Verify the description has something
		if (description == "") {
			errors.add("assignmentDescriptionEmpty");
			result = false;
		}
		return result;

	}
	
	
	private boolean isDescriptionValid(final Map<Locale, String> descriptionMap, final List<String> errors) {
		boolean result = true;
		// Verify the Title has something
		if (descriptionMap.isEmpty()) {
			errors.add("descriptionTitleEmpty");
			result = false;
		}
		return result;
	}

	/**
	 * Returns <code>true</code> if due date is valid for an assignment. If not
	 * valid, an error message is added to the errors list. Note that this error
	 * can't ever happen in the user interface because we are always getting a
	 * default value on the controller layer (Action Commands) However, this service
	 * could be access through the WS Api, which is why we need it.
	 *
	 * @param dueDate
	 * @param errors
	 * @return boolean <code>true</code> if due date is valid, otherwise
	 *         <code>false</code>
	 */
	private boolean isDueDateValid(final Date dueDate, final List<String> errors) {
		boolean result = true;
		if (Validator.isNull(dueDate)) {
			errors.add("assignmentDateEmpty");
			result = false;
		}
		return result;
	}

	/**
	 * Returns <code>true</code> if title is valid for an assignment. If not valid,
	 * an error message is added to the errors list.
	 *
	 * @param title
	 * @param errors
	 * @return boolean <code>true</code> if the title is valid, otherwise
	 *         <code>false</code>
	 */
	private boolean isTitleValid(final String title, final List<String> errors) {
		boolean result = true;
		// Verify the Title has something
		if (title == "") {
			errors.add("assignmentTitleEmpty");
			result = false;
		}
		return result;
	}
	
	private boolean isTitleValid(final Map<Locale, String> titleMap, final List<String> errors) {
		boolean result = true;
		// Verify the Title has something
		if (titleMap.isEmpty()) {
			errors.add("assignmentTitleEmpty");
			result = false;
		}
		return result;
	}
}
