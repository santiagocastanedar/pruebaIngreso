package co.com.ceiba.mobile.pruebadeingreso.presenter;

import android.content.Context;
import android.view.View;

import java.util.List;

import co.com.ceiba.mobile.pruebadeingreso.model.InteractorMainActivity;
import co.com.ceiba.mobile.pruebadeingreso.pojo.User;
import co.com.ceiba.mobile.pruebadeingreso.view.MainView;

public class PresenterMainActivity implements MainView.Presenter {

    Context context;
    InteractorMainActivity interactorMainActivity;
    MainView mainView;

    public PresenterMainActivity(Context context, MainView view) {
        this.context = context;
        this.mainView = view;
        interactorMainActivity = new InteractorMainActivity(context,this);
    }

    public InteractorMainActivity getInteractorMainActivity() {
        return interactorMainActivity;
    }

    public void setInteractorMainActivity(InteractorMainActivity interactorMainActivity) {
        this.interactorMainActivity = interactorMainActivity;
    }

    @Override
    public void setUserInRecyclerView(List<User> users) {
        mainView.setUserInRecyclerView(users);
    }




}
