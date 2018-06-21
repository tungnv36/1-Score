package a1_score.tima.vn.a1_score_viper.Common.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import a1_score.tima.vn.a1_score_viper.Modules.Login.Entity.LoginResultEntity;
import a1_score.tima.vn.a1_score_viper.Modules.UpdateProfile.Entity.UpdateProfileEntity;
import a1_score.tima.vn.a1_score_viper.Modules.UpdateProfile.Entity.UploadImageEntity;
import a1_score.tima.vn.a1_score_viper.Modules.UpdateProfile.Entity.UploadImageResultEntity;

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
    private static final String KEY_USER_PHONE = "Phone";
    private static final String KEY_USER_FULLNAME = "Fullname";
    private static final String KEY_USER_DATE_OF_BIRTH = "DateOfBirth";
    private static final String KEY_USER_ID_NUMBER = "IdNumber";
    private static final String KEY_USER_ADDRESS = "Address";
    private static final String KEY_USER_ACC_NUMBER = "BankAccNumber";
    private static final String KEY_USER_CARD_TERM = "CardTerm";
    private static final String KEY_USER_SEX = "Sex";
    private static final String KEY_USER_SCORED = "Scored";
    private static final String KEY_USER_URL_IMAGE1 = "UrlImage1";
    private static final String KEY_USER_URL_IMAGE2 = "UrlImage2";
    private static final String KEY_USER_URL_CARD_IMAGE = "UrlCardImage";

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
        String create_images_table = String.format("CREATE TABLE IF NOT EXISTS %s(%s INTEGER PRIMARY KEY, %s TEXT, %s TEXT, %s TEXT, %s TEXT, %s TEXT)",
                TABLE_NAME_IMAGES, KEY_IMAGES_ID, KEY_IMAGES_URL, KEY_IMAGES_TYPE, KEY_IMAGES_NAME, KEY_IMAGES_USER, KEY_IMAGES_FORMAT);
        db.execSQL(create_images_table);
        String create_profile_table = String.format("CREATE TABLE IF NOT EXISTS %s(%s INTEGER PRIMARY KEY, %s TEXT, %s TEXT, %s TEXT, %s TEXT, %s TEXT, %s INTEGER, %s INTEGER, %s TEXT, %s TEXT, %s TEXT, %s INTEGER)",
                TABLE_NAME_PROFILE, KEY_PROFILE_ID, KEY_PROFILE_USERNAME, KEY_PROFILE_FULLNAME, KEY_PROFILE_DATE_OF_BIRTH, KEY_PROFILE_ADDRESS,
                KEY_PROFILE_ID_NUMBER, KEY_PROFILE_ID_IMAGE_1, KEY_PROFILE_ID_IMAGE_2, KEY_PROFILE_BANK_ACC_NUMBER, KEY_PROFILE_CARD_TERM,
                KEY_PROFILE_CARD_IMAGE, KEY_PROFILE_SEX);
        String create_user_table = String.format("CREATE TABLE IF NOT EXISTS %s(%s INTEGER PRIMARY KEY, %s TEXT, %s TEXT, %s TEXT, %s TEXT, %s TEXT, %s INTEGER, %s INTEGER, %s TEXT, %s TEXT, %s TEXT, %s TEXT, %s TEXT, %s TEXT)",
                TABLE_NAME_USER, KEY_USER_ID, KEY_USER_NAME, KEY_USER_PHONE, KEY_USER_FULLNAME, KEY_USER_DATE_OF_BIRTH,
                KEY_USER_ID_NUMBER, KEY_USER_ADDRESS, KEY_USER_ACC_NUMBER, KEY_USER_CARD_TERM, KEY_USER_SEX,
                KEY_USER_SCORED, KEY_USER_URL_IMAGE1, KEY_USER_URL_IMAGE2, KEY_USER_URL_CARD_IMAGE);
        db.execSQL(create_images_table);
        db.execSQL(create_profile_table);
        db.execSQL(create_user_table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String drop_images_table = String.format("DROP TABLE IF EXISTS %s", TABLE_NAME_IMAGES);
        String drop_profile_table = String.format("DROP TABLE IF EXISTS %s", TABLE_NAME_PROFILE);
        String drop_user_table = String.format("DROP TABLE IF EXISTS %s", TABLE_NAME_USER);
        db.execSQL(drop_images_table);
        db.execSQL(drop_profile_table);
        db.execSQL(drop_user_table);

        onCreate(db);
    }

    //---------------user---------------
    public void addUser(LoginResultEntity loginResultEntity) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_USER_ID, loginResultEntity.getUser().getUserid());
        values.put(KEY_USER_NAME, loginResultEntity.getUser().getUsername());
        values.put(KEY_USER_PHONE, loginResultEntity.getUser().getPhone());
        values.put(KEY_USER_FULLNAME, loginResultEntity.getUser().getFullname());
        values.put(KEY_USER_DATE_OF_BIRTH, loginResultEntity.getUser().getDateofbirth());
        values.put(KEY_USER_ID_NUMBER, loginResultEntity.getUser().getIdnumber());
        values.put(KEY_USER_ADDRESS, loginResultEntity.getUser().getAddress());
        values.put(KEY_USER_ACC_NUMBER, loginResultEntity.getUser().getBankaccnumber());
        values.put(KEY_USER_CARD_TERM, loginResultEntity.getUser().getCardterm());
        values.put(KEY_USER_SEX, loginResultEntity.getUser().getSex());
        values.put(KEY_USER_SCORED, loginResultEntity.getUser().getScored());
        values.put(KEY_USER_URL_IMAGE1, loginResultEntity.getUser().getUrlimage1());
        values.put(KEY_USER_URL_IMAGE2, loginResultEntity.getUser().getUrlimage2());
        values.put(KEY_USER_URL_CARD_IMAGE, loginResultEntity.getUser().getUrlcardimage());

        db.insert(TABLE_NAME_USER, null, values);
        db.close();
    }

    public void deleteUser() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME_USER, null, null);
        db.close();
    }

    //---------------Images---------------
    public void addImage(UploadImageResultEntity uploadImageResultEntity, String imageName, String username, String type) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_IMAGES_ID, uploadImageResultEntity.getImage().getId());
        values.put(KEY_IMAGES_URL, uploadImageResultEntity.getImage().getUrl());
        values.put(KEY_IMAGES_TYPE, type);
        values.put(KEY_IMAGES_NAME, username + imageName);
        values.put(KEY_IMAGES_USER, username);
        values.put(KEY_IMAGES_FORMAT, uploadImageResultEntity.getImage().getImagetype());

        String returl = String.valueOf(db.insert(TABLE_NAME_IMAGES, null, values));
        db.close();
    }

    public void deleteImageBy(String username, String type) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME_IMAGES, String.format("%s=? AND %s=?", KEY_IMAGES_USER, KEY_IMAGES_TYPE), new String[] { username, type });
        db.close();
    }

    public int getImageByPhone(String username, String type) {
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
    public void addProfile(UpdateProfileEntity updateProfileEntity) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_PROFILE_USERNAME, updateProfileEntity.getUsername());
        values.put(KEY_PROFILE_FULLNAME, updateProfileEntity.getFullname());
        values.put(KEY_PROFILE_DATE_OF_BIRTH, updateProfileEntity.getDate_of_birth());
        values.put(KEY_PROFILE_ADDRESS, updateProfileEntity.getAddress());
        values.put(KEY_PROFILE_ID_NUMBER, updateProfileEntity.getId_number());
        values.put(KEY_PROFILE_ID_IMAGE_1, updateProfileEntity.getId_image_1());
        values.put(KEY_PROFILE_ID_IMAGE_2, updateProfileEntity.getId_image_2());
        values.put(KEY_PROFILE_BANK_ACC_NUMBER, updateProfileEntity.getBank_acc_number());
        values.put(KEY_PROFILE_CARD_TERM, updateProfileEntity.getCard_term());
        values.put(KEY_PROFILE_CARD_IMAGE, updateProfileEntity.getCard_image());
        values.put(KEY_PROFILE_SEX, updateProfileEntity.getSex());

        long result = db.insert(TABLE_NAME_PROFILE, null, values);
        db.close();
    }

    public UpdateProfileEntity getProfileByPhone(String username) {
        UpdateProfileEntity updateProfileEntity = new UpdateProfileEntity();
        SQLiteDatabase db = this.getReadableDatabase();
        String countQuery = String.format("SELECT * FROM %s WHERE %s = '%s'", TABLE_NAME_PROFILE, KEY_PROFILE_USERNAME, username);
        Cursor cursor = db.rawQuery(countQuery, null);
        if(cursor.moveToFirst()) {
            do {
                updateProfileEntity.setUsername(cursor.getString(cursor.getColumnIndex(KEY_PROFILE_USERNAME)));
                updateProfileEntity.setFullname(cursor.getString(cursor.getColumnIndex(KEY_PROFILE_FULLNAME)));
                updateProfileEntity.setDate_of_birth(cursor.getString(cursor.getColumnIndex(KEY_PROFILE_DATE_OF_BIRTH)));
                updateProfileEntity.setAddress(cursor.getString(cursor.getColumnIndex(KEY_PROFILE_ADDRESS)));
                updateProfileEntity.setId_number(cursor.getString(cursor.getColumnIndex(KEY_PROFILE_ID_NUMBER)));
                updateProfileEntity.setId_image_1(cursor.getInt(cursor.getColumnIndex(KEY_PROFILE_ID_IMAGE_1)));
                updateProfileEntity.setId_image_2(cursor.getInt(cursor.getColumnIndex(KEY_PROFILE_ID_IMAGE_2)));
                updateProfileEntity.setBank_acc_number(cursor.getString(cursor.getColumnIndex(KEY_PROFILE_BANK_ACC_NUMBER)));
                updateProfileEntity.setCard_term(cursor.getString(cursor.getColumnIndex(KEY_PROFILE_CARD_TERM)));
                updateProfileEntity.setCard_image(cursor.getInt(cursor.getColumnIndex(KEY_PROFILE_CARD_IMAGE)));
                updateProfileEntity.setSex(cursor.getInt(cursor.getColumnIndex(KEY_PROFILE_SEX)));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return updateProfileEntity;
    }

}
