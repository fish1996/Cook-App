package com.baidu.android.guidepage;

import com.baidu.speech.recognizerdemo.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class InitAcitivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//getWindow().requestFeature(Window.FEATURE_NO_TITLE);
		this.setContentView(R.layout.init);
        Intent intent = new Intent();
        intent.setClass(this, com.baidu.android.guidepage.ViewFlowActivity.class);
        startActivity(intent);
	    Button voiceRecognizeBtn = (Button)findViewById(R.id.voiceRecognizeBtn);
	    voiceRecognizeBtn.setOnClickListener(new Button.OnClickListener(){//创建监听    
            public void onClick(View v) {   
                Intent intent1 = new Intent();
                intent1.setClass(InitAcitivity.this, com.baidu.android.voicedemo.ActivityMain.class);
                startActivity(intent1);
            }
		 });
	    
	    Button voiceComposeBtn = (Button)findViewById(R.id.voiceComposeBtn);
	    voiceComposeBtn.setOnClickListener(new Button.OnClickListener(){//创建监听    
            public void onClick(View v) {   
                Intent intent2 = new Intent();
                intent2.setClass(InitAcitivity.this, com.baidu.tts.MainActivity.class);
                startActivity(intent2);
            }
		 });
	}
}
