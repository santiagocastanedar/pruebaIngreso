package co.com.ceiba.mobile.pruebadeingreso.presenter;

import android.content.Context;

import java.util.List;

import co.com.ceiba.mobile.pruebadeingreso.model.InteractorMainActivity;
import co.com.ceiba.mobile.pruebadeingreso.model.InteractorPostActivity;
import co.com.ceiba.mobile.pruebadeingreso.pojo.Post;
import co.com.ceiba.mobile.pruebadeingreso.pojo.User;
import co.com.ceiba.mobile.pruebadeingreso.view.PostView;

public class PresenterPostActivity implements PostView.Presenter {

    Context context;
    InteractorPostActivity interactorPostActivity;
    PostView postView;

    public InteractorPostActivity getInteractorPostActivity() {
        return interactorPostActivity;
    }

    public void setInteractorPostActivity(InteractorPostActivity interactorPostActivity) {
        this.interactorPostActivity = interactorPostActivity;
    }

    public PresenterPostActivity(Context context, PostView postView) {
        this.context = context;
        this.postView = postView;
        interactorPostActivity = new InteractorPostActivity(context,this);
    }

    @Override
    public void setPostInRecyclerView(List<Post> posts) {
        postView.setPostInRecyclerView(posts);
    }

    @Override
    public void setUserInformation(User user) {
        postView.setUserInformation(user);
    }
}
