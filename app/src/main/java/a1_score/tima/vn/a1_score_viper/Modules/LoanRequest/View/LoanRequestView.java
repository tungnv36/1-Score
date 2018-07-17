package a1_score.tima.vn.a1_score_viper.Modules.LoanRequest.View;

import android.graphics.Bitmap;
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
import a1_score.tima.vn.a1_score_viper.Common.DialogUtils;
import a1_score.tima.vn.a1_score_viper.Modules.LoanRequest.Entity.LoanEntity;
import a1_score.tima.vn.a1_score_viper.Modules.LoanRequest.Entity.LoanRequest;
import a1_score.tima.vn.a1_score_viper.Modules.LoanRequest.Entity.LoanResponse;
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

    public static int sLevel = 1;

    private List<LoanResponse.LoanCreditPackagesEntity> mLoanList = new ArrayList<>();
    private List<LoanEntity> mLoanEntities = new ArrayList<>();
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

        ibMenu.setOnClickListener(this);

        new Thread(new Runnable() {
            @Override
            public void run() {
//                avi.show();
                mPresenter.initAvatar();
            }
        }).start();
        mPresenter.initData();
    }

    @Override
    protected void onResume() {
        super.onResume();
//        mPresenter.initAnimationLogo(ivLogo);
//        mPresenter.setupAnimationProgress(pbLevel, mStartScore, mScoreOfLevel);
        mPresenter.getLoanCreditPackage();
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
    public void initAvatar(final Bitmap bmp) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ivLogo.setImageBitmap(bmp);
            }
        });
    }

    @Override
    public void initData(String fullName, int level, long score) {
        sLevel = level;
        tvName.setText(fullName);
        tvScore.setText(String.valueOf(score));
        tvLevel.setText(String.valueOf(level));
    }

    @Override
    public void getLoanCreditPackageSuccess(List<LoanResponse.LoanCreditPackagesEntity> loanCreditPackagesEntities) {
        mLoanEntities = new ArrayList<>();
        for (LoanResponse.LoanCreditPackagesEntity loanCreditPackagesEntity : loanCreditPackagesEntities) {
            LoanEntity loanEntity = new LoanEntity();
            loanEntity.setFee(loanCreditPackagesEntity.getFee());
            loanEntity.setId(loanCreditPackagesEntity.getId());
            loanEntity.setOpen(false);
            loanEntity.setLevelrequiment(loanCreditPackagesEntity.getLevelrequiment());
            loanEntity.setMaxduration(loanCreditPackagesEntity.getMaxduration());
            loanEntity.setMaxvalue(loanCreditPackagesEntity.getMaxvalue());
            loanEntity.setMinduration(loanCreditPackagesEntity.getMinduration());
            loanEntity.setMinvalue(loanCreditPackagesEntity.getMinvalue());
            loanEntity.setName(loanCreditPackagesEntity.getName());
            loanEntity.setProfit(loanCreditPackagesEntity.getProfit());
            loanEntity.setIconUrl(loanCreditPackagesEntity.getIconUrl());
            mLoanEntities.add(loanEntity);
        }
        mLoanList = loanCreditPackagesEntities;
        mLoanRequestAdapter = new LoanRequestAdapter(this, mPresenter, mLoanEntities);
        Commons.setVerticalRecyclerView(this, rvLoanList);
        rvLoanList.setAdapter(mLoanRequestAdapter);
    }

    @Override
    public void getLoanCreditPackageFail(String err) {
        DialogUtils.showAlertDialog(this, getString(R.string.dialog_title), err);
    }

    @Override
    public void openOrCloseInfo(RelativeLayout view, Button button, boolean isOpen, int position) {
        mLoanEntities.get(position).setOpen(!mLoanEntities.get(position).isOpen());
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
