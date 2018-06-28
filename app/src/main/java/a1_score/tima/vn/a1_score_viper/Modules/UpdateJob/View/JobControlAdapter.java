package a1_score.tima.vn.a1_score_viper.Modules.UpdateJob.View;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;

import java.util.List;

import a1_score.tima.vn.a1_score_viper.Modules.UpdateFamily.Entity.UpdateFamilyEntity;
import a1_score.tima.vn.a1_score_viper.Modules.UpdateJob.Entity.ColleagueEntity;
import a1_score.tima.vn.a1_score_viper.R;
import butterknife.BindView;
import butterknife.ButterKnife;

public class JobControlAdapter extends RecyclerView.Adapter<JobControlAdapter.ProfileViewHolder> {

    private List<ColleagueEntity> colleagueEntities;
    private Context context;
//    private ProfileInterface.Presenter presenter;

    public JobControlAdapter(Context context, List<ColleagueEntity> colleagueEntities) {
        this.colleagueEntities = colleagueEntities;
        this.context = context;
//        this.presenter = presenter;
    }

    @NonNull
    @Override
    public ProfileViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_colleague, parent, false);
        return new ProfileViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProfileViewHolder holder, final int position) {
        final ColleagueEntity colleagueEntity= colleagueEntities.get(position);

    }

    @Override
    public int getItemCount() {
        return colleagueEntities == null ? 0 : colleagueEntities.size();
    }

    public class ProfileViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.etColleagueName)
        EditText etColleagueName;
        @BindView(R.id.etColleaguePhone)
        EditText etColleaguePhone;

        public ProfileViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

}
