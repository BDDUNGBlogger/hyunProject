package kr.co.soldesk.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.soldesk.beans.BoardInfoBean;
import kr.co.soldesk.beans.CampaignBean;
import kr.co.soldesk.beans.ContentBean;
import kr.co.soldesk.service.BoardService;
import kr.co.soldesk.service.CampaignService;
import kr.co.soldesk.service.TopMenuService;

@Controller
public class MainController {

   @Autowired
   private CampaignService campaignService;

   @Autowired
   private BoardService boardService;

   @Autowired
   private TopMenuService topMenuService;

   @GetMapping("/main")
   public String main(@RequestParam(value = "page", defaultValue = "1") int page, Model model) {

      ArrayList<List<ContentBean>> list = new ArrayList<List<ContentBean>>();

      for (int i = 1; i < 4; i++) {
         List<ContentBean> list1 = boardService.getContentList(i, 0);
         list.add(list1);
      }

      model.addAttribute("list", list);

      List<BoardInfoBean> board_list = topMenuService.getTopMenuList(); // �Խ����� ��ȣ�� �̸��� ������
      model.addAttribute("board_list", board_list);

      // Campaign data
      List<CampaignBean> naturalCampaigns = campaignService.getCampaignList("ȯ��", page);
      List<CampaignBean> animalCampaigns = campaignService.getCampaignList("����", page);

      model.addAttribute("naturalCampaigns", naturalCampaigns);
      model.addAttribute("animalCampaigns", animalCampaigns);

      return "main"; // main.jsp ���Ϸ� �̵�
      
   }
}
