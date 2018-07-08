package a1_score.tima.vn.a1_score_viper.Modules.UpdateSocialNetwork.View;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.suke.widget.SwitchButton;

import java.util.List;

import a1_score.tima.vn.a1_score_viper.Modules.UpdateSocialNetwork.Entity.SocialNetworkRequest;
import a1_score.tima.vn.a1_score_viper.R;
import butterknife.BindView;
import butterknife.ButterKnife;

public class SocialNetworkAdapter extends RecyclerView.Adapter<SocialNetworkAdapter.ProfileViewHolder> {

    private List<SocialNetworkRequest> mSocialNetworkList;
    private Context mContext;
//    private ProfileInterface.Presenter presenter;

    public SocialNetworkAdapter(Context context, List<SocialNetworkRequest> socialNetworkList) {
        mSocialNetworkList = socialNetworkList;
        mContext = context;
    }

    @NonNull
    @Override
    public ProfileViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_social_network, parent, false);
//        Animation anim = AnimationUtils.loadAnimation(context, R.anim.item_move);
//        view.startAnimation(anim);
        return new ProfileViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProfileViewHolder holder, final int position) {
        final SocialNetworkRequest socialNetworkRequest = mSocialNetworkList.get(position);

        holder.ivLogo.setImageResource(socialNetworkRequest.getIcon());
        holder.tvName.setText(socialNetworkRequest.getName());
        holder.swOnOff.setChecked(socialNetworkRequest.isOn());
        holder.tvName.setTextColor(ContextCompat.getColor(mContext, socialNetworkRequest.getTextColor()));

        if(position == mSocialNetworkList.size() - 1) {
            holder.rlLinkedin.setBackgroundResource(R.color.transparent);
        }

    }

    @Override
    public int getItemCount() {
        return mSocialNetworkList == null ? 0 : mSocialNetworkList.size();
    }

    public class ProfileViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.ivLogo)
        ImageView ivLogo;
        @BindView(R.id.tvName)
        TextView tvName;
        @BindView(R.id.swOnOff)
        SwitchButton swOnOff;
        @BindView(R.id.rlLinkedin)
        RelativeLayout rlLinkedin;

        public ProfileViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

}
