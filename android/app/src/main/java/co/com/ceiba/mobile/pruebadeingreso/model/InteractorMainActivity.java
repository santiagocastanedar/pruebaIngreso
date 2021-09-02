package co.com.ceiba.mobile.pruebadeingreso.model;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.List;

import co.com.ceiba.mobile.pruebadeingreso.apiController.Retrofit2Client;
import co.com.ceiba.mobile.pruebadeingreso.dataBase.DBHandler;
import co.com.ceiba.mobile.pruebadeingreso.dataBase.TdUsers;
import co.com.ceiba.mobile.pruebadeingreso.pojo.User;
import co.com.ceiba.mobile.pruebadeingreso.presenter.PresenterMainActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InteractorMainActivity {

    Context context;
    DBHandler dbHandler;
    TdUsers tdUsers;
    PresenterMainActivity presenterMainActivity;
    List<User> users =  new ArrayList<User>();
    ProgressDialog progressDialog;
    public InteractorMainActivity(Context context,PresenterMainActivity presenterMainActivity) {
        this.context = context;
        dbHandler = new DBHandler(context);
        tdUsers = new TdUsers(dbHandler);
        progressDialog =  new ProgressDialog(context);
        progressDialog.setMessage("Cargando usuarios...");
        this.presenterMainActivity = presenterMainActivity;
    }

    public void getUsers(){
        users = getUsersFromDataBase();
        if(users.size() != 0){
            presenterMainActivity.setUserInRecyclerView(users);
        }else{
            getUsersFromWebService();
        }

    }

    public List<User> getUsersFromDataBase(){
        return tdUsers.selectUser();
    }

    public void getUsersFromWebService(){
        progressDialog.show();

        Retrofit2Client.getInstance().getApiController().getListUser().enqueue(new Callback<List<User>>(){

            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {

                for (User user:response.body()) {
                    tdUsers.insertUser(user);
                }
                users = response.body();
                presenterMainActivity.setUserInRecyclerView(users);
                progressDialog.dismiss();

            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {


            }
        });
    }

    public List<User> getUsersFilter(){
        users = getUsersFromDataBase();
        return users;
    }
}
