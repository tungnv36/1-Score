package a1_score.tima.vn.a1_score_viper.Modules.LoanRegistration.View;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import a1_score.tima.vn.a1_score_viper.Common.Commons;
import a1_score.tima.vn.a1_score_viper.Modules.LoanRegistration.Entity.LoanDictionaryResponse;
import a1_score.tima.vn.a1_score_viper.Modules.LoanRegistration.Entity.LoanRequest;
import a1_score.tima.vn.a1_score_viper.Modules.LoanRegistration.Interface.LoanRegistrationInterface;
import a1_score.tima.vn.a1_score_viper.Modules.LoanRegistration.Presenter.LoanRegistrationPresenter;
import a1_score.tima.vn.a1_score_viper.R;
import butterknife.BindView;
import butterknife.ButterKnife;

public class LoanRegistrationView extends AppCompatActivity implements LoanRegistrationInterface.View, View.OnClickListener {


    @BindView(R.id.tvFormOfLoans)
    TextView tvFormOfLoans;
    @BindView(R.id.llFormOfLoans)
    LinearLayout llFormOfLoans;
    @BindView(R.id.spPaymentMethods)
    Spinner spPaymentMethods;
    @BindView(R.id.llPaymentMethods)
    LinearLayout llPaymentMethods;
    @BindView(R.id.spLoanPurpose)
    Spinner spLoanPurpose;
    @BindView(R.id.llLoanPurpose)
    LinearLayout llLoanPurpose;
    @BindView(R.id.sbLoanMoney)
    SeekBar sbLoanMoney;
    @BindView(R.id.tvStartLoanMoney)
    TextView tvStartLoanMoney;
    @BindView(R.id.tvEndLoanMoney)
    TextView tvEndLoanMoney;
    @BindView(R.id.llLoanMoney)
    LinearLayout llLoanMoney;
    @BindView(R.id.sbLoanTurm)
    SeekBar sbLoanTurm;
    @BindView(R.id.tvStartLoanTurm)
    TextView tvStartLoanTurm;
    @BindView(R.id.tvEndLoanTurm)
    TextView tvEndLoanTurm;
    @BindView(R.id.llLoanTurm)
    LinearLayout llLoanTurm;
    @BindView(R.id.llInfo)
    RelativeLayout llInfo;
    @BindView(R.id.view)
    View view;
    @BindView(R.id.tvLabelMoneyLoan)
    TextView tvLabelMoneyLoan;
    @BindView(R.id.tvMoneyLoan)
    TextView tvMoneyLoan;
    @BindView(R.id.tvLabelCostOfLoan)
    TextView tvLabelCostOfLoan;
    @BindView(R.id.tvCostOfLoan)
    TextView tvCostOfLoan;
    @BindView(R.id.tvLabelInterest)
    TextView tvLabelInterest;
    @BindView(R.id.tvInterest)
    TextView tvInterest;
    @BindView(R.id.tvLabelTotalMoney)
    TextView tvLabelTotalMoney;
    @BindView(R.id.tvTotalMoney)
    TextView tvTotalMoney;
    @BindView(R.id.cbContact)
    CheckBox cbContact;
    @BindView(R.id.btContract)
    Button btContract;
    @BindView(R.id.rlLoanInfo)
    RelativeLayout rlLoanInfo;
    @BindView(R.id.btCancel)
    Button btCancel;
    @BindView(R.id.btRegister)
    Button btRegister;
    @BindView(R.id.tvLoanMoneySelectedValue)
    TextView tvLoanMoneySelectedValue;
    @BindView(R.id.tvLoanTurmSelectedValue)
    TextView tvLoanTurmSelectedValue;

    private List<Integer> mPurposeIdList = new ArrayList<>();
    private List<Integer> mPaymentMethodIdList = new ArrayList<>();

    private LoanRegistrationInterface.Presenter mPresenter;

    private long mAddValue = 1000000;
    private int mMinValue;
    private int mMinDuration;

