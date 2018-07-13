package a1_score.tima.vn.a1_score_viper.Modules.UpdateFamily.Entity;

import com.google.gson.annotations.SerializedName;

public class FamilyResponse {

    @SerializedName("Family")
    private FamilyEntity family;
    @SerializedName("Message")
    private String message;
    @SerializedName("StatusCode")
    private int statuscode;

    public FamilyEntity getFamily() {
        return family;
    }

    public void setFamily(FamilyEntity family) {
        this.family = family;
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

    public static class FamilyEntity {
        @SerializedName("ChildrenNumber")
        private int childrennumber;
        @SerializedName("MarriageRegistrationId")
        private int marriageregistrationid;
        @SerializedName("FamilyPhone")
        private String familyphone;
        @SerializedName("FamilyName")
        private String familyname;
        @SerializedName("MarriageStatus")
        private int marriagestatus;

        public int getChildrennumber() {
            return childrennumber;
        }

        public void setChildrennumber(int childrennumber) {
            this.childrennumber = childrennumber;
        }

        public int getMarriageregistrationid() {
            return marriageregistrationid;
        }

        public void setMarriageregistrationid(int marriageregistrationid) {
            this.marriageregistrationid = marriageregistrationid;
        }

        public String getFamilyphone() {
            return familyphone;
        }

        public void setFamilyphone(String familyphone) {
            this.familyphone = familyphone;
        }

        public String getFamilyname() {
            return familyname;
        }

        public void setFamilyname(String familyname) {
            this.familyname = familyname;
        }

        public int getMarriagestatus() {
            return marriagestatus;
        }

        public void setMarriagestatus(int marriagestatus) {
            this.marriagestatus = marriagestatus;
        }
    }
}
