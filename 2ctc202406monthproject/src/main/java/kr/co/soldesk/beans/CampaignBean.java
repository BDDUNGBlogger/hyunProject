package kr.co.soldesk.beans;

import java.sql.Date;
import java.sql.Timestamp;

import javax.validation.constraints.NotBlank;

import org.springframework.web.multipart.MultipartFile;

public class CampaignBean {
	
	private String campaignID; //ķ���� ID, �⺻ Ű
	@NotBlank //DB���� not null�� ����
	private String cam_title; //ķ���� ����
	@NotBlank
	private String cam_host; //ķ���� ����
	@NotBlank
	private String cam_intro; //ķ���� ���� (�뷮�� �ؽ�Ʈ �����͸� ������ �� �ֵ��� CLOB ���)
	private String cam_news = "��ݼҽĴ�ü�� ��ݻ���� ���õ� �ҽ��� ����帳�ϴ�."; //ķ���� ���� (�뷮�� �ؽ�Ʈ �����͸� ������ �� �ֵ��� CLOB ���)
	private double goal_amount; //��ǥ �ݾ� (���� �Ҽ����� ����Ͽ� ���е� ����)
	private double current_amount; //���� ��ݵ� �ݾ�, �⺻���� 0
	private Timestamp create_date; //�ۼ� ��¥, �⺻���� ���� �ð�
	private Timestamp last_update; //������ ������Ʈ ��¥, �⺻���� ���� �ð�
	
	
	private MultipartFile upload_file;//�������� ���� ���� ���� DB�� �Ѿ�� ����.
    
	private String cam_img; //ķ���� �̹��� ���
	private Date start_date; //���� ��¥
	private Date end_date; // ���� ��¥
    private String category; //ȯ��, ���� ī�װ�, 'ȯ��' �Ǵ� '����'�� ���
    private int donor_count; //���� ����� ��� ��, �⺻���� 0
    
    
    public CampaignBean() {
        this.cam_news = "��ݼҽĴ�ü�� ��ݻ���� ���õ� �ҽ��� ����帳�ϴ�.";
    }
    
	public String getCampaignID() {
		return campaignID;
	}
	public void setCampaignID(String campaignID) {
		this.campaignID = campaignID;
	}
	public String getCam_title() {
		return cam_title;
	}
	public void setCam_title(String cam_title) {
		this.cam_title = cam_title;
	}
	public String getCam_host() {
		return cam_host;
	}
	public void setCam_host(String cam_host) {
		this.cam_host = cam_host;
	}
	public String getCam_intro() {
		return cam_intro;
	}
	public void setCam_intro(String cam_intro) {
		this.cam_intro = cam_intro;
	}
	
	public String getCam_news() {
		return cam_news;
	}
	public void setCam_news(String cam_news) {
		this.cam_news = cam_news;
	}
	public double getGoal_amount() {
		return goal_amount;
	}
	public void setGoal_amount(double goal_amount) {
		this.goal_amount = goal_amount;
	}
	public double getCurrent_amount() {
		return current_amount;
	}
	public void setCurrent_amount(double current_amount) {
		this.current_amount = current_amount;
	}
	public Timestamp getCreate_date() {
		return create_date;
	}
	public void setCreate_date(Timestamp create_date) {
		this.create_date = create_date;
	}
	public Timestamp getLast_update() {
		return last_update;
	}
	public void setLast_update(Timestamp last_update) {
		this.last_update = last_update;
	}
	
	public MultipartFile getUpload_file() {
		return upload_file;
	}
	public void setUpload_file(MultipartFile upload_file) {
		this.upload_file = upload_file;
	}
	
	public String  getCam_img() {
		return cam_img;
	}
	public void setCam_img(String cam_img) {
		this.cam_img = cam_img;
	}
	public Date getStart_date() {
		return start_date;
	}
	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}
	public Date getEnd_date() {
		return end_date;
	}
	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public int getDonor_count() {
		return donor_count;
	}
	public void setDonor_count(int donor_count) {
		this.donor_count = donor_count;
	}
	

    
}
