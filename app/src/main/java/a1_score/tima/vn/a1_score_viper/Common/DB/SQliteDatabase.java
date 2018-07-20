package a1_score.tima.vn.a1_score_viper.Common.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import a1_score.tima.vn.a1_score_viper.Common.Commons;
import a1_score.tima.vn.a1_score_viper.Modules.LoanRegistration.Entity.LoanDictionaryResponse;
import a1_score.tima.vn.a1_score_viper.Modules.LoanRegistration.Entity.LoanResponse;
import a1_score.tima.vn.a1_score_viper.Modules.Login.Entity.LoginResponse;
import a1_score.tima.vn.a1_score_viper.Modules.UpdateFamily.Entity.FamilyMembersResponse;
import a1_score.tima.vn.a1_score_viper.Modules.UpdateFamily.Entity.FamilyResponse;
import a1_score.tima.vn.a1_score_viper.Modules.UpdateJob.Entity.JobDictionaryResponse;
import a1_score.tima.vn.a1_score_viper.Modules.UpdateJob.Entity.ColleagueResponse;
import a1_score.tima.vn.a1_score_viper.Modules.UpdateJob.Entity.JobResponse;
import a1_score.tima.vn.a1_score_viper.Modules.UpdatePapers.Entity.ImagesEntity;
import a1_score.tima.vn.a1_score_viper.Modules.UpdatePapers.Entity.PapersEntity;
import a1_score.tima.vn.a1_score_viper.Modules.UpdatePapers.Entity.PapersResponse;
import a1_score.tima.vn.a1_score_viper.Modules.UpdateProfile.Entity.ProfileRequest;
import a1_score.tima.vn.a1_score_viper.Modules.UpdateProfile.Entity.ImageProfileResponse;
import a1_score.tima.vn.a1_score_viper.Modules.UpdateSocialNetwork.Entity.FacebookResponse;

