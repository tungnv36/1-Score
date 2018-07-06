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
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import a1_score.tima.vn.a1_score_viper.Common.Commons;
import a1_score.tima.vn.a1_score_viper.Common.DialogUtils;
import a1_score.tima.vn.a1_score_viper.Modules.UpdateJob.Entity.UpdateColleagueEntity;
import a1_score.tima.vn.a1_score_viper.Modules.UpdateJob.Entity.JobDictionaryResultEntity;
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
    @BindView(R.id.rvColleague)
    RecyclerView rvColleague;
    @BindView(R.id.llContent1)
    LinearLayout llContent1;
    @BindView(R.id.btUpdate)
    Button btUpdate;
    @BindView(R.id.ibAddColleague)
    ImageButton ibAddColleague;
    @BindView(R.id.tvTitleBank)
    TextView tvTitleBank;
    @BindView(R.id.tvBankScore)
    TextView tvBankScore;
    @BindView(R.id.rlTitleBank)
    RelativeLayout rlTitleBank;
    @BindView(R.id.llContent2)
    LinearLayout llContent2;

    private UpdateJobInterface.Presenter presenter;

    private String fileName;
    private List<UpdateColleagueEntity.ColleagueEntity> colleagueEntities;
    private JobControlAdapter jobControlAdapter;

    private List<Integer> jobIDs = new ArrayList<>();
    private List<Integer> positionIDs = new ArrayList<>();
    private List<Integer> salarieIDs = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_job);
        ButterKnife.bind(this);
        setupActionBar();
        changeStatusBarColor();
        styleView();
        initColleagueControl();

        presenter = new UpdateJobPresenter(this);
        presenter.getJobDictionary();

        rlCV.setOnClickListener(this);
        rlContract.setOnClickListener(this);
        rlSalaryBoard.setOnClickListener(this);
        ibAddColleague.setOnClickListener(this);
        btUpdate.setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        initData();
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
                int type = data.getIntExtra(getString(R.string.type), 0);//type = 1: Vẽ khung ảnh chụp CMND, type = 2: Chụp ảnh thường (hợp đồng, hoá đơn, ...)
                int imageType = data.getIntExtra(getString(R.string.image_type), 0);//imageType = 1: _front_cmnd //imageType = 2 => _back_cmnd
                presenter.updateImage(type, imageType, filePath, fileName);
            }
        }
    }

    private void initColleagueControl() {
        colleagueEntities = new ArrayList<>();
        UpdateColleagueEntity.ColleagueEntity colleagueEntity = new UpdateColleagueEntity.ColleagueEntity();
        colleagueEntity.setColleagueName("");
        colleagueEntity.setColleaguePhone("");
        colleagueEntities.add(colleagueEntity);
        jobControlAdapter = new JobControlAdapter(this, colleagueEntities);
        Commons.setVerticalRecyclerView(this, rvColleague);
        rvColleague.setAdapter(jobControlAdapter);
    }

    private void initData() {
        presenter.initImage(1, "_cv");
        presenter.initImage(2, "_contract");
        presenter.initImage(3, "_salary_board");
//        presenter.initData();
    }

    @Override
    public void initImage(int type, Bitmap bitmap) {
        switch (type) {
            case 1://CV
                llCV.setVisibility(View.GONE);
                ivCV.setImageBitmap(bitmap);
                break;
            case 2://Hợp đồng
                llContract.setVisibility(View.GONE);
                ivContract.setImageBitmap(bitmap);
                break;
            case 3://bảng lương
                llSalaryBoard.setVisibility(View.GONE);
                ivSalaryBoard.setImageBitmap(bitmap);
                break;
        }
    }

    @Override
    public void initJobs(List<JobDictionaryResultEntity.JobsEntity> jobsEntities) {
        List<String> jobs = new ArrayList<>();
        for (JobDictionaryResultEntity.JobsEntity jobsEntity : jobsEntities) {
            jobs.add(jobsEntity.getJobtype());
            jobIDs.add(jobsEntity.getId());
        }
        ArrayAdapter<String> adapterJob = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, jobs);
        adapterJob.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        spJob.setAdapter(adapterJob);
    }

    @Override
    public void initPosition(List<JobDictionaryResultEntity.PositionsEntity> positionsEntities) {
        List<String> positions = new ArrayList<>();
        for (JobDictionaryResultEntity.PositionsEntity positionsEntity : positionsEntities) {
            positions.add(positionsEntity.getPosition());
            positionIDs.add(positionsEntity.getId());
        }
        ArrayAdapter<String> adapterPosition = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, positions);
        adapterPosition.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        spPosition.setAdapter(adapterPosition);
    }

    @Override
    public void iniSalaryLevel(List<JobDictionaryResultEntity.SalaryLevelsEntity> salaryLevelsEntities) {
        List<String> salaries = new ArrayList<>();
        for (JobDictionaryResultEntity.SalaryLevelsEntity salaryLevelsEntity : salaryLevelsEntities) {
            salaries.add(salaryLevelsEntity.getSalary());
            salarieIDs.add(salaryLevelsEntity.getId());
        }
        ArrayAdapter<String> adapterSalary = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, salaries);
        adapterSalary.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        spSalary.setAdapter(adapterSalary);
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
        DialogUtils.showAlertDialog(UpdateJobView.this, getString(R.string.dialog_title), err);
    }

    @Override
    public void updateJobSuccess(String msg) {
        DialogUtils.showAlertDialog(UpdateJobView.this, getString(R.string.dialog_title), msg, new DialogUtils.OnClickListener() {
            @Override
            public void onClickSuccess() {
                UpdateJobView.this.finish();
            }

            @Override
            public void onClickSuccess2() {

            }
        });
    }

    @Override
    public void updateJobFailed(String err) {
        DialogUtils.showAlertDialog(UpdateJobView.this, getString(R.string.dialog_title), err);
    }

    @Override
    public void onClick(View v) {
        boolean result = false;
        switch (v.getId()) {
            case R.id.rlCV:
                result = Commons.checkPermission2(UpdateJobView.this);
                if (result) {
                    fileName = "_cv";
                    presenter.takePhoto(2, 1);//type = 2: Vẽ khung ảnh chụp giấy tờ //imageType = 1 => rlCV
                }
                break;
            case R.id.rlContract:
                result = Commons.checkPermission2(UpdateJobView.this);
                if (result) {
                    fileName = "_contract";
                    presenter.takePhoto(2, 2);//type = 2: Vẽ khung ảnh chụp giấy tờ //imageType = 2 => rlContract
                }
                break;
            case R.id.rlSalaryBoard:
                result = Commons.checkPermission2(UpdateJobView.this);
                if (result) {
                    fileName = "_salary_board";
                    presenter.takePhoto(2, 3);//type = 2: Vẽ khung ảnh chụp giấy tờ //imageType = 3 => rlSalaryBoard
                }
                break;
            case R.id.ibAddColleague:
                UpdateColleagueEntity.ColleagueEntity colleagueEntity = new UpdateColleagueEntity.ColleagueEntity();
                colleagueEntity.setColleagueName("");
                colleagueEntity.setColleaguePhone("");
                colleagueEntities.add(colleagueEntity);
                jobControlAdapter.notifyDataSetChanged();
                break;
            case R.id.btUpdate:
                presenter.updateJob(
                        jobIDs.get(spJob.getSelectedItemPosition()),
                        etCompanyName.getText().toString(),
                        etCompanyAddress.getText().toString(),
                        positionIDs.get(spPosition.getSelectedItemPosition()),
                        salarieIDs.get(spSalary.getSelectedItemPosition()),
                        colleagueEntities
                );
                break;
        }
    }
}
