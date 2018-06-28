package a1_score.tima.vn.a1_score_viper.Modules.Main.View;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import a1_score.tima.vn.a1_score_viper.Common.Commons;
import a1_score.tima.vn.a1_score_viper.Common.DB.SQliteDatabase;
import a1_score.tima.vn.a1_score_viper.Common.DialogUtils;
import a1_score.tima.vn.a1_score_viper.Modules.Main.Interface.MainInterface;
import a1_score.tima.vn.a1_score_viper.Modules.Main.Presenter.MainPresenter;
import a1_score.tima.vn.a1_score_viper.R;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainView extends AppCompatActivity implements View.OnClickListener, MainInterface.View {

    @BindView(R.id.btLogin)
    Button btLogin;
    @BindView(R.id.btRegister)
    Button btRegister;

    private MainInterface.Presenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        if(Commons.isTablet(this)) {
            DialogUtils.showAlertDialog(this, getString(R.string.dialog_title), getString(R.string.tablet_info), new DialogUtils.OnClickListener() {
                @Override
                public void onClickSuccess() {
                    finish();
                }
            });
        }

        presenter = new MainPresenter(this);

        //style button
        btLogin.setTypeface(Commons.setFont(this, getResources().getString(R.string.font_segoe)));
        btRegister.setTypeface(Commons.setFont(this, getResources().getString(R.string.font_segoe)));

        btLogin.setOnClickListener(this);
        btRegister.setOnClickListener(this);

        getSupportActionBar().hide();
    }

    @Override
    protected void onResume() {
        super.onResume();
        SQliteDatabase.getInstance(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btLogin:
                presenter.lauchLoginScreen();
                break;
            case R.id.btRegister:
                presenter.lauchRegisterScreen();
                break;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
