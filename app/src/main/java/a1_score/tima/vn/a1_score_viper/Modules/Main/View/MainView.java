package a1_score.tima.vn.a1_score_viper.Modules.Main.View;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

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

    private MainInterface.Presenter mPresenter;

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

                @Override
                public void onClickSuccess2() {

                }
            });
        }

        mPresenter = new MainPresenter(this);

        //style button
        btLogin.setTypeface(Commons.setFont(this, getResources().getString(R.string.font_segoe)));
        btRegister.setTypeface(Commons.setFont(this, getResources().getString(R.string.font_segoe)));

        btLogin.setOnClickListener(this);
        btRegister.setOnClickListener(this);

        getSupportActionBar().hide();


//        try {
//            PackageInfo info = getPackageManager().getPackageInfo(
//                    "a1_score.tima.vn.a1_score_viper",
//                    PackageManager.GET_SIGNATURES);
//            for (Signature signature : info.signatures) {
//                MessageDigest md = MessageDigest.getInstance("SHA");
//                md.update(signature.toByteArray());
//                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
//            }
//        } catch (PackageManager.NameNotFoundException e) {
//
//        } catch (NoSuchAlgorithmException e) {
//
//        }
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
                mPresenter.lauchLoginScreen();
                break;
            case R.id.btRegister:
                mPresenter.lauchRegisterScreen();
                break;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
