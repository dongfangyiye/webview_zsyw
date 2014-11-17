package com.example.webview;  
 
import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;  
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;  
import android.view.KeyEvent;  
import android.webkit.WebView;  
import android.webkit.WebViewClient;  
import android.widget.Toast;


 
public class MainActivity extends Activity {  
    private WebView webview; 
    @Override 
    public void onCreate(Bundle savedInstanceState) {  
        super.onCreate(savedInstanceState);  
        setContentView(R.layout.activity_main);  
        webview = (WebView) findViewById(R.id.webview);  
        //设置WebView属性，能够执行Javascript脚本  
        webview.getSettings().setJavaScriptEnabled(true);  
        //加载需要显示的网页  
        webview.loadUrl("http://服务器IP/zsyw");  
        //设置Web视图  
        webview.setWebViewClient(new HelloWebViewClient ());  
    }  


    private static Boolean isExit = false;
    Timer tExit = new Timer();
    TimerTask task = new TimerTask() {
            @Override
            public void run() {
                    isExit = false;
            }
    };

    //重写按下“后退”键时所做的操作
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
            System.out.println("TabHost_Index.java onKeyDown");
            if (keyCode == KeyEvent.KEYCODE_BACK) {
                    if (isExit == false) {
                            isExit = true;
                            Toast.makeText(this, "再按一次后退键退出应用程序", Toast.LENGTH_SHORT).show();
                            // 定义计划任务，根据参数的不同可以完成以下种类的工作：
                            // 在固定时间执行某任务，在固定时间开始重复执行某任务，重复时间间隔可控，在延迟多久后执行某任务，在延迟多久后重复执行某任务，重复时间间隔可控
                            task = null;
                            task = new TimerTask(){
                                    @Override
                                    public void run() {
                                            isExit = false;
                                    } 
                            };
                            webview.goBack();
                            tExit.schedule(task, 2000);
                    } else {
                            finish();
                            System.exit(0);
                    }
            }
            return true;
    }
    
    
    

    //Web视图  
    private class HelloWebViewClient extends WebViewClient {  
        @Override 
        public boolean shouldOverrideUrlLoading(WebView view, String url) {  
            view.loadUrl(url);  
            return true;  
        }  
    }  
} 