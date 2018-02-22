package com.robby.lesson05_android_client;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.Toast;

import com.robby.lesson05_android_client.entity.UserData;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.et_username)
    EditText txtUsername;
    @BindView(R.id.et_password)
    EditText txtPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_login)
    public void loginAction() {
        if (!TextUtils.isEmpty(txtUsername.getText().toString().trim())
                && !TextUtils.isEmpty(txtPassword.getText().toString().trim())) {
            UserLoginTask userLoginTask = new UserLoginTask(this);
            userLoginTask.execute(txtUsername.getText().toString(), txtPassword.getText().toString());
        } else {
            Toast.makeText(this, "Please fill username and password", Toast.LENGTH_SHORT).show();
        }
    }

    public void openDataActivity(UserData userData) {
        if (null != userData && userData.getStatus() == 1 && null != userData.getUser()) {
            Toast.makeText(this, userData.getMessage(), Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(LoginActivity.this, DataActivity.class);
            this.startActivity(intent);
            this.finish();
        } else {
            Toast.makeText(this, this.getResources().getString(R.string.user_not_approved),
                    Toast.LENGTH_SHORT).show();
        }
    }
}
