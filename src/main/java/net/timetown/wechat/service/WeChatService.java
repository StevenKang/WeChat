package net.timetown.wechat.service;

import net.timetown.wechat.wxbean.Message;

public interface WeChatService {

	/***
	 * 处理微信推送过来的消息
	 */
	public Message process(Message message);
	
	/***
	 * 获取指定公众号的access_token
	 * @param appid		第三方用户唯一凭证
	 * @param secret	第三方用户唯一凭证密钥，即appsecret
	 */
	public String getAccessToken(String appid, String secret);
	
	/***
	 * 获取jsapi_ticket
	 * @param accessToken	调用微信JS接口的临时票据
	 */
	public String getJsapiTicket(String accessToken);
}
