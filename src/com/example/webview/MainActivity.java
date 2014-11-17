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
        //����WebView���ԣ��ܹ�ִ��Javascript�ű�  
        webview.getSettings().setJavaScriptEnabled(true);  
        //������Ҫ��ʾ����ҳ  
        webview.loadUrl("http://������IP/zsyw");  
        //����Web��ͼ  
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

    //��д���¡����ˡ���ʱ�����Ĳ���
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
            System.out.println("TabHost_Index.java onKeyDown");
            if (keyCode == KeyEvent.KEYCODE_BACK) {
                    if (isExit == false) {
                            isExit = true;
                            Toast.makeText(this, "�ٰ�һ�κ��˼��˳�Ӧ�ó���", Toast.LENGTH_SHORT).show();
                            // ����ƻ����񣬸��ݲ����Ĳ�ͬ���������������Ĺ�����
                            // �ڹ̶�ʱ��ִ��ĳ�����ڹ̶�ʱ�俪ʼ�ظ�ִ��ĳ�����ظ�ʱ�����ɿأ����ӳٶ�ú�ִ��ĳ�������ӳٶ�ú��ظ�ִ��ĳ�����ظ�ʱ�����ɿ�
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
    
    
    

    //Web��ͼ  
    private class HelloWebViewClient extends WebViewClient {  
        @Override 
        public boolean shouldOverrideUrlLoading(WebView view, String url) {  
            view.loadUrl(url);  
            return true;  
        }  
    }  
} 