public class SQliteDatabase extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "onescore";
    private static final int DATABASE_VERSION = 1;

    //Create table images
    private static final String TABLE_NAME_IMAGES = "tblImages";
    private static final String KEY_IMAGES_ID = "Id";
    private static final String KEY_IMAGES_URL = "Url";
    private static final String KEY_IMAGES_FORMAT = "Format";
    private static final String KEY_IMAGES_NAME = "Image_Name";
    private static final String KEY_IMAGES_USER = "User";
    private static final String KEY_IMAGES_TYPE = "Image_Type";
    private static final String KEY_IMAGES_TYPE_ID = "Type_Id";

    //Create table profile
    private static final String TABLE_NAME_PROFILE = "tblProfile";
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
    private static final String TABLE_NAME_USER = "tblUser";
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
    private static final String TABLE_NAME_JOBS_DIC = "tblJobDictionary";
    private static final String KEY_JOBS_DIC_ID = "Id";
    private static final String KEY_JOBS_DIC_JOB_TYPE = "JobType";

    //create table positions dic
    private static final String TABLE_NAME_POSITIONS_DIC = "tblPositionDictionary";
    private static final String KEY_POSITIONS_DIC_ID = "Id";
    private static final String KEY_POSITIONS_DIC_JOB_TYPE = "Position";

    //create table salaries dic
    private static final String TABLE_NAME_SALARIES_DIC = "tblSalaryDictionary";
    private static final String KEY_SALARIES_DIC_ID = "Id";
    private static final String KEY_SALARIES_DIC_POSITION = "Salary";

    //create table purpose dic
    private static final String TABLE_NAME_PURPOSE_DIC = "tblPurposeDictionary";
    private static final String KEY_PURPOSE_DIC_ID = "Id";
    private static final String KEY_PURPOSE_DIC_VALUE = "Purpose";

    //create table purpose dic
    private static final String TABLE_NAME_PAYMENT_METHOD_DIC = "tblPaymentMethodDictionary";
    private static final String KEY_PAYMENT_METHOD_DIC_ID = "Id";
    private static final String KEY_PAYMENT_METHOD_DIC_VALUE = "Method";

    //create table job
    private static final String TABLE_NAME_JOB = "tblJob";
    private static final String KEY_JOB_USERNAME = "Username";
    private static final String KEY_JOB_JOB_ID = "JobId";
    private static final String KEY_JOB_COMPANY_NAME = "CompanyName";
    private static final String KEY_JOB_COMPANY_ADDRESS = "CompanyAddress";
    private static final String KEY_JOB_POSITION_ID = "PositionId";
    private static final String KEY_JOB_SALARY_ID = "SalaryId";
    private static final String KEY_JOB_CV_ID = "CvId";
    private static final String KEY_JOB_CONTRACT_ID = "ContractId";
    private static final String KEY_JOB_SALARY_BOARD_ID = "SalaryBoardId";

    //create table colleague
    private static  final String TABLE_NAME_COLLEAGUE = "tblColleague";
    private static  final String KEY_COLLEAGUE_USERNAME = "username";
    private static  final String KEY_COLLEAGUE_NAME = "ColleagueName";
    private static  final String KEY_COLLEAGUE_PHONE = "ColleaguePhone";

    //create table family members
    private static  final String TABLE_NAME_FAMILY_MEMBERS = "tblFamilyMembers";
    private static  final String KEY_FAMILY_MEMBERS_USERNAME = "username";
    private static  final String KEY_FAMILY_MEMBERS_BIRTH_CERTIFICATE_ID = "birthCertificateId";
    private static  final String KEY_FAMILY_MEMBERS_STUDENT_CARD_ID = "studentCardId";
    private static  final String KEY_FAMILY_MEMBERS_RELATIONSHIP_PHONE = "RelationshipPhone";
    private static  final String KEY_FAMILY_MEMBERS_RELATIONSHIP_NAME = "RelationshipName";
    private static  final String KEY_FAMILY_MEMBERS_RELATIONSHIP_TYPEID = "RelationshipTypeId";

    //create table family
    private static  final String TABLE_NAME_FAMILY = "tblFamily";
    private static  final String KEY_FAMILY_USERNAME = "username";
    private static  final String KEY_FAMILY_MERRIAGE_STATUS = "MerriageStatus";
    private static  final String KEY_FAMILY_NAME = "FamilyName";
    private static  final String KEY_FAMILY_PHONE = "FamilyPhone";
    private static  final String KEY_FAMILY_MERRIAGE_REGISTRATION_ID = "MarriageRegistrationId";
    private static  final String KEY_FAMILY_CHILDREN_NUMBER = "ChildrenNumber";

    //create table facebook
    private static  final String TABLE_NAME_FACEBOOK = "tblFacebook";
    private static  final String KEY_FACEBOOK_USERNAME = "username";
    private static  final String KEY_FACEBOOK_ID = "id";
    private static  final String KEY_FACEBOOK_NAME = "name";
    private static  final String KEY_FACEBOOK_ADDRESS = "address";
    private static  final String KEY_FACEBOOK_EMAIL = "email";

    //create table image type
    private static  final String TABLE_NAME_IMAGE_TYPE = "tblImageType";
    private static  final String KEY_IMAGE_TYPE_USERNAME = "Username";
    private static  final String KEY_IMAGE_TYPE_ID = "TypeId";
    private static  final String KEY_IMAGE_TYPE_NAME = "TypeName";
    private static  final String KEY_IMAGE_TYPE_IMAGE_SIZE = "ImageSize";
    private static  final String KEY_IMAGE_TYPE_DONE = "Done";

    //create table image type
    private static  final String TABLE_NAME_LOAN_REGISTRATION = "tblLoanRegistration";
    private static  final String KEY_LOAN_REGISTRATION_USERNAME = "Username";
    private static  final String KEY_LOAN_REGISTRATION_PACKAGE_ID = "PackageId";
    private static  final String KEY_LOAN_REGISTRATION_DURATION = "Duration";
    private static  final String KEY_LOAN_REGISTRATION_VALUE = "Value";
    private static  final String KEY_LOAN_REGISTRATION_FORMALITY_ID = "FormalityId";
    private static  final String KEY_LOAN_REGISTRATION_PURPOSE_ID = "PurposeId";
    private static  final String KEY_LOAN_REGISTRATION_PAYMENT_METHOD_ID = "PaymentMethodId";
    private static  final String KEY_LOAN_REGISTRATION_FEE = "Fee";
    private static  final String KEY_LOAN_REGISTRATION_CONSULTANTFEE = "ConsultantFee";
    private static  final String KEY_LOAN_REGISTRATION_PROFIT = "Profit";
    private static  final String KEY_LOAN_REGISTRATION_REQUEST_TIME = "RequestTime";

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
        String create_images_table = String.format("CREATE TABLE IF NOT EXISTS %s(%s INTEGER, %s TEXT, %s TEXT, %s TEXT, %s TEXT, %s TEXT, %s INTEGER)",
                TABLE_NAME_IMAGES,
                KEY_IMAGES_ID,
                KEY_IMAGES_URL,
                KEY_IMAGES_TYPE,
                KEY_IMAGES_NAME,
                KEY_IMAGES_USER,
                KEY_IMAGES_FORMAT,
                KEY_IMAGES_TYPE_ID);
        String create_profile_table = String.format("CREATE TABLE IF NOT EXISTS %s(%s INTEGER PRIMARY KEY, %s TEXT, %s TEXT, %s TEXT, %s TEXT, %s TEXT, %s INTEGER, %s INTEGER, %s TEXT, %s TEXT, %s TEXT, %s INTEGER)",
                TABLE_NAME_PROFILE,
                KEY_PROFILE_ID,
                KEY_PROFILE_USERNAME,
                KEY_PROFILE_FULLNAME,
                KEY_PROFILE_DATE_OF_BIRTH,
                KEY_PROFILE_ADDRESS,
                KEY_PROFILE_ID_NUMBER,
                KEY_PROFILE_ID_IMAGE_1,
                KEY_PROFILE_ID_IMAGE_2,
                KEY_PROFILE_BANK_ACC_NUMBER,
                KEY_PROFILE_CARD_TERM,
                KEY_PROFILE_CARD_IMAGE,
                KEY_PROFILE_SEX);
        String create_user_table = String.format("CREATE TABLE IF NOT EXISTS %s(%s TEXT PRIMARY KEY, %s TEXT, %s TEXT, %s TEXT, %s TEXT, %s TEXT, %s TEXT, %s TEXT, %s INTEGER, %s INTEGER, %s INTEGER, %s TEXT, %s TEXT, %s TEXT, %s TEXT, %s INTEGER)",
                TABLE_NAME_USER,
                KEY_USER_ID,
                KEY_USER_NAME,
                KEY_USER_FULLNAME,
                KEY_USER_DATE_OF_BIRTH,
                KEY_USER_ID_NUMBER,
                KEY_USER_ADDRESS,
                KEY_USER_ACC_NUMBER,
                KEY_USER_CARD_TERM,
                KEY_USER_SEX,
                KEY_USER_SCORED,
                KEY_USER_LEVEL,
                KEY_USER_URL_IMAGE1,
                KEY_USER_URL_IMAGE2,
                KEY_USER_URL_CARD_IMAGE,
                KEY_USER_URL_AVATAR,
                KEY_USER_PROGRESS);
        String create_jobs_dic_table = String.format("CREATE TABLE IF NOT EXISTS %s(%s INTEGER, %s TEXT)",
                TABLE_NAME_JOBS_DIC,
                KEY_JOBS_DIC_ID,
                KEY_JOBS_DIC_JOB_TYPE);
        String create_positions_dic_table = String.format("CREATE TABLE IF NOT EXISTS %s(%s INTEGER, %s TEXT)",
                TABLE_NAME_POSITIONS_DIC,
                KEY_POSITIONS_DIC_ID,
                KEY_POSITIONS_DIC_JOB_TYPE);
        String create_salaries_dic_table = String.format("CREATE TABLE IF NOT EXISTS %s(%s INTEGER, %s TEXT)",
                TABLE_NAME_SALARIES_DIC,
                KEY_SALARIES_DIC_ID,
                KEY_SALARIES_DIC_POSITION);
        String create_job_table = String.format("CREATE TABLE IF NOT EXISTS %s(%s TEXT, %s INTEGER, %s TEXT, %s TEXT, %s INTEGER, %s INTEGER, %s INTEGER, %s INTEGER, %s INTEGER)",
                TABLE_NAME_JOB,
                KEY_JOB_USERNAME,
                KEY_JOB_JOB_ID,
                KEY_JOB_COMPANY_NAME,
                KEY_JOB_COMPANY_ADDRESS,
                KEY_JOB_POSITION_ID,
                KEY_JOB_SALARY_ID,
                KEY_JOB_CV_ID,
                KEY_JOB_CONTRACT_ID,
                KEY_JOB_SALARY_BOARD_ID);
        String create_colleague_table = String.format("CREATE TABLE IF NOT EXISTS %s(%s TEXT, %s TEXT, %s TEXT)",
                TABLE_NAME_COLLEAGUE,
                KEY_COLLEAGUE_USERNAME,
                KEY_COLLEAGUE_NAME,
                KEY_COLLEAGUE_PHONE);
        String create_family_members_table = String.format("CREATE TABLE IF NOT EXISTS %s(%s INTEGER, %s INTEGER, %s TEXT, %s TEXT, %s INTEGER, %s TEXT)",
                TABLE_NAME_FAMILY_MEMBERS,
                KEY_FAMILY_MEMBERS_BIRTH_CERTIFICATE_ID,
                KEY_FAMILY_MEMBERS_STUDENT_CARD_ID,
                KEY_FAMILY_MEMBERS_RELATIONSHIP_PHONE,
                KEY_FAMILY_MEMBERS_RELATIONSHIP_NAME,
                KEY_FAMILY_MEMBERS_RELATIONSHIP_TYPEID,
                KEY_FAMILY_MEMBERS_USERNAME);
        String create_family_table = String.format("CREATE TABLE IF NOT EXISTS %s(%s TEXT, %s INTEGER, %s TEXT, %s TEXT, %s INTEGER, %s INTEGER)",
                TABLE_NAME_FAMILY,
                KEY_FAMILY_USERNAME,
                KEY_FAMILY_MERRIAGE_STATUS,
                KEY_FAMILY_NAME,
                KEY_FAMILY_PHONE,
                KEY_FAMILY_MERRIAGE_REGISTRATION_ID,
                KEY_FAMILY_CHILDREN_NUMBER);
        String create_facebook_table = String.format("CREATE TABLE IF NOT EXISTS %s(%s TEXT, %s TEXT, %s TEXT, %s TEXT, %s TEXT)",
                TABLE_NAME_FACEBOOK,
                KEY_FACEBOOK_USERNAME,
                KEY_FACEBOOK_ID,
                KEY_FACEBOOK_NAME,
                KEY_FACEBOOK_ADDRESS,
                KEY_FACEBOOK_EMAIL);
        String create_image_type_table = String.format("CREATE TABLE IF NOT EXISTS %s(%s TEXT, %s INTEGER, %s TEXT, %s INTEGER, %s INTEGER)",
                TABLE_NAME_IMAGE_TYPE,
                KEY_IMAGE_TYPE_USERNAME,
                KEY_IMAGE_TYPE_ID,
                KEY_IMAGE_TYPE_NAME,
                KEY_IMAGE_TYPE_IMAGE_SIZE,
                KEY_IMAGE_TYPE_DONE);
        String create_purpose_table = String.format("CREATE TABLE IF NOT EXISTS %s(%s INTEGER, %s TEXT)",
                TABLE_NAME_PURPOSE_DIC,
                KEY_PURPOSE_DIC_ID,
                KEY_PURPOSE_DIC_VALUE);
        String create_payment_method_table = String.format("CREATE TABLE IF NOT EXISTS %s(%s INTEGER, %s TEXT)",
                TABLE_NAME_PAYMENT_METHOD_DIC,
                KEY_PAYMENT_METHOD_DIC_ID,
                KEY_PAYMENT_METHOD_DIC_VALUE);
        String create_loan_registration_table = String.format("CREATE TABLE IF NOT EXISTS %s(%s TEXT, %s INTEGER, %s INTEGER, %s INTEGER, %s INTEGER, %s INTEGER, %s INTEGER, %s INTEGER, %s INTEGER, %s INTEGER, %s TEXT)",
                TABLE_NAME_LOAN_REGISTRATION,
                KEY_LOAN_REGISTRATION_USERNAME,
                KEY_LOAN_REGISTRATION_PACKAGE_ID,
                KEY_LOAN_REGISTRATION_DURATION,
                KEY_LOAN_REGISTRATION_VALUE,
                KEY_LOAN_REGISTRATION_FORMALITY_ID,
                KEY_LOAN_REGISTRATION_PURPOSE_ID,
                KEY_LOAN_REGISTRATION_PAYMENT_METHOD_ID,
                KEY_LOAN_REGISTRATION_FEE,
                KEY_LOAN_REGISTRATION_CONSULTANTFEE,
                KEY_LOAN_REGISTRATION_PROFIT,
                KEY_LOAN_REGISTRATION_REQUEST_TIME);

        db.execSQL(create_images_table);
        db.execSQL(create_profile_table);
        db.execSQL(create_user_table);
        db.execSQL(create_jobs_dic_table);
        db.execSQL(create_positions_dic_table);
        db.execSQL(create_salaries_dic_table);
        db.execSQL(create_job_table);
        db.execSQL(create_colleague_table);
        db.execSQL(create_family_members_table);
        db.execSQL(create_family_table);
        db.execSQL(create_facebook_table);
        db.execSQL(create_image_type_table);
        db.execSQL(create_purpose_table);
        db.execSQL(create_payment_method_table);
        db.execSQL(create_loan_registration_table);
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
        String drop_family_members_table = String.format("DROP TABLE IF EXISTS %s", TABLE_NAME_FAMILY_MEMBERS);
        String drop_family_table = String.format("DROP TABLE IF EXISTS %s", TABLE_NAME_FAMILY);
        String drop_facebook_table = String.format("DROP TABLE IF EXISTS %s", TABLE_NAME_FACEBOOK);
        String drop_image_type_table = String.format("DROP TABLE IF EXISTS %s", TABLE_NAME_IMAGE_TYPE);
        String drop_purpose_dic_table = String.format("DROP TABLE IF EXISTS %s", TABLE_NAME_PURPOSE_DIC);
        String drop_payment_method_table = String.format("DROP TABLE IF EXISTS %s", TABLE_NAME_PAYMENT_METHOD_DIC);
        String drop_loan_registration_table = String.format("DROP TABLE IF EXISTS %s", TABLE_NAME_LOAN_REGISTRATION);

        db.execSQL(drop_images_table);
        db.execSQL(drop_profile_table);
        db.execSQL(drop_user_table);
        db.execSQL(drop_jobs_dic_table);
        db.execSQL(drop_positions_dic_table);
        db.execSQL(drop_salaries_dic_table);
        db.execSQL(drop_job_table);
        db.execSQL(drop_colleague_table);
        db.execSQL(drop_family_members_table);
        db.execSQL(drop_family_table);
        db.execSQL(drop_facebook_table);
        db.execSQL(drop_image_type_table);
        db.execSQL(drop_purpose_dic_table);
        db.execSQL(drop_payment_method_table);
        db.execSQL(drop_loan_registration_table);

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
        values.put(KEY_IMAGES_TYPE_ID, imageProfileResponse.getImage().getTypeId());
        values.put(KEY_IMAGES_NAME, username + imageName);
        values.put(KEY_IMAGES_USER, username);
        values.put(KEY_IMAGES_FORMAT, imageProfileResponse.getImage().getImageType());

        long result = db.insert(TABLE_NAME_IMAGES, null, values);
        db.close();
    }

    public long addImageLong(ImageProfileResponse imageProfileResponse, String imageName, String username, String type) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_IMAGES_ID, imageProfileResponse.getImage().getId());
        values.put(KEY_IMAGES_URL, imageProfileResponse.getImage().getUrl());
        values.put(KEY_IMAGES_TYPE, type);
        values.put(KEY_IMAGES_TYPE_ID, imageProfileResponse.getImage().getTypeId());
        values.put(KEY_IMAGES_NAME, imageName);
        values.put(KEY_IMAGES_USER, username);
        values.put(KEY_IMAGES_FORMAT, imageProfileResponse.getImage().getImageType());

        long result = db.insert(TABLE_NAME_IMAGES, null, values);
        db.close();
        return result;
    }

    public void deleteImageBy(String username, String type) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME_IMAGES, String.format("%s=? AND %s=?", KEY_IMAGES_USER, KEY_IMAGES_TYPE), new String[] { username, type });
        db.close();
    }

    public void deleteImageByTypeId(String username, int typeId) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME_IMAGES, String.format("%s=? AND %s=?", KEY_IMAGES_USER, KEY_IMAGES_TYPE_ID), new String[] { username, String.format("%s", typeId) });
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

    public int getImageIDByTypeId(String username, int typeId) {
        SQLiteDatabase db = this.getReadableDatabase();
        String countQuery = String.format("SELECT %s FROM %s WHERE %s = '%s' AND %s = '%s'", KEY_IMAGES_ID, TABLE_NAME_IMAGES, KEY_IMAGES_USER, username, KEY_IMAGES_TYPE_ID, typeId);
        Cursor cursor = db.rawQuery(countQuery, null);
        if(cursor.moveToFirst()) {
            int id = cursor.getInt(0);
            return id;
        }
        cursor.close();
        return 0;
    }

    public List<ImagesEntity> getImages(String username) {
        List<ImagesEntity> imageEntities = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String query = String.format("SELECT * FROM %s WHERE %s = '%s'", TABLE_NAME_IMAGES, KEY_IMAGES_USER, username);
        Cursor c = db.rawQuery(query, null);
        if(c.moveToFirst()) {
            do {
                ImagesEntity imageEntity = new ImagesEntity();
                imageEntity.setTypeId(c.getString(c.getColumnIndex(KEY_IMAGES_TYPE_ID)));
                imageEntity.setImageName(c.getString(c.getColumnIndex(KEY_IMAGES_NAME)));
                imageEntities.add(imageEntity);
            } while (c.moveToNext());
        }
        return imageEntities;
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
        SQLiteDatabase db = this.getReadableDatabase();
        String countQuery = String.format("SELECT * FROM %s", TABLE_NAME_JOBS_DIC);
        Cursor cursor = db.rawQuery(countQuery, null);
        if(cursor.moveToFirst()) {
            do {
                JobDictionaryResponse.JobsEntity jobsEntity = new JobDictionaryResponse.JobsEntity();
                jobsEntity.setId(cursor.getInt(cursor.getColumnIndex(KEY_JOBS_DIC_ID)));
                jobsEntity.setJobtype(cursor.getString(cursor.getColumnIndex(KEY_JOBS_DIC_JOB_TYPE)));
                jobsEntities.add(jobsEntity);
            } while (cursor.moveToNext());
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

        SQLiteDatabase db = this.getReadableDatabase();
        String countQuery = String.format("SELECT * FROM %s", TABLE_NAME_POSITIONS_DIC);
        Cursor cursor = db.rawQuery(countQuery, null);
        if(cursor.moveToFirst()) {
            do {
                JobDictionaryResponse.PositionsEntity positionsEntity = new JobDictionaryResponse.PositionsEntity();
                positionsEntity.setId(cursor.getInt(cursor.getColumnIndex(KEY_POSITIONS_DIC_ID)));
                positionsEntity.setPosition(cursor.getString(cursor.getColumnIndex(KEY_POSITIONS_DIC_JOB_TYPE)));
                positionsEntities.add(positionsEntity);
            } while (cursor.moveToNext());
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

        SQLiteDatabase db = this.getReadableDatabase();
        String countQuery = String.format("SELECT * FROM %s", TABLE_NAME_SALARIES_DIC);
        Cursor cursor = db.rawQuery(countQuery, null);
        if(cursor.moveToFirst()) {
            do {
                JobDictionaryResponse.SalaryLevelsEntity salaryLevelsEntity = new JobDictionaryResponse.SalaryLevelsEntity();
                salaryLevelsEntity.setId(cursor.getInt(cursor.getColumnIndex(KEY_SALARIES_DIC_ID)));
                salaryLevelsEntity.setSalary(cursor.getString(cursor.getColumnIndex(KEY_SALARIES_DIC_POSITION)));
                salaryLevelsEntities.add(salaryLevelsEntity);
            } while (cursor.moveToNext());
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

    //------------------purpose dictionary----------------
    public long addPurposeDic(LoanDictionaryResponse.PurposeEntity purposeEntity) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_PURPOSE_DIC_ID, purposeEntity.getId());
        values.put(KEY_PURPOSE_DIC_VALUE, purposeEntity.getPurpose());

        long result = db.insert(TABLE_NAME_PURPOSE_DIC, null, values);
        db.close();
        return result;
    }

    public List<LoanDictionaryResponse.PurposeEntity> getPurposeDic() {
        List<LoanDictionaryResponse.PurposeEntity> purposeEntities = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String countQuery = String.format("SELECT * FROM %s", TABLE_NAME_PURPOSE_DIC);
        Cursor cursor = db.rawQuery(countQuery, null);
        if(cursor.moveToFirst()) {
            do {
                LoanDictionaryResponse.PurposeEntity purposeEntity = new LoanDictionaryResponse.PurposeEntity();
                purposeEntity.setId(cursor.getInt(cursor.getColumnIndex(KEY_PURPOSE_DIC_ID)));
                purposeEntity.setPurpose(cursor.getString(cursor.getColumnIndex(KEY_PURPOSE_DIC_VALUE)));
                purposeEntities.add(purposeEntity);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return purposeEntities;
    }

    public int checkPurposeDicExist() {
        SQLiteDatabase db = this.getReadableDatabase();
        String countQuery = String.format("SELECT COUNT(*) FROM %s", TABLE_NAME_PURPOSE_DIC);
        Cursor cursor = db.rawQuery(countQuery, null);
        if(cursor.moveToFirst()) {
            int id = cursor.getInt(0);
            return id;
        }
        cursor.close();
        return 0;
    }

    public void deleteAllPurposeDic() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME_PURPOSE_DIC, null, null);
        db.close();
    }

    //------------------payment method dictionary----------------
    public long addPaymentMethodDic(LoanDictionaryResponse.PaymentMethodEntity paymentMethodEntity) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_PAYMENT_METHOD_DIC_ID, paymentMethodEntity.getId());
        values.put(KEY_PAYMENT_METHOD_DIC_VALUE, paymentMethodEntity.getMethod());

        long result = db.insert(TABLE_NAME_PAYMENT_METHOD_DIC, null, values);
        db.close();
        return result;
    }

    public List<LoanDictionaryResponse.PaymentMethodEntity> getPaymentMethodDic() {
        List<LoanDictionaryResponse.PaymentMethodEntity> paymentMethodEntities = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String countQuery = String.format("SELECT * FROM %s", TABLE_NAME_PAYMENT_METHOD_DIC);
        Cursor cursor = db.rawQuery(countQuery, null);
        if(cursor.moveToFirst()) {
            do {
                LoanDictionaryResponse.PaymentMethodEntity paymentMethodEntity = new LoanDictionaryResponse.PaymentMethodEntity();
                paymentMethodEntity.setId(cursor.getInt(cursor.getColumnIndex(KEY_PAYMENT_METHOD_DIC_ID)));
                paymentMethodEntity.setMethod(cursor.getString(cursor.getColumnIndex(KEY_PAYMENT_METHOD_DIC_VALUE)));
                paymentMethodEntities.add(paymentMethodEntity);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return paymentMethodEntities;
    }

    public int checkPaymentMethodDicExist() {
        SQLiteDatabase db = this.getReadableDatabase();
        String countQuery = String.format("SELECT COUNT(*) FROM %s", TABLE_NAME_PAYMENT_METHOD_DIC);
        Cursor cursor = db.rawQuery(countQuery, null);
        if(cursor.moveToFirst()) {
            int id = cursor.getInt(0);
            return id;
        }
        cursor.close();
        return 0;
    }

    public void deleteAllPaymentMethodDic() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME_PAYMENT_METHOD_DIC, null, null);
        db.close();
    }

    //------------------Job----------------
    public long addJob(JobResponse.JobEntity jobEntity) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_JOB_USERNAME, jobEntity.getUsername());
        values.put(KEY_JOB_JOB_ID, jobEntity.getJobid());
        values.put(KEY_JOB_COMPANY_NAME, jobEntity.getCompanyname());
        values.put(KEY_JOB_COMPANY_ADDRESS, jobEntity.getCompanyAddress());
        values.put(KEY_JOB_POSITION_ID, jobEntity.getPositionid());
        values.put(KEY_JOB_SALARY_ID, jobEntity.getSalaryid());
        values.put(KEY_JOB_CV_ID, jobEntity.getCvid());
        values.put(KEY_JOB_CONTRACT_ID, jobEntity.getContractid());
        values.put(KEY_JOB_SALARY_BOARD_ID, jobEntity.getSalaryboardid());

        long result = db.insert(TABLE_NAME_JOB, null, values);
        db.close();
        return result;
    }

    public void deleteJobByUsername(String username) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME_JOB, String.format("%s = ?", KEY_JOB_USERNAME), new String[]{username});
        db.close();
    }

    public JobResponse.JobEntity getJob(String username) {
        JobResponse.JobEntity jobEntity = new JobResponse.JobEntity();
        SQLiteDatabase db = this.getReadableDatabase();
        String countQuery = String.format("SELECT * FROM %s", TABLE_NAME_JOB);
        Cursor cursor = db.rawQuery(countQuery, null);
        if(cursor.moveToFirst()) {
            jobEntity.setUsername(cursor.getString(cursor.getColumnIndex(KEY_JOB_USERNAME)));
            jobEntity.setJobid(cursor.getInt(cursor.getColumnIndex(KEY_JOB_JOB_ID)));
            jobEntity.setCompanyname(cursor.getString(cursor.getColumnIndex(KEY_JOB_COMPANY_NAME)));
            jobEntity.setCompanyAddress(cursor.getString(cursor.getColumnIndex(KEY_JOB_COMPANY_ADDRESS)));
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

    public void deleteColleagueByUsername(String username, String phone) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME_COLLEAGUE, String.format("%s = ? AND %s = ?", KEY_JOB_USERNAME, KEY_COLLEAGUE_PHONE), new String[]{username, phone});
        db.close();
    }

    public List<ColleagueResponse.ColleagueEntity> getColleague(String username) {
        List<ColleagueResponse.ColleagueEntity> colleagueEntities = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String countQuery = String.format("SELECT * FROM %s WHERE %s = '%s'", TABLE_NAME_COLLEAGUE, KEY_COLLEAGUE_USERNAME, username);
        Cursor cursor = db.rawQuery(countQuery, null);
        if(cursor.moveToFirst()) {
            do {
                ColleagueResponse.ColleagueEntity colleagueEntity = new ColleagueResponse.ColleagueEntity();
                colleagueEntity.setColleaguename(cursor.getString(cursor.getColumnIndex(KEY_COLLEAGUE_NAME)));
                colleagueEntity.setColleaguephone(Commons.changePhone0(cursor.getString(cursor.getColumnIndex(KEY_COLLEAGUE_PHONE))));
                colleagueEntities.add(colleagueEntity);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return colleagueEntities;
    }

    //------------------Family members---------------
    public long addFamilyMembers(String username, FamilyMembersResponse.RelationshipEntity relationshipEntity) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_FAMILY_MEMBERS_USERNAME, username);
        values.put(KEY_FAMILY_MEMBERS_BIRTH_CERTIFICATE_ID, relationshipEntity.getBirthcertificateid());
        values.put(KEY_FAMILY_MEMBERS_RELATIONSHIP_NAME, relationshipEntity.getRelationshipname());
        values.put(KEY_FAMILY_MEMBERS_RELATIONSHIP_PHONE, relationshipEntity.getRelationshipphone());
        values.put(KEY_FAMILY_MEMBERS_RELATIONSHIP_TYPEID, relationshipEntity.getRelationshiptypeid());
        values.put(KEY_FAMILY_MEMBERS_STUDENT_CARD_ID, relationshipEntity.getStudentcardid());

        long result = db.insert(TABLE_NAME_FAMILY_MEMBERS, null, values);
        db.close();
        return result;
    }

    public void deleteFamilyMenbersByUsername(String username) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME_FAMILY_MEMBERS, String.format("%s = ?", KEY_FAMILY_MEMBERS_USERNAME), new String[]{username});
        db.close();
    }

    public List<FamilyMembersResponse.RelationshipEntity> getFamilyMember(String username) {
        List<FamilyMembersResponse.RelationshipEntity> relationshipEntities = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String countQuery = String.format("SELECT * FROM %s WHERE %s = '%s'", TABLE_NAME_FAMILY_MEMBERS, KEY_FAMILY_MEMBERS_USERNAME, username);
        Cursor cursor = db.rawQuery(countQuery, null);
        if(cursor.moveToFirst()) {
            do {
                FamilyMembersResponse.RelationshipEntity relationshipEntity = new FamilyMembersResponse.RelationshipEntity();
                relationshipEntity.setBirthcertificateid(cursor.getInt(cursor.getColumnIndex(KEY_FAMILY_MEMBERS_BIRTH_CERTIFICATE_ID)));
                relationshipEntity.setRelationshipname(cursor.getString(cursor.getColumnIndex(KEY_FAMILY_MEMBERS_RELATIONSHIP_NAME)));
                relationshipEntity.setRelationshipphone(cursor.getString(cursor.getColumnIndex(KEY_FAMILY_MEMBERS_RELATIONSHIP_PHONE)));
                relationshipEntity.setRelationshiptypeid(cursor.getInt(cursor.getColumnIndex(KEY_FAMILY_MEMBERS_RELATIONSHIP_TYPEID)));
                relationshipEntity.setStudentcardid(cursor.getInt(cursor.getColumnIndex(KEY_FAMILY_MEMBERS_STUDENT_CARD_ID)));
                relationshipEntities.add(relationshipEntity);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return relationshipEntities;
    }

    //------------------Family---------------
    public long addFamily(String username, FamilyResponse.FamilyEntity familyEntity) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_FAMILY_USERNAME, username);
        values.put(KEY_FAMILY_MERRIAGE_STATUS, familyEntity.getMarriagestatus());
        values.put(KEY_FAMILY_NAME, familyEntity.getFamilyname());
        values.put(KEY_FAMILY_PHONE, familyEntity.getFamilyphone());
        values.put(KEY_FAMILY_MERRIAGE_REGISTRATION_ID, familyEntity.getMarriageregistrationid());
        values.put(KEY_FAMILY_CHILDREN_NUMBER, familyEntity.getChildrennumber());

        long result = db.insert(TABLE_NAME_FAMILY, null, values);
        db.close();
        return result;
    }

    public void deleteFamilyByUsername(String username) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME_FAMILY, String.format("%s = ?", KEY_FAMILY_USERNAME), new String[]{username});
        db.close();
    }

    public FamilyResponse.FamilyEntity getFamily(String username) {
        FamilyResponse.FamilyEntity familyEntity = new FamilyResponse.FamilyEntity();
        SQLiteDatabase db = this.getReadableDatabase();
        String countQuery = String.format("SELECT * FROM %s WHERE %s = '%s'", TABLE_NAME_FAMILY, KEY_FAMILY_USERNAME, username);
//        String countQuery = String.format("SELECT * FROM %s", TABLE_NAME_FAMILY);
        Cursor cursor = db.rawQuery(countQuery, null);
        if(cursor.moveToFirst()) {
            familyEntity.setMarriageregistrationid(cursor.getInt(cursor.getColumnIndex(KEY_FAMILY_MERRIAGE_REGISTRATION_ID)));
            familyEntity.setFamilyname(cursor.getString(cursor.getColumnIndex(KEY_FAMILY_NAME)));
            familyEntity.setFamilyphone(cursor.getString(cursor.getColumnIndex(KEY_FAMILY_PHONE)));
            familyEntity.setMarriagestatus(cursor.getInt(cursor.getColumnIndex(KEY_FAMILY_MERRIAGE_STATUS)));
            familyEntity.setChildrennumber(cursor.getInt(cursor.getColumnIndex(KEY_FAMILY_CHILDREN_NUMBER)));
        }
        cursor.close();
        return familyEntity;
    }

    //------------------Family---------------
    public long addFacebook(String username, FacebookResponse.FacebookprofileEntity facebookprofileEntity) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_FACEBOOK_USERNAME, username);
        values.put(KEY_FACEBOOK_ID, facebookprofileEntity.getFacebookid());
        values.put(KEY_FACEBOOK_NAME, facebookprofileEntity.getFacebookname());
        values.put(KEY_FACEBOOK_ADDRESS, facebookprofileEntity.getFacebookaddress());
        values.put(KEY_FACEBOOK_EMAIL, facebookprofileEntity.getFacebookemail());

        long result = db.insert(TABLE_NAME_FACEBOOK, null, values);
        db.close();
        return result;
    }

    public void deleteFacebookByUsername(String username) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME_FACEBOOK, String.format("%s = ?", KEY_FACEBOOK_USERNAME), new String[]{username});
        db.close();
    }

    public FacebookResponse.FacebookprofileEntity getFacebook(String username) {
        FacebookResponse.FacebookprofileEntity facebookprofileEntity = new FacebookResponse.FacebookprofileEntity();
        SQLiteDatabase db = this.getReadableDatabase();
        String countQuery = String.format("SELECT * FROM %s WHERE %s = '%s'", TABLE_NAME_FACEBOOK, KEY_FACEBOOK_USERNAME, username);
        Cursor cursor = db.rawQuery(countQuery, null);
        if(cursor.moveToFirst()) {
            facebookprofileEntity.setFacebookid(cursor.getString(cursor.getColumnIndex(KEY_FACEBOOK_ID)));
            facebookprofileEntity.setFacebookname(cursor.getString(cursor.getColumnIndex(KEY_FACEBOOK_NAME)));
            facebookprofileEntity.setFacebookaddress(cursor.getString(cursor.getColumnIndex(KEY_FACEBOOK_ADDRESS)));
            facebookprofileEntity.setFacebookemail(cursor.getString(cursor.getColumnIndex(KEY_FACEBOOK_EMAIL)));
        }
        cursor.close();
        return facebookprofileEntity;
    }

    //------------------Image types---------------
    public long addImageType(PapersResponse.ImageTypesEntity imageTypesEntity, String username, boolean done) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_IMAGE_TYPE_USERNAME, username);
        values.put(KEY_IMAGE_TYPE_ID, imageTypesEntity.getTypeid());
        values.put(KEY_IMAGE_TYPE_NAME, imageTypesEntity.getTypename());
        values.put(KEY_IMAGE_TYPE_IMAGE_SIZE, imageTypesEntity.getImagesize());
        values.put(KEY_IMAGE_TYPE_DONE, done?1:0);

        long result = db.insert(TABLE_NAME_IMAGE_TYPE, null, values);
        db.close();
        return result;
    }

    public long updateImageType(String username, int typeID, boolean done) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_IMAGE_TYPE_DONE, done?1:0);

        long result = db.update(TABLE_NAME_IMAGE_TYPE, values, KEY_IMAGE_TYPE_USERNAME + " = '" + username + "' and " + KEY_IMAGE_TYPE_ID + " = '" + typeID + "'", null);
        db.close();
        return result;
    }

    public void deleteAllImageTypes() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME_IMAGE_TYPE, null, null);
        db.close();
    }

    public List<PapersEntity> getImageType() {
        List<PapersEntity> papersEntities = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String countQuery = String.format("SELECT * FROM %s", TABLE_NAME_IMAGE_TYPE);
        Cursor cursor = db.rawQuery(countQuery, null);
        if(cursor.moveToFirst()) {
            do {
                PapersEntity papersEntity = new PapersEntity();
                papersEntity.setUsername(cursor.getString(cursor.getColumnIndex(KEY_IMAGE_TYPE_USERNAME)));
                papersEntity.setTypeid(cursor.getInt(cursor.getColumnIndex(KEY_IMAGE_TYPE_ID)));
                papersEntity.setTypename(cursor.getString(cursor.getColumnIndex(KEY_IMAGE_TYPE_NAME)));
                papersEntity.setImagesize(cursor.getInt(cursor.getColumnIndex(KEY_IMAGE_TYPE_IMAGE_SIZE)));
                papersEntity.setDone(cursor.getInt(cursor.getColumnIndex(KEY_IMAGE_TYPE_DONE))==1?true:false);
                papersEntities.add(papersEntity);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return papersEntities;
    }

    //------------------Loan registration-----------------
    public long addLoanRegistration(LoanResponse.LoancreditEntity loancreditEntity, String username) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        
        values.put(KEY_LOAN_REGISTRATION_USERNAME, username);
        values.put(KEY_LOAN_REGISTRATION_PACKAGE_ID, loancreditEntity.getPackageid());
        values.put(KEY_LOAN_REGISTRATION_DURATION, loancreditEntity.getDuration());
        values.put(KEY_LOAN_REGISTRATION_VALUE, loancreditEntity.getValue());
        values.put(KEY_LOAN_REGISTRATION_FORMALITY_ID, loancreditEntity.getFormalityId());
        values.put(KEY_LOAN_REGISTRATION_PURPOSE_ID, loancreditEntity.getPurposeid());
        values.put(KEY_LOAN_REGISTRATION_PAYMENT_METHOD_ID, loancreditEntity.getPaymentmethodid());
        values.put(KEY_LOAN_REGISTRATION_FEE, loancreditEntity.getFee());
        values.put(KEY_LOAN_REGISTRATION_CONSULTANTFEE, loancreditEntity.getConsultantfee());
        values.put(KEY_LOAN_REGISTRATION_PROFIT, loancreditEntity.getProfit());
        values.put(KEY_LOAN_REGISTRATION_REQUEST_TIME, loancreditEntity.getRequesttime());

        long result = db.insert(TABLE_NAME_LOAN_REGISTRATION, null, values);
        db.close();
        return result;
    }

    public List<LoanResponse.LoancreditEntity> getLoanCreditRegister(String username) {
        List<LoanResponse.LoancreditEntity> loancreditEntities = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String countQuery = String.format("SELECT * FROM %s WHERE %s = '%s'", TABLE_NAME_LOAN_REGISTRATION, KEY_LOAN_REGISTRATION_USERNAME, username);
        Cursor cursor = db.rawQuery(countQuery, null);
        if(cursor.moveToFirst()) {
            do {
                LoanResponse.LoancreditEntity loancreditEntity = new LoanResponse.LoancreditEntity();
                loancreditEntity.setConsultantfee(cursor.getLong(cursor.getColumnIndex(KEY_LOAN_REGISTRATION_CONSULTANTFEE)));
                loancreditEntity.setDuration(cursor.getInt(cursor.getColumnIndex(KEY_LOAN_REGISTRATION_DURATION)));
                loancreditEntity.setFee(cursor.getLong(cursor.getColumnIndex(KEY_LOAN_REGISTRATION_FEE)));
                loancreditEntity.setFormalityId(cursor.getInt(cursor.getColumnIndex(KEY_LOAN_REGISTRATION_FORMALITY_ID)));
                loancreditEntity.setPackageid(cursor.getInt(cursor.getColumnIndex(KEY_LOAN_REGISTRATION_PACKAGE_ID)));
                loancreditEntity.setPaymentmethodid(cursor.getInt(cursor.getColumnIndex(KEY_LOAN_REGISTRATION_PAYMENT_METHOD_ID)));
                loancreditEntity.setProfit(cursor.getLong(cursor.getColumnIndex(KEY_LOAN_REGISTRATION_PROFIT)));
                loancreditEntity.setPurposeid(cursor.getInt(cursor.getColumnIndex(KEY_LOAN_REGISTRATION_PURPOSE_ID)));
                loancreditEntity.setRequesttime(cursor.getString(cursor.getColumnIndex(KEY_LOAN_REGISTRATION_REQUEST_TIME)));
                loancreditEntity.setValue(cursor.getLong(cursor.getColumnIndex(KEY_LOAN_REGISTRATION_VALUE)));
                loancreditEntities.add(loancreditEntity);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return loancreditEntities;
    }
}
