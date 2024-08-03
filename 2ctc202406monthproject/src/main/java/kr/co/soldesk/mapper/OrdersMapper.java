package kr.co.soldesk.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kr.co.soldesk.beans.OrdersBean;



public interface OrdersMapper { 
	
	//���ų��� ��������
	@Select("select p.product_name, o.color, o.quantity, o.price, p.product_img "
			+ "from orders o, product p , member m "
			+ "where o.productid = p.productid and o.userid = m.userID "
			+ "and m.userID = #{userID}")
	List<OrdersBean> getAllorders(@Param("userID") String userID);
	
	
	//���ų��� ���� �ֱ�
	@Insert("INSERT INTO orders (orderID, userID, productID, username, phone, post_num, address, color, quantity, price, payment_method) "
			+ "VALUES (#{orderID}, #{userID}, #{productID}, #{username}, #{phone}, #{post_num}, #{address}, #{color}, #{quantity}, #{price}, #{payment_method})")
	void addOrdersInfo(OrdersBean addordersInfo);
	
	//����� �ּ� ���� ������Ʈ
	@Update("update orders set username = #{username}, phone = #{phone}, post_num = #{post_num}, address = #{address} "
			+ "where orderID = #{orderID}")
	void updateOrdersInfo(OrdersBean updateordersInfo);
 
}
