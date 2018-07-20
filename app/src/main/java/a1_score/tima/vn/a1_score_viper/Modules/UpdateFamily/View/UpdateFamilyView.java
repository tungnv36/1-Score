package a1_score.tima.vn.a1_score_viper.Modules.UpdateFamily.View;

import android.app.Dialog;
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
import android.view.ViewGroup;
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

import com.suke.widget.SwitchButton;

import java.util.ArrayList;
import java.util.List;

import a1_score.tima.vn.a1_score_viper.Common.Commons;
import a1_score.tima.vn.a1_score_viper.Common.Constant;
import a1_score.tima.vn.a1_score_viper.Common.DialogUtils;
import a1_score.tima.vn.a1_score_viper.Modules.UpdateFamily.Entity.FamilyMembersRequest;
import a1_score.tima.vn.a1_score_viper.Modules.UpdateFamily.Entity.FamilyMembersResponse;
import a1_score.tima.vn.a1_score_viper.Modules.UpdateFamily.Entity.FamilyResponse;
import a1_score.tima.vn.a1_score_viper.Modules.UpdateFamily.Entity.RelationshipDictionaryResponse;
import a1_score.tima.vn.a1_score_viper.Modules.UpdateFamily.Entity.RelationshipResponse;
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
    @BindView(R.id.tvMarriageRegistrationLabel)
    TextView tvMarriageRegistrationLabel;
    @BindView(R.id.ivMarriageRegistration)
    ImageView ivMarriageRegistration;
    @BindView(R.id.llMarriageRegistration)
    LinearLayout llMarriageRegistration;
    @BindView(R.id.rlMarriageRegistration)
    RelativeLayout rlMarriageRegistration;
    @BindView(R.id.llPaper)
    LinearLayout llPaper;
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

    private Dialog mDialog;
    private ImageView ivBirthCertificate;
    private ImageView ivStudyCard;

    private List<FamilyMembersRequest> mFamilyList;
    private List<Bitmap> mBirthCertificateList;
    private List<Bitmap> mStudyCardList;
    private List<String> mRelationshipType;
    private List<Integer> mRelationshipIdList = new ArrayList<>();
    private List<String> mRelationshipList = new ArrayList<>();
    private FamilyControlAdapter mFamilyControlAdapter;

    public static String sFileName;
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
//        setupDropdown();

        mPresenter = new UpdateFamilyPresenter(this);

        ibAdd.setOnClickListener(this);
        rlMarriageRegistration.setOnClickListener(this);
        btUpdate.setOnClickListener(this);
