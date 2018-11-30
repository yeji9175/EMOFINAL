package com.trois.project.emo.test;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    SQLiteDatabase db;
    // DBHelper 생성자로 관리할 DB 이름과 버전 정보를 받음
    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    // DB를 새로 생성할 때 호출되는 함수
    @Override
    public void onCreate(SQLiteDatabase db) {
        // 새로운 테이블 생성
        /* 이름은 MONEYBOOK이고, 자동으로 값이 증가하는 _id 정수형 기본키 컬럼과
        item 문자열 컬럼, price 정수형 컬럼, create_at 문자열 컬럼으로 구성된 테이블을 생성. */
        db.execSQL("CREATE TABLE USER (_id TEXT PRIMARY KEY, password TEXT, name TEXT, phone TEXT);");
    }

    // DB 업그레이드를 위해 버전이 변경될 때 호출되는 함수
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


    public void insert_user(String id, String password, String name, String phone){
        db = getWritableDatabase();
        //ContentValues cv = new ContentValues();
        //cv.put("_id",id);
        //cv.put("password",password);
        //cv.put("name", name);
        //cv.put("phone",phone);

        //db.insert("USER",null,cv);
        db.execSQL("INSERT INTO USER values('"+id+"','"+password+"','"+name+"','"+phone+"');");
        db.close();

    }

    public String getPassword(String id) {
        // 읽기가 가능하게 DB 열기
        SQLiteDatabase db = getReadableDatabase();
        String result = "";

        // DB에 있는 데이터를 쉽게 처리하기 위해 Cursor를 사용하여 테이블에 있는 모든 데이터 출력
        Cursor cursor = db.rawQuery("SELECT * FROM USER", null);
        cursor.moveToFirst();
        if(cursor.getString(0).equals(id)){
            result = cursor.getString(1);
            return result;
        }

        while (cursor.moveToNext()) {
            if(id.equals(cursor.getString(0))){
                result = cursor.getString(1);
                break;
            }
        }
        return result;
    }
    public String findPassword(String id, String name, String phone) {
        // 읽기가 가능하게 DB 열기
        SQLiteDatabase db = getReadableDatabase();
        String result = "";

        // DB에 있는 데이터를 쉽게 처리하기 위해 Cursor를 사용하여 테이블에 있는 모든 데이터 출력
        Cursor cursor = db.rawQuery("SELECT * FROM USER", null);
        cursor.moveToFirst();
        if(cursor.getString(0).equals(id)&&cursor.getString(2).equals(name)&&
                cursor.getString(3).equals(phone)){
            result = cursor.getString(1);
            return result;
        }

        while (cursor.moveToNext()) {
            if(cursor.getString(0).equals(id)&&cursor.getString(2).equals(name)&&
                    cursor.getString(3).equals(phone)){
                result = cursor.getString(1);
                break;
            }
        }
        return result;
    }

    public boolean isExist(String id, String name, String phone) {
        // 읽기가 가능하게 DB 열기
        SQLiteDatabase db = getReadableDatabase();
        String result = "";

        // DB에 있는 데이터를 쉽게 처리하기 위해 Cursor를 사용하여 테이블에 있는 모든 데이터 출력
        Cursor cursor = db.rawQuery("SELECT * FROM USER", null);
        cursor.moveToFirst();
        if(cursor.getString(0).equals(id)&&cursor.getString(2).equals(name)&&
                cursor.getString(3).equals(phone)){
            return true;
        }

        while (cursor.moveToNext()) {
            if(cursor.getString(0).equals(id)&&cursor.getString(2).equals(name)&&
                    cursor.getString(3).equals(phone)){
                return true;
            }
        }
        return false;
    }

    public boolean IsExistID(String id) {
        // 읽기가 가능하게 DB 열기
        db = getReadableDatabase();

        // DB에 있는 데이터를 쉽게 처리하기 위해 Cursor를 사용하여 테이블에 있는 모든 데이터 출력
        Cursor cursor = db.rawQuery("SELECT * FROM USER", null);
        cursor.moveToFirst();

        if(cursor.getString(0).equals(id)){
            return true;
        }
        while (cursor.moveToNext()) {
            if(id.equals(cursor.getString(0))){
                return true;
            }
        }
        return false;
    }
}


