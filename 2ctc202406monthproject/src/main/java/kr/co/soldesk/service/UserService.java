package kr.co.soldesk.service;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.soldesk.beans.UserBean;
import kr.co.soldesk.dao.UserDao;
 
@Service
public class UserService {

	@Autowired
	private UserDao userDao;

	@Resource(name = "loginUserBean")
	private UserBean loginUserBean;

//	@Resource(name = "mypageUserBean")
//	private UserBean mypageUserBean;

	// �Ѱܹ��� ���̵� ����Ͽ� ��ȯ ���� �̸��� ��� �������� �Ǵ� ����
	public boolean checkUserExist(String userid) {
		String user_name = userDao.checkUserExist(userid);

		if (user_name == null) {
			return true; // ��밡��
		} else {
			return false; // �̹� �����ϴ� user_name
		}

	}

	public void addUserInfo(UserBean joinUserBean) {
		userDao.addUserInfo(joinUserBean);
	}

	/*
	public UserBean getUserListInfo(String userID) {
		
		return userDao.getUserListInfo(userID);
	
	}
	*/

	// tempLoginUserBean Ű�����̺�Ʈ, loginUserBean �����ͺ��̽�
	public void getLoginUserInfo(UserBean tempLoginUserBean) {

		UserBean tempLoginUserBean2 = userDao.getLoginUserInfo(tempLoginUserBean);
		

		// ������ �����Ͱ� �ִٸ�
		if (tempLoginUserBean2 != null) {
			loginUserBean.setName(tempLoginUserBean2.getName());
			loginUserBean.setRolecd(tempLoginUserBean2.getRolecd());
			loginUserBean.setUserID(tempLoginUserBean2.getUserID());
			 
			loginUserBean.setUserLogin(true); // �α��� ����
		}

	}

	public void getModifyUserInfo(UserBean modifyUserBean) {
		UserBean tempModifyUserBean = userDao.getModifyUserInfo(loginUserBean.getUserID());

		modifyUserBean.setUserID(tempModifyUserBean.getUserID());
		modifyUserBean.setName(tempModifyUserBean.getName());
		modifyUserBean.setBirthday(tempModifyUserBean.getBirthday());
		modifyUserBean.setGender(tempModifyUserBean.getGender());
		modifyUserBean.setEmail(tempModifyUserBean.getEmail());
		modifyUserBean.setPhone(tempModifyUserBean.getPhone());
		modifyUserBean.setPost_num(tempModifyUserBean.getPost_num());
		modifyUserBean.setAddress(tempModifyUserBean.getAddress());
		
		

	}

	public void modifyUserInfo(UserBean modifyUserBean) {
		// ������ ���̵�, �̸�, ���, ���Ȯ�� ������ �α����� ���� �߰�(UserID)
		modifyUserBean.setUserID(loginUserBean.getUserID());
		modifyUserBean.setName(loginUserBean.getName());
		userDao.modifyUserInfo(modifyUserBean);
	}

	public UserBean getFindUser() {

		return userDao.getFindUser(loginUserBean.getUserID());

	}

	public UserBean getfindid(String name, String email, String phone) {
		return userDao.getfindid(name, email, phone);
	}

	public UserBean getfindpw(String userID, String name, String email) {

		return userDao.getfindpw(userID, name, email);
	}

	public UserBean getfindid(String password) {
		return userDao.getfindid(password, password, password);
	}
	
	public UserBean getFindRoleCD(String userID) {
		return userDao.getFindRoleCD(userID);
	}
	
	public boolean checkGoogleUserIDExist(String userID) {
        String user_name = userDao.checkGoogleUserIDExist(userID);
        return user_name == null;
    }

    @Transactional
    public void addGoogleUserInfo(String userID, String password, String email, String name) {
        userDao.addGoogleUserInfo(userID, password, email, name);
        
        loginUserBean.setUserID(userID);
        loginUserBean.setPassword(password);
        loginUserBean.setEmail(email);
        loginUserBean.setName(name);
        loginUserBean.setUserLogin(true); 
        System.out.println("UserBean updated in service");
    }
    @Transactional
    public void updateGoogleUserInfo(String userID, String name, String password) {
        userDao.updateGoogleUserInfo(userID, name, password);
        System.out.println("Google user updated: " + userID);
    }
      

}
