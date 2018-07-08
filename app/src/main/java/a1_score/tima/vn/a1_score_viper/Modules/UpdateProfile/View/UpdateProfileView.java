package a1_score.tima.vn.a1_score_viper.Modules.UpdateProfile.View;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import a1_score.tima.vn.a1_score_viper.Common.Commons;
import a1_score.tima.vn.a1_score_viper.Common.Constant;
import a1_score.tima.vn.a1_score_viper.Common.DialogUtils;
import a1_score.tima.vn.a1_score_viper.Common.MYPickerDialog;
import a1_score.tima.vn.a1_score_viper.Modules.UpdateProfile.Entity.ProfileRequest;
import a1_score.tima.vn.a1_score_viper.Modules.UpdateProfile.Interface.UpdateProfileInterface;
import a1_score.tima.vn.a1_score_viper.Modules.UpdateProfile.Presenter.UpdateProfilePresenter;
import a1_score.tima.vn.a1_score_viper.R;
import butterknife.BindView;
import butterknife.ButterKnife;

public class UpdateProfileView extends AppCompatActivity implements View.OnClickListener, UpdateProfileInterface.View {


    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.tvPersonScore)
    TextView tvPersonScore;
    @BindView(R.id.rlTitleInfo)
    RelativeLayout rlTitleInfo;
    @BindView(R.id.etName)
    EditText etName;
    @BindView(R.id.tvBirthDay)
    TextView tvBirthDay;
    @BindView(R.id.llBirthDay)
    LinearLayout llBirthDay;
    @BindView(R.id.spSex)
    Spinner spSex;
    @BindView(R.id.llSex)
    LinearLayout llSex;
    @BindView(R.id.etCMND)
    EditText etCMND;
    @BindView(R.id.etAddress)
    EditText etAddress;
    @BindView(R.id.ivFrontCMND)
    ImageView ivFrontCMND;
    @BindView(R.id.llFrontCMND)
    LinearLayout llFrontCMND;
    @BindView(R.id.rlFrontCMND)
    RelativeLayout rlFrontCMND;
    @BindView(R.id.ivBehindCMND)
    ImageView ivBehindCMND;
    @BindView(R.id.llBehindCMND)
    LinearLayout llBehindCMND;
    @BindView(R.id.rlBehindCMND)
    RelativeLayout rlBehindCMND;
    @BindView(R.id.llContent1)
    LinearLayout llContent1;
    @BindView(R.id.tvTitleBank)
    TextView tvTitleBank;
    @BindView(R.id.tvBankScore)
    TextView tvBankScore;
    @BindView(R.id.rlTitleBank)
    RelativeLayout rlTitleBank;
    @BindView(R.id.etAccount)
    EditText etAccount;
    @BindView(R.id.tvCardTurm)
    TextView tvCardTurm;
    @BindView(R.id.llCardTurm)
    LinearLayout llCardTurm;
    @BindView(R.id.ivCard)
    ImageView ivCard;
    @BindView(R.id.llCard)
    LinearLayout llCard;
    @BindView(R.id.rlCard)
    RelativeLayout rlCard;
    @BindView(R.id.llContent2)
    LinearLayout llContent2;
    @BindView(R.id.btUpdate)
    Button btUpdate;

    private Calendar cal;
    private Date date;
    private String monthYearStr;

    private UpdateProfileInterface.Presenter presenter;
    private String fileName = "";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);
        ButterKnife.bind(this);
        setupActionBar();
        changeStatusBarColor();
        styleView();

        presenter = new UpdateProfilePresenter(this);

        llBirthDay.setOnClickListener(this);
        llCardTurm.setOnClickListener(this);
        rlFrontCMND.setOnClickListener(this);
        rlBehindCMND.setOnClickListener(this);
        rlCard.setOnClickListener(this);
        btUpdate.setOnClickListener(this);

        initDatePicker();
        setupDropdownSex();
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
        getSupportActionBar().setTitle(getString(R.string.person_info));
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

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onClick(View v) {
        boolean result = false;
        switch (v.getId()) {
            case R.id.llBirthDay:
                showDateDialog();
                break;
            case R.id.llCardTurm:
                showDateMYDialog();
                break;
            case R.id.rlFrontCMND:
                result = Commons.checkPermission2(UpdateProfileView.this);
                if(result) {
                    fileName = "_front_cmnd";
                    presenter.takePhoto(1, 1);//type = 1: Vẽ khung ảnh chụp CMND //imageType = 1 => llFontCMND
                }
                break;
            case R.id.rlBehindCMND:
                fileName = "_back_cmnd";
                result = Commons.checkPermission2(UpdateProfileView.this);
                if(result) {
                    presenter.takePhoto(1, 2);//type = 1: Vẽ khung ảnh chụp CMND //imageType = 2 => llBehindCMND
                }
                break;
            case R.id.rlCard:
                fileName = "_card_cmnd";
                result = Commons.checkPermission2(UpdateProfileView.this);
                if(result) {
                    presenter.takePhoto(1, 3);//type = 1: Vẽ khung ảnh chụp CMND //imageType = 3 => llCard
                }
                break;
            case R.id.btUpdate:
                presenter.updateProfile(etName.getText().toString(), tvBirthDay.getText().toString(), etCMND.getText().toString()
                        , etAddress.getText().toString(), etAccount.getText().toString(), tvCardTurm.getText().toString(), spSex.getSelectedItemPosition() + 1);
                break;
        }
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

    private void initData() {
        presenter.initImage(1, "_front_cmnd");
        presenter.initImage(2, "_back_cmnd");
        presenter.initImage(3, "_card_cmnd");
        presenter.initData();
    }

    private void setupDropdownSex() {
        ArrayAdapter<String> adapterSex = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, Constant.ARRAY_SEX);
        adapterSex.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        spSex.setAdapter(adapterSex);
    }

    private void initDatePicker() {
        cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        String strDate = sdf.format(cal.getTime());
        tvBirthDay.setText(strDate);
    }

    //setup dialog chọn ngày/tháng/năm
    @RequiresApi(api = Build.VERSION_CODES.N)
    private void showDateDialog() {
        DatePickerDialog.OnDateSetListener callback = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                tvBirthDay.setText((day < 10 ? "0" + day : day) + "/" + ((month + 1) < 10 ? "0" + (month + 1) : (month + 1)) + "/" + year);
                cal.set(year, month, day);
                date = cal.getTime();
            }
        };
        int ngay = Integer.parseInt(Commons.getToday().split("/")[0].toString());
        int thang = Integer.parseInt(Commons.getToday().split("/")[1].toString());
        int nam = Integer.parseInt(Commons.getToday().split("/")[2].toString());
        String sDay = tvBirthDay.getText().toString();
        if(!sDay.isEmpty()) {
            String strArrtmp[] = sDay.split("/");
            ngay = Integer.parseInt(strArrtmp[0]);
            thang = Integer.parseInt(strArrtmp[1]) - 1;
            nam = Integer.parseInt(strArrtmp[2]);
            DatePickerDialog pic = new DatePickerDialog(this, callback, nam, thang, ngay);
            pic.show();
        } else {
            DatePickerDialog pic = new DatePickerDialog(this, callback, nam, thang, ngay);
            pic.show();
        }
    }

    //setup dialog chọn tháng/năm
    private void showDateMYDialog() {
        MYPickerDialog pickerDialog = new MYPickerDialog();
        pickerDialog.setListener(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int i2) {
                monthYearStr = year + "-" + (month + 1) + "-" + i2;
                tvCardTurm.setText(Commons.formatMonthYear(monthYearStr));
            }
        });
        pickerDialog.show(getSupportFragmentManager(), "MonthYearPickerDialog");
    }

    @Override
    public void initImage(int type, Bitmap bitmap) {
        switch (type) {
            case 1://CMND truoc
                llFrontCMND.setVisibility(View.GONE);
                ivFrontCMND.setImageBitmap(bitmap);
                break;
            case 2://CMND sau
                llBehindCMND.setVisibility(View.GONE);
                ivBehindCMND.setImageBitmap(bitmap);
                break;
            case 3://The ngan hang
                llCard.setVisibility(View.GONE);
                ivCard.setImageBitmap(bitmap);
                break;
        }
    }

    @Override
    public void initDataSuccess(ProfileRequest profileRequest) {
        if(profileRequest != null) {
            etName.setText(profileRequest.getFullname());
            tvBirthDay.setText(profileRequest.getDateOfBirth());
            spSex.setSelection(profileRequest.getSex() - 1);
            etCMND.setText(profileRequest.getIdNumber());
            etAddress.setText(profileRequest.getAddress());
            etAccount.setText(profileRequest.getBankAccNumber());
            tvCardTurm.setText(profileRequest.getCardTerm());
        }
    }

    @Override
    public void updateImage(int imageType, Bitmap img) {
        switch (imageType) {
            case 1://llFrontCMND
                llFrontCMND.setVisibility(View.GONE);
                ivFrontCMND.setImageBitmap(img);
                break;
            case 2://llBehindCMND
                llBehindCMND.setVisibility(View.GONE);
                ivBehindCMND.setImageBitmap(img);
                break;
            case 3://llCard
                llCard.setVisibility(View.GONE);
                ivCard.setImageBitmap(img);
                break;
        }
    }

    @Override
    public void updateImageFailed(String err) {
        DialogUtils.showAlertDialog(this, getString(R.string.dialog_title), err);
    }

    @Override
    public void updateProfileFailed(String err) {
        DialogUtils.showAlertDialog(this, getString(R.string.dialog_title), err);
    }

    @Override
    public void updateProfileSuccess(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
        this.finish();
    }

}
