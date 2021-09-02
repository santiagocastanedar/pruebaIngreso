package co.com.ceiba.mobile.pruebadeingreso.dataBase;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import co.com.ceiba.mobile.pruebadeingreso.pojo.User;

import static co.com.ceiba.mobile.pruebadeingreso.dataBase.Constanst.TABLE_USERS;

public class TdUsers implements DatabaseInterface {
    DBHandler dbHandler;

    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String USER_NAME = "user_name";
    private static final String EMAIL = "email";
    private static final String PHONE = "phone";

    public TdUsers(DBHandler dbHandler) {
        this.dbHandler = dbHandler;
    }



    @Override
    public String getQueryCreate() {
        return "CREATE TABLE " + TABLE_USERS +
                "(" + ID + " TEXT PRIMARY KEY," +
                NAME + " TEXT," +
                USER_NAME + " TEXT," +
                EMAIL + " TEXT," +
                PHONE + " TEXT" + ")";
    }

    public void insertUser(User user){
        SQLiteDatabase db = dbHandler.getWritableDatabase();
        ContentValues userRegistry =  new ContentValues();
        userRegistry.put(ID,user.getId());
        userRegistry.put(NAME,user.getName());
        userRegistry.put(USER_NAME,user.getUserName());
        userRegistry.put(EMAIL,user.getEmail());
        userRegistry.put(PHONE,user.getPhone());
        db.insert(TABLE_USERS,null,userRegistry);
        db.close();
    }

    public List<User> selectUser(){
        SQLiteDatabase db = dbHandler.getReadableDatabase();
        List<User> userList = new ArrayList<User>();

        Cursor cursor = db.rawQuery("SELECT "+ID+","+NAME+","+USER_NAME+","+EMAIL+","+PHONE+" FROM "+TABLE_USERS,null);
        if (cursor.moveToFirst()){
            do{
                User user = new User();
                user.setId(cursor.getInt(0));
                user.setName(cursor.getString(1));
                user.setUserName(cursor.getString(2));
                user.setEmail(cursor.getString(3));
                user.setPhone(cursor.getString(4));

                userList.add(user);

            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return userList;
    }

    public User selectUserById(int id){
        SQLiteDatabase db = dbHandler.getReadableDatabase();
        User user = new User();

        Cursor cursor = db.rawQuery("SELECT "+ID+","+NAME+","+USER_NAME+","+EMAIL+","+PHONE+" FROM "+TABLE_USERS + " WHERE "+ID+" = "+id,null);
        if (cursor.moveToFirst()){
            do{

                user.setId(cursor.getInt(0));
                user.setName(cursor.getString(1));
                user.setUserName(cursor.getString(2));
                user.setEmail(cursor.getString(3));
                user.setPhone(cursor.getString(4));



            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return user;
    }

}
