package kr.co.soldesk.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;

import kr.co.soldesk.beans.ContentBean;
import kr.co.soldesk.beans.UserBean;
import kr.co.soldesk.service.BoardService;

public class CheckWriterInterceptor implements HandlerInterceptor {

	private UserBean loginUserBean;
	private BoardService boardService;

	public CheckWriterInterceptor(UserBean loginUserBean, BoardService boardService) {
		this.loginUserBean = loginUserBean;
		this.boardService = boardService;
	}

	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

		String noticeID = request.getParameter("noticeID");
		
		ContentBean currentContentBean = boardService.getContentInfo(noticeID);
		
		//�ۼ��� ����� �� ��ȣ�� �α����� ����� �� ��ȣ�� �ٸ��� 
		if(!currentContentBean.getWriterID().equals(loginUserBean.getUserID()) && !"M".equals(loginUserBean.getRolecd())) {
			//��θ� �о
			String contextPath = request.getContextPath();
			System.out.println("contextPath: " + contextPath);
			//not_writer ȣ��
			response.sendRedirect(contextPath + "/board/not_writer");
			
			return false;
		}
		return true;
	}

}
