package kr.co.soldesk.beans;

import java.sql.Timestamp;
import java.util.UUID;

public class DonationBean {
	
	private String donationID; //�Ŀ� ID, �⺻ Ű
	private String campaignID; //ķ���� ID
	private String userID; // ����� ID
	private double cam_amount; // �Ŀ� �ݾ�
	private Timestamp donation_date; // �Ŀ� ��¥, �⺻���� ���� �ð�
	private String payment_method; //���� ���, card, paypal, bank_transfer�� ���
	private String anonymous;
	
	private CampaignBean campaign; // �߰�
	
	
	
	public String getDonationID() {
		if (donationID == null || donationID.isEmpty()) {
			donationID = UUID.randomUUID().toString();
		}
		return donationID;
	}

	public void setDonationID(String donationID) {
		this.donationID = donationID;
	}
	
	public CampaignBean getCampaign() {
		return campaign;
	}

	public void setCampaign(CampaignBean campaign) {
		this.campaign = campaign;
	}
	
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getAnonymous() {
		return anonymous;
	}
	public void setAnonymous(String anonymous) {
		this.anonymous = anonymous;
	}
	public String getCampaignID() {
		return campaignID;
	}
	public void setCampaignID(String campaignID) {
		this.campaignID = campaignID;
	}
	
	public double getCam_amount() {
		return cam_amount;
	}
	public void setCam_amount(double cam_amount) {
		this.cam_amount = cam_amount;
	}
	public Timestamp getDonation_date() {
		return donation_date;
	}
	public void setDonation_date(Timestamp donation_date) {
		this.donation_date = donation_date;
	}
	public String getPayment_method() {
		return payment_method;
	}
	public void setPayment_method(String payment_method) {
		this.payment_method = payment_method;
	}

	// ����� ID�� ����ŷ ó���ϴ� �޼��� �߰�
	 public String getMaskedUserID() {
	        if (anonymous.equals("T")) {
	            return "�͸�";
	        } else {
	            return maskUserID(userID);
	        }
	    }

	    private String maskUserID(String userID) {
	        if (userID.length() <= 2) {
	            return userID;
	        }
	        StringBuilder maskedID = new StringBuilder();
	        maskedID.append(userID.charAt(0));
	        for (int i = 1; i < userID.length() - 1; i++) {
	            maskedID.append("*");
	        }
	        maskedID.append(userID.charAt(userID.length() - 1));
	        return maskedID.toString();
	    }
}
