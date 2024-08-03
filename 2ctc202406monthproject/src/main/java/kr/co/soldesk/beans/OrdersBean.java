package kr.co.soldesk.beans;

import java.sql.Timestamp;

public class OrdersBean {

	private String orderID; // �ֹ�ID
	private String userID; // �����ID 
	private String productID; // ��ǰID
	private Timestamp orderDate; // �ֹ���¥
	private String username; // �ֹ� �� �̸�
	private String phone; // �ֹ� �� ��ȣ
	private String post_num; // �ֹ� �� �����ȣ
	private String address; // �ֹ� �� �ּ�
	private String color; // �ֹ� ����
	private int quantity; // �ֹ� ����
	private int price; // �ֹ� ����
	private String payment_method; // ���� ���, 'card', 'paypal', 'bank_transfer'�� ���
	

	public String getOrderID() {
		return orderID;
	}

	public void setOrderID(String orderID) {
		this.orderID = orderID;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getProductID() {
		return productID;
	}

	public void setProductID(String productID) {
		this.productID = productID;
	}

	public Timestamp getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Timestamp orderDate) {
		this.orderDate = orderDate;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPost_num() {
		return post_num;
	}

	public void setPost_num(String post_num) {
		this.post_num = post_num;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getPayment_method() {
		return payment_method;
	}

	public void setPayment_method(String payment_method) {
		this.payment_method = payment_method;
	}

}
