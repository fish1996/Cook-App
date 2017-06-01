package com.baidu.android.voicedemo;


import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.speech.RecognitionListener;
import android.speech.SpeechRecognizer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;
import com.baidu.speech.VoiceRecognitionService;
import com.baidu.speech.recognizerdemo.R;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class ActivityOffline extends Activity {

    private TextView txtLog;
    private final String DESC_TEXT = "" +
            "�������﷨ʶ��(�״�ʹ����Ҫ������Ȩ)\n" +
            "����޷�����ʹ������:\n" +
            " 1. �Ƿ���AndroidManifest.xml������APP_ID\n" +
            " 2. �Ƿ��ڿ���ƽ̨��ӦӦ�ð��˰���\n" +
            "\n" +
            "�����ʼ�������˵(���Ը����﷨���ж�������˵��):\n" +
            " 1. ��绰������(����)\n" +
            " 2. ��绰������(����)\n" +
            " 3. �򿪼�����(����)\n" +
            " 4. ����������ô��(��Ҫ����)\n" +
            " ..." +
            "\n";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sdk2_api);
        findViewById(R.id.setting).setVisibility(View.GONE);
        findViewById(R.id.txtResult).setVisibility(View.GONE);

        txtLog = (TextView) findViewById(R.id.txtLog);

        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent("com.baidu.action.RECOGNIZE_SPEECH");
                intent.putExtra("grammar", "asset:///baidu_speech_grammar.bsg"); // �������ߵ���Ȩ�ļ�(����ģ����Ҫ��Ȩ), ���﷨�������Զ������幤������, ����http://yuyin.baidu.com/asr#m5
                //intent.putExtra("slot-data", your slots); // ����grammar����Ҫ���ǵĴ���,����ϵ����
                startActivityForResult(intent, 1);

                txtLog.setText(DESC_TEXT);
            }
        });

        txtLog.setText(DESC_TEXT);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            Bundle results = data.getExtras();
            ArrayList<String> results_recognition = results.getStringArrayList("results_recognition");
            txtLog.append("ʶ����(������ʽ): " + results_recognition + "\n");
        }
    }
}
