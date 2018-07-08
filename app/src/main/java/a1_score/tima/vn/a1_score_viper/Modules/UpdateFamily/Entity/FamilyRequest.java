package a1_score.tima.vn.a1_score_viper.Modules.UpdateFamily.Entity;

import com.google.gson.annotations.SerializedName;

public class FamilyRequest {
    @SerializedName("id")
    private int id;
    @SerializedName("RelationshipID")
    private int relationshipID;
    @SerializedName("Name")
    private String name;
    @SerializedName("phone")
    private String phone;

    public FamilyRequest(int id, int relationshipID, String name, String phone) {
        this.id = id;
        this.relationshipID = relationshipID;
        this.name = name;
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRelationshipID() {
        return relationshipID;
    }

    public void setRelationshipID(int relationshipID) {
        this.relationshipID = relationshipID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
