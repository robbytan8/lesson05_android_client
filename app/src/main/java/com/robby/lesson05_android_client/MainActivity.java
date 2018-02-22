package com.robby.lesson05_android_client;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.Toast;

import com.robby.lesson05_android_client.entity.StatusMessage;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.et_name)
    EditText txtName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    public void updateShowBasedOnWs(StatusMessage result) {
        Toast.makeText(this, result.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.btn_submit)
    public void submitDataAction() {
        if (!TextUtils.isEmpty(txtName.getText().toString().trim())) {
            UserHelloTask userLoginTask = new UserHelloTask(this);
            userLoginTask.execute(txtName.getText().toString());
        }
    }
}
