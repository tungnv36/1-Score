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
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import a1_score.tima.vn.a1_score_viper.Common.Commons;
import a1_score.tima.vn.a1_score_viper.Common.MYPickerDialog;
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

    private String arrSex[] = {"Nam", "Nữ"};

    private UpdateProfileInterface.Presenter presenter;

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

        initBirthDay();
        initSex();
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
            getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getColor(R.color.main_light_blue)));
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
            window.setStatusBarColor(getResources().getColor(R.color.main_light_blue));
        }
    }

    private void styleView() {
        btUpdate.setTypeface(Commons.setFont(this, getResources().getString(R.string.font_segoe)), Typeface.BOLD);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.llBirthDay:
                showDateDialog();
                break;
            case R.id.llCardTurm:
                showDateMYDialog();
                break;
            case R.id.rlFrontCMND:
                presenter.takePhoto(1, 1);//type = 1: Vẽ khung ảnh chụp CMND //imageType = 1 => llFontCMND
                break;
            case R.id.rlBehindCMND:
                presenter.takePhoto(1, 2);//type = 1: Vẽ khung ảnh chụp CMND //imageType = 2 => llBehindCMND
                break;
            case R.id.rlCard:
                presenter.takePhoto(1, 3);//type = 1: Vẽ khung ảnh chụp CMND //imageType = 3 => llCard
                break;

        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Commons.TAKE_PHOTO_REQUEST_CODE) {
            if (data != null) {
                String filePath = data.getStringExtra("result");
                int type = data.getIntExtra("type", 0);
                int imageType = data.getIntExtra("image_type", 0);
                presenter.updateImage(type, imageType, filePath);
            }
        }
    }

    private void initSex() {
        ArrayAdapter<String> adapterSex = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, arrSex);
        adapterSex.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        spSex.setAdapter(adapterSex);
    }

    private void initBirthDay() {
        cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        String strDate = sdf.format(cal.getTime());
        tvBirthDay.setText(strDate);
    }

    //setup dialog chọn ngày/tháng/năm
    private void showDateDialog() {
        DatePickerDialog.OnDateSetListener callback = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                tvBirthDay.setText((day < 10 ? "0" + day : day) + "/" + ((month + 1) < 10 ? "0" + (month + 1) : (month + 1)) + "/" + year);
                cal.set(year, month, day);
                date = cal.getTime();
            }
        };
        String sDay = tvBirthDay.getText().toString();
        String strArrtmp[] = sDay.split("/");
        int ngay = Integer.parseInt(strArrtmp[0]);
        int thang = Integer.parseInt(strArrtmp[1]) - 1;
        int nam = Integer.parseInt(strArrtmp[2]);

        DatePickerDialog pic = new DatePickerDialog(this, callback, nam, thang, ngay);
        pic.show();
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
    public void updateImage(int imageType, Bitmap img) {
        switch (imageType) {
            case 1://llFrontCMND
//                ivFrontCMND.setVisibility(View.VISIBLE);
                llFrontCMND.setVisibility(View.GONE);
                ivFrontCMND.setImageBitmap(img);
                break;
            case 2://llBehindCMND
//                ivBehindCMND.setVisibility(View.VISIBLE);
                llBehindCMND.setVisibility(View.GONE);
                ivBehindCMND.setImageBitmap(img);
                break;
            case 3://llCard
//                ivCard.setVisibility(View.VISIBLE);
                llCard.setVisibility(View.GONE);
                ivCard.setImageBitmap(img);
                break;
        }
    }

    @Override
    public void updateImageFailed(String err) {
        Toast.makeText(this, err, Toast.LENGTH_LONG).show();
    }
}