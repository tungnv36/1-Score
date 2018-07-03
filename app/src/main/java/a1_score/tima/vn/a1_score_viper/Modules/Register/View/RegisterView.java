package a1_score.tima.vn.a1_score_viper.Modules.Register.View;

import android.app.ProgressDialog;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
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
import android.widget.Toast;

import a1_score.tima.vn.a1_score_viper.Common.Commons;
import a1_score.tima.vn.a1_score_viper.Modules.Login.View.LoginView;
import a1_score.tima.vn.a1_score_viper.Modules.Register.Interface.RegisterInterface;
import a1_score.tima.vn.a1_score_viper.Modules.Register.Presenter.RegisterPresenter;
import a1_score.tima.vn.a1_score_viper.R;
import butterknife.BindView;
import butterknife.ButterKnife;

public class RegisterView extends AppCompatActivity implements View.OnClickListener, RegisterInterface.View {

    @BindView(R.id.ivLogo)
    ImageView ivLogo;
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
    @BindView(R.id.tvRePass)
    TextView tvRePass;
    @BindView(R.id.etRePassword)
    EditText etRePassword;
    @BindView(R.id.tvFullName)
    TextView tvFullName;
    @BindView(R.id.etFullName)
    EditText etFullName;
    @BindView(R.id.ivFullName)
    ImageView ivFullName;
    @BindView(R.id.rlLoginView)
    RelativeLayout rlLoginView;
    @BindView(R.id.btRegister)
    Button btRegister;
    @BindView(R.id.rootView)
    RelativeLayout rootView;

    private RegisterInterface.Presenter presenter;

    private ProgressDialog progress;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        getSupportActionBar().hide();
        changeStatusBarColor();

        btRegister.setTypeface(Commons.setFont(this, "fonts/Segoe UI.ttf"));

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int newHeight = 4 * displayMetrics.heightPixels / 10;
        changeHeightBanner(newHeight);

        rootView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {

                Rect r = new Rect();
                rootView.getWindowVisibleDisplayFrame(r);
                int screenHeight = rootView.getRootView().getHeight();

                int keypadHeight = screenHeight - r.bottom;

                if (keypadHeight > screenHeight * 0.15) {//open
                    int newHeight = 1 * (screenHeight - keypadHeight) / 10;
                    changeHeightBanner(newHeight);
                } else {//close
                    int newHeight = 4 * (screenHeight - keypadHeight) / 10;
                    changeHeightBanner(newHeight);
                }
            }
        });

        presenter = new RegisterPresenter(this);

        ibBack.setOnClickListener(this);
        btRegister.setOnClickListener(this);
    }

    /**
     * Making notification bar transparent
     */
    private void changeStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.main_dark_blue));
        }
    }

    public void changeHeightBanner(int height) {
        ViewGroup.LayoutParams params = llTopBanner.getLayoutParams();
        params.height = height;
        llTopBanner.setLayoutParams(params);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ibBack:
                this.finish();
                break;
            case R.id.btRegister:
                progress = new ProgressDialog(RegisterView.this);
                progress.setTitle("Loading");
                progress.setMessage("Vui lòng chờ trong giây lát...");
                progress.setCancelable(false);
                progress.show();
                presenter.register(progress, etUsername.getText().toString(), etPassword.getText().toString(), etRePassword.getText().toString(), etFullName.getText().toString());
                break;
        }
    }

    @Override
    public void EdittextEmpty(ProgressDialog mProgress, int type, String error) {
        mProgress.dismiss();
        switch (type) {
            case 0:
                etUsername.setError(error);
                etUsername.requestFocus();
                break;
            case 1:
                etPassword.setError(error);
                etPassword.requestFocus();
                break;
            case 2:
                etRePassword.setError(error);
                etRePassword.requestFocus();
                break;
            case 3:
                etFullName.setError(error);
                etFullName.requestFocus();
                break;
        }
    }

    @Override
    public void registerFailed(ProgressDialog mProgress, String error) {
        mProgress.dismiss();
        Toast.makeText(this, error, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
        presenter = null;
    }
}
