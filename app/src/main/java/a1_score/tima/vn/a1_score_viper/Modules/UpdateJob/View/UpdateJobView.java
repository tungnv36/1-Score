package a1_score.tima.vn.a1_score_viper.Modules.UpdateJob.View;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import a1_score.tima.vn.a1_score_viper.Common.Commons;
import a1_score.tima.vn.a1_score_viper.Modules.UpdateJob.Interface.UpdateJobInterface;
import a1_score.tima.vn.a1_score_viper.Modules.UpdateJob.Presenter.UpdateJobPresenter;
import a1_score.tima.vn.a1_score_viper.R;
import butterknife.BindView;
import butterknife.ButterKnife;

public class UpdateJobView extends AppCompatActivity implements UpdateJobInterface.View, View.OnClickListener {


    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.tvJobScore)
    TextView tvJobScore;
    @BindView(R.id.rlTitleInfo)
    RelativeLayout rlTitleInfo;
    @BindView(R.id.spJob)
    Spinner spJob;
    @BindView(R.id.llJob)
    LinearLayout llJob;
    @BindView(R.id.etCompanyName)
    EditText etCompanyName;
    @BindView(R.id.etCompanyAddress)
    EditText etCompanyAddress;
    @BindView(R.id.spPosition)
    Spinner spPosition;
    @BindView(R.id.llPosition)
    LinearLayout llPosition;
    @BindView(R.id.spSalary)
    Spinner spSalary;
    @BindView(R.id.llSalary)
    LinearLayout llSalary;
    @BindView(R.id.ivCV)
    ImageView ivCV;
    @BindView(R.id.llCV)
    LinearLayout llCV;
    @BindView(R.id.rlCV)
    RelativeLayout rlCV;
    @BindView(R.id.ivContract)
    ImageView ivContract;
    @BindView(R.id.llContract)
    LinearLayout llContract;
    @BindView(R.id.rlContract)
    RelativeLayout rlContract;
    @BindView(R.id.ivSalaryBoard)
    ImageView ivSalaryBoard;
    @BindView(R.id.llSalaryBoard)
    LinearLayout llSalaryBoard;
    @BindView(R.id.rlSalaryBoard)
    RelativeLayout rlSalaryBoard;
    @BindView(R.id.etColleagueName)
    EditText etColleagueName;
    @BindView(R.id.etColleaguePhone)
    EditText etColleaguePhone;
    @BindView(R.id.llContent1)
    LinearLayout llContent1;
    @BindView(R.id.btUpdate)
    Button btUpdate;
    private UpdateJobInterface.Presenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_job);
        ButterKnife.bind(this);
        setupActionBar();
        changeStatusBarColor();
        styleView();

        presenter = new UpdateJobPresenter(this);

        rlCV.setOnClickListener(this);
        rlContract.setOnClickListener(this);
        rlSalaryBoard.setOnClickListener(this);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    private void setupActionBar() {
        getSupportActionBar().setTitle(getString(R.string.job_info));
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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Commons.TAKE_PHOTO_REQUEST_CODE) {
            if (data != null) {
                String filePath = data.getStringExtra(getString(R.string.result));
                int type = data.getIntExtra(getString(R.string.type), 0);
                int imageType = data.getIntExtra(getString(R.string.image_type), 0);
                presenter.updateImage(type, imageType, filePath);
            }
        }
    }

    @Override
    public void updateImage(int imageType, Bitmap img) {
        switch (imageType) {
            case 1://rlCV
                llCV.setVisibility(View.GONE);
                ivCV.setImageBitmap(img);
                break;
            case 2://rlContract
                llContract.setVisibility(View.GONE);
                ivContract.setImageBitmap(img);
                break;
            case 3://rlSalaryBoard
                llSalaryBoard.setVisibility(View.GONE);
                ivSalaryBoard.setImageBitmap(img);
                break;
        }
    }

    @Override
    public void updateImageFailed(String err) {
        Toast.makeText(UpdateJobView.this, err, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onClick(View v) {
        boolean result = false;
        switch (v.getId()) {
            case R.id.rlCV:
                result = Commons.checkPermission2(UpdateJobView.this);
                if (result) {
                    presenter.takePhoto(2, 1);//type = 2: Vẽ khung ảnh chụp giấy tờ //imageType = 1 => rlCV
                }
                break;
            case R.id.rlContract:
                result = Commons.checkPermission2(UpdateJobView.this);
                if (result) {
                    presenter.takePhoto(2, 2);//type = 2: Vẽ khung ảnh chụp giấy tờ //imageType = 2 => rlContract
                }
                break;
            case R.id.rlSalaryBoard:
                result = Commons.checkPermission2(UpdateJobView.this);
                if (result) {
                    presenter.takePhoto(2, 3);//type = 2: Vẽ khung ảnh chụp giấy tờ //imageType = 3 => rlSalaryBoard
                }
                break;
        }
    }
}
