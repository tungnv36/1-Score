package a1_score.tima.vn.a1_score_viper.Common.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import a1_score.tima.vn.a1_score_viper.Common.Commons;
import a1_score.tima.vn.a1_score_viper.Modules.Login.Entity.LoginResponse;
import a1_score.tima.vn.a1_score_viper.Modules.UpdateJob.Entity.JobDictionaryResponse;
import a1_score.tima.vn.a1_score_viper.Modules.UpdateJob.Entity.ColleagueResponse;
import a1_score.tima.vn.a1_score_viper.Modules.UpdateJob.Entity.JobResponse;
import a1_score.tima.vn.a1_score_viper.Modules.UpdateProfile.Entity.ProfileRequest;
import a1_score.tima.vn.a1_score_viper.Modules.UpdateProfile.Entity.ImageProfileResponse;

public class SQliteDatabase extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "onescore";
    private static final int DATABASE_VERSION = 1;

    //Create table images
    private static final String TABLE_NAME_IMAGES = "images";
    private static final String KEY_IMAGES_ID = "Id";
    private static final String KEY_IMAGES_URL = "Url";
    private static final String KEY_IMAGES_FORMAT = "Format";
    private static final String KEY_IMAGES_NAME = "Image_Name";
    private static final String KEY_IMAGES_USER = "User";
    private static final String KEY_IMAGES_TYPE = "Image_Type";

    //Create table profile
    private static final String TABLE_NAME_PROFILE = "profile";
    private static final String KEY_PROFILE_ID = "Id";
    private static final String KEY_PROFILE_USERNAME = "username";
    private static final String KEY_PROFILE_FULLNAME = "fullname";
    private static final String KEY_PROFILE_DATE_OF_BIRTH = "date_of_birth";
    private static final String KEY_PROFILE_ADDRESS = "address";
    private static final String KEY_PROFILE_ID_NUMBER = "id_number";
    private static final String KEY_PROFILE_ID_IMAGE_1 = "id_image_1";
    private static final String KEY_PROFILE_ID_IMAGE_2 = "id_image_2";
    private static final String KEY_PROFILE_BANK_ACC_NUMBER = "bank_acc_number";
    private static final String KEY_PROFILE_CARD_TERM = "card_term";
    private static final String KEY_PROFILE_CARD_IMAGE = "card_image";
    private static final String KEY_PROFILE_SEX = "sex";

    //Create table user
    private static final String TABLE_NAME_USER = "user";
    private static final String KEY_USER_ID = "UserId";
    private static final String KEY_USER_NAME = "Username";
    private static final String KEY_USER_FULLNAME = "Fullname";
    private static final String KEY_USER_DATE_OF_BIRTH = "DateOfBirth";
    private static final String KEY_USER_ID_NUMBER = "IdNumber";
    private static final String KEY_USER_ADDRESS = "Address";
    private static final String KEY_USER_ACC_NUMBER = "BankAccNumber";
    private static final String KEY_USER_CARD_TERM = "CardTerm";
    private static final String KEY_USER_SEX = "Sex";
    private static final String KEY_USER_SCORED = "Scored";
    private static final String KEY_USER_LEVEL = "Level";
    private static final String KEY_USER_URL_IMAGE1 = "UrlImage1";
    private static final String KEY_USER_URL_IMAGE2 = "UrlImage2";
    private static final String KEY_USER_URL_CARD_IMAGE = "UrlCardImage";
    private static final String KEY_USER_URL_AVATAR = "UrlAvatar";
    private static final String KEY_USER_PROGRESS = "Progress";

    //create table jobs dic
    private static final String TABLE_NAME_JOBS_DIC = "jobDictionary";
    private static final String KEY_JOBS_DIC_ID = "Id";
    private static final String KEY_JOBS_DIC_JOB_TYPE = "JobType";

    //create table positions dic
    private static final String TABLE_NAME_POSITIONS_DIC = "positionDictionary";
    private static final String KEY_POSITIONS_DIC_ID = "Id";
    private static final String KEY_POSITIONS_DIC_JOB_TYPE = "Position";

    //create table salaries dic
    private static final String TABLE_NAME_SALARIES_DIC = "salaryDictionary";
    private static final String KEY_SALARIES_DIC_ID = "Id";
    private static final String KEY_SALARIES_DIC_POSITION = "Salary";

    //create table job
    private static final String TABLE_NAME_JOB = "job";
    private static final String KEY_JOB_USERNAME = "Username";
    private static final String KEY_JOB_JOB_ID = "JobId";
    private static final String KEY_JOB_COMPANY_NAME = "CompanyName";
    private static final String KEY_JOB_POSITION_ID = "PositionId";
    private static final String KEY_JOB_SALARY_ID = "SalaryId";
    private static final String KEY_JOB_CV_ID = "CvId";
    private static final String KEY_JOB_CONTRACT_ID = "ContractId";
    private static final String KEY_JOB_SALARY_BOARD_ID = "SalaryBoardId";

    //create table colleague
    private static  final String TABLE_NAME_COLLEAGUE = "colleague";
    private static  final String KEY_COLLEAGUE_USERNAME = "username";
    private static  final String KEY_COLLEAGUE_NAME = "ColleagueName";
    private static  final String KEY_COLLEAGUE_PHONE = "ColleaguePhone";

    public static SQliteDatabase mInstance;

    public static SQliteDatabase getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new SQliteDatabase(context);
        }
        return mInstance;
    }

    private SQliteDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String create_images_table = String.format("CREATE TABLE IF NOT EXISTS %s(%s INTEGER, %s TEXT, %s TEXT, %s TEXT, %s TEXT, %s TEXT)",
                TABLE_NAME_IMAGES, KEY_IMAGES_ID, KEY_IMAGES_URL, KEY_IMAGES_TYPE, KEY_IMAGES_NAME, KEY_IMAGES_USER, KEY_IMAGES_FORMAT);
        db.execSQL(create_images_table);
        String create_profile_table = String.format("CREATE TABLE IF NOT EXISTS %s(%s INTEGER PRIMARY KEY, %s TEXT, %s TEXT, %s TEXT, %s TEXT, %s TEXT, %s INTEGER, %s INTEGER, %s TEXT, %s TEXT, %s TEXT, %s INTEGER)",
                TABLE_NAME_PROFILE, KEY_PROFILE_ID, KEY_PROFILE_USERNAME, KEY_PROFILE_FULLNAME, KEY_PROFILE_DATE_OF_BIRTH, KEY_PROFILE_ADDRESS,
                KEY_PROFILE_ID_NUMBER, KEY_PROFILE_ID_IMAGE_1, KEY_PROFILE_ID_IMAGE_2, KEY_PROFILE_BANK_ACC_NUMBER, KEY_PROFILE_CARD_TERM,
                KEY_PROFILE_CARD_IMAGE, KEY_PROFILE_SEX);
        String create_user_table = String.format("CREATE TABLE IF NOT EXISTS %s(%s TEXT PRIMARY KEY, %s TEXT, %s TEXT, %s TEXT, %s TEXT, %s TEXT, %s TEXT, %s TEXT, %s INTEGER, %s INTEGER, %s INTEGER, %s TEXT, %s TEXT, %s TEXT, %s TEXT, %s INTEGER)",
                TABLE_NAME_USER, KEY_USER_ID, KEY_USER_NAME, KEY_USER_FULLNAME, KEY_USER_DATE_OF_BIRTH,
                KEY_USER_ID_NUMBER, KEY_USER_ADDRESS, KEY_USER_ACC_NUMBER, KEY_USER_CARD_TERM, KEY_USER_SEX,
                KEY_USER_SCORED, KEY_USER_LEVEL, KEY_USER_URL_IMAGE1, KEY_USER_URL_IMAGE2, KEY_USER_URL_CARD_IMAGE, KEY_USER_URL_AVATAR, KEY_USER_PROGRESS);
        String create_jobs_dic_table = String.format("CREATE TABLE IF NOT EXISTS %s(%s INTEGER, %s TEXT)",
                TABLE_NAME_JOBS_DIC, KEY_JOBS_DIC_ID, KEY_JOBS_DIC_JOB_TYPE);
        String create_positions_dic_table = String.format("CREATE TABLE IF NOT EXISTS %s(%s INTEGER, %s TEXT)",
                TABLE_NAME_POSITIONS_DIC, KEY_POSITIONS_DIC_ID, KEY_POSITIONS_DIC_JOB_TYPE);
        String create_salaries_dic_table = String.format("CREATE TABLE IF NOT EXISTS %s(%s INTEGER, %s TEXT)",
                TABLE_NAME_SALARIES_DIC, KEY_SALARIES_DIC_ID, KEY_SALARIES_DIC_POSITION);
        String create_job_table = String.format("CREATE TABLE IF NOT EXISTS %s(%s TEXT, %s INTEGER, %s TEXT, %s INTEGER, %s INTEGER, %s INTEGER, %s INTEGER, %s INTEGER)",
                TABLE_NAME_JOB,
                KEY_JOB_USERNAME,
                KEY_JOB_JOB_ID,
                KEY_JOB_COMPANY_NAME,
                KEY_JOB_POSITION_ID,
                KEY_JOB_SALARY_ID,
                KEY_JOB_CV_ID,
                KEY_JOB_CONTRACT_ID,
                KEY_JOB_SALARY_BOARD_ID);
        String create_colleague_table = String.format("CREATE TABLE IF NOT EXISTS %s(%s TEXT, %s TEXT, %s TEXT)",
                TABLE_NAME_JOB,
                KEY_COLLEAGUE_USERNAME,
                KEY_COLLEAGUE_NAME,
                KEY_COLLEAGUE_PHONE);

        db.execSQL(create_images_table);
        db.execSQL(create_profile_table);
        db.execSQL(create_user_table);
        db.execSQL(create_jobs_dic_table);
        db.execSQL(create_positions_dic_table);
        db.execSQL(create_salaries_dic_table);
        db.execSQL(create_job_table);
        db.execSQL(create_colleague_table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String drop_images_table = String.format("DROP TABLE IF EXISTS %s", TABLE_NAME_IMAGES);
        String drop_profile_table = String.format("DROP TABLE IF EXISTS %s", TABLE_NAME_PROFILE);
        String drop_user_table = String.format("DROP TABLE IF EXISTS %s", TABLE_NAME_USER);
        String drop_jobs_dic_table = String.format("DROP TABLE IF EXISTS %s", TABLE_NAME_JOBS_DIC);
        String drop_positions_dic_table = String.format("DROP TABLE IF EXISTS %s", TABLE_NAME_POSITIONS_DIC);
        String drop_salaries_dic_table = String.format("DROP TABLE IF EXISTS %s", TABLE_NAME_SALARIES_DIC);
        String drop_job_table = String.format("DROP TABLE IF EXISTS %s", TABLE_NAME_JOB);
        String drop_colleague_table = String.format("DROP TABLE IF EXISTS %s", TABLE_NAME_COLLEAGUE);

        db.execSQL(drop_images_table);
        db.execSQL(drop_profile_table);
        db.execSQL(drop_user_table);
        db.execSQL(drop_jobs_dic_table);
        db.execSQL(drop_positions_dic_table);
        db.execSQL(drop_salaries_dic_table);
        db.execSQL(drop_job_table);
        db.execSQL(drop_colleague_table);

        onCreate(db);
    }

    //---------------user---------------
    public void addUser(LoginResponse loginResponse) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_USER_ID, loginResponse.getUser().getUserid());
        values.put(KEY_USER_NAME, Commons.changePhone0(loginResponse.getUser().getUsername()));
        values.put(KEY_USER_FULLNAME, loginResponse.getUser().getFullname());
        values.put(KEY_USER_DATE_OF_BIRTH, loginResponse.getUser().getDateofbirth());
        values.put(KEY_USER_ID_NUMBER, loginResponse.getUser().getIdnumber());
        values.put(KEY_USER_ADDRESS, loginResponse.getUser().getAddress());
        values.put(KEY_USER_ACC_NUMBER, loginResponse.getUser().getBankaccnumber());
        values.put(KEY_USER_CARD_TERM, loginResponse.getUser().getCardterm());
        values.put(KEY_USER_SEX, loginResponse.getUser().getSex());
        values.put(KEY_USER_SCORED, loginResponse.getUser().getScored());
        values.put(KEY_USER_LEVEL, loginResponse.getUser().getLevel());
        values.put(KEY_USER_URL_IMAGE1, loginResponse.getUser().getUrlimage1());
        values.put(KEY_USER_URL_IMAGE2, loginResponse.getUser().getUrlimage2());
        values.put(KEY_USER_URL_CARD_IMAGE, loginResponse.getUser().getUrlcardimage());
        values.put(KEY_USER_URL_AVATAR, loginResponse.getUser().getUrlavatar());
        values.put(KEY_USER_PROGRESS, loginResponse.getUser().getProgress());

        db.insert(TABLE_NAME_USER, null, values);
        db.close();
    }

    public void updateUser(String oldPhone, String newPhone) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_USER_NAME, Commons.changePhone0(newPhone));

        db.update(TABLE_NAME_USER, values, String.format("%s=?", KEY_USER_NAME), new String[]{ oldPhone });
        db.close();
    }

    public void deleteUser() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME_USER, null, null);
        db.close();
    }

    public LoginResponse.UserEntity getUser() {
        LoginResponse.UserEntity userEntity = new LoginResponse.UserEntity();
        SQLiteDatabase db = this.getReadableDatabase();
        String countQuery = String.format("SELECT * FROM %s", TABLE_NAME_USER);
        Cursor cursor = db.rawQuery(countQuery, null);
        if(cursor.moveToFirst()) {
            userEntity.setUserid(cursor.getString(cursor.getColumnIndex(KEY_USER_ID)));
            userEntity.setUsername(cursor.getString(cursor.getColumnIndex(KEY_USER_NAME)));
            userEntity.setFullname(cursor.getString(cursor.getColumnIndex(KEY_USER_FULLNAME)));
            userEntity.setDateofbirth(cursor.getString(cursor.getColumnIndex(KEY_USER_DATE_OF_BIRTH)));
            userEntity.setIdnumber(cursor.getString(cursor.getColumnIndex(KEY_USER_ID_NUMBER)));
            userEntity.setAddress(cursor.getString(cursor.getColumnIndex(KEY_USER_ADDRESS)));
            userEntity.setBankaccnumber(cursor.getString(cursor.getColumnIndex(KEY_USER_ACC_NUMBER)));
            userEntity.setCardterm(cursor.getString(cursor.getColumnIndex(KEY_USER_CARD_TERM)));
            userEntity.setSex(cursor.getInt(cursor.getColumnIndex(KEY_USER_SEX)));
            userEntity.setScored(cursor.getInt(cursor.getColumnIndex(KEY_USER_SCORED)));
            userEntity.setLevel(cursor.getInt(cursor.getColumnIndex(KEY_USER_LEVEL)));
            userEntity.setUrlimage1(cursor.getString(cursor.getColumnIndex(KEY_USER_URL_IMAGE1)));
            userEntity.setUrlimage2(cursor.getString(cursor.getColumnIndex(KEY_USER_URL_IMAGE2)));
            userEntity.setUrlcardimage(cursor.getString(cursor.getColumnIndex(KEY_USER_URL_CARD_IMAGE)));
            userEntity.setUrlavatar(cursor.getString(cursor.getColumnIndex(KEY_USER_URL_AVATAR)));
            userEntity.setProgress(cursor.getInt(cursor.getColumnIndex(KEY_USER_PROGRESS)));
        }
        cursor.close();
        return userEntity;
    }

    //---------------Images---------------
    public void addImage(ImageProfileResponse imageProfileResponse, String imageName, String username, String type) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_IMAGES_ID, imageProfileResponse.getImage().getId());
        values.put(KEY_IMAGES_URL, imageProfileResponse.getImage().getUrl());
        values.put(KEY_IMAGES_TYPE, type);
        values.put(KEY_IMAGES_NAME, username + imageName);
        values.put(KEY_IMAGES_USER, username);
        values.put(KEY_IMAGES_FORMAT, imageProfileResponse.getImage().getImagetype());

        db.insert(TABLE_NAME_IMAGES, null, values);
        db.close();
    }

    public void deleteImageBy(String username, String type) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME_IMAGES, String.format("%s=? AND %s=?", KEY_IMAGES_USER, KEY_IMAGES_TYPE), new String[] { username, type });
        db.close();
    }

    public int getImageID(String username, String type) {
        SQLiteDatabase db = this.getReadableDatabase();
        String countQuery = String.format("SELECT %s FROM %s WHERE %s = '%s' AND %s = '%s'", KEY_IMAGES_ID, TABLE_NAME_IMAGES, KEY_IMAGES_USER, username, KEY_IMAGES_TYPE, type);
        Cursor cursor = db.rawQuery(countQuery, null);
        if(cursor.moveToFirst()) {
            int id = cursor.getInt(0);
            return id;
        }
        cursor.close();
        return 0;
    }

    //---------------Profile---------------
    public void addProfile(ProfileRequest profileRequest) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_PROFILE_USERNAME, profileRequest.getUsername());
        values.put(KEY_PROFILE_FULLNAME, profileRequest.getFullname());
        values.put(KEY_PROFILE_DATE_OF_BIRTH, profileRequest.getDateOfBirth());
        values.put(KEY_PROFILE_ADDRESS, profileRequest.getAddress());
        values.put(KEY_PROFILE_ID_NUMBER, profileRequest.getIdNumber());
        values.put(KEY_PROFILE_ID_IMAGE_1, profileRequest.getIdImage1());
        values.put(KEY_PROFILE_ID_IMAGE_2, profileRequest.getIdImage2());
        values.put(KEY_PROFILE_BANK_ACC_NUMBER, profileRequest.getBankAccNumber());
        values.put(KEY_PROFILE_CARD_TERM, profileRequest.getCardTerm());
        values.put(KEY_PROFILE_CARD_IMAGE, profileRequest.getIdCardImage());
        values.put(KEY_PROFILE_SEX, profileRequest.getSex());

        db.insert(TABLE_NAME_PROFILE, null, values);
        db.close();
    }

    public void updateFullName(String username, String fullname) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_PROFILE_FULLNAME, fullname);

        db.update(TABLE_NAME_PROFILE, values, String.format("%s = ?", KEY_PROFILE_USERNAME), new String[]{username});
        db.close();
    }

    public ProfileRequest getProfileByPhone(String username) {
        ProfileRequest profileRequest = new ProfileRequest();
        SQLiteDatabase db = this.getReadableDatabase();
        String countQuery = String.format("SELECT * FROM %s WHERE %s = '%s'", TABLE_NAME_PROFILE, KEY_PROFILE_USERNAME, username);
        Cursor cursor = db.rawQuery(countQuery, null);
        if(cursor.moveToFirst()) {
            do {
                profileRequest.setUsername(cursor.getString(cursor.getColumnIndex(KEY_PROFILE_USERNAME)));
                profileRequest.setFullname(cursor.getString(cursor.getColumnIndex(KEY_PROFILE_FULLNAME)));
                profileRequest.setDateOfBirth(cursor.getString(cursor.getColumnIndex(KEY_PROFILE_DATE_OF_BIRTH)));
                profileRequest.setAddress(cursor.getString(cursor.getColumnIndex(KEY_PROFILE_ADDRESS)));
                profileRequest.setIdNumber(cursor.getString(cursor.getColumnIndex(KEY_PROFILE_ID_NUMBER)));
                profileRequest.setIdImage1(cursor.getInt(cursor.getColumnIndex(KEY_PROFILE_ID_IMAGE_1)));
                profileRequest.setIdImage2(cursor.getInt(cursor.getColumnIndex(KEY_PROFILE_ID_IMAGE_2)));
                profileRequest.setBankAccNumber(cursor.getString(cursor.getColumnIndex(KEY_PROFILE_BANK_ACC_NUMBER)));
                profileRequest.setCardTerm(cursor.getString(cursor.getColumnIndex(KEY_PROFILE_CARD_TERM)));
                profileRequest.setIdCardImage(cursor.getInt(cursor.getColumnIndex(KEY_PROFILE_CARD_IMAGE)));
                profileRequest.setSex(cursor.getInt(cursor.getColumnIndex(KEY_PROFILE_SEX)));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return profileRequest;
    }

    //------------------jobs dictionary----------------
    public long addJobDic(JobDictionaryResponse.JobsEntity jobsEntity) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_JOBS_DIC_ID, jobsEntity.getId());
        values.put(KEY_JOBS_DIC_JOB_TYPE, jobsEntity.getJobtype());

        long result = db.insert(TABLE_NAME_JOBS_DIC, null, values);
        db.close();
        return result;
    }

    public List<JobDictionaryResponse.JobsEntity> getJobsDic() {
        List<JobDictionaryResponse.JobsEntity> jobsEntities = new ArrayList<>();
        JobDictionaryResponse.JobsEntity jobsEntity = new JobDictionaryResponse.JobsEntity();
        SQLiteDatabase db = this.getReadableDatabase();
        String countQuery = String.format("SELECT * FROM %s", TABLE_NAME_JOBS_DIC);
        Cursor cursor = db.rawQuery(countQuery, null);
        if(cursor.moveToFirst()) {
            do {
                jobsEntity.setId(cursor.getInt(cursor.getColumnIndex(KEY_JOBS_DIC_ID)));
                jobsEntity.setJobtype(cursor.getString(cursor.getColumnIndex(KEY_JOBS_DIC_JOB_TYPE)));
            } while (cursor.moveToNext());
            jobsEntities.add(jobsEntity);
        }
        cursor.close();
        return jobsEntities;
    }

    public int checkJobsDicExist() {
        SQLiteDatabase db = this.getReadableDatabase();
        String countQuery = String.format("SELECT COUNT(*) FROM %s", TABLE_NAME_JOBS_DIC);
        Cursor cursor = db.rawQuery(countQuery, null);
        if(cursor.moveToFirst()) {
            int id = cursor.getInt(0);
            return id;
        }
        cursor.close();
        return 0;
    }

    public void deleteAllJobsDic() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME_JOBS_DIC, null, null);
        db.close();
    }

    //------------------positions dictionary----------------
    public long addPositionDic(JobDictionaryResponse.PositionsEntity positionsEntity) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_POSITIONS_DIC_ID, positionsEntity.getId());
        values.put(KEY_POSITIONS_DIC_JOB_TYPE, positionsEntity.getPosition());

        long result = db.insert(TABLE_NAME_POSITIONS_DIC, null, values);
        db.close();
        return result;
    }

    public List<JobDictionaryResponse.PositionsEntity> getPositionsDic() {
        List<JobDictionaryResponse.PositionsEntity> positionsEntities = new ArrayList<>();
        JobDictionaryResponse.PositionsEntity positionsEntity = new JobDictionaryResponse.PositionsEntity();
        SQLiteDatabase db = this.getReadableDatabase();
        String countQuery = String.format("SELECT * FROM %s", TABLE_NAME_POSITIONS_DIC);
        Cursor cursor = db.rawQuery(countQuery, null);
        if(cursor.moveToFirst()) {
            do {
                positionsEntity.setId(cursor.getInt(cursor.getColumnIndex(KEY_POSITIONS_DIC_ID)));
                positionsEntity.setPosition(cursor.getString(cursor.getColumnIndex(KEY_POSITIONS_DIC_JOB_TYPE)));
            } while (cursor.moveToNext());
            positionsEntities.add(positionsEntity);
        }
        cursor.close();
        return positionsEntities;
    }

    public int checkPositionsDicExist() {
        SQLiteDatabase db = this.getReadableDatabase();
        String countQuery = String.format("SELECT COUNT(*) FROM %s", TABLE_NAME_POSITIONS_DIC);
        Cursor cursor = db.rawQuery(countQuery, null);
        if(cursor.moveToFirst()) {
            int id = cursor.getInt(0);
            return id;
        }
        cursor.close();
        return 0;
    }

    public void deleteAllPositionsDic() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME_POSITIONS_DIC, null, null);
        db.close();
    }

    //------------------salaries dictionary----------------
    public long addSalaryDic(JobDictionaryResponse.SalaryLevelsEntity salaryLevelsEntity) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_SALARIES_DIC_ID, salaryLevelsEntity.getId());
        values.put(KEY_SALARIES_DIC_POSITION, salaryLevelsEntity.getSalary());

        long result = db.insert(TABLE_NAME_SALARIES_DIC, null, values);
        db.close();
        return result;
    }

    public List<JobDictionaryResponse.SalaryLevelsEntity> getSalariesDic() {
        List<JobDictionaryResponse.SalaryLevelsEntity> salaryLevelsEntities = new ArrayList<>();
        JobDictionaryResponse.SalaryLevelsEntity salaryLevelsEntity = new JobDictionaryResponse.SalaryLevelsEntity();
        SQLiteDatabase db = this.getReadableDatabase();
        String countQuery = String.format("SELECT * FROM %s", TABLE_NAME_SALARIES_DIC);
        Cursor cursor = db.rawQuery(countQuery, null);
        if(cursor.moveToFirst()) {
            do {
                salaryLevelsEntity.setId(cursor.getInt(cursor.getColumnIndex(KEY_SALARIES_DIC_ID)));
                salaryLevelsEntity.setSalary(cursor.getString(cursor.getColumnIndex(KEY_SALARIES_DIC_POSITION)));
            } while (cursor.moveToNext());
            salaryLevelsEntities.add(salaryLevelsEntity);
        }
        cursor.close();
        return salaryLevelsEntities;
    }

    public int checkSalariesDicExist() {
        SQLiteDatabase db = this.getReadableDatabase();
        String countQuery = String.format("SELECT COUNT(*) FROM %s", TABLE_NAME_SALARIES_DIC);
        Cursor cursor = db.rawQuery(countQuery, null);
        if(cursor.moveToFirst()) {
            int id = cursor.getInt(0);
            return id;
        }
        cursor.close();
        return 0;
    }

    public void deleteAllSalariesDic() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME_SALARIES_DIC, null, null);
        db.close();
    }

    //------------------Job----------------
    public long addJob(JobResponse.JobEntity jobEntity) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_JOB_USERNAME, jobEntity.getUsername());
        values.put(KEY_JOB_JOB_ID, jobEntity.getJobid());
        values.put(KEY_JOB_COMPANY_NAME, jobEntity.getCompanyname());
        values.put(KEY_JOB_POSITION_ID, jobEntity.getPositionid());
        values.put(KEY_JOB_SALARY_ID, jobEntity.getSalaryid());
        values.put(KEY_JOB_CV_ID, jobEntity.getCvid());
        values.put(KEY_JOB_CONTRACT_ID, jobEntity.getContractid());
        values.put(KEY_JOB_SALARY_BOARD_ID, jobEntity.getSalaryboardid());

        long result = db.insert(TABLE_NAME_JOB, null, values);
        db.close();
        return result;
    }

    public void deleteAllJob() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME_JOB, null, null);
        db.close();
    }

    public JobResponse.JobEntity getJob() {
        JobResponse.JobEntity jobEntity = new JobResponse.JobEntity();
        SQLiteDatabase db = this.getReadableDatabase();
        String countQuery = String.format("SELECT * FROM %s", TABLE_NAME_JOB);
        Cursor cursor = db.rawQuery(countQuery, null);
        if(cursor.moveToFirst()) {
            jobEntity.setCompanyname(cursor.getString(cursor.getColumnIndex(KEY_SALARIES_DIC_ID)));
            jobEntity.setUsername(cursor.getString(cursor.getColumnIndex(KEY_JOB_USERNAME)));
            jobEntity.setJobid(cursor.getInt(cursor.getColumnIndex(KEY_JOB_JOB_ID)));
            jobEntity.setCompanyname(cursor.getString(cursor.getColumnIndex(KEY_JOB_COMPANY_NAME)));
            jobEntity.setPositionid(cursor.getInt(cursor.getColumnIndex(KEY_JOB_POSITION_ID)));
            jobEntity.setSalaryid(cursor.getInt(cursor.getColumnIndex(KEY_JOB_SALARY_ID)));
            jobEntity.setCvid(cursor.getInt(cursor.getColumnIndex(KEY_JOB_CV_ID)));
            jobEntity.setContractid(cursor.getInt(cursor.getColumnIndex(KEY_JOB_CONTRACT_ID)));
            jobEntity.setSalaryboardid(cursor.getInt(cursor.getColumnIndex(KEY_JOB_SALARY_BOARD_ID)));
        }
        cursor.close();
        return jobEntity;
    }

    //------------------Colleague----------------
    public long addColleague(String username, ColleagueResponse.ColleagueEntity colleagueEntity) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_COLLEAGUE_USERNAME, username);
        values.put(KEY_COLLEAGUE_NAME, colleagueEntity.getColleaguename());
        values.put(KEY_COLLEAGUE_PHONE, colleagueEntity.getColleaguephone());

        long result = db.insert(TABLE_NAME_COLLEAGUE, null, values);
        db.close();
        return result;
    }

    public void deleteAllColleague() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME_COLLEAGUE, null, null);
        db.close();
    }

    public ColleagueResponse.ColleagueEntity getColleague() {
        ColleagueResponse.ColleagueEntity colleagueEntity = new ColleagueResponse.ColleagueEntity();
        SQLiteDatabase db = this.getReadableDatabase();
        String countQuery = String.format("SELECT * FROM %s", TABLE_NAME_JOB);
        Cursor cursor = db.rawQuery(countQuery, null);
        if(cursor.moveToFirst()) {
            colleagueEntity.setColleaguename(cursor.getString(cursor.getColumnIndex(KEY_COLLEAGUE_NAME)));
            colleagueEntity.setColleaguephone(cursor.getString(cursor.getColumnIndex(KEY_COLLEAGUE_PHONE)));
        }
        cursor.close();
        return colleagueEntity;
    }

}
