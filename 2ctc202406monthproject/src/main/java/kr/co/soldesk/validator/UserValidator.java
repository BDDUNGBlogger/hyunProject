package kr.co.soldesk.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import kr.co.soldesk.beans.UserBean;

public class UserValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {

		return UserBean.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		UserBean userBean = (UserBean) target; // UserBean���� ����ȯ

		// � ��ü�� ����ϴ� ��ȣ�� �˻����� ��ȯ���ִ� �޼ҵ�
		String beanName = errors.getObjectName();
		System.out.println("����������:" + beanName); // joinUserBean

		if (beanName.equals("joinUserBean") || beanName.equals("modifyUserBean")) {

			if (userBean.getPassword().equals(userBean.getPassword2()) == false) {
				System.out.println("UserValidator �������");
				errors.rejectValue("password", "NotEquals");
				errors.rejectValue("password2", "NotEquals");
			}

			// �ߺ��� üũ
			if (beanName.equals("joinUserBean")) {
				if (userBean.isUserIdExist() == false) {
					errors.rejectValue("userID", "DontCheckUserIdExist");

				}
			}
		}

	}

}
