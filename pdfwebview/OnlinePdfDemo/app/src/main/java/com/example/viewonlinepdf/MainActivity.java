package com.example.viewonlinepdf;

import java.io.UnsupportedEncodingException;
import com.example.viewonlinepdf.util.BASE64Encoder;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends FragmentActivity
{

	private WebView pdfShowWebView;
	// docPath =
	// "http://192.168.40.160:9988/xxjrweb/order/viewPdf.json?filepath=/2017/04/20/2016睢宁特色小镇第1期信新直融计划产品说明书_1492654457390.pdf";
//	private String docPath = "https://d11.baidupcs.com/file/ba53f41293af845addc44dc468923a9f?bkt=p3-1400ba53f41293af845addc44dc468923a9fb024210a00000f68e76c&xcode=b7b9ef9c9234679f8736717a18ba0f4712944d12b5f6eb9765d75071b4dbd0f7&fid=2334672476-250528-7836772807729&time=1499055076&sign=FDTAXGERLBHS-DCb740ccc5511e5e8fedcff06b081203-SBXLZNcKJUACUMLGJ9rXQwBN0NI%3D&to=d11&size=258533228&sta_dx=258533228&sta_cs=8726&sta_ft=pdf&sta_ct=7&sta_mt=7&fm2=MH,Yangquan,Netizen-anywhere,,jiangsu,ct&newver=1&newfm=1&secfm=1&flow_ver=3&pkey=1400ba53f41293af845addc44dc468923a9fb024210a00000f68e76c&sl=71565390&expires=8h&rt=pr&r=343614804&mlogid=4256064688440471098&vuk=2334672476&vbdid=3466206402&fin=JavaScript%E5%85%A5%E9%97%A8%E7%BB%8F%E5%85%B8%EF%BC%88%E7%AC%AC%E4%B8%89%E7%89%88%EF%BC%89.pdf&fn=JavaScript%E5%85%A5%E9%97%A8%E7%BB%8F%E5%85%B8%EF%BC%88%E7%AC%AC%E4%B8%89%E7%89%88%EF%BC%89.pdf&rtype=1&iv=0&dp-logid=4256064688440471098&dp-callid=0.1.1&hps=1&csl=155&csign=bpdsh7Zgm7NBO%2B%2FRyN7Pd9BBLTs%3D&so=0&ut=6&uter=4&serv=0&by=themis";
//	private String docPath = "https://static.miduo.com/group1/M00/01/4F/CgECC1nLaPaAU4gUAAeP4VCPX00003.pdf";
	private String docPath = "file:///android_asset/demo.pdf";
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		pdfShowWebView = (WebView) findViewById(R.id.pdf_show_webview);
		pdfShowWebView.setWebViewClient(new WebViewClient()
		{
			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url)
			{
				// 返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
				view.loadUrl(url);
				return true;
			}
		});
		WebSettings settings = pdfShowWebView.getSettings();
		settings.setSavePassword(false);
		settings.setJavaScriptEnabled(true);
		settings.setAllowFileAccessFromFileURLs(true);
		settings.setAllowUniversalAccessFromFileURLs(true);
		settings.setBuiltInZoomControls(true);
		pdfShowWebView.setWebChromeClient(new WebChromeClient());
		if (!"".equals(docPath))
		{
			byte[] bytes = null;
			try
			{// 获取以字符编码为utf-8的字符
				bytes = docPath.getBytes("UTF-8");
			}
			catch (UnsupportedEncodingException e1)
			{
				e1.printStackTrace();
			}
			if (bytes != null)
			{
				docPath = new BASE64Encoder().encode(bytes);// BASE64转码
			}
		}
		pdfShowWebView
				.loadUrl("file:///android_asset/pdfjs/web/viewer.html?file="
						+ docPath);
	}
}
