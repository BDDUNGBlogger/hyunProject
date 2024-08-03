package kr.co.soldesk.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;

import kr.co.soldesk.beans.UserBean;

public class CheckLoginInterceptor implements HandlerInterceptor{
	
	private UserBean loginUserBean;
	
	//DI
	public CheckLoginInterceptor(UserBean loginUserBean) {
		this.loginUserBean = loginUserBean;
	}
	//�̺�Ʈ�� �߻��ϸ� �α��� �� ����ϼ���... �������� �̵�
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		// �α����� ���� �ʾҴٸ�
		if (loginUserBean.isUserLogin() == false) {
			String contextPath = request.getContextPath();
			
			//�α����� �Ǿ� ���� �ʾ����Ƿ� ������������ not_login�� ��û�ϵ��� ������
			response.sendRedirect(contextPath + "/user/not_login");
			System.out.println("contextPath");
			return false;
		}
		//�α��� ����
		return true;
	}
	
}