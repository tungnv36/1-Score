package a1_score.tima.vn.a1_score_viper.Modules.LoanRequest.View;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import a1_score.tima.vn.a1_score_viper.Common.Commons;
import a1_score.tima.vn.a1_score_viper.Modules.LoanRequest.Entity.LoanRequest;
import a1_score.tima.vn.a1_score_viper.Modules.LoanRequest.Interface.LoanRequestInterface;
import a1_score.tima.vn.a1_score_viper.R;
import butterknife.BindView;
import butterknife.ButterKnife;

public class LoanRequestAdapter extends RecyclerView.Adapter<LoanRequestAdapter.ProfileViewHolder> {

    private List<LoanRequest> mLoanList;
    private Context mContext;
    private LoanRequestInterface.Presenter mPresenter;

    private int mPos = -1;

    public LoanRequestAdapter(Context context, LoanRequestInterface.Presenter presenter, List<LoanRequest> loanList) {
        mLoanList = loanList;
        mContext = context;
        mPresenter = presenter;
    }

    @NonNull
    @Override
    public ProfileViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_loan_request, parent, false);
        mPresenter.setupAnimationItem(mContext, view);
        return new ProfileViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ProfileViewHolder holder, final int position) {
        final LoanRequest loanRequest = mLoanList.get(position);

        holder.ivIcon.setImageResource(loanRequest.getIcon());
        holder.tvLoanName.setText(loanRequest.getLoanTitle());
        holder.tvLoanMoney.setText(loanRequest.getLoanMoney());

        holder.tvCost.setText(loanRequest.getCost());
        holder.tvInterest.setText(loanRequest.getInterest());
        holder.tvState.setText(loanRequest.getState() + "/" + loanRequest.getStateCount());
        holder.tvCondition.setText(loanRequest.getCondition());

        holder.btInfo.setTypeface(Commons.setFont(mContext, mContext.getResources().getString(R.string.font_segoe)), Typeface.NORMAL);

        if(mPos == position) {
            mPresenter.setupAnimationOpenOrClose(mContext, holder.rlInfo, loanRequest.isOpen());
        }

        if(loanRequest.isOpen()) {
            holder.rlInfo.setVisibility(View.VISIBLE);
            holder.btInfo.setText(mContext.getString(R.string.info_button_open));
        } else {
            holder.rlInfo.setVisibility(View.GONE);
            holder.btInfo.setText(mContext.getString(R.string.info_button_close));
        }

        holder.btInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.openOrCloseInfo(mContext, holder.rlInfo, holder.btInfo, mLoanList.get(position).isOpen(), position);
                mPos = position;
            }
        });

        holder.btLoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.goToLoanRegistration();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mLoanList == null ? 0 : mLoanList.size();
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