//        rlSonBirthCertificate.setOnClickListener(this);
//        rlStudentCard.setOnClickListener(this);

        switchButton.setOnCheckedChangeListener(new SwitchButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SwitchButton view, boolean isChecked) {
                showHideView(isChecked);
            }
        });

        mPresenter.getRelationshipDictionary();

        setupDropdown();
        initData();
        showHideView(switchButton.isChecked());
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void setupFamilyControl() {
        mFamilyList = new ArrayList<>();
        mBirthCertificateList = new ArrayList<>();
        mStudyCardList = new ArrayList<>();
        mRelationshipType = new ArrayList<>();
//        mFamilyList.add(createNewFamilyControl());
        mFamilyControlAdapter = new FamilyControlAdapter(this, mPresenter, mFamilyList, mBirthCertificateList, mStudyCardList, mRelationshipType);
        Commons.setVerticalRecyclerView(this, rvFamily);
        rvFamily.setAdapter(mFamilyControlAdapter);
    }

    private void initData() {
//        mPresenter.initImage(1, "_mr");
        mPresenter.getFamily();
//        mPresenter.getRelationshipDictionary();
//        mPresenter.initRelationship();
    }

    private void setupDropdown() {
        ArrayAdapter<String> adapterSex = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, Constant.ARRAY_NUMBER_SON);
        adapterSex.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        spSon.setAdapter(adapterSex);
    }

    private void showHideView(boolean isShow) {
        if (!isShow) {
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

    private int getPositionById(int ID) {
        for (int i = 0; i < mRelationshipIdList.size(); i++) {
            if(ID == mRelationshipIdList.get(i)) {
                return i;
            }
        }
        return 0;
    }

    public String getRelationshipTypeById(int ID) {
        if(mRelationshipList != null && mRelationshipList.size() > 0) {
            int pos = getPositionById(ID);
            return mRelationshipList.get(pos);
        }
        return "";
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

    private void showDialogAddFamilyMembers() {
        try {
            mDialog = new Dialog(this);
            mDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            mDialog.setContentView(R.layout.dialog_add_family_members);
            mDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
            mDialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

            final Spinner spRelationship = (Spinner) mDialog.findViewById(R.id.spRelationship);
            final EditText etName = (EditText) mDialog.findViewById(R.id.etName);
            final EditText etPhone = (EditText) mDialog.findViewById(R.id.etPhone);
            RelativeLayout rlBirthCertificate = (RelativeLayout) mDialog.findViewById(R.id.rlBirthCertificate);
            RelativeLayout rlStudyCard = (RelativeLayout) mDialog.findViewById(R.id.rlStudyCard);
            ivBirthCertificate = (ImageView) mDialog.findViewById(R.id.ivBirthCertificate);
            ivStudyCard = (ImageView) mDialog.findViewById(R.id.ivStudyCard);
            Button btCancel = (Button)mDialog.findViewById(R.id.btCancel);
            Button btUpdate = (Button)mDialog.findViewById(R.id.btUpdate);

            ArrayAdapter<String> adapterJob = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, mRelationshipList);
            adapterJob.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
            spRelationship.setAdapter(adapterJob);

            rlBirthCertificate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    boolean result = Commons.checkPermission2(UpdateFamilyView.this);
                    if (result) {
                        sFileName = "_bc" + (mFamilyList.size() + 1);//SonBirthCertificate
                        mPresenter.takePhoto(2, 2);
                    }
                }
            });

            rlStudyCard.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    boolean result = Commons.checkPermission2(UpdateFamilyView.this);
                    if (result) {
                        sFileName = "_sc" + (mFamilyList.size() + 1);//SonBirthCertificate
                        mPresenter.takePhoto(1, 3);
                    }
                }
            });

            btUpdate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mPresenter.updateFamilyMembers(
                            mDialog,
                            mRelationshipIdList.get(spRelationship.getSelectedItemPosition()),
                            etName.getText().toString(),
                            etPhone.getText().toString(),
                            "_bc" + (mFamilyList.size() + 1),
                            "_sc" + (mFamilyList.size() + 1));
                }
            });

            btCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mDialog.dismiss();
                }
            });

            mDialog.show();
        } catch (Exception ex) {
            DialogUtils.showAlertDialog(UpdateFamilyView.this, getString(R.string.dialog_title), ex.getMessage());
        }
    }

    @Override
    public void onClick(View v) {
        boolean result = false;
        switch (v.getId()) {
            case R.id.ibAdd:
                showDialogAddFamilyMembers();
                break;
            case R.id.rlMarriageRegistration:
                result = Commons.checkPermission2(UpdateFamilyView.this);
                if (result) {
                    sFileName = "_mr";//MarriageRegistration
                    mPresenter.takePhoto(2, 1);
                }
                break;
            case R.id.btUpdate:
                mPresenter.updateFamily(switchButton.isChecked(), etNameVC.getText().toString(), etPhoneVC.getText().toString(), spSon.getSelectedItemPosition());
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
                mPresenter.updateImage(type, imageType, filePath, sFileName);
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
                mBirthCertificateList.add(bitmap);
//                llSonBirthCertificate.setVisibility(View.GONE);
//                ivSonBirthCertificate.setImageBitmap(bitmap);
                break;
            case 3://Thẻ học sinh
                mStudyCardList.add(bitmap);
//                llStudentCard.setVisibility(View.GONE);
//                ivStudentCard.setImageBitmap(bitmap);
                break;
        }
    }

    @Override
    public void initFamily(FamilyResponse.FamilyEntity familyEntity) {
        switchButton.setChecked(familyEntity.getMarriagestatus()==1);
        etNameVC.setText(familyEntity.getFamilyname());
        etPhoneVC.setText(familyEntity.getFamilyphone());
        spSon.setSelection(2);
    }

    @Override
    public void initRelationshipDictionary(List<RelationshipDictionaryResponse.RelationshipTypeEntity> relationshipTypeEntities) {
        for(RelationshipDictionaryResponse.RelationshipTypeEntity relationshipTypeEntity : relationshipTypeEntities) {
            mRelationshipIdList.add(relationshipTypeEntity.getId());
            mRelationshipList.add(relationshipTypeEntity.getTypename());
        }
        mPresenter.initRelationship();
    }

    @Override
    public void initRelationship(String username, List<FamilyMembersResponse.RelationshipEntity> relationshipEntities) {
        for(FamilyMembersResponse.RelationshipEntity relationshipsEntity : relationshipEntities) {
            FamilyMembersRequest familyMembersRequest = new FamilyMembersRequest();
            familyMembersRequest.setUsername(username);
            familyMembersRequest.setStudentCardId(relationshipsEntity.getStudentcardid());
            familyMembersRequest.setBirthCertificateId(relationshipsEntity.getBirthcertificateid());
            familyMembersRequest.setRelationshipPhone(relationshipsEntity.getRelationshipphone());
            familyMembersRequest.setRelationshipName(relationshipsEntity.getRelationshipname());
            familyMembersRequest.setRelationshipTypeId(relationshipsEntity.getRelationshiptypeid());
            mFamilyList.add(familyMembersRequest);
            mRelationshipType.add(getRelationshipTypeById(relationshipsEntity.getRelationshiptypeid()));
//            mPresenter.initImage(2, "_bc" + mFamilyList.size());
//            mPresenter.initImage(3, "_sc" + mFamilyList.size());
        }
        mFamilyControlAdapter.notifyDataSetChanged();
    }

    @Override
    public void updateImage(int imageType, Bitmap img) {
        switch (imageType) {
            case 1://llMarriageRegistration
                llMarriageRegistration.setVisibility(View.GONE);
                ivMarriageRegistration.setImageBitmap(img);
                break;
            case 2://llSonBirthCertificate
                ivBirthCertificate.setImageBitmap(img);
                break;
            case 3://llStudentCard
                ivStudyCard.setImageBitmap(img);
                break;
        }
    }

    @Override
    public void updateImageFailed(String err) {
        DialogUtils.showAlertDialog(this, getString(R.string.dialog_title), err);
    }

    @Override
    public void updateFamilyFailed(String err) {
        DialogUtils.showAlertDialog(this, getString(R.string.dialog_title), err);
    }

    @Override
    public void updateFamilySuccess(String msg) {
        DialogUtils.showAlertDialog(this, getString(R.string.dialog_title), msg, new DialogUtils.OnClickListener() {
            @Override
            public void onClickSuccess() {
                UpdateFamilyView.this.finish();
            }

            @Override
            public void onClickSuccess2() {
                UpdateFamilyView.this.finish();
            }
        });
    }

    @Override
    public void updateFamilyMembersSuccess(Dialog dialog, String msg, FamilyMembersRequest familyMembersRequest) {
        dialog.dismiss();
        mFamilyList.add(familyMembersRequest);
        mRelationshipType.add(getRelationshipTypeById(familyMembersRequest.getRelationshipTypeId()));
        mPresenter.initImage(2, "_bc" + mFamilyList.size());
        mPresenter.initImage(3, "_sc" + mFamilyList.size());
        mFamilyControlAdapter.notifyDataSetChanged();
    }

    @Override
    public void updateFamilyMembersFailed(Dialog dialog, String err) {
        DialogUtils.showAlertDialog(this, getString(R.string.dialog_title), err);
    }
}
