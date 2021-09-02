package co.com.ceiba.mobile.pruebadeingreso.model;

import android.app.ProgressDialog;
import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import co.com.ceiba.mobile.pruebadeingreso.apiController.Retrofit2Client;
import co.com.ceiba.mobile.pruebadeingreso.dataBase.DBHandler;
import co.com.ceiba.mobile.pruebadeingreso.dataBase.TdUsers;
import co.com.ceiba.mobile.pruebadeingreso.pojo.Post;
import co.com.ceiba.mobile.pruebadeingreso.pojo.User;
import co.com.ceiba.mobile.pruebadeingreso.presenter.PresenterPostActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InteractorPostActivity {

    Context context;
    DBHandler dbHandler;
    TdUsers tdUsers;
    PresenterPostActivity presenterPostActivity;
    List<Post> posts = new ArrayList<Post>();
    ProgressDialog progressDialog;

    public InteractorPostActivity(Context context, PresenterPostActivity presenterPostActivity) {
        this.context = context;
        this.presenterPostActivity = presenterPostActivity;
        dbHandler =  new DBHandler(context);
        tdUsers =  new TdUsers(dbHandler);
        progressDialog =  new ProgressDialog(context);
        progressDialog.setMessage("Cargando publicaciones...");
    }
    public User getUserInfo(int id){
        return tdUsers.selectUserById(id);
    }



    public void getPostByUserId(int id){
        progressDialog.show();
        Retrofit2Client.getInstance().getApiController().getListPost(id).enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {

                posts = response.body();
                presenterPostActivity.setPostInRecyclerView(posts);
                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {

            }
        });
    }
}
