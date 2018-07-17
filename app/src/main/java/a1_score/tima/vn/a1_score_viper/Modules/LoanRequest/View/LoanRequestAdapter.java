package a1_score.tima.vn.a1_score_viper.Modules.LoanRequest.View;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
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
import a1_score.tima.vn.a1_score_viper.Modules.LoanRequest.Entity.LoanEntity;
import a1_score.tima.vn.a1_score_viper.Modules.LoanRequest.Entity.LoanRequest;
import a1_score.tima.vn.a1_score_viper.Modules.LoanRequest.Entity.LoanResponse;
import a1_score.tima.vn.a1_score_viper.Modules.LoanRequest.Interface.LoanRequestInterface;
import a1_score.tima.vn.a1_score_viper.R;
import butterknife.BindView;
import butterknife.ButterKnife;

public class LoanRequestAdapter extends RecyclerView.Adapter<LoanRequestAdapter.ProfileViewHolder> {

    private List<LoanEntity> mLoanEntities;
    private Context mContext;
    private LoanRequestInterface.Presenter mPresenter;

    private int mPos = -1;

    public LoanRequestAdapter(Context context, LoanRequestInterface.Presenter presenter, List<LoanEntity> loanEntities) {
        mLoanEntities = loanEntities;
        mContext = context;
        mPresenter = presenter;
    }

    @NonNull
    @Override
    public ProfileViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_loan_request, parent, false);
//        mPresenter.setupAnimationItem(mContext, view);
        return new ProfileViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ProfileViewHolder holder, final int position) {
        final LoanEntity loanEntity = mLoanEntities.get(position);

        if(loanEntity.getIconUrl() != null && !loanEntity.getIconUrl().isEmpty()) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    final Bitmap bmp =  Commons.getBitmapFromURL(loanEntity.getIconUrl());
                    ((Activity)mContext).runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            holder.ivIcon.setImageBitmap(bmp);
                        }
                    });
                }
            }).start();
        }
        holder.tvLoanName.setText(loanEntity.getName());
        holder.tvLoanMoney.setText(Commons.formatMoney(loanEntity.getMaxvalue()));

        holder.tvCost.setText(Commons.formatMoney(loanEntity.getFee()));
        holder.tvInterest.setText(Commons.formatMoney(loanEntity.getProfit()));
        holder.tvState.setText(LoanRequestView.sLevel + "/" + loanEntity.getLevelrequiment());
        holder.tvCondition.setText(String.format("Level %d", loanEntity.getLevelrequiment()));

        holder.btInfo.setTypeface(Commons.setFont(mContext, mContext.getResources().getString(R.string.font_segoe)), Typeface.NORMAL);

        if(LoanRequestView.sLevel >= loanEntity.getLevelrequiment()) {
            holder.btLoan.setEnabled(true);
        } else {
            holder.btLoan.setEnabled(false);
        }

//        if(mPos == position) {
//            mPresenter.setupAnimationOpenOrClose(mContext, holder.rlInfo, loanEntity.isOpen());
//        }

        if(loanEntity.isOpen()) {
            holder.rlInfo.setVisibility(View.VISIBLE);
            holder.btInfo.setText(mContext.getString(R.string.info_button_open));
        } else {
            holder.rlInfo.setVisibility(View.GONE);
            holder.btInfo.setText(mContext.getString(R.string.info_button_close));
        }

        holder.btInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.openOrCloseInfo(mContext, holder.rlInfo, holder.btInfo, mLoanEntities.get(position).isOpen(), position);
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
        return mLoanEntities == null ? 0 : mLoanEntities.size();
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
