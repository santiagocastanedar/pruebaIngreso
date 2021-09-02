package co.com.ceiba.mobile.pruebadeingreso.view;

import java.util.List;

import co.com.ceiba.mobile.pruebadeingreso.pojo.User;

public interface MainView {

    void setUserInRecyclerView(List<User> users);



    interface Presenter extends MainView {

    }

    interface ViewMain extends MainView{
        void onClickPost(int id);
    }
}
