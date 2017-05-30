package com.example.administrator.myapplication;
import com.baidu.speech.VoiceRecognitionService;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.content.ComponentName;
import android.content.Intent;
import android.speech.SpeechRecognizer;
import android.speech.RecognitionListener;
import android.widget.Button;
import android.view.View;
class MainActivity extends AppCompatActivity implements RecognitionListener {
    private SpeechRecognizer speechRecognizer;
    private Button okBtn;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        setContentView(R.layout.content_main);

        // 创建识别器
        speechRecognizer = SpeechRecognizer.createSpeechRecognizer(this, new ComponentName(this, VoiceRecognitionService.class));
        // 注册监听器
        speechRecognizer.setRecognitionListener(this);
    }

    public void onClick(View v) {
        switch(v.getId()){
            case R.id.okButton:{
                startASR();
                break;
            }

        }
    }
    void initView(){
        okBtn = (Button)findViewById(R.id.okButton);
    }
    // 开始识别
    void startASR() {
        Intent intent = new Intent();
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
        // 开始说话处理
    }
    public void onRmsChanged(float rmsdB) {
        // 音量变化处理
    }
    public void onBufferReceived(byte[] buffer) {
        // 录音数据传出处理
    }
    public void onEndOfSpeech() {
        // 说话结束处理
    }
    public void onError(int error) {
        // 出错处理
    }
    public void onResults(Bundle results) {
        // 最终结果处理
    }
    public void onPartialResults(Bundle partialResults) {
        // 临时结果处理
    }
    public void onEvent(int eventType, Bundle params) {
        // 处理事件回调
    }
}