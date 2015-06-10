package net.timetown.wechat.test;

import net.timetown.wechat.util.WeixinUtil;
import net.timetown.wechat.wxbean.AccessToken;

public class WeixinTest {

	public static void main(String[] args){
		AccessToken token = WeixinUtil.getAccessToken();
		System.out.println("票据："+token.getToken());
		System.err.println("有效时间："+token.getExpiresIn());
		
//		String menu = JSONObject.fromObject(WeixinUtil.initMenu()).toString();
//		int result = WeixinUtil.createMenu(token.getToken(),menu );
//		if(result == 0){
//			System.err.println("创建菜单成功");
//		}else{
//			System.err.println("错误码："+result);
//		}
	}

}
