package a1_score.tima.vn.a1_score_viper.Modules.UpdateFamily.Entity;

import com.google.gson.annotations.SerializedName;

public class FamilyMembersRequest {

    @SerializedName("birth_certificate_id")
    private int birthCertificateId;
    @SerializedName("student_card_id")
    private int studentCardId;
    @SerializedName("relationship_phone")
    private String relationshipPhone;
    @SerializedName("relationship_name")
    private String relationshipName;
    @SerializedName("relationship_type_id")
    private int relationshipTypeId;
    @SerializedName("username")
    private String username;

    public int getBirthCertificateId() {
        return birthCertificateId;
    }

    public void setBirthCertificateId(int birthCertificateId) {
        this.birthCertificateId = birthCertificateId;
    }

    public int getStudentCardId() {
        return studentCardId;
    }

    public void setStudentCardId(int studentCardId) {
        this.studentCardId = studentCardId;
    }

    public String getRelationshipPhone() {
        return relationshipPhone;
    }

    public void setRelationshipPhone(String relationshipPhone) {
        this.relationshipPhone = relationshipPhone;
    }

    public String getRelationshipName() {
        return relationshipName;
    }

    public void setRelationshipName(String relationshipName) {
        this.relationshipName = relationshipName;
    }

    public int getRelationshipTypeId() {
        return relationshipTypeId;
    }

    public void setRelationshipTypeId(int relationshipTypeId) {
        this.relationshipTypeId = relationshipTypeId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
