package co.com.ceiba.mobile.pruebadeingreso.view;

import java.util.List;

import co.com.ceiba.mobile.pruebadeingreso.pojo.Post;
import co.com.ceiba.mobile.pruebadeingreso.pojo.User;

public interface PostView {

    void setPostInRecyclerView(List<Post> posts);

    void setUserInformation(User user);

    public interface Presenter extends PostView{

    }
}
