package a1_score.tima.vn.a1_score_viper.Modules.LoanRequest.View;

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
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import a1_score.tima.vn.a1_score_viper.Common.Commons;
import a1_score.tima.vn.a1_score_viper.Modules.LoanRequest.Entity.LoanRequest;
import a1_score.tima.vn.a1_score_viper.Modules.LoanRequest.Interface.LoanRequestInterface;
import a1_score.tima.vn.a1_score_viper.Modules.LoanRequest.Presenter.LoanRequestPresenter;
import a1_score.tima.vn.a1_score_viper.R;
import butterknife.BindView;
import butterknife.ButterKnife;

public class LoanRequestView extends AppCompatActivity implements LoanRequestInterface.View, View.OnClickListener {

    @BindView(R.id.ibMenu)
    ImageButton ibMenu;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.rlBar)
    RelativeLayout rlBar;
    @BindView(R.id.pbLevel)
    ProgressBar pbLevel;
    @BindView(R.id.tvLevelTitle)
    TextView tvLevelTitle;
    @BindView(R.id.tvLevel)
    TextView tvLevel;
    @BindView(R.id.tvScoreTitle)
    TextView tvScoreTitle;
    @BindView(R.id.tvScore)
    TextView tvScore;
    @BindView(R.id.tvName)
    TextView tvName;
    @BindView(R.id.llbanner)
    LinearLayout llbanner;
    @BindView(R.id.rlTop)
    RelativeLayout rlTop;
    @BindView(R.id.rvLoanList)
    RecyclerView rvLoanList;
    @BindView(R.id.ivLogo)
    ImageView ivLogo;

    private LoanRequestInterface.Presenter mPresenter;

    private int mScoreOfLevel = 80;
    private int mStartScore = 0;

    private List<LoanRequest> mLoanList;
    private LoanRequestAdapter mLoanRequestAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loan_request);
        ButterKnife.bind(this);
        getSupportActionBar().hide();
        changeStatusBarColor();
        changeHeightBanner();
        styleView();

        mPresenter = new LoanRequestPresenter(this);

        initData();

        ibMenu.setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.initAnimationLogo(ivLogo);
        mPresenter.setupAnimationProgress(pbLevel, mStartScore, mScoreOfLevel);
    }

    private void initData() {
        mLoanList = new ArrayList<>();
        mLoanList.add(new LoanRequest(
                R.mipmap.ic_micro_loan,
                "Micro loan",
                "3.000.000 VNĐ",
                "300.000 VNĐ",
                "80.000 VNĐ",
                10,
                10,
                "Level 5",
                false
        ));
        mLoanList.add(new LoanRequest(
                R.mipmap.ic_medium_loan,
                "Micro loan",
                "5.000.000 VNĐ",
                "500.000 VNĐ",
                "120.000 VNĐ",
                8,
                10,
                "Level 15",
                false
        ));
        mLoanList.add(new LoanRequest(
                R.mipmap.ic_big_loan,
                "Micro loan",
                "10.000.000 VNĐ",
                "800.000 VNĐ",
                "200.000 VNĐ",
                2,
                12,
                "Level 20",
                false
        ));

        mLoanRequestAdapter = new LoanRequestAdapter(this, mPresenter, mLoanList);
        Commons.setVerticalRecyclerView(this, rvLoanList);
        rvLoanList.setAdapter(mLoanRequestAdapter);
    }

    private void styleView() {
        tvName.setTypeface(Commons.setFont(this, getResources().getString(R.string.font_segoe)), Typeface.BOLD);
    }

    public void changeHeightBanner() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        ViewGroup.LayoutParams params = rlTop.getLayoutParams();
        params.height = (int) (displayMetrics.heightPixels / 4.2);
        rlTop.setLayoutParams(params);
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
    public void openOrCloseInfo(RelativeLayout view, Button button, boolean isOpen, int position) {
        mLoanList.get(position).setOpen(!mLoanList.get(position).isOpen());
        mLoanRequestAdapter.notifyDataSetChanged();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ibMenu:
                finish();
                break;
        }
    }
}
