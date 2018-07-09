package a1_score.tima.vn.a1_score_viper.Modules.UpdateFamily.Entity;

import com.google.gson.annotations.SerializedName;

public class FamilyRequest {

    @SerializedName("children_number")
    private int childrenNumber;
    @SerializedName("marriage_registration_id")
    private int marriageRegistrationId;
    @SerializedName("family_phone")
    private String familyPhone;
    @SerializedName("family_name")
    private String familyName;
    @SerializedName("merriage_status")
    private int merriageStatus;
    @SerializedName("username")
    private String username;

    public int getChildrenNumber() {
        return childrenNumber;
    }

    public void setChildrenNumber(int childrenNumber) {
        this.childrenNumber = childrenNumber;
    }

    public int getMarriageRegistrationId() {
        return marriageRegistrationId;
    }

    public void setMarriageRegistrationId(int marriageRegistrationId) {
        this.marriageRegistrationId = marriageRegistrationId;
    }

    public String getFamilyPhone() {
        return familyPhone;
    }

    public void setFamilyPhone(String familyPhone) {
        this.familyPhone = familyPhone;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public int getMerriageStatus() {
        return merriageStatus;
    }

    public void setMerriageStatus(int merriageStatus) {
        this.merriageStatus = merriageStatus;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
