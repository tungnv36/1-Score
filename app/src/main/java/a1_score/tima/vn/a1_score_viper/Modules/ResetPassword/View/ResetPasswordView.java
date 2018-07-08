package a1_score.tima.vn.a1_score_viper.Modules.ResetPassword.View;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import a1_score.tima.vn.a1_score_viper.Modules.ResetPassword.Interface.ResetPasswordInterface;
import a1_score.tima.vn.a1_score_viper.Modules.ResetPassword.Presenter.ResetPasswordPresenter;
import a1_score.tima.vn.a1_score_viper.R;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ResetPasswordView extends AppCompatActivity implements ResetPasswordInterface.View, View.OnClickListener {

    @BindView(R.id.ivLogo)
    ImageView ivLogo;
    @BindView(R.id.llTopBanner)
    LinearLayout llTopBanner;
    @BindView(R.id.ibBack)
    ImageButton ibBack;
    @BindView(R.id.tvPass)
    TextView tvPass;
    @BindView(R.id.etPassword)
    EditText etPassword;
    @BindView(R.id.ivPass)
    ImageView ivPass;
    @BindView(R.id.tvRePass)
    TextView tvRePass;
    @BindView(R.id.etRePassword)
    EditText etRePassword;
    @BindView(R.id.rlLoginView)
    RelativeLayout rlLoginView;
    @BindView(R.id.btChangePass)
    Button btChangePass;
    @BindView(R.id.rootView)
    RelativeLayout rootView;

    private ResetPasswordInterface.Presenter mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password_forgot);
        ButterKnife.bind(this);
        getSupportActionBar().hide();

        rootView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {

                Rect r = new Rect();
                rootView.getWindowVisibleDisplayFrame(r);
                int screenHeight = rootView.getRootView().getHeight();

                int keypadHeight = screenHeight - r.bottom;

                if (keypadHeight > screenHeight * 0.15) {//open
                    int newHeight = 4 * (screenHeight - keypadHeight) / 10;
                    changeHeightBanner(newHeight);
                } else {//close
                    int newHeight = 4 * (screenHeight - keypadHeight) / 10;
                    changeHeightBanner(newHeight);
                }
            }
        });

        mPresenter = new ResetPasswordPresenter(this);
        btChangePass.setOnClickListener(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.onDestroy();
        mPresenter = null;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btChangePass:
                mPresenter.changePass(getIntent().getStringExtra("PHONE_NUMBER"), etPassword.getText().toString(), etRePassword.getText().toString(), getIntent().getStringExtra("TOKEN"));
                break;
        }
    }

    public void changeHeightBanner(int height) {
        ViewGroup.LayoutParams params = llTopBanner.getLayoutParams();
        params.height = height;
        llTopBanner.setLayoutParams(params);
    }

    @Override
    public void changePassSuccess(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

    @Override
    public void changePassFailed(String err) {
        Toast.makeText(this, err, Toast.LENGTH_LONG).show();
    }
}
