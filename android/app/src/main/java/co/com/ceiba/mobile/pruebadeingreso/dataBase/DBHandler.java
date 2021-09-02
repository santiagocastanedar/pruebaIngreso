package co.com.ceiba.mobile.pruebadeingreso.dataBase;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;
import android.util.Log;


import static co.com.ceiba.mobile.pruebadeingreso.dataBase.Constanst.TABLE_USERS;

public class DBHandler extends SQLiteOpenHelper {
    Context contextDb;
    private static final String DATABASE_NAME = "publication.db";
    private static final int DATABASE_VERSION = 1;


    public DBHandler(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);

    }



    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        Log.e("entre","entre");
        String tdUsers = new TdUsers(this).getQueryCreate();


        //Exects
        sqLiteDatabase.execSQL(tdUsers);


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        onCreate(sqLiteDatabase);

    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }


}
