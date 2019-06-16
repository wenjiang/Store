package com.example.wenbiaozheng.dataapplication;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DataStoreModule dataModule = new DataStoreModule();
        DataModule userModule = dataModule.getModule(UserInfo.class);
        userModule.get("0", new IModuleCallback() {

            @Override
            public void onSuccess(Object data) {

            }
        });
    }
}
