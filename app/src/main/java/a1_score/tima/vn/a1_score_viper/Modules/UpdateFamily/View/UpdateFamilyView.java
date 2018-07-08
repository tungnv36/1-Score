package a1_score.tima.vn.a1_score_viper.Modules.UpdateFamily.View;

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
import android.widget.Toast;

import com.suke.widget.SwitchButton;

import java.util.ArrayList;
import java.util.List;

import a1_score.tima.vn.a1_score_viper.Common.Commons;
import a1_score.tima.vn.a1_score_viper.Common.Constant;
import a1_score.tima.vn.a1_score_viper.Modules.UpdateFamily.Entity.FamilyRequest;
import a1_score.tima.vn.a1_score_viper.Modules.UpdateFamily.Interface.UpdateFamilyInterface;
import a1_score.tima.vn.a1_score_viper.Modules.UpdateFamily.Presenter.UpdateFamilyPresenter;
import a1_score.tima.vn.a1_score_viper.R;
import butterknife.BindView;
import butterknife.ButterKnife;

public class UpdateFamilyView extends AppCompatActivity implements View.OnClickListener, UpdateFamilyInterface.View {

    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.tvPersonScore)
    TextView tvPersonScore;
    @BindView(R.id.rlTitleInfo)
    RelativeLayout rlTitleInfo;
    @BindView(R.id.tvLinkedin)
    TextView tvLinkedin;
    @BindView(R.id.switch_button)
    SwitchButton switchButton;
    @BindView(R.id.rlLinkedin)
    RelativeLayout rlLinkedin;
    @BindView(R.id.etNameVC)
    EditText etNameVC;
    @BindView(R.id.etPhoneVC)
    EditText etPhoneVC;
    @BindView(R.id.spSon)
    Spinner spSon;
    @BindView(R.id.llSon)
    LinearLayout llSon;
    @BindView(R.id.ivMarriageRegistration)
    ImageView ivMarriageRegistration;
    @BindView(R.id.llMarriageRegistration)
    LinearLayout llMarriageRegistration;
    @BindView(R.id.rlMarriageRegistration)
    RelativeLayout rlMarriageRegistration;
    @BindView(R.id.ivSonBirthCertificate)
    ImageView ivSonBirthCertificate;
    @BindView(R.id.llSonBirthCertificate)
    LinearLayout llSonBirthCertificate;
    @BindView(R.id.rlSonBirthCertificate)
    RelativeLayout rlSonBirthCertificate;
    @BindView(R.id.ivStudentCard)
    ImageView ivStudentCard;
    @BindView(R.id.llStudentCard)
    LinearLayout llStudentCard;
    @BindView(R.id.rlStudentCard)
    RelativeLayout rlStudentCard;
    @BindView(R.id.llContent1)
    LinearLayout llContent1;
    @BindView(R.id.tvTitleBank)
    TextView tvTitleBank;
    @BindView(R.id.tvBankScore)
    TextView tvBankScore;
    @BindView(R.id.rlTitleBank)
    RelativeLayout rlTitleBank;
    @BindView(R.id.rvFamily)
    RecyclerView rvFamily;
    @BindView(R.id.ibAdd)
    ImageButton ibAdd;
    @BindView(R.id.llContent2)
    LinearLayout llContent2;
    @BindView(R.id.btUpdate)
    Button btUpdate;
    @BindView(R.id.llPaper)
    LinearLayout llPaper;
    
    private List<FamilyRequest> mFamilyList;
    private FamilyControlAdapter mFamilyControlAdapter;

    private String mFileName;
    private UpdateFamilyInterface.Presenter mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_family);
        ButterKnife.bind(this);
        setupActionBar();
        changeStatusBarColor();
        styleView();
        setupFamilyControl();
        setupDropdown();

        mPresenter = new UpdateFamilyPresenter(this);

        ibAdd.setOnClickListener(this);
        rlMarriageRegistration.setOnClickListener(this);
        rlSonBirthCertificate.setOnClickListener(this);
        rlStudentCard.setOnClickListener(this);

        switchButton.setOnCheckedChangeListener(new SwitchButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SwitchButton view, boolean isChecked) {
                showHideView(isChecked);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        initData();
        showHideView(switchButton.isChecked());
    }

    private void setupFamilyControl() {
        mFamilyList = new ArrayList<>();
        mFamilyList.add(new FamilyRequest(1, 1, "", ""));
        mFamilyControlAdapter = new FamilyControlAdapter(this, mFamilyList);
        Commons.setVerticalRecyclerView(this, rvFamily);
        rvFamily.setAdapter(mFamilyControlAdapter);
    }

    private void initData() {
        mPresenter.initImage(1, "_mr");
        mPresenter.initImage(2, "_sbc");
        mPresenter.initImage(3, "_sc");
//        mPresenter.initData();

    }

    private void setupDropdown() {
        ArrayAdapter<String> adapterSex = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, Constant.ARRAY_NUMBER_SON);
        adapterSex.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        spSon.setAdapter(adapterSex);
    }

    private void showHideView(boolean isShow) {
        if (isShow) {
            etNameVC.setVisibility(View.GONE);
            etPhoneVC.setVisibility(View.GONE);
            llSon.setVisibility(View.GONE);
            llPaper.setVisibility(View.GONE);
        } else {
            etNameVC.setVisibility(View.VISIBLE);
            etPhoneVC.setVisibility(View.VISIBLE);
            llSon.setVisibility(View.VISIBLE);
            llPaper.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    private void setupActionBar() {
        getSupportActionBar().setTitle(getString(R.string.family_info));
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
    public void onClick(View v) {
        boolean result = false;
        switch (v.getId()) {
            case R.id.ibAdd:
                mFamilyList.add(new FamilyRequest(mFamilyList.size() + 1, 1, "", ""));
                mFamilyControlAdapter.notifyDataSetChanged();
                break;
            case R.id.rlMarriageRegistration:
                result = Commons.checkPermission2(UpdateFamilyView.this);
                if (result) {
                    mFileName = "_mr";//MarriageRegistration
                    mPresenter.takePhoto(2, 1);
                }
                break;
            case R.id.rlSonBirthCertificate:
                result = Commons.checkPermission2(UpdateFamilyView.this);
                if (result) {
                    mFileName = "_sbc";//SonBirthCertificate
                    mPresenter.takePhoto(2, 2);
                }
                break;
            case R.id.rlStudentCard:
                result = Commons.checkPermission2(UpdateFamilyView.this);
                if (result) {
                    mFileName = "_sc";//StudentCard
                    mPresenter.takePhoto(1, 3);
                }
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Commons.TAKE_PHOTO_REQUEST_CODE) {
            if (data != null) {
                String filePath = data.getStringExtra(getString(R.string.result));
                int type = data.getIntExtra(getString(R.string.type), 0);
                int imageType = data.getIntExtra(getString(R.string.image_type), 0);
                mPresenter.updateImage(type, imageType, filePath, mFileName);
            }
        }
    }

    @Override
    public void initImage(int type, Bitmap bitmap) {
        switch (type) {
            case 1://Đăng ký kết hôn
                llMarriageRegistration.setVisibility(View.GONE);
                ivMarriageRegistration.setImageBitmap(bitmap);
                break;
            case 2://Giấy khai sinh của con
                llSonBirthCertificate.setVisibility(View.GONE);
                ivSonBirthCertificate.setImageBitmap(bitmap);
                break;
            case 3://Thẻ học sinh
                llStudentCard.setVisibility(View.GONE);
                ivStudentCard.setImageBitmap(bitmap);
                break;
        }
    }

    @Override
    public void updateImage(int imageType, Bitmap img) {
        switch (imageType) {
            case 1://llMarriageRegistration
                llMarriageRegistration.setVisibility(View.GONE);
                ivMarriageRegistration.setImageBitmap(img);
                break;
            case 2://llSonBirthCertificate
                llSonBirthCertificate.setVisibility(View.GONE);
                ivSonBirthCertificate.setImageBitmap(img);
                break;
            case 3://llStudentCard
                llStudentCard.setVisibility(View.GONE);
                ivStudentCard.setImageBitmap(img);
                break;
        }
    }

    @Override
    public void updateImageFailed(String err) {
        Toast.makeText(this, err, Toast.LENGTH_LONG).show();
    }

    @Override
    public void updateFamilyFailed(String err) {

    }

    @Override
    public void updateFamilySuccess(String msg) {

    }
}
