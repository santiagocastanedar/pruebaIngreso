package co.com.ceiba.mobile.pruebadeingreso.apiController;

import java.util.List;

import co.com.ceiba.mobile.pruebadeingreso.pojo.Post;
import co.com.ceiba.mobile.pruebadeingreso.pojo.User;
import co.com.ceiba.mobile.pruebadeingreso.rest.Endpoints;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

import static co.com.ceiba.mobile.pruebadeingreso.rest.Endpoints.GET_POST_USER;
import static co.com.ceiba.mobile.pruebadeingreso.rest.Endpoints.GET_USERS;

public interface ApiController {


    @GET(GET_USERS)
    public Call<List<User>> getListUser();

    @GET(GET_POST_USER)
    public Call<List<Post>> getListPost(@Query("userId") int id);
}
