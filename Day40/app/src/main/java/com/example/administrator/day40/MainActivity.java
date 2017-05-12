package com.example.administrator.day40;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.umeng.comm.core.CommunitySDK;
import com.umeng.comm.core.impl.CommunityFactory;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        Button shequ = (Button) findViewById(R.id.shequ);
        // 获取CommunitySDK实例, 参数1为Context类型
        final CommunitySDK mCommSDK = CommunityFactory.getCommSDK(this);
        final Context context = this;
        shequ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 打开微社区的接口, 参数为Context类型
                mCommSDK.openCommunity(context);
            }
        });

    }
}
