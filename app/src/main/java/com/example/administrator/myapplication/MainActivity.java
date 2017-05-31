package com.example.administrator.myapplication;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SpeechRecognizerTool.ResultsCallback {

    private Button mStartSpeechButton;
    private TextView mTextView;

    private SpeechRecognizerTool mSpeechRecognizerTool = new SpeechRecognizerTool(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);

        mTextView = (TextView) findViewById(R.id.speechTextView);

        mStartSpeechButton = (Button) findViewById(R.id.startSpeechButton);
        mStartSpeechButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();
                switch (action) {
                    case MotionEvent.ACTION_DOWN:
                        mSpeechRecognizerTool.startASR(MainActivity.this);
                        break;
                    case MotionEvent.ACTION_UP:
                        mSpeechRecognizerTool.stopASR();
                        break;
                    default:
                        return false;
                }

                return true;
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        mSpeechRecognizerTool.createTool();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mSpeechRecognizerTool.destroyTool();
    }

    @Override
    public void onResults(String result) {
        final String finalResult = result;
        MainActivity.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mTextView.setText(finalResult);
            }
        });
    }
}