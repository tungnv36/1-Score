package a1_score.tima.vn.a1_score_viper.Modules.UpdateSocialNetwork.View;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.suke.widget.SwitchButton;

import java.util.List;

import a1_score.tima.vn.a1_score_viper.Modules.Profile.Entity.MenuEntity;
import a1_score.tima.vn.a1_score_viper.Modules.UpdateSocialNetwork.Entity.SocialNetworkEntity;
import a1_score.tima.vn.a1_score_viper.R;
import butterknife.BindView;
import butterknife.ButterKnife;

public class SocialNetworkAdapter extends RecyclerView.Adapter<SocialNetworkAdapter.ProfileViewHolder> {

    private List<SocialNetworkEntity> lstSocialNetwork;
    private Context context;
//    private ProfileInterface.Presenter presenter;

    public SocialNetworkAdapter(Context context, List<SocialNetworkEntity> lstSocialNetwork) {
        this.lstSocialNetwork = lstSocialNetwork;
        this.context = context;
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
        final SocialNetworkEntity socialNetworkEntity = lstSocialNetwork.get(position);

        holder.ivLogo.setImageResource(socialNetworkEntity.getIcon());
        holder.tvName.setText(socialNetworkEntity.getName());
        holder.swOnOff.setChecked(socialNetworkEntity.isOn());
        holder.tvName.setTextColor(ContextCompat.getColor(context, socialNetworkEntity.getTextColor()));

        if(position == lstSocialNetwork.size() - 1) {
            holder.rlLinkedin.setBackgroundResource(R.color.transparent);
        }

    }

    @Override
    public int getItemCount() {
        return lstSocialNetwork == null ? 0 : lstSocialNetwork.size();
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
