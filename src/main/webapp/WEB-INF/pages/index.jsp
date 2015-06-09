<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<title>测试标题</title>

<meta content="text/html; charset=utf-8" http-equiv="Content-Type" />
<meta content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no" name="viewport">
<!-- Mobile Devices Support @begin -->
<meta content="application/xhtml+xml;charset=UTF-8" http-equiv="Content-Type">
<meta content="no-cache,must-revalidate" http-equiv="Cache-Control">
<meta content="no-cache" http-equiv="pragma">
<meta content="0" http-equiv="expires">
<meta content="telephone=no, address=no" name="format-detection">
<meta content="width=device-width, initial-scale=1.0" name="viewport">
<meta name="apple-mobile-web-app-capable" content="yes" />
<!-- apple devices fullscreen -->
<meta name="apple-mobile-web-app-status-bar-style" content="black-translucent" />


</head>
<body onselectstart="return true;" ondragstart="return false;">
test
</body>
<footer>
	<script src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
	<script>
		/**
         * 所有需要使用JS-SDK的页面必须先注入配置信息，否则将无法调用（同一个url仅需调用一次，对于变化url的SPA的web app可在每次url变化时进行调用,
         * 目前Android微信客户端不支持pushState的H5新特性，所以使用pushState来实现web app的页面会导致签名失败，此问题会在Android6.2中修复）。
		 */
		wx.config({
		    debug: true, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
		    appId: 'wxac8428f6d6426ab7', // 必填，公众号的唯一标识
		    timestamp: ${params.timestamp}, // 必填，生成签名的时间戳
		    nonceStr: '${params.noncestr}', // 必填，生成签名的随机串
		    signature: '${params.signature}',// 必填，签名，见附录1
		    jsApiList: ["onMenuShareTimeline"] // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
		});
		wx.ready(function(){

		    // config信息验证后会执行ready方法，所有接口调用都必须在config接口获得结果之后，config是一个客户端的异步操作，所以如果需要在页面加载时就调用相关接口，则须把相关接口放在ready函数中调用来确保正确执行。对于用户触发时才调用的接口，则可以直接调用，不需要放在ready函数中。
			alert("wx.ready");
		});
		wx.error(function(res){

		    // config信息验证失败会执行error函数，如签名过期导致验证失败，具体错误信息可以打开config的debug模式查看，也可以在返回的res参数中查看，对于SPA可以在这里更新签名。
			alert("wx.error： " + res);
		});
		// 获取“分享到朋友圈”按钮点击状态及自定义分享内容接口
		wx.onMenuShareTimeline({
		    title: '分享标题（测试）', // 分享标题
		    link: 'http://www.baidu.com', // 分享链接
		    imgUrl: 'http://data.attachment.timetown.net/portal/201403/08/205957jotw0doi0twzt0dq.png', // 分享图标
		    success: function () { 
		        // 用户确认分享后执行的回调函数
		        alert("分享成功");
		    },
		    cancel: function () { 
		        // 用户取消分享后执行的回调函数
		        alert("取消分享");
		    }
		});
	</script>
</footer>
</html>