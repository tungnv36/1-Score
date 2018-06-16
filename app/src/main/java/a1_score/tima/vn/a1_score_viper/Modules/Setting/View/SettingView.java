package a1_score.tima.vn.a1_score_viper.Modules.Setting.View;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import a1_score.tima.vn.a1_score_viper.Common.Commons;
import a1_score.tima.vn.a1_score_viper.Modules.Setting.Entity.SettingEntity;
import a1_score.tima.vn.a1_score_viper.Modules.Setting.Interface.SettingInterface;
import a1_score.tima.vn.a1_score_viper.Modules.Setting.Presenter.SettingPresenter;
import a1_score.tima.vn.a1_score_viper.R;
import butterknife.BindView;
import butterknife.ButterKnife;
import me.tankery.lib.circularseekbar.CircularSeekBar;

public class SettingView extends AppCompatActivity implements SettingInterface.View, View.OnClickListener {

    @BindView(R.id.ibBack)
    ImageButton ibBack;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.sbLevel)
    CircularSeekBar sbLevel;
    @BindView(R.id.ivLogo)
    ImageView ivLogo;
    @BindView(R.id.tvCurrentLevel)
    TextView tvCurrentLevel;
    @BindView(R.id.tvNextLevel)
    TextView tvNextLevel;
    @BindView(R.id.tvName)
    TextView tvName;
    @BindView(R.id.rvMenu)
    RecyclerView rvMenu;

    private List<SettingEntity> arrSetting = new ArrayList<>();
    private SettingAdapter settingAdapter = null;

    private SettingInterface.Presenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        ButterKnife.bind(this);
        getSupportActionBar().hide();
        changeStatusBarColor();

        presenter = new SettingPresenter(this);
        initMenu();
        ibBack.setOnClickListener(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
        presenter = null;
    }

    void initMenu() {
        arrSetting.add(new SettingEntity(R.mipmap.ic_change_pass, getString(R.string.change_pass)));
        arrSetting.add(new SettingEntity(R.mipmap.ic_change_phone, getString(R.string.change_phone)));
        arrSetting.add(new SettingEntity(R.mipmap.ic_logout, getString(R.string.logout)));

        settingAdapter = new SettingAdapter(this, presenter, arrSetting);
        Commons.setVerticalRecyclerView(this, rvMenu);
        rvMenu.setAdapter(settingAdapter);
    }

    /**
     * Making notification bar transparent
     */
    private void changeStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.text_gray));
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ibBack:
                finish();
                break;
        }
    }
}
