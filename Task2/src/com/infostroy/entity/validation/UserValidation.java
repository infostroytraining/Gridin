package com.infostroy.entity.validation;

import com.infostroy.dto.UserDTO;

public class UserValidation {

	public static boolean validate(UserDTO user) {
		if (user.getFirstName() != null && user.getFirstName().matches("\\w+")) {
			if (user.getLastName() != null && user.getLastName().matches("\\w+")) {
				if (user.getEmail() != null
						&& user.getEmail().matches("^\\w+[\\w-\\.]*\\@\\w+((-\\w+)|(\\w*))\\.[a-z]{2,3}$")) {
					if (user.getPassword() != null && user.getPassword().matches("^[a-zA-Z][a-zA-Z0-9-_\\.]{1,20}$")) {
						return true;
					}
				}
			}
		}
		return false;
	}
}