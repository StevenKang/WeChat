package net.timetown.wechat.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.codec.digest.DigestUtils;

public class Util {
	// 与接口配置信息中的Token要一致
	private static final String TOKEN = "token";

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
	 * 
	 * 验证签名
	 * 
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
	public static boolean checkSignature(String signature, String timestamp, String nonce) {
		try {
			/**
			 * 加密/校验流程：
				1. 将token、timestamp、nonce三个参数进行字典序排序
				2. 将三个参数字符串拼接成一个字符串进行sha1加密
				3. 开发者获得加密后的字符串可与signature对比，标识该请求来源于微信
			 */
			if (TOKEN == null || timestamp == null || nonce == null) {
				return false;
			}
			
			String[] str = { TOKEN, timestamp, nonce };
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
	
	// JS-SDK签名算法
	public static String jsSign(Map<String, String> params) {
		try {
            if (params == null || params.size() == 0) return null;
            
            // 获取key
            List<String> keys = new ArrayList<String>(params.keySet());
            
            // 对key键值按字典升序排序  
            Collections.sort(keys);
            
            // 拼接字符串
            String stringA = new String();
            for (String key: keys) {
            	String val = params.get(key);
                if (val != null && !val.trim().equals(""))
                        stringA += key+"="+val+"&";
            }
            String stringSignTemp = stringA.substring(0, stringA.length()-1);
            
            String sign= sha1(stringSignTemp);
            return sign;
	    } catch (Exception e) {
	            e.printStackTrace();
	            return null;
	    }
	}
	
	public static String sign(Map<String, String> params, String signKey) {
        try {
                if (params == null || params.size() == 0) return null;
                
                // 获取key
                List<String> keys = new ArrayList<String>(params.keySet());
                
                // 对key键值按字典升序排序  
                Collections.sort(keys);
                
                // 拼接字符串
                String stringA = new String();
                for (String key: keys) {
                	String val = params.get(key);
                    if (val != null && !val.trim().equals(""))
                            stringA += key+"="+val;
                }
                String stringSignTemp = stringA + "key=" + signKey;
                String sign= DigestUtils.md5Hex(stringSignTemp.getBytes("utf-8")).toUpperCase();
                return sign;
        } catch (Exception e) {
                e.printStackTrace();
                return null;
        }
	}
}
