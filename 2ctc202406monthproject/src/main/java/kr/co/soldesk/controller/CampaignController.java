package kr.co.soldesk.controller;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;
import javax.validation.Valid;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.soldesk.beans.CampaignBean;
import kr.co.soldesk.beans.DonationBean;
import kr.co.soldesk.beans.PageBean;
import kr.co.soldesk.beans.UserBean;
import kr.co.soldesk.service.CampaignService;
import kr.co.soldesk.service.DonationService;

@Controller
@RequestMapping("/campaign")
public class CampaignController {
    
    @Autowired
    private CampaignService campaignService;
    
    @Autowired
    private DonationService donationService;
    
    @Resource(name="loginUserBean")
    private UserBean loginUserBean;

    @GetMapping("/campaignMain")
    public String campaignMain(@RequestParam(value="page", defaultValue = "1") int page, Model model) { 
        List<CampaignBean> naturalCampaigns = campaignService.getCampaignList("ȯ��", page);
        List<CampaignBean> animalCampaigns = campaignService.getCampaignList("����", page);
        
        model.addAttribute("naturalCampaigns", naturalCampaigns);
        model.addAttribute("animalCampaigns", animalCampaigns);

        PageBean pageBean = campaignService.getCampaignCount("ȯ��", page);
        model.addAttribute("pageBean", pageBean);
        
        return "campaign/campaignMain"; //jsp
    }
    
    @GetMapping("/campaignNatural")
    public String campaignNatural(@RequestParam(value="page", defaultValue = "1") int page, Model model) {
        List<CampaignBean> naturalCampaigns = campaignService.getCampaignList("ȯ��", page);
        
        model.addAttribute("naturalCampaigns", naturalCampaigns);

        PageBean pageBean = campaignService.getCampaignCount("ȯ��", page);
        model.addAttribute("pageBean", pageBean);
        
        return "campaign/campaignNatural"; //jsp
    }
    
    @GetMapping("/campaignAnimal")
    public String campaignAnimal(@RequestParam(value="page", defaultValue = "1") int page, Model model) {
        
        List<CampaignBean> animalCampaigns = campaignService.getCampaignList("����", page);
        
        model.addAttribute("animalCampaigns", animalCampaigns);

        PageBean pageBean = campaignService.getCampaignCount("����", page);
        model.addAttribute("pageBean", pageBean);
        
        return "campaign/campaignAnimal"; //jsp
    }
    
    @GetMapping("/campaignDetail")
    public String campaignDetail(@RequestParam("campaignID") String campaignID, Model model) {
        CampaignBean readCampaignBean = campaignService.getCampaignInfo(campaignID);
        
        // ��¥ ���� ���
        long diffInMillies = Math.abs(readCampaignBean.getEnd_date().getTime() - readCampaignBean.getStart_date().getTime());
        long remainingDays = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
        
        // ��� ���� ��������
        List<DonationBean> donations = donationService.getDonationsByCampaignID(campaignID);

        // �ٹٲ��� <br> �±׷� ��ȯ�Ͽ� �𵨿� �߰�
        String introWithBreaks = readCampaignBean.getCam_intro().replaceAll("\n", "<br>");
        String newsWithBreaks = readCampaignBean.getCam_news().replaceAll("\n", "<br>");
        
        model.addAttribute("readCampaignBean", readCampaignBean);
        model.addAttribute("remainingDays", remainingDays);
        model.addAttribute("donations", donations);
        model.addAttribute("introWithBreaks", introWithBreaks);
        model.addAttribute("newsWithBreaks", newsWithBreaks);
        
        return "campaign/campaignDetail"; // JSP ���� ���
    }
    
    @GetMapping("/campaign_write")
    public String campaignWrite(@ModelAttribute("writeCampaignBean") CampaignBean writeCampaignBean) {
    	
    	 // ���� Ȯ��: 'M'�� �ƴϸ� ���� ���� �������� �����̷�Ʈ
        if (!"M".equals(loginUserBean.getRolecd())) {
            return "redirect:/campaign/campaign_not_write";
        }
    	
        // writeCampaignBean �ʱ�ȭ �� �⺻�� ����
        if (writeCampaignBean.getCam_news() == null || writeCampaignBean.getCam_news().isEmpty()) {
            writeCampaignBean.setCam_news("��ݼҽĴ�ü�� ��ݻ���� ���õ� �ҽ��� ����帳�ϴ�.");
        }

        
        return "campaign/campaign_write"; // JSP ���� ���
    }
    
    @GetMapping("/campaign_not_writer")
    public String campaign_not_writer() {
       return "campaign/campaign_not_writer";
    }
    
    @PostMapping("/campaignwrite_pro")
    public String campaignWritePro(@Valid @ModelAttribute("writeCampaignBean") CampaignBean writeCampaignBean,
            BindingResult result) {
    	 
    	// ���� Ȯ��: 'M'�� �ƴϸ� ���� ���� �������� �����̷�Ʈ
        if (!"M".equals(loginUserBean.getRolecd())) {
            return "redirect:/campaign/campaign_not_writer";
        }
    	
    	if(result.hasErrors()) {
            System.out.println("Validation errors in campaign_write form");
            return "campaign/campaign_write"; // JSP ���� ���
        }
        campaignService.addCampaignInfo(writeCampaignBean);
        System.out.println("Adding campaign info");
        return "campaign/campaign_write_success";
    }
    
    @GetMapping("/campaign_modify")
    public String campaign_modify(@RequestParam("campaignID") String campaignID, Model model) {
        
    	// ���� Ȯ��: 'M'�� �ƴϸ� ���� ���� �������� �����̷�Ʈ
        if (!"M".equals(loginUserBean.getRolecd())) {
            return "redirect:/campaign/campaign_not_writer";
        }
    	
        CampaignBean modifyCampaignBean = campaignService.getCampaignInfo(campaignID);
        
        model.addAttribute("modifyCampaignBean", modifyCampaignBean);
        
        return "campaign/campaign_modify"; // JSP ���� ���
    }
    
    @PostMapping("/campaign_modify_pro")
    public String campaign_modify_pro(@Valid @ModelAttribute("modifyCampaignBean") CampaignBean modifyCampaignBean, 
                                      BindingResult result) {
    	 // ���� Ȯ��: 'M'�� �ƴϸ� ���� ���� �������� �����̷�Ʈ
        if (!"M".equals(loginUserBean.getRolecd())) {
            return "redirect:/campaign/campaign_not_writer";
        }
    	
        if(result.hasErrors()) {
            return "campaign/campaign_modify"; // JSP ���� ���
        }
        
        campaignService.modifyCampaignInfo(modifyCampaignBean);
        
        return "campaign/campaign_modify_success";
    }
    
    @GetMapping("/campaign_delete")
    public String campaign_delete(@RequestParam("campaignID") String campaignID) {
    	 
    	// ���� Ȯ��: 'M'�� �ƴϸ� ���� ���� �������� �����̷�Ʈ
        if (!"M".equals(loginUserBean.getRolecd())) {
            return "redirect:/campaign/campaign_not_writer";
        }
    	
        campaignService.deleteCampaignInfo(campaignID);
        return "campaign/campaign_delete";
    }
}
