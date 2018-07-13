package a1_score.tima.vn.a1_score_viper.Modules.UpdateSocialNetwork.Entity;

import com.google.gson.annotations.SerializedName;

public class FacebookResponse {

    @SerializedName("FaceBookProfile")
    private FacebookprofileEntity facebookprofile;
    @SerializedName("Message")
    private String message;
    @SerializedName("StatusCode")
    private int statuscode;

    public FacebookprofileEntity getFacebookprofile() {
        return facebookprofile;
    }

    public void setFacebookprofile(FacebookprofileEntity facebookprofile) {
        this.facebookprofile = facebookprofile;
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

    public static class FacebookprofileEntity {
        @SerializedName("FaceBookEmail")
        private String facebookemail;
        @SerializedName("FaceBookAddress")
        private String facebookaddress;
        @SerializedName("FaceBookName")
        private String facebookname;
        @SerializedName("FaceBookId")
        private String facebookid;

        public String getFacebookemail() {
            return facebookemail;
        }

        public void setFacebookemail(String facebookemail) {
            this.facebookemail = facebookemail;
        }

        public String getFacebookaddress() {
            return facebookaddress;
        }

        public void setFacebookaddress(String facebookaddress) {
            this.facebookaddress = facebookaddress;
        }

        public String getFacebookname() {
            return facebookname;
        }

        public void setFacebookname(String facebookname) {
            this.facebookname = facebookname;
        }

        public String getFacebookid() {
            return facebookid;
        }

        public void setFacebookid(String facebookid) {
            this.facebookid = facebookid;
        }
    }
}
