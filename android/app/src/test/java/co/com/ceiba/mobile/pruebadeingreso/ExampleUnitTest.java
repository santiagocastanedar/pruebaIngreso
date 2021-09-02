package co.com.ceiba.mobile.pruebadeingreso;

import android.content.Context;
import android.test.InstrumentationTestCase;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import co.com.ceiba.mobile.pruebadeingreso.apiController.Retrofit2Client;
import co.com.ceiba.mobile.pruebadeingreso.dataBase.DBHandler;
import co.com.ceiba.mobile.pruebadeingreso.dataBase.TdUsers;
import co.com.ceiba.mobile.pruebadeingreso.pojo.User;
import co.com.ceiba.mobile.pruebadeingreso.presenter.PresenterMainActivity;
import co.com.ceiba.mobile.pruebadeingreso.view.MainView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest  {


    List<User> usersWebService = new ArrayList<User>();




    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void anywhere(){
        Retrofit2Client.getInstance().getApiController().getListUser().enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                usersWebService = response.body();
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {

            }
        });

        assertEquals(10,usersWebService.size());
    }


}