package a1_score.tima.vn.a1_score_viper.Modules.LoanRequest.Entity;

import com.google.gson.annotations.SerializedName;

public class LoanEntity {
    @SerializedName("Profit")
    private long profit;
    @SerializedName("Fee")
    private long fee;
    @SerializedName("MaxDuration")
    private int maxduration;
    @SerializedName("MinDuration")
    private int minduration;
    @SerializedName("LevelRequiment")
    private int levelrequiment;
    @SerializedName("MaxValue")
    private long maxvalue;
    @SerializedName("MinValue")
    private long minvalue;
    @SerializedName("Name")
    private String name;
    @SerializedName("Id")
    private int id;
    @SerializedName("IconUrl")
    private String IconUrl;
    @SerializedName("IsOpen")
    private boolean isOpen;

    public long getProfit() {
        return profit;
    }

    public void setProfit(long profit) {
        this.profit = profit;
    }

    public long getFee() {
        return fee;
    }

    public void setFee(long fee) {
        this.fee = fee;
    }

    public int getMaxduration() {
        return maxduration;
    }

    public void setMaxduration(int maxduration) {
        this.maxduration = maxduration;
    }

    public int getMinduration() {
        return minduration;
    }

    public void setMinduration(int minduration) {
        this.minduration = minduration;
    }

    public int getLevelrequiment() {
        return levelrequiment;
    }

    public void setLevelrequiment(int levelrequiment) {
        this.levelrequiment = levelrequiment;
    }

    public long getMaxvalue() {
        return maxvalue;
    }

    public void setMaxvalue(long maxvalue) {
        this.maxvalue = maxvalue;
    }

    public long getMinvalue() {
        return minvalue;
    }

    public void setMinvalue(long minvalue) {
        this.minvalue = minvalue;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIconUrl() {
        return IconUrl;
    }

    public void setIconUrl(String iconUrl) {
        IconUrl = iconUrl;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }
}
