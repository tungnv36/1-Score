package a1_score.tima.vn.a1_score_viper.Modules.ChangePhone.Entity;

public class ChangePhoneUserEntity {
    private int Id;
    private String Username;
    private String Fullname;
    private String Phone;
    private String Email;
    private String Password;
    private String Role;
    private long LastLoginTime;
    private long CreatedDate;
    private int Actived;

    public ChangePhoneUserEntity(int id, String username, String fullname, String phone, String email, String password, String role, long lastLoginTime, long createdDate, int actived) {
        Id = id;
        Username = username;
        Fullname = fullname;
        Phone = phone;
        Email = email;
        Password = password;
        Role = role;
        LastLoginTime = lastLoginTime;
        CreatedDate = createdDate;
        Actived = actived;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getFullname() {
        return Fullname;
    }

    public void setFullname(String fullname) {
        Fullname = fullname;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getRole() {
        return Role;
    }

    public void setRole(String role) {
        Role = role;
    }

    public long getLastLoginTime() {
        return LastLoginTime;
    }

    public void setLastLoginTime(long lastLoginTime) {
        LastLoginTime = lastLoginTime;
    }

    public long getCreatedDate() {
        return CreatedDate;
    }

    public void setCreatedDate(long createdDate) {
        CreatedDate = createdDate;
    }

    public int getActived() {
        return Actived;
    }

    public void setActived(int actived) {
        Actived = actived;
    }
}
