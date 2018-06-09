package a1_score.tima.vn.a1_score_viper.Modules.UpdateFamily.Entity;

public class UpdateFamilyEntity {
    private int id;
    private int RelationshipID;
    private String Name;
    private String phone;

    public UpdateFamilyEntity(int id, int relationshipID, String name, String phone) {
        this.id = id;
        RelationshipID = relationshipID;
        Name = name;
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRelationshipID() {
        return RelationshipID;
    }

    public void setRelationshipID(int relationshipID) {
        RelationshipID = relationshipID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
