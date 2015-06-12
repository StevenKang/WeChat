package net.timetown.wechat.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/***
 * 管理中心控制器（对公众账号的一些操作）
 * 
 * @author stevenkang
 *
 */
@Controller
@RequestMapping("/manage")
public class ManageController {

	
	
	@RequestMapping
	public String get() {
		return "manage/index";
	}
	
	@RequestMapping("/menu")
	public String getMenu() {
		
		return "manage/menu/index";
	}
}