    @SuppressLint("NewApi")
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loan_registration);
        ButterKnife.bind(this);
        setupActionBar();
        changeStatusBarColor();

        mPresenter = new LoanRegistrationPresenter(this);
        mPresenter.getLoanDictionary();

        initData();

        btCancel.setOnClickListener(this);
        btRegister.setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.onDestroy();
        mPresenter = null;
    }

    private void initData() {
        tvFormOfLoans.setText(getIntent().getStringExtra("Name"));
        tvStartLoanMoney.setText(Commons.formatMoney(getIntent().getLongExtra("MinValue", 0)) + " triệu");
        tvEndLoanMoney.setText(Commons.formatMoney(getIntent().getLongExtra("MaxValue", 0)) + " triệu");
        tvStartLoanTurm.setText(getIntent().getIntExtra("MinDuration", 0) + " ngày");
        tvEndLoanTurm.setText(getIntent().getIntExtra("MaxDuration", 0) + " ngày");

        final int loanTurmStep = 10;
        mMinDuration = getIntent().getIntExtra("MinDuration", 0);
        int max = getIntent().getIntExtra("MaxDuration", 0);
        sbLoanTurm.setMax(max - mMinDuration);
        sbLoanTurm.setProgress(max - mMinDuration);
        tvLoanTurmSelectedValue.setText(String.format("%d ngày", getIntent().getIntExtra("MaxDuration", 0)));

        sbLoanTurm.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progress = progress / loanTurmStep;
                progress = progress * loanTurmStep;
                sbLoanTurm.setProgress(progress);
                tvLoanTurmSelectedValue.setText(String.format("%d ngày", progress + mMinDuration));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        mMinValue = (int) (getIntent().getLongExtra("MinValue", 0) / mAddValue);
        int maxValue = (int) (getIntent().getLongExtra("MaxValue", 0) / mAddValue);
        final int loanMoneyStep = 1;
        sbLoanMoney.setMax(maxValue - mMinValue);
        sbLoanMoney.setProgress(maxValue - mMinValue);
        tvLoanMoneySelectedValue.setText(Commons.formatMoney(maxValue * mAddValue) + " triệu");

        sbLoanMoney.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progress = progress / loanMoneyStep;
                progress = progress * loanMoneyStep;
                sbLoanMoney.setProgress(progress);
                tvLoanMoneySelectedValue.setText(Commons.formatMoney((long)((progress + mMinValue) * mAddValue)) + " triệu");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Log.i("PROGRESS: ", "" + seekBar.getProgress());
            }
        });
    }


    private void setupActionBar() {
        getSupportActionBar().setTitle(getString(R.string.title_loan_registration));
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

    @Override
    public void initPurpose(List<LoanDictionaryResponse.PurposeEntity> purposeEntities) {
        List<String> purposes = new ArrayList<>();
        for (LoanDictionaryResponse.PurposeEntity purposeEntity : purposeEntities) {
            purposes.add(purposeEntity.getPurpose());
            mPurposeIdList.add(purposeEntity.getId());
        }
        ArrayAdapter<String> adapterJob = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, purposes);
        adapterJob.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        spLoanPurpose.setAdapter(adapterJob);
    }

    @Override
    public void initPaymentMethod(List<LoanDictionaryResponse.PaymentMethodEntity> paymentMethodEntities) {
        List<String> paymentMethods = new ArrayList<>();
        for (LoanDictionaryResponse.PaymentMethodEntity paymentMethodEntity : paymentMethodEntities) {
            paymentMethods.add(paymentMethodEntity.getMethod());
            mPaymentMethodIdList.add(paymentMethodEntity.getId());
        }
        ArrayAdapter<String> adapterJob = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, paymentMethods);
        adapterJob.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        spPaymentMethods.setAdapter(adapterJob);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btCancel:
                LoanRegistrationView.this.finish();
                break;
            case R.id.btRegister:
                LoanRequest loanRequest = new LoanRequest();
                loanRequest.setUsername("");
                loanRequest.setDuration(sbLoanTurm.getProgress());
                loanRequest.setPackageId(getIntent().getIntExtra("Id", 0));
                loanRequest.setPaymentMethodId(mPaymentMethodIdList.get(spPaymentMethods.getSelectedItemPosition()));
                loanRequest.setPurposeId(mPurposeIdList.get(spLoanPurpose.getSelectedItemPosition()));
                loanRequest.setValue(sbLoanMoney.getProgress() * mAddValue);
                mPresenter.goToLoanAuth(loanRequest);
                break;
        }
    }
}
