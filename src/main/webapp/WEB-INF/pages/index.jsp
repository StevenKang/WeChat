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
<span id="init">微信JS-SDK正在初始化</span>
<div style="display: none" id="module">
<ul>
	<li id="mod1">
		<h3>判断当前客户端版本是否支持指定JS接口</h3>
		<input type="button" value="点击进行检测"/><br>
		<span class="show"></span>
	</li>
	<li id="mod2">
		<h3>拍照或从手机相册中选图接口</h3>
		<input type="button" value="点击进行图片选择" /><br>
		<div class="show">
			<img src="http://mmbiz.qpic.cn/mmbiz/Ba4qyFks2T1MlvfXIxlCyUPErjKFX2cSN8v2F9icS2agGZGDlrwkaa8AWZJlGKHIeGTQ1vT5WWuZruAbAD7H8MA/0"/>
			<img src="http://mmbiz.qpic.cn/mmbiz/Ba4qyFks2T1MlvfXIxlCyUPErjKFX2cSN8v2F9icS2agGZGDlrwkaa8AWZJlGKHIeGTQ1vT5WWuZruAbAD7H8MA/0"/>
		</div>
	</li>
</ul>
</div>
</body>
<footer>
	<script src="http://lib.sinaapp.com/js/jquery/1.8.3/jquery.min.js"></script>
	<script src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
	<script>
		/**
         * 所有需要使用JS-SDK的页面必须先注入配置信息，否则将无法调用（同一个url仅需调用一次，对于变化url的SPA的web app可在每次url变化时进行调用,
         * 目前Android微信客户端不支持pushState的H5新特性，所以使用pushState来实现web app的页面会导致签名失败，此问题会在Android6.2中修复）。
		 */
		 var jsApiList = ["onMenuShareTimeline", "onMenuShareAppMessage", "onMenuShareQQ", "onMenuShareWeibo", "startRecord", 
		    	"stopRecord", "onVoiceRecordEnd", "playVoice", "pauseVoice", "stopVoice", "onVoicePlayEnd", "uploadVoice", 
		    	"downloadVoice", "chooseImage", "previewImage", "uploadImage", "downloadImage", "translateVoice", "getNetworkType", 
		    	"openLocation", "getLocation", "hideOptionMenu", "showOptionMenu", "hideMenuItems", "showMenuItems", 
		    	"hideAllNonBaseMenuItem", "showAllNonBaseMenuItem", "closeWindow", "scanQRCode", "chooseWXPay", 
		    	"openProductSpecificView", "addCard", "chooseCard", "openCard"];
		 
		wx.config({
		    debug: true, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
		    appId: 'wxac8428f6d6426ab7', // 必填，公众号的唯一标识
		    timestamp: ${params.timestamp}, // 必填，生成签名的时间戳
		    nonceStr: '${params.noncestr}', // 必填，生成签名的随机串
		    signature: '${params.signature}',// 必填，签名，见附录1
		    // 必填，需要使用的JS接口列表，所有JS接口列表见附录2（版本1.0.0接口）
		    jsApiList:  jsApiList
		});
		wx.ready(function(){

		    // config信息验证后会执行ready方法，所有接口调用都必须在config接口获得结果之后，config是一个客户端的异步操作，所以如果需要在页面加载时就调用相关接口，则须把相关接口放在ready函数中调用来确保正确执行。对于用户触发时才调用的接口，则可以直接调用，不需要放在ready函数中。
			document.getElementById("init").innerHTML = "JS-SDK初始化完成";
			document.getElementById("module").style.display = "block";
		});
		wx.error(function(res){

		    // config信息验证失败会执行error函数，如签名过期导致验证失败，具体错误信息可以打开config的debug模式查看，也可以在返回的res参数中查看，对于SPA可以在这里更新签名。
			alert("wx.error： " + res);
		});
		
		$(function(){
		
			// 判断当前客户端版本是否支持指定JS接口
			// http://mp.weixin.qq.com/wiki/7/aaa137b55fb2e0456bf8dd9148dd613f.html#.E5.88.A4.E6.96.AD.E5.BD.93.E5.89.8D.E5.AE.A2.E6.88.B7.E7.AB.AF.E7.89.88.E6.9C.AC.E6.98.AF.E5.90.A6.E6.94.AF.E6.8C.81.E6.8C.87.E5.AE.9AJS.E6.8E.A5.E5.8F.A3
			$("#mod1 input[type='button']").click(function(){
				$("#mod1 .show").html("");
				wx.checkJsApi({
				    jsApiList: jsApiList, // 需要检测的JS接口列表，所有JS接口列表见附录2,
				    success: function(res) {
				        // 以键值对的形式返回，可用的api值true，不可用为false
				        // 如：{"checkResult":{"chooseImage":true},"errMsg":"checkJsApi:ok"}
					  	for ( var key in res.checkResult){
					  		$("#mod1 .show").append(key + ": " + res.checkResult[key] + "<br/>");
					  	}
				    }
				});
			});
			
			// 获取“分享到朋友圈”按钮点击状态及自定义分享内容接口（未接入成功）
			// http://mp.weixin.qq.com/wiki/7/aaa137b55fb2e0456bf8dd9148dd613f.html#.E5.88.86.E4.BA.AB.E6.8E.A5.E5.8F.A3
			wx.onMenuShareTimeline({
			    title: '分享标题（测试）', // 分享标题
			    link: 'http://stevenkang.tunnel.mobi/WeChat/', // 分享链接
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
			
			// 获取“分享给朋友”按钮点击状态及自定义分享内容接口（未接入成功）
			// http://mp.weixin.qq.com/wiki/7/aaa137b55fb2e0456bf8dd9148dd613f.html#.E8.8E.B7.E5.8F.96.E2.80.9C.E5.88.86.E4.BA.AB.E7.BB.99.E6.9C.8B.E5.8F.8B.E2.80.9D.E6.8C.89.E9.92.AE.E7.82.B9.E5.87.BB.E7.8A.B6.E6.80.81.E5.8F.8A.E8.87.AA.E5.AE.9A.E4.B9.89.E5.88.86.E4.BA.AB.E5.86.85.E5.AE.B9.E6.8E.A5.E5.8F.A3
			wx.onMenuShareAppMessage({
			    title: '分享标题（测试）', // 分享标题
			    desc: '分享描述（测试）', // 分享描述
			    link: 'http://stevenkang.tunnel.mobi/WeChat/', // 分享链接
			    imgUrl: 'http://data.attachment.timetown.net/portal/201403/08/205957jotw0doi0twzt0dq.png', // 分享图标
			    type: '', // 分享类型,music、video或link，不填默认为link
			    dataUrl: '', // 如果type是music或video，则要提供数据链接，默认为空
			    success: function () { 
			        // 用户确认分享后执行的回调函数
			        alert("分享成功")
			    },
			    cancel: function () { 
			        // 用户取消分享后执行的回调函数
			        alert("取消分享");
			    }
			});
			
			// 获取“分享到QQ”按钮点击状态及自定义分享内容接口（未接入成功）
			// http://mp.weixin.qq.com/wiki/7/aaa137b55fb2e0456bf8dd9148dd613f.html#.E8.8E.B7.E5.8F.96.E2.80.9C.E5.88.86.E4.BA.AB.E5.88.B0QQ.E2.80.9D.E6.8C.89.E9.92.AE.E7.82.B9.E5.87.BB.E7.8A.B6.E6.80.81.E5.8F.8A.E8.87.AA.E5.AE.9A.E4.B9.89.E5.88.86.E4.BA.AB.E5.86.85.E5.AE.B9.E6.8E.A5.E5.8F.A3
			wx.onMenuShareQQ({
			    title: "分享标题", // 分享标题
			    desc: "分享描述", // 分享描述
			    link: "http://stevenkang.tunnel.mobi/WeChat/", // 分享链接
			    imgUrl: "http://data.attachment.timetown.net/portal/201403/08/205957jotw0doi0twzt0dq.png", // 分享图标
			    success: function () { 
			       // 用户确认分享后执行的回调函数
			       alert("分享成功");
			    },
			    cancel: function () { 
			       // 用户取消分享后执行的回调函数
			       alert("取消分享");
			    }
			});
			
			// 获取“分享到腾讯微博”按钮点击状态及自定义分享内容接口（如何分享到腾讯微博？？？）
			// http://mp.weixin.qq.com/wiki/7/aaa137b55fb2e0456bf8dd9148dd613f.html#.E8.8E.B7.E5.8F.96.E2.80.9C.E5.88.86.E4.BA.AB.E5.88.B0.E8.85.BE.E8.AE.AF.E5.BE.AE.E5.8D.9A.E2.80.9D.E6.8C.89.E9.92.AE.E7.82.B9.E5.87.BB.E7.8A.B6.E6.80.81.E5.8F.8A.E8.87.AA.E5.AE.9A.E4.B9.89.E5.88.86.E4.BA.AB.E5.86.85.E5.AE.B9.E6.8E.A5.E5.8F.A3
			wx.onMenuShareWeibo({
			    title: '分享标题', // 分享标题
			    desc: '分享描述', // 分享描述
			    link: 'http://stevenkang.tunnel.mobi/WeChat/', // 分享链接
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
			
			// 拍照或从手机相册中选图接口（开发中...）
			// http://mp.weixin.qq.com/wiki/7/aaa137b55fb2e0456bf8dd9148dd613f.html#.E6.8B.8D.E7.85.A7.E6.88.96.E4.BB.8E.E6.89.8B.E6.9C.BA.E7.9B.B8.E5.86.8C.E4.B8.AD.E9.80.89.E5.9B.BE.E6.8E.A5.E5.8F.A3
			$("#mod2 input[type='button']").click(function(){
				wx.chooseImage({
				    success: function (res) {
				    	alert("选择成功");
				        var localIds = res.localIds; // 返回选定照片的本地ID列表，localId可以作为img标签的src属性显示图片
				        for (var i=0; i<localIds.length; i++) {
				        	$("#mod2 .show img:eq("+i+")").attr("src", localIds[i]);
				        }
				    }
				});
			});
			
		});
	</script>
</footer>
</html>