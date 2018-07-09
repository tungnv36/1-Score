package a1_score.tima.vn.a1_score_viper.Modules.UpdateFamily.View;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.List;

import a1_score.tima.vn.a1_score_viper.Modules.UpdateFamily.Entity.FamilyMembersRequest;
import a1_score.tima.vn.a1_score_viper.Modules.UpdateFamily.Interface.UpdateFamilyInterface;
import a1_score.tima.vn.a1_score_viper.R;
import butterknife.BindView;
import butterknife.ButterKnife;

public class FamilyControlAdapter extends RecyclerView.Adapter<FamilyControlAdapter.ProfileViewHolder> {

    private List<FamilyMembersRequest> mFamilyList;
    private List<Bitmap> mBirthCertificateList;
    private List<Bitmap> mStudyCardList;
    private Context mContext;
    private UpdateFamilyInterface.Presenter mPresenter;

    public FamilyControlAdapter(Context context, UpdateFamilyInterface.Presenter presenter, List<FamilyMembersRequest> familyList, List<Bitmap> birthCertificateList, List<Bitmap> studyCardList) {
        mFamilyList = familyList;
        mContext = context;
        mPresenter = presenter;
        mBirthCertificateList = birthCertificateList;
        mStudyCardList = studyCardList;
    }

    @NonNull
    @Override
    public ProfileViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_family, parent, false);
        return new ProfileViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProfileViewHolder holder, final int position) {
        final FamilyMembersRequest familyMembersRequest = mFamilyList.get(position);

//        mPresenter.initImage(2, "_sbc" + (position + 1));
//        mPresenter.initImage(3, "_sc" + (position + 1));

//        holder.ivBirthCertificate.setImageBitmap(mBirthCertificateList.get(position));
//        holder.ivStudyCard.setImageBitmap(mStudyCardList.get(position));
//
//        holder.llBirthCertificate.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                boolean result = Commons.checkPermission2(mContext);
//                if (result) {
//                    UpdateFamilyView.sFileName = "_sbc" + (position + 1);//SonBirthCertificate
//                    UpdateFamilyView.sPositionFamilySelected = position;
//                    mPresenter.takePhoto(2, 2);
//                }
//            }
//        });
//
//        holder.llStudyCard.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                boolean result = Commons.checkPermission2(mContext);
//                if (result) {
//                    UpdateFamilyView.sFileName = "_sc" + (position + 1);//SonBirthCertificate
//                    UpdateFamilyView.sPositionFamilySelected = position;
//                    mPresenter.takePhoto(1, 3);
//                }
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return mFamilyList == null ? 0 : mFamilyList.size();
    }

    public class ProfileViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tvRelationship)
        TextView tvRelationship;
        @BindView(R.id.tvName)
        TextView tvName;
        @BindView(R.id.tvPhone)
        TextView tvPhone;
        @BindView(R.id.ivBirthCertificate)
        ImageView ivBirthCertificate;
        @BindView(R.id.ivStudyCard)
        ImageView ivStudyCard;

        public ProfileViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

}
