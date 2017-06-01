package com.baidu.android.voicedemo;

import android.app.Activity;
import android.os.Bundle;
import android.util.AndroidRuntimeException;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import com.baidu.speech.EventListener;
import com.baidu.speech.EventManager;
import com.baidu.speech.EventManagerFactory;
import com.baidu.speech.recognizerdemo.R;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class ActivityWakeUp extends Activity {
    private static final String TAG = "ActivityWakeUp";
    private TextView txtResult;
    private TextView txtLog;

    private final String DESC_TEXT = "" +
            "�����Ѿ�����(�״�ʹ����Ҫ������Ȩ)\n" +
            "����޷�����ʹ������:\n" +
            " 1. �Ƿ���AndroidManifest.xml������APP_ID\n" +
            " 2. �Ƿ��ڿ���ƽ̨��ӦӦ�ð��˰���\n" +
            "\n";

    private EventManager mWpEventManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sdk2_api);

        txtResult = (TextView) findViewById(R.id.txtResult);
        txtLog = (TextView) findViewById(R.id.txtLog);
        findViewById(R.id.btn).setVisibility(View.GONE);
        findViewById(R.id.setting).setVisibility(View.GONE);

        txtResult.setText("��˵���Ѵ�:  С����� �� �ٶ�һ��");
    }

    @Override
    protected void onResume() {
        super.onResume();

        // ���ѹ��ܴ򿪲���
        // 1) ���������¼�������
        mWpEventManager = EventManagerFactory.create(ActivityWakeUp.this, "wp");

        // 2) ע�ỽ���¼�������
        mWpEventManager.registerListener(new EventListener() {
            @Override
            public void onEvent(String name, String params, byte[] data, int offset, int length) {
                Log.d(TAG, String.format("event: name=%s, params=%s", name, params));
                try {
                    JSONObject json = new JSONObject(params);
                    if ("wp.data".equals(name)) { // ÿ�λ��ѳɹ�, ����ص�name=wp.data��ʱ��, ������Ļ��Ѵ���params��word�ֶ�
                        String word = json.getString("word");
                        txtLog.append("���ѳɹ�, ���Ѵ�: " + word + "\r\n");
                    } else if ("wp.exit".equals(name)) {
                        txtLog.append("�����Ѿ�ֹͣ: " + params + "\r\n");
                    }
                } catch (JSONException e) {
                    throw new AndroidRuntimeException(e);
                }
            }
        });

        // 3) ֪ͨ���ѹ�����, �������ѹ���
        HashMap params = new HashMap();
        params.put("kws-file", "assets:///WakeUp.bin"); // ���û�����Դ, ������Դ�뵽 http://yuyin.baidu.com/wake#m4 �������͵���
        mWpEventManager.send("wp.start", new JSONObject(params).toString(), null, 0, 0);

        txtLog.setText(DESC_TEXT);
    }

    @Override
    protected void onPause() {
        super.onPause();
        // ֹͣ���Ѽ���
        mWpEventManager.send("wp.stop", null, null, 0, 0);
    }
}
