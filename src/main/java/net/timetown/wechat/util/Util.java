package net.timetown.wechat.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.UUID;

public class Util {

	public static String uuid32() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
	
	public static String sha1(String bigStr) {
		byte[] bt = bigStr.getBytes();
		MessageDigest md;
		String result = "";
		try {
			md = MessageDigest.getInstance("SHA-1");
			md.update(bt);
			result = new BigInteger(1, md.digest()).toString(16);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 加密/校验流程：
		1. 将token、timestamp、nonce三个参数进行字典序排序
		2. 将三个参数字符串拼接成一个字符串进行sha1加密
		3. 开发者获得加密后的字符串可与signature对比，标识该请求来源于微信
	 * @param signature	微信加密签名，signature结合了开发者填写的token参数和请求中的timestamp参数、nonce参数。
	 * @param token		token
	 * @param timestamp	时间戳
	 * @param nonce		随机数
	 * @return
	 */
	public static boolean checkSignature(String signature, String token, String timestamp, String nonce) {
		try {
			/**
			 * 加密/校验流程：
				1. 将token、timestamp、nonce三个参数进行字典序排序
				2. 将三个参数字符串拼接成一个字符串进行sha1加密
				3. 开发者获得加密后的字符串可与signature对比，标识该请求来源于微信
			 */
			if (token == null || timestamp == null || nonce == null) {
				return false;
			}
			
			String[] str = { token, timestamp, nonce };
			Arrays.sort(str); // 字典序排序
			String bigStr = str[0] + str[1] + str[2];

			String digest = Util.sha1(bigStr);
			// 确认请求来至微信
			if (digest.equalsIgnoreCase(signature)) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
