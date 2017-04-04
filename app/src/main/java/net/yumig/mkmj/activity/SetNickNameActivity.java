package net.yumig.mkmj.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.currency.library.controller.BaseActivity;

import net.yumig.mkmj.R;
import net.yumig.mkmj.util.DialogUtil;

import butterknife.BindView;
import butterknife.OnClick;

public class SetNickNameActivity extends BaseActivity {


    @BindView(R.id.btn_back)
    ImageButton btnBack;
    @BindView(R.id.tv_titleName)
    TextView tvTitleName;
    @BindView(R.id.btn_save)
    Button btnSave;
    @BindView(R.id.et_phone)
    EditText etPhone;


    @Override
    public void setContentView() {
        setContentView(R.layout.activity_set_nick_name);
    }

    @Override
    protected void onViewCreatedFinish(Bundle saveInstanceState) {
        initView();

    }

    @Override
    protected String[] getRequestUrls() {
        return new String[0];
    }

    private void initView() {
        tvTitleName.setText("更改昵称");
        btnSave.setText("保存");
    }

    @OnClick({R.id.btn_back, R.id.btn_save})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_back:
                finish();
                break;
            case R.id.btn_save:
                DialogUtil.showToast(this, "保存", Toast.LENGTH_SHORT);
                break;
        }
    }
}
