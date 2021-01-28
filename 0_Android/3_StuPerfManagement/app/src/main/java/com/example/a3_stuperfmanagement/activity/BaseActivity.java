package com.example.a3_stuperfmanagement.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.a3_stuperfmanagement.LoginActivity;

//所有活动的父类，动态注册广播接收器
public class BaseActivity extends AppCompatActivity {
    private ForceOffLineReceiver receiver;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityController.addActivity(this);
    }

    //注册FORCE_OFFLINE，保证只有栈顶活动才接受到强制下线广播
    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.example.a3_stuperfmanagement.FORCE_OFFLINE");
        receiver = new ForceOffLineReceiver();
        registerReceiver(receiver, intentFilter);
    }

    //取消注册FORCE_OFFLINE
    @Override
    protected void onPause() {
        super.onPause();
        if(receiver != null){
            unregisterReceiver(receiver);
            receiver = null;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityController.removeActivity(this);
    }

    //内部类广播接收器
    class ForceOffLineReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(final Context context, Intent intent) {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setMessage("您已退出登录");
            builder.setCancelable(false);//设置用户不可取消
            //设置确定按钮及其响应函数
            builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    ActivityController.finishAll();//销毁所有活动
                    Intent intent = new Intent(context, LoginActivity.class);//跳转至Login
                    context.startActivity(intent);
                }
            });
            builder.show();
        }
    }
}
