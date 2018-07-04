package a1_score.tima.vn.a1_score_viper.Modules.Otp.View;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import a1_score.tima.vn.a1_score_viper.Modules.Otp.Interface.OtpInterface;
import a1_score.tima.vn.a1_score_viper.Modules.Otp.Presenter.OtpPresenter;
import a1_score.tima.vn.a1_score_viper.R;
import butterknife.BindView;
import butterknife.ButterKnife;

public class OtpView extends AppCompatActivity implements View.OnClickListener, OtpInterface.View {

    @BindView(R.id.etFirst)
    EditText etFirst;
    @BindView(R.id.etSecond)
    EditText etSecond;
    @BindView(R.id.etThird)
    EditText etThird;
    @BindView(R.id.etFourth)
    EditText etFourth;
    @BindView(R.id.llOtp)
    LinearLayout llOtp;
    @BindView(R.id.tvDescription)
    TextView tvDescription;
    @BindView(R.id.ivLogo)
    ImageView ivLogo;
    @BindView(R.id.btConfirm)
    Button btConfirm;
    @BindView(R.id.rootView)
    RelativeLayout rootView;
    @BindView(R.id.etFifth)
    EditText etFifth;
    @BindView(R.id.etSixth)
    EditText etSixth;

    private OtpInterface.Presenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);
        ButterKnife.bind(this);
        getSupportActionBar().hide();
        changeStatusBarColor();

        tvDescription.setText(getIntent().getStringExtra("MSG"));

        presenter = new OtpPresenter(this);

        etFirst.post(new Runnable() {
            @Override
            public void run() {
                etFirst.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if (count == 1) {
                            etSecond.requestFocus();
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
            }
        });

        etSecond.post(new Runnable() {
            @Override
            public void run() {
                etSecond.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if (count == 1) {
                            etThird.requestFocus();
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
            }
        });

        etThird.post(new Runnable() {
            @Override
            public void run() {
                etThird.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if (count == 1) {
                            etFourth.requestFocus();
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
            }
        });

        etFourth.post(new Runnable() {
            @Override
            public void run() {
                etFourth.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if (count == 1) {
                            etFifth.requestFocus();
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
            }
        });

        etFifth.post(new Runnable() {
            @Override
            public void run() {
                etFifth.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if (count == 1) {
                            etSixth.requestFocus();
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
            }
        });

        etSixth.post(new Runnable() {
            @Override
            public void run() {
                etSixth.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
            }
        });

        btConfirm.setOnClickListener(this);
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btConfirm:
                String otp = new StringBuilder()
                        .append(etFirst.getText().toString())
                        .append(etSecond.getText().toString())
                        .append(etThird.getText().toString())
                        .append(etFourth.getText().toString())
                        .append(etFifth.getText().toString())
                        .append(etSixth.getText().toString()).toString();
                presenter.compareOtp(getIntent().getStringExtra("PHONE_NUMBER"), getIntent().getStringExtra("ACTION"), otp, getIntent().getIntExtra("TYPE", 0));
                break;
        }
    }

    @Override
    public void compareOtpSuccess(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

    @Override
    public void compareOtpFailed(String err) {
        Toast.makeText(this, err, Toast.LENGTH_LONG).show();
    }
}
