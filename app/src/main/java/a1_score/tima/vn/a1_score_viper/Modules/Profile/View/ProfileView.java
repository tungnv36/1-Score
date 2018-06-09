package a1_score.tima.vn.a1_score_viper.Modules.Profile.View;

import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import a1_score.tima.vn.a1_score_viper.Common.Commons;
import a1_score.tima.vn.a1_score_viper.Modules.Profile.Entity.MenuEntity;
import a1_score.tima.vn.a1_score_viper.Modules.Profile.Interface.ProfileInterface;
import a1_score.tima.vn.a1_score_viper.Modules.Profile.Presenter.ProfilePresenter;
import a1_score.tima.vn.a1_score_viper.R;
import butterknife.BindView;
import butterknife.ButterKnife;
import me.tankery.lib.circularseekbar.CircularSeekBar;

public class ProfileView extends AppCompatActivity implements View.OnClickListener, ProfileInterface.View {

    @BindView(R.id.ibMenu)
    ImageButton ibMenu;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.tvScore)
    TextView tvScore;
    @BindView(R.id.rlBar)
    RelativeLayout rlBar;
    @BindView(R.id.rlTop)
    LinearLayout rlTop;
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
    @BindView(R.id.rootView)
    RelativeLayout rootView;

    private ProfileInterface.Presenter presenter;
    private int scoreOfLevel = 80;
    private int startScore = 0;

    private List<MenuEntity> lstMenu;
    private MenuProfileAdapter menuProfileAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        ButterKnife.bind(this);
        getSupportActionBar().hide();
        changeStatusBarColor();
        changeHeightBanner();
        Commons.setupSizeLogo(this, ivLogo, sbLevel);
        styleView();

        presenter = new ProfilePresenter(this);

        ibMenu.setOnClickListener(this);

        initMenu();
    }

    private void initMenu() {
        lstMenu = new ArrayList<>();
        lstMenu.add(new MenuEntity(R.mipmap.ic_info, getString(R.string.menu_info), getString(R.string.info_expired), 0, 80, false));
        lstMenu.add(new MenuEntity(R.mipmap.ic_job, getString(R.string.menu_job), getString(R.string.info_expired), 0, 20, true));
        lstMenu.add(new MenuEntity(R.mipmap.ic_family, getString(R.string.menu_family), getString(R.string.info_expired), 0, 100, false));
        lstMenu.add(new MenuEntity(R.mipmap.ic_social_network, getString(R.string.menu_social_network), getString(R.string.info_expired), 0, 50, false));
        lstMenu.add(new MenuEntity(R.mipmap.ic_paper, getString(R.string.menu_paper), getString(R.string.info_expired), 0, 40, false));

        menuProfileAdapter = new MenuProfileAdapter(this, presenter, lstMenu);
        Commons.setVerticalRecyclerView(this, rvMenu);
        rvMenu.setAdapter(menuProfileAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.initAnimationLogo(ivLogo);
        presenter.setupAnimationSeekBar(sbLevel, startScore, scoreOfLevel);
    }

    private void styleView() {
        tvName.setTypeface(Commons.setFont(this, getResources().getString(R.string.font_segoe)), Typeface.BOLD);
    }

    public void changeHeightBanner() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        ViewGroup.LayoutParams params = rlTop.getLayoutParams();
        params.height = (int) (3.8 * displayMetrics.heightPixels / 10);
        rlTop.setLayoutParams(params);
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
            case R.id.ibMenu:
                finish();
                break;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
