package com.baidu.android.voicedemo;


import com.baidu.speech.VoiceRecognitionService;
import com.baidu.speech.recognizerdemo.R;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.SpeechRecognizer;
import android.widget.TextView;
import android.widget.Toast;


public class ActivityMain extends Activity implements RecognitionListener {
    public static final String CATEGORY_SAMPLE_CODE = "com.baidu.speech.recognizerdemo.intent.category.SAMPLE_CODE";
    private SpeechRecognizer speechRecognizer;
    private TextView text;
    private Intent intent;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {//��ʼ��
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        // 创建识别器
        speechRecognizer = SpeechRecognizer.createSpeechRecognizer(this, new ComponentName(this, VoiceRecognitionService.class));
        // 注册监听器
        speechRecognizer.setRecognitionListener(this);
        text = (TextView)findViewById(R.id.my_test);
        startASR();
    }
    
    void startASR() {
    	Toast.makeText(getApplicationContext(), "startASR",Toast.LENGTH_SHORT).show();
        intent = new Intent();
        intent.putExtra("grammar", "asset:///baidu_speech_grammar.bsg");
        bindParams(intent);
        speechRecognizer.startListening(intent);
    }
    void bindParams(Intent intent) {
        // 设置识别参数
    }
    public void onReadyForSpeech(Bundle params) {
        // 准备就绪
    }
    @Override
    public void onBeginningOfSpeech() {
    	//Toast.makeText(getApplicationContext(), "Beginspeech",Toast.LENGTH_SHORT).show();
        // 开始说话处理
    }
    public void onRmsChanged(float rmsdB) {
    	//Toast.makeText(getApplicationContext(), "RmsChanged",Toast.LENGTH_SHORT).show();
            // 音量变化处理
    }
    public void onBufferReceived(byte[] buffer) {
            // 录音数据传出处理
    }
    public void onEndOfSpeech() {
    	//Toast.makeText(getApplicationContext(), "OnEndOfSpeech",Toast.LENGTH_SHORT).show();
        // 说话结束处理
    }
    public void onError(int error) {
    	Toast.makeText(getApplicationContext(), "Error",Toast.LENGTH_SHORT).show();
    	speechRecognizer.startListening(intent);
        // 出错处理
    }
    public void onResults(Bundle results) {
    	text.setText(results.toString());
    	speechRecognizer.startListening(intent);
        // 最终结果处理
    }
    public void onPartialResults(Bundle partialResults) {
        // 临时结果处理
    }
    public void onEvent(int eventType, Bundle params) {
        // 处理事件回调
    }
}

