package a1_score.tima.vn.a1_score_viper.Modules.ChangePhone.Entity;

import com.google.gson.annotations.SerializedName;

public class UserPhoneRequest {
    @SerializedName("Id")
    private int id;
    @SerializedName("Username")
    private String username;
    @SerializedName("Fullname")
    private String fullname;
    @SerializedName("Phone")
    private String phone;
    @SerializedName("Email")
    private String email;
    @SerializedName("Password")
    private String password;
    @SerializedName("Role")
    private String role;
    @SerializedName("LastLoginTime")
    private long lastLoginTime;
    @SerializedName("CreatedDate")
    private long createdDate;
    @SerializedName("Actived")
    private int actived;

    public UserPhoneRequest(int id, String username, String fullname, String phone, String email, String password, String role, long lastLoginTime, long createdDate, int actived) {
        this.id = id;
        this.username = username;
        this.fullname = fullname;
        this.phone = phone;
        this.email = email;
        this.password = password;
        this.role = role;
        this.lastLoginTime = lastLoginTime;
        this.createdDate = createdDate;
        this.actived = actived;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public long getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(long lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public long getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(long createdDate) {
        this.createdDate = createdDate;
    }

    public int getActived() {
        return actived;
    }

    public void setActived(int actived) {
        this.actived = actived;
    }
}
