package kr.co.soldesk.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import kr.co.soldesk.beans.OrdersBean;
import kr.co.soldesk.dao.OrdersDao;

@Service
public class OrdersService {

	@Autowired  
	private OrdersDao ordersDao;  

	public List<OrdersBean> getAllorders(String userID) {
		return ordersDao.getAllorders(userID);

	}

	// ���ų��� ���̺� ���� �ֱ�
	public void addOrdersInfo(OrdersBean addordersInfo) {
	
			addordersInfo.setOrderID(generateNewOrderID());
			ordersDao.addOrdersInfo(addordersInfo);
			
			System.out.println("addOrdersInfo : "+addordersInfo.getOrderID());
	}

	// ����� ���� ������Ʈ
	public void updateOrdersInfo(OrdersBean updateordersInfo) {		
		ordersDao.updateOrdersInfo(updateordersInfo);
	}

	private String generateNewOrderID() {
		long timeMillis = System.currentTimeMillis();
	    String uniquePart = Long.toString(timeMillis).substring(5); // ������ 9�ڸ� ���
	    return "O" + uniquePart;
	}
		

}
