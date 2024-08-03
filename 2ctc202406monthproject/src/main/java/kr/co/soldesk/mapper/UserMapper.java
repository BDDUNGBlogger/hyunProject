package kr.co.soldesk.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kr.co.soldesk.beans.UserBean;

public interface UserMapper {
  
	// ���̵� �ߺ� üũ�� ���� sql
	 
	// ���̹�Ƽ��� #���� �޾Ƶ���
	@Select("select name " + "from member " + "where userid=#{userID}")
	String checkUserIdExist(String userID);


	// ȸ������(����)
	@Insert("INSERT INTO member (userID, password, name, gender, birthday, email, phone, post_num, create_date, last_update, address, rolecd) "
			+ "VALUES (#{userID}, #{password}, #{name}, #{gender}, #{birthday}, #{email}, #{phone}, #{post_num}, SYSDATE, SYSDATE, #{address}, 'U')")
	void addUserInfo(UserBean joinUserBean);
	
	//�α��ν� ȸ������ ���� Ȯ��
	@Select("select userID, name, roleCD " + 
			"from member " + 
			"where userID=#{userID} and password=#{password}")
	UserBean getLoginUserInfo(UserBean tempLoginUserBean);
	
	//���������� �Ǵ� ���������� �ʿ��� ������ ��������
	@Select("select userID, password, name, birthday, gender, email, phone, post_num, address " +
			"from member " +
			"where userID = #{userID}")
	UserBean getModifyUserInfo(String userID);
	
	//�������� Update
	@Update("update member " +
			"set password = #{password}, email= #{email}, phone= #{phone}, post_num= #{post_num}, address= #{address} " +
			"where userID = #{userID}")
	void modifyUserInfo(UserBean modifyUserBean);
	
	/*
	//ȸ������ ���������
	@Select("select userID, name, birthday, gender, email, phone, post_num, address " + 
			"from member " + 
			"where userID=#{userID}")
	UserBean getUserListInfo(String userID);
	*/
	//���̵�ã�� 
	@Select("select * from member where userID=#{userID} " )
	UserBean getFindUser(String userID);
	
	//���̵�ã�� �������ִٸ� �׻���� ���̵� �����ֱ�
	@Select("SELECT userID FROM member WHERE name=#{name} AND email=#{email} AND phone=#{phone}" )
	UserBean getfindid(@Param("name") String name , @Param("email") String email ,@Param("phone") String phone);
	
	//��й�ȣã��
	@Select("select * from member where password=#{password} " )
	UserBean getFindUserpw(String password);
	
	//���̵� ������ ��й�ȣ �����ֱ�
	@Select("SELECT password FROM member WHERE userID=#{userID} AND name=#{name} AND email=#{email}" )
	UserBean getfindpw(@Param("userID") String userID , @Param("name") String name ,@Param("email") String email);

	//�����ڵ�
	@Select("select roleCD from member where userID=#{userID}")
	UserBean getFindRoleCD(@Param("userID") String userID);
	
	@Insert("insert into member (userID, password, name, email, create_date, roleCD) "
	         + "values (#{userID}, #{password}, #{name}, #{email}, sysdate, 'U')")
	   void addGoogleUserInfo(@Param("userID") String userID, @Param("password") String password,
	         @Param("email") String email, @Param("name") String name);

	   @Select("select name, userID from member where userID = #{userID}")
	   UserBean getGoogleLoginUserInfo(@Param("userID") String userID);

	   @Select("select name from member where userID = #{userID}")
	   String checkGoogleUserIDExist(@Param("userID") String userID);
	   
	   @Update("UPDATE member SET name = #{name}, password = #{password} WHERE userID = #{userID}")
	    void updateGoogleUserInfo(@Param("userID") String userID, 
	                              @Param("name") String name, 
	                              @Param("password") String password);
	
}



