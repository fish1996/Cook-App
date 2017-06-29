package com.baidu.android.login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.baidu.speech.recognizerdemo.R;

public class User extends Activity {
//    private Button mReturnButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.classify_main);
//        mReturnButton = (Button)findViewById(R.id.returnback);
    }
//    public void back_to_login(View view) {
//        //setContentView(R.layout.login);
//        Intent intent3 = new Intent(User.this,Login.class) ;
//        startActivity(intent3);
//        finish();
//    }
}
