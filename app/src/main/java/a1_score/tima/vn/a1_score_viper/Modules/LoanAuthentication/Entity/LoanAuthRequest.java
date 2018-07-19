package a1_score.tima.vn.a1_score_viper.Modules.LoanAuthentication.Entity;

import com.google.gson.annotations.SerializedName;

public class LoanAuthRequest {
    @SerializedName("usename")
    private String username;
    @SerializedName("description")
    private String description;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
