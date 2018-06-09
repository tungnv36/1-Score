package a1_score.tima.vn.a1_score_viper.Modules.LoanRequest.Entity;

public class LoanRequestEntity {

    private int icon;
    private String loanTitle;
    private String loanMoney;
    private String cost;
    private String interest;
    private int state;
    private int stateCount;
    private String condition;
    private boolean isOpen;

    public LoanRequestEntity(int icon, String loanTitle, String loanMoney, String cost, String interest, int state, int stateCount, String condition, boolean isOpen) {
        this.icon = icon;
        this.loanTitle = loanTitle;
        this.loanMoney = loanMoney;
        this.cost = cost;
        this.interest = interest;
        this.state = state;
        this.stateCount = stateCount;
        this.condition = condition;
        this.isOpen = isOpen;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getLoanTitle() {
        return loanTitle;
    }

    public void setLoanTitle(String loanTitle) {
        this.loanTitle = loanTitle;
    }

    public String getLoanMoney() {
        return loanMoney;
    }

    public void setLoanMoney(String loanMoney) {
        this.loanMoney = loanMoney;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getInterest() {
        return interest;
    }

    public void setInterest(String interest) {
        this.interest = interest;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getStateCount() {
        return stateCount;
    }

    public void setStateCount(int stateCount) {
        this.stateCount = stateCount;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }
}
