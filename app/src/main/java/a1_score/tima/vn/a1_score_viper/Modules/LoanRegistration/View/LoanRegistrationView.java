package a1_score.tima.vn.a1_score_viper.Modules.LoanRegistration.View;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import a1_score.tima.vn.a1_score_viper.Modules.LoanRegistration.Interface.LoanRegistrationInterface;
import a1_score.tima.vn.a1_score_viper.R;
import butterknife.BindView;
import butterknife.ButterKnife;

public class LoanRegistrationView extends AppCompatActivity implements LoanRegistrationInterface.View {


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

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loan_registration);
        ButterKnife.bind(this);
        setupActionBar();
        changeStatusBarColor();
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

}
