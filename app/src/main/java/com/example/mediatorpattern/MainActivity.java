package com.example.mediatorpattern;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Checkable;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText account,password;
    private Button ensure,cancel;
    private CheckBox auto,remember;
    private String strAccount,strPassword;
    private boolean isAuto,isRemember;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //获取账户文本框并设置改变监听
        account = (EditText)findViewById(R.id.account);
        account.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //回传数据
                strAccount = s.toString();

                //通知Account改变状态
                change();

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        //获取密码文本框并设置改变监听和出事状态
        password = (EditText)findViewById(R.id.password);
        password.setEnabled(false);
        password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //回传数据
                strPassword = s.toString();

                //通知Activity状态改变
                change();

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        //获取确定按钮并设置点击事件监听和初始化状态
        ensure = (Button)findViewById(R.id.ensure);
        ensure.setEnabled(false);
        ensure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this,"登陆成功",Toast.LENGTH_SHORT).show();
            }
        });
        //获取取消按钮并设置点击事件监听
        cancel = (Button)findViewById(R.id.cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this,"取消登录",Toast.LENGTH_SHORT).show();
            }
        });
        //获取自动登录复选框并设置勾选事件监听和初始化状态
        auto = (CheckBox)findViewById(R.id.auto);
        auto.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //回传数据
                isAuto = isChecked;

                //通知Activity改变状态
                change();
            }
        });
        //获取记住账号复选框并设置事件监听和初始化状态
        remember = (CheckBox)findViewById(R.id.remember);
        remember.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //回传数据
                isRemember = isChecked;
                //通知Activity改变状态
                change();
            }
        });

    }
    /**
     * 各控件状态改变后协调各控件状态显示
     */
    private void change(){
        if (TextUtils.isEmpty(strAccount)){
            password.setEnabled(false);
            remember.setEnabled(false);
            auto.setEnabled(false);
            ensure.setEnabled(false);
            cancel.setEnabled(false);
        }else if (!TextUtils.isEmpty(strAccount)&&TextUtils.isEmpty(strPassword)){
            password.setEnabled(true);
            auto.setEnabled(false);
            remember.setEnabled(true);
            ensure.setEnabled(false);
            cancel.setEnabled(true);
        }else if (!TextUtils.isEmpty(strAccount)&&TextUtils.isEmpty(strPassword)){
            password.setEnabled(true);
            auto.setEnabled(true);
            remember.setEnabled(true);
            ensure.setEnabled(true);
            cancel.setEnabled(true);
        }
    }
}
