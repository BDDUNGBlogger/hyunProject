package kr.co.soldesk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import kr.co.soldesk.service.UserService;

//�񵿱�Ŀ���
@RestController
public class RestApiController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/user/checkUserIdExist/{userid}")
	public String chekUserIdExist(@PathVariable String userid) {
		//@PathVariable : �ּҿ� ������ ���̱�
		boolean chek=userService.checkUserExist(userid);
		System.out.println("RestApiController ���");
		
		return chek+"";
		
	}

}
