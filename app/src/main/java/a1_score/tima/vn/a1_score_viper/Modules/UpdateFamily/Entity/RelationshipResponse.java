package a1_score.tima.vn.a1_score_viper.Modules.UpdateFamily.Entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RelationshipResponse {

    @SerializedName("Relationships")
    private List<RelationshipsEntity> relationships;
    @SerializedName("Message")
    private String message;
    @SerializedName("StatusCode")
    private int statuscode;

    public List<RelationshipsEntity> getRelationships() {
        return relationships;
    }

    public void setRelationships(List<RelationshipsEntity> relationships) {
        this.relationships = relationships;
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

    public static class RelationshipsEntity {
        @SerializedName("BirthCertificateId")
        private int birthcertificateid;
        @SerializedName("StudentCardId")
        private int studentcardid;
        @SerializedName("RelationshipPhone")
        private String relationshipphone;
        @SerializedName("RelationshipName")
        private String relationshipname;
        @SerializedName("RelationshipTypeId")
        private int relationshiptypeid;

        public int getBirthcertificateid() {
            return birthcertificateid;
        }

        public void setBirthcertificateid(int birthcertificateid) {
            this.birthcertificateid = birthcertificateid;
        }

        public int getStudentcardid() {
            return studentcardid;
        }

        public void setStudentcardid(int studentcardid) {
            this.studentcardid = studentcardid;
        }

        public String getRelationshipphone() {
            return relationshipphone;
        }

        public void setRelationshipphone(String relationshipphone) {
            this.relationshipphone = relationshipphone;
        }

        public String getRelationshipname() {
            return relationshipname;
        }

        public void setRelationshipname(String relationshipname) {
            this.relationshipname = relationshipname;
        }

        public int getRelationshiptypeid() {
            return relationshiptypeid;
        }

        public void setRelationshiptypeid(int relationshiptypeid) {
            this.relationshiptypeid = relationshiptypeid;
        }
    }
}
