package a1_score.tima.vn.a1_score_viper.Modules.ChangePhone.View;

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

import a1_score.tima.vn.a1_score_viper.Modules.ChangePhone.Interface.ChangePhoneInterface;
import a1_score.tima.vn.a1_score_viper.Modules.ChangePhone.Presenter.ChangePhonePresenter;
import a1_score.tima.vn.a1_score_viper.R;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ChangePhoneView extends AppCompatActivity implements ChangePhoneInterface.View, View.OnClickListener {

    @BindView(R.id.ivLogo)
    ImageView ivLogo;
    @BindView(R.id.llTopBanner)
    LinearLayout llTopBanner;
    @BindView(R.id.ibBack)
    ImageButton ibBack;
    @BindView(R.id.tvOldPhone)
    TextView tvOldPhone;
    @BindView(R.id.etOldPhone)
    EditText etOldPhone;
    @BindView(R.id.ivOldPhone)
    ImageView ivOldPhone;
    @BindView(R.id.tvNewPhone)
    TextView tvNewPhone;
    @BindView(R.id.etNewPhone)
    EditText etNewPhone;
    @BindView(R.id.ivNewPhone)
    ImageView ivNewPhone;
    @BindView(R.id.tvPass)
    TextView tvPass;
    @BindView(R.id.etPass)
    EditText etPass;
    @BindView(R.id.ivPass)
    ImageView ivPass;
    @BindView(R.id.rlLoginView)
    RelativeLayout rlLoginView;
    @BindView(R.id.btChangePhone)
    Button btChangePhone;
    @BindView(R.id.rootView)
    RelativeLayout rootView;

    private ChangePhoneInterface.Presenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_phone);
        ButterKnife.bind(this);
        getSupportActionBar().hide();

        presenter = new ChangePhonePresenter(this);

        rootView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {

                Rect r = new Rect();
                rootView.getWindowVisibleDisplayFrame(r);
                int screenHeight = rootView.getRootView().getHeight();

                int keypadHeight = screenHeight - r.bottom;

                if (keypadHeight > screenHeight * 0.15) {//open
                    int newHeight = 2 * (screenHeight - keypadHeight) / 10;
                    changeHeightBanner(newHeight);
                } else {//close
                    int newHeight = 4 * (screenHeight - keypadHeight) / 10;
                    changeHeightBanner(newHeight);
                }
            }
        });

        ibBack.setOnClickListener(this);
        btChangePhone.setOnClickListener(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
        presenter = null;
    }

    public void changeHeightBanner(int height) {
        ViewGroup.LayoutParams params = llTopBanner.getLayoutParams();
        params.height = height;
        llTopBanner.setLayoutParams(params);
    }

    @Override
    public void changePhoneSuccess(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

    @Override
    public void changePhoneFailed(String err) {
        Toast.makeText(this, err, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ibBack:
                this.finish();
                break;
            case R.id.btChangePhone:
                presenter.changePhone(etOldPhone.getText().toString(), etNewPhone.getText().toString(), etPass.getText().toString());
                break;
        }
    }
}
