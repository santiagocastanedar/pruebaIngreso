package co.com.ceiba.mobile.pruebadeingreso.view;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import java.util.List;

import co.com.ceiba.mobile.pruebadeingreso.R;
import co.com.ceiba.mobile.pruebadeingreso.adapters.AdapterPostList;
import co.com.ceiba.mobile.pruebadeingreso.pojo.Post;
import co.com.ceiba.mobile.pruebadeingreso.pojo.User;
import co.com.ceiba.mobile.pruebadeingreso.presenter.PresenterPostActivity;

import static co.com.ceiba.mobile.pruebadeingreso.utils.utils.ID_USER;

public class PostActivity extends Activity implements PostView{

    PresenterPostActivity presenterPostActivity;
    RecyclerView recyclerPosts;
    AdapterPostList adapterPostList;
    TextView textViewName,textViewEmail,textViewPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        init();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }


    public void init(){
        textViewName = (TextView)findViewById(R.id.name);
        textViewEmail = (TextView)findViewById(R.id.email);
        textViewPhone = (TextView)findViewById(R.id.phone);
        User user = new User();
        presenterPostActivity =  new PresenterPostActivity(this,this);

        int id = getIntent().getIntExtra(ID_USER,0);
        presenterPostActivity.getInteractorPostActivity().getPostByUserId(id);
        user = presenterPostActivity.getInteractorPostActivity().getUserInfo(id);
        textViewName.setText(user.getName());
        textViewEmail.setText(user.getEmail());
        textViewPhone.setText(user.getPhone());
    }
    @Override
    public void setPostInRecyclerView(List<Post> posts) {
        recyclerPosts = (RecyclerView) findViewById(R.id.recyclerViewPostsResults);
        adapterPostList = new AdapterPostList(posts);
        recyclerPosts.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        recyclerPosts.setAdapter(adapterPostList);
    }

    @Override
    public void setUserInformation(User user) {

    }
}
