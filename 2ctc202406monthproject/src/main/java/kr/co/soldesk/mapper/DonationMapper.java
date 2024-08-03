package kr.co.soldesk.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kr.co.soldesk.beans.DonationBean;

public interface DonationMapper {

	// �Ŀ� ������ �����ͺ��̽��� ����
	@Insert("INSERT INTO donation (donationID, campaignID, userID, cam_amount, payment_method, anonymous) "
			+ "VALUES (#{donationID, jdbcType=VARCHAR}, #{campaignID}, #{userID}, #{cam_amount}, #{payment_method}, #{anonymous})")
	void addDonation(DonationBean donationBean);

	// �Ŀ� ������ ��ȸ
	@Select("SELECT d.*, c.cam_title, c.start_date, c.end_date " + "FROM donation d "
			+ "JOIN campaign c ON d.campaignID = c.campaignID " + "WHERE d.userID = #{userID}")
	@Results({ @Result(property = "donationID", column = "donationID"),
			@Result(property = "campaignID", column = "campaignID"), @Result(property = "userID", column = "userID"),
			@Result(property = "cam_amount", column = "cam_amount"),
			@Result(property = "payment_method", column = "payment_method"),
			@Result(property = "anonymous", column = "anonymous"),
			@Result(property = "donation_date", column = "donation_date"),
			@Result(property = "campaign.cam_title", column = "cam_title"),
			@Result(property = "campaign.start_date", column = "start_date"),
			@Result(property = "campaign.end_date", column = "end_date") })
	List<DonationBean> getDonationList(String userID);

	// Ư�� donationID�� �Ŀ� ������ ��ȸ
	@Select("SELECT d.*, c.cam_title, c.cam_host, c.start_date, c.end_date " + "FROM donation d "
			+ "JOIN campaign c ON d.campaignID = c.campaignID " + "WHERE d.donationID = #{donationID}")
	@Results({ @Result(property = "donationID", column = "donationID"),
			@Result(property = "campaignID", column = "campaignID"), @Result(property = "userID", column = "userID"),
			@Result(property = "cam_amount", column = "cam_amount"),
			@Result(property = "payment_method", column = "payment_method"),
			@Result(property = "anonymous", column = "anonymous"),
			@Result(property = "donation_date", column = "donation_date"),
			@Result(property = "campaign.cam_title", column = "cam_title"),
			@Result(property = "campaign.cam_host", column = "cam_host"),
			@Result(property = "campaign.start_date", column = "start_date"),
			@Result(property = "campaign.end_date", column = "end_date") })
	DonationBean getDonationById(String donationID);

	// Ư�� ��¥ ���� ���� �Ŀ� ������ ��ȸ
	@Select("SELECT d.*, c.cam_title, c.start_date, c.end_date " + "FROM donation d "
			+ "JOIN campaign c ON d.campaignID = c.campaignID " + "WHERE d.userID = #{userID} "
			+ "AND TRUNC(d.donation_date) BETWEEN TO_DATE(#{donation_startDate}, 'YYYY-MM-DD') AND TO_DATE(#{donation_endDate}, 'YYYY-MM-DD')")
	@Results({ @Result(property = "donationID", column = "donationID"),
			@Result(property = "campaignID", column = "campaignID"), @Result(property = "userID", column = "userID"),
			@Result(property = "cam_amount", column = "cam_amount"),
			@Result(property = "payment_method", column = "payment_method"),
			@Result(property = "anonymous", column = "anonymous"),
			@Result(property = "donation_date", column = "donation_date"),
			@Result(property = "campaign.cam_title", column = "cam_title"),
			@Result(property = "campaign.cam_host", column = "cam_host"),
			@Result(property = "campaign.start_date", column = "start_date"),
			@Result(property = "campaign.end_date", column = "end_date") })
	List<DonationBean> getDonationListByDateRange(@Param("userID") String userID,
			@Param("donation_startDate") String donation_startDate, @Param("donation_endDate") String donation_endDate);

	// �Ŀ� ������ ķ���� ID�� ��ȸ
	@Select("SELECT d.*, m.userID FROM donation d " + "JOIN member m ON d.userID = m.userID "
			+ "WHERE d.campaignID = #{campaignID}")
	@Results({ @Result(property = "donationID", column = "donationID"),
			@Result(property = "campaignID", column = "campaignID"), @Result(property = "userID", column = "userID"),
			@Result(property = "cam_amount", column = "cam_amount"),
			@Result(property = "payment_method", column = "payment_method"),
			@Result(property = "anonymous", column = "anonymous"),
			@Result(property = "donation_date", column = "donation_date") })
	List<DonationBean> getDonationsByCampaignID(@Param("campaignID") String campaignID);

	// �Ŀ� ���
	@Delete("DELETE FROM donation WHERE donationID = #{donationID}")
	void deleteDonation(String donationID);

	// ķ���� ���� ������Ʈ
	@Update("UPDATE campaign SET current_amount = current_amount - #{cam_amount}, donor_count = donor_count - 1 WHERE campaignID = #{campaignID}")
	void updateCampaignOnCancellation(DonationBean donationBean);

}
