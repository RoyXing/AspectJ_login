package com.login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.login.annotation.ClickBehavior;
import com.login.annotation.LoginCheck;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "roy";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    //登录点击事件(做行为统计)
    @ClickBehavior("登录行为统计")
    public void login(View view) {
        Log.e(TAG, "模拟接口请求。。。验证通过，登录成功");

    }

    //我的专区点击事件(做行为统计)
    @ClickBehavior("我的专区行为统计")
    @LoginCheck
    public void area(View view) {
        Log.e(TAG, "开始跳转到--->我的专区");
        startActivity(new Intent(this,OtherActivity.class));
    }

    //我的优惠券点击事件(做行为统计)
    @ClickBehavior("我的优惠券行为统计")
    @LoginCheck
    public void coupon(View view) {
        Log.e(TAG, "开始跳转到--->我的优惠券");
        startActivity(new Intent(this,OtherActivity.class));
    }

    //我的积分点击事件(做行为统计)
    @ClickBehavior("我的积分行为统计")
    @LoginCheck
    public void score(View view) {
        Log.e(TAG, "开始跳转到--->我的积分");
        startActivity(new Intent(this,OtherActivity.class));
    }
}
