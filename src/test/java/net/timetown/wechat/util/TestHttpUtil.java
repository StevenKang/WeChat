package net.timetown.wechat.util;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import junit.framework.TestCase;

public class TestHttpUtil extends TestCase {

	@Test
	public void test() {
		String content = HttpUtil.get("http://localhost:8080/WeChat/test2");
		Assert.assertNotNull(content);
		Assert.assertFalse("".equals(content));
		System.out.println("——————————————————————————————————————————————");
		System.out.printf(content);
		System.out.println("——————————————————————————————————————————————");
	}
	
	@Test
	public void testPost() {
		String url = "http://localhost:8080/WeChat/test";
		Map<String, String> params = new HashMap<>();
		params.put("key1", "val1");
		params.put("key2", "中文");
		String content = HttpUtil.post(url, params);
		System.out.println("content: " + content);
	}
}
