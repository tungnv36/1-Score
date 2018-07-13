package a1_score.tima.vn.a1_score_viper.Modules.UpdateFamily.Entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RelationshipDictionaryResponse {

    @SerializedName("RelationshipType")
    private List<RelationshipTypeEntity> relationshiptype;
    @SerializedName("Message")
    private String message;
    @SerializedName("StatusCode")
    private int statuscode;

    public List<RelationshipTypeEntity> getRelationshiptype() {
        return relationshiptype;
    }

    public void setRelationshiptype(List<RelationshipTypeEntity> relationshiptype) {
        this.relationshiptype = relationshiptype;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatuscode() {
        return statuscode;
    }

    public void setStatuscode(int statuscode) {
        this.statuscode = statuscode;
    }

    public static class RelationshipTypeEntity {
        @SerializedName("TypeName")
        private String typename;
        @SerializedName("Id")
        private int id;

        public String getTypename() {
            return typename;
        }

        public void setTypename(String typename) {
            this.typename = typename;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }
}
