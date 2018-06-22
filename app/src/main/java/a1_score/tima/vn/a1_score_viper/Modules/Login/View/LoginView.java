package a1_score.tima.vn.a1_score_viper.Modules.Login.View;

import android.content.DialogInterface;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import a1_score.tima.vn.a1_score_viper.Common.Commons;
import a1_score.tima.vn.a1_score_viper.Modules.Login.Interface.LoginInterface;
import a1_score.tima.vn.a1_score_viper.Modules.Login.Presenter.LoginPresenter;
import a1_score.tima.vn.a1_score_viper.R;
import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginView extends AppCompatActivity implements LoginInterface.View, View.OnClickListener {

    @BindView(R.id.llTopBanner)
    LinearLayout llTopBanner;
    @BindView(R.id.ibBack)
    ImageButton ibBack;
    @BindView(R.id.tvLogin)
    TextView tvLogin;
    @BindView(R.id.etUsername)
    EditText etUsername;
    @BindView(R.id.ivPhone)
    ImageView ivPhone;
    @BindView(R.id.tvPass)
    TextView tvPass;
    @BindView(R.id.etPassword)
    EditText etPassword;
    @BindView(R.id.ivPass)
    ImageView ivPass;
    @BindView(R.id.btLostPass)
    Button btLostPass;
    @BindView(R.id.rlLoginView)
    RelativeLayout rlLoginView;
    @BindView(R.id.btLogin)
    Button btLogin;
    @BindView(R.id.rootView)
    RelativeLayout rootView;
    @BindView(R.id.ivLogo)
    ImageView ivLogo;

    private LoginInterface.Presenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        getSupportActionBar().hide();
        changeStatusBarColor();
        btLogin.setTypeface(Commons.setFont(this, getResources().getString(R.string.font_segoe)));

        presenter = new LoginPresenter(this);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int newHeight = 4 * displayMetrics.heightPixels / 10;
        int marginLogo = 0;
        presenter.changeHeightBanner(newHeight, marginLogo);

        rootView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {

                Rect r = new Rect();
                rootView.getWindowVisibleDisplayFrame(r);
                int screenHeight = rootView.getRootView().getHeight();

                int keypadHeight = screenHeight - r.bottom;

                if (keypadHeight > screenHeight * 0.15) {
                    int newHeight = 3 * (screenHeight - keypadHeight) / 10;
                    int marginLogo = 60;
                    presenter.changeHeightBanner(newHeight, marginLogo);
                } else {
                    int newHeight = 4 * (screenHeight - keypadHeight) / 10;
                    int marginLogo = 00;
                    presenter.changeHeightBanner(newHeight, marginLogo);
                }
            }
        });

        ibBack.setOnClickListener(this);
        btLostPass.setOnClickListener(this);
        btLogin.setOnClickListener(this);

//        etUsername.setText("admin");
//        etPassword.setText("admin123");

        etUsername.setText("01656226909");
        etPassword.setText("123456");
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.createFolder();
    }

    @Override
    public void changeHeightBanner(int height, int margin) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        ViewGroup.LayoutParams params = llTopBanner.getLayoutParams();
        params.height = height;
        llTopBanner.setLayoutParams(params);

        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(margin == 0 ? displayMetrics.widthPixels / 3 : LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT);
        lp.setMargins(margin, margin, margin, margin);
        ivLogo.setLayoutParams(lp);
    }

    @Override
    public void usernameEmpty(String error) {
        etUsername.setError(error);
    }

    @Override
    public void passwordEmpty(String error) {
        etPassword.setError(error);
    }

    @Override
    public void loginFailed(String error) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setTitle(getString(R.string.dialog_title));
        alertDialog.setMessage(error);
        alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        alertDialog.show();
    }

    @Override
    public void onDestroy() {
        presenter.onDestroy();
        presenter = null;
        super.onDestroy();
    }

    /**
     * Making notification bar transparent
     */
    private void changeStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.main_light_blue));
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ibBack:
                this.finish();
                break;
            case R.id.btLostPass:
                presenter.goToForgotPassword();
                break;
            case R.id.btLogin:
                presenter.login(etUsername.getText().toString(), etPassword.getText().toString());
                break;
        }
    }
}
