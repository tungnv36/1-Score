package a1_score.tima.vn.a1_score_viper.Modules.LoanAuthentication.Entity;

import com.google.gson.annotations.SerializedName;

public class LoanAuthResponse {

    @SerializedName("File")
    private FileEntity file;
    @SerializedName("Message")
    private String message;
    @SerializedName("StatusCode")
    private int statuscode;

    public FileEntity getFile() {
        return file;
    }

    public void setFile(FileEntity file) {
        this.file = file;
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

    public static class FileEntity {
        @SerializedName("Description")
        private String description;
        @SerializedName("FileType")
        private String filetype;
        @SerializedName("UrlFile")
        private String urlfile;
        @SerializedName("Id")
        private int id;

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getFiletype() {
            return filetype;
        }

        public void setFiletype(String filetype) {
            this.filetype = filetype;
        }

        public String getUrlfile() {
            return urlfile;
        }

        public void setUrlfile(String urlfile) {
            this.urlfile = urlfile;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }
}
