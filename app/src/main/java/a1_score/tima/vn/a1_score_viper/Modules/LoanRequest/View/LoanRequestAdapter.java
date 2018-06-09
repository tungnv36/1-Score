package a1_score.tima.vn.a1_score_viper.Modules.LoanRequest.View;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import a1_score.tima.vn.a1_score_viper.Common.Commons;
import a1_score.tima.vn.a1_score_viper.Modules.LoanRequest.Entity.LoanRequestEntity;
import a1_score.tima.vn.a1_score_viper.Modules.LoanRequest.Interface.LoanRequestInterface;
import a1_score.tima.vn.a1_score_viper.R;
import butterknife.BindView;
import butterknife.ButterKnife;

public class LoanRequestAdapter extends RecyclerView.Adapter<LoanRequestAdapter.ProfileViewHolder> {

    private List<LoanRequestEntity> lstLoan;
    private Context context;
    private LoanRequestInterface.Presenter presenter;

    private int pos = -1;

    public LoanRequestAdapter(Context context, LoanRequestInterface.Presenter presenter, List<LoanRequestEntity> lstLoan) {
        this.lstLoan = lstLoan;
        this.context = context;
        this.presenter = presenter;
    }

    @NonNull
    @Override
    public ProfileViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_loan_request, parent, false);
        presenter.setupAnimationItem(context, view);
        return new ProfileViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ProfileViewHolder holder, final int position) {
        final LoanRequestEntity loanRequestEntity = lstLoan.get(position);

        holder.ivIcon.setImageResource(loanRequestEntity.getIcon());
        holder.tvLoanName.setText(loanRequestEntity.getLoanTitle());
        holder.tvLoanMoney.setText(loanRequestEntity.getLoanMoney());

        holder.tvCost.setText(loanRequestEntity.getCost());
        holder.tvInterest.setText(loanRequestEntity.getInterest());
        holder.tvState.setText(loanRequestEntity.getState() + "/" + loanRequestEntity.getStateCount());
        holder.tvCondition.setText(loanRequestEntity.getCondition());

        holder.btInfo.setTypeface(Commons.setFont(context, context.getResources().getString(R.string.font_segoe)), Typeface.NORMAL);

        if(pos == position) {
            presenter.setupAnimationOpenOrClose(context, holder.rlInfo, loanRequestEntity.isOpen());
        }

        if(loanRequestEntity.isOpen()) {
            holder.rlInfo.setVisibility(View.VISIBLE);
            holder.btInfo.setText(context.getString(R.string.info_button_open));
        } else {
            holder.rlInfo.setVisibility(View.GONE);
            holder.btInfo.setText(context.getString(R.string.info_button_close));
        }

        holder.btInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.openOrCloseInfo(context, holder.rlInfo, holder.btInfo, lstLoan.get(position).isOpen(), position);
                pos = position;
            }
        });

        holder.btLoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.goToLoanRegistration();
            }
        });
    }

    @Override
    public int getItemCount() {
        return lstLoan == null ? 0 : lstLoan.size();
    }

    public class ProfileViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.ivIcon)
        ImageView ivIcon;
        @BindView(R.id.tvLoanName)
        TextView tvLoanName;
        @BindView(R.id.tvLoanMoney)
        TextView tvLoanMoney;
        @BindView(R.id.llViewText)
        LinearLayout llViewText;
        @BindView(R.id.btLoan)
        Button btLoan;
        @BindView(R.id.rlTop)
        RelativeLayout rlTop;
        @BindView(R.id.tvLabelCost)
        TextView tvLabelCost;
        @BindView(R.id.tvCost)
        TextView tvCost;
        @BindView(R.id.tvState)
        TextView tvState;
        @BindView(R.id.tvLabelState)
        TextView tvLabelState;
        @BindView(R.id.tvLabelInterest)
        TextView tvLabelInterest;
        @BindView(R.id.tvInterest)
        TextView tvInterest;
        @BindView(R.id.tvLabelCondition)
        TextView tvLabelCondition;
        @BindView(R.id.tvCondition)
        TextView tvCondition;
        @BindView(R.id.rlInfo)
        RelativeLayout rlInfo;
        @BindView(R.id.btInfo)
        Button btInfo;

        ProfileViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
