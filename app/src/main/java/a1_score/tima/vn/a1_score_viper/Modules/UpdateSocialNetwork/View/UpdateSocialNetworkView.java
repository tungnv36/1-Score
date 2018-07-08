package a1_score.tima.vn.a1_score_viper.Modules.UpdateSocialNetwork.View;

import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import a1_score.tima.vn.a1_score_viper.Common.Commons;
import a1_score.tima.vn.a1_score_viper.Modules.UpdateSocialNetwork.Entity.SocialNetworkRequest;
import a1_score.tima.vn.a1_score_viper.R;
import butterknife.BindView;
import butterknife.ButterKnife;

public class UpdateSocialNetworkView extends AppCompatActivity {

    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.tvJobScore)
    TextView tvJobScore;
    @BindView(R.id.rlTitleInfo)
    RelativeLayout rlTitleInfo;
    @BindView(R.id.rvSocialNetwork)
    RecyclerView rvSocialNetwork;
    @BindView(R.id.llContent1)
    LinearLayout llContent1;
    @BindView(R.id.btUpdate)
    Button btUpdate;

    private List<SocialNetworkRequest> mSocialNetworkList;
    private SocialNetworkAdapter mSocialNetworkAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_social_network);
        ButterKnife.bind(this);
        setupActionBar();
        changeStatusBarColor();
        styleView();
        initSocialNetwork();
    }

    private void initSocialNetwork() {
        mSocialNetworkList = new ArrayList<>();
        mSocialNetworkList.add(new SocialNetworkRequest(1, R.mipmap.ic_facebook, "Facebook", true, R.color.color_facebook));
        mSocialNetworkList.add(new SocialNetworkRequest(2, R.mipmap.ic_zalo, "Zalo", false, R.color.color_zalo));
        mSocialNetworkList.add(new SocialNetworkRequest(3, R.mipmap.ic_instagram, "Instagram", false, R.color.color_instagram));
        mSocialNetworkList.add(new SocialNetworkRequest(4, R.mipmap.ic_linkedin, "Linkedin", false, R.color.color_linkedin));

        mSocialNetworkAdapter = new SocialNetworkAdapter(this, mSocialNetworkList);
        Commons.setVerticalRecyclerView(this, rvSocialNetwork);
        rvSocialNetwork.setAdapter(mSocialNetworkAdapter);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    private void setupActionBar() {
        getSupportActionBar().setTitle(getString(R.string.social_network));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getColor(R.color.main_dark_blue)));
        } else {
            getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#3499FF")));
        }
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

    private void styleView() {
        btUpdate.setTypeface(Commons.setFont(this, getResources().getString(R.string.font_segoe)), Typeface.BOLD);
    }

}
