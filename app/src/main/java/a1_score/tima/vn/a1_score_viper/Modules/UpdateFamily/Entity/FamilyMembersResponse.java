package a1_score.tima.vn.a1_score_viper.Modules.UpdateFamily.Entity;

import com.google.gson.annotations.SerializedName;

public class FamilyMembersResponse {

    @SerializedName("Relationship")
    private RelationshipEntity relationship;
    @SerializedName("Message")
    private String message;
    @SerializedName("StatusCode")
    private int statuscode;

    public RelationshipEntity getRelationship() {
        return relationship;
    }

    public void setRelationship(RelationshipEntity relationship) {
        this.relationship = relationship;
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

    public static class RelationshipEntity {
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
