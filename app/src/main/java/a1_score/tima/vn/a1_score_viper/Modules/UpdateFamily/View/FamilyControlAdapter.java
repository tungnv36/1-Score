package a1_score.tima.vn.a1_score_viper.Modules.UpdateFamily.View;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.List;

import a1_score.tima.vn.a1_score_viper.Modules.UpdateFamily.Entity.UpdateFamilyEntity;
import a1_score.tima.vn.a1_score_viper.R;
import butterknife.BindView;
import butterknife.ButterKnife;

public class FamilyControlAdapter extends RecyclerView.Adapter<FamilyControlAdapter.ProfileViewHolder> {

    private List<UpdateFamilyEntity> lstFamily;
    private Context context;
//    private ProfileInterface.Presenter presenter;

    public FamilyControlAdapter(Context context, List<UpdateFamilyEntity> lstFamily) {
        this.lstFamily = lstFamily;
        this.context = context;
//        this.presenter = presenter;
    }

    @NonNull
    @Override
    public ProfileViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_family, parent, false);
//        Animation anim = AnimationUtils.loadAnimation(context, R.anim.item_move);
//        view.startAnimation(anim);
        return new ProfileViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProfileViewHolder holder, final int position) {
        final UpdateFamilyEntity updateFamilyEntity = lstFamily.get(position);


    }

    @Override
    public int getItemCount() {
        return lstFamily == null ? 0 : lstFamily.size();
    }

    public class ProfileViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.spRelationship)
        Spinner spRelationship;
        @BindView(R.id.llRelationship)
        LinearLayout llRelationship;
        @BindView(R.id.etName)
        EditText etName;
        @BindView(R.id.etPhone)
        EditText etPhone;

        public ProfileViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

}
