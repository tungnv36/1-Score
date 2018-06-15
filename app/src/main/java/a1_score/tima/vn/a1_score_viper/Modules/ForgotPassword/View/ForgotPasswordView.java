package a1_score.tima.vn.a1_score_viper.Modules.ForgotPassword.View;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import a1_score.tima.vn.a1_score_viper.Modules.ForgotPassword.Interface.ForgotPasswordInterface;
import a1_score.tima.vn.a1_score_viper.Modules.ForgotPassword.Presenter.ForgotPasswordPresenter;
import a1_score.tima.vn.a1_score_viper.R;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ForgotPasswordView extends AppCompatActivity implements View.OnClickListener, ForgotPasswordInterface.View {

    @BindView(R.id.etPhone)
    EditText etPhone;
    @BindView(R.id.tvDescription)
    TextView tvDescription;
    @BindView(R.id.ivLogo)
    ImageView ivLogo;
    @BindView(R.id.btConfirm)
    Button btConfirm;

    private ForgotPasswordInterface.Presenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        ButterKnife.bind(this);
        getSupportActionBar().hide();

        presenter = new ForgotPasswordPresenter(this);
        btConfirm.setOnClickListener(this);

        etPhone.setText("01656226909");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btConfirm:
                presenter.forgotPassword(etPhone.getText().toString());
                break;
        }
    }

    @Override
    public void forgotPasswordSuccess(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

    @Override
    public void forgotPasswordFailed(String err) {
        Toast.makeText(this, err, Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }
}
