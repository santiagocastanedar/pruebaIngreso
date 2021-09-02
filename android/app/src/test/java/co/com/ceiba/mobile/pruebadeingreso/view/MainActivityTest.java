package co.com.ceiba.mobile.pruebadeingreso.view;

import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import co.com.ceiba.mobile.pruebadeingreso.pojo.User;

import static org.junit.Assert.*;
@RunWith(DataProviderRunner.class)
public class MainActivityTest {

    List<User> users = Arrays.asList(new User(1,"Santiago","santiago@gmail.com","1234567","santiagoC"),new User(2,"santiago","santiago@gmail.com","1234567","santiagoC"),new User(3,"Sebastian","sebas@gmail.com","1234567","sebas"));
    MainActivity mainActivity = new MainActivity();

    @DataProvider
    public static Object[][] nameUser(){
        return new Object[][]{
                {"Santiago",2},
                {"S",3},
                {"",3},
                {"9",0},

        };
    }


    @Test
    @UseDataProvider("nameUser")
    public void filterUsers(String name,int expectedValue) {
        List<User> userList = mainActivity.filterUsers(name, users);

        assertEquals(expectedValue,userList.size());
    }
}