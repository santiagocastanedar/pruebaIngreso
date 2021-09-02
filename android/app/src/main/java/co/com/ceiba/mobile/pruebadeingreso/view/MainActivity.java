package co.com.ceiba.mobile.pruebadeingreso.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import co.com.ceiba.mobile.pruebadeingreso.R;
import co.com.ceiba.mobile.pruebadeingreso.adapters.AdapterUserList;
import co.com.ceiba.mobile.pruebadeingreso.pojo.User;
import co.com.ceiba.mobile.pruebadeingreso.presenter.PresenterMainActivity;

import static co.com.ceiba.mobile.pruebadeingreso.utils.utils.ID_USER;
import static co.com.ceiba.mobile.pruebadeingreso.utils.utils.inflate;

public class MainActivity extends Activity implements MainView.ViewMain{


    PresenterMainActivity presenterMainActivity;
    AdapterUserList adapterUserList;
    EditText editTextSearch;
    RecyclerView recyclerUsers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    public void init(){
        presenterMainActivity =  new PresenterMainActivity(this,this);
        presenterMainActivity.getInteractorMainActivity().getUsers();
        editTextSearch = (EditText) findViewById(R.id.editTextSearch);
        searchInList();
    }

    public void searchInList(){
        editTextSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                List<User> userList = filterUsers(editable.toString(), presenterMainActivity.getInteractorMainActivity().getUsersFilter());
                adapterUserList.filter(userList);
                if(userList.size() == 0){
                    isEmptyList();
                }

            }
        });
    }
    @Override
    public void setUserInRecyclerView(List<User> users) {
        recyclerUsers = (RecyclerView) findViewById(R.id.recyclerViewSearchResults);
        adapterUserList = new AdapterUserList(users,this);
        recyclerUsers.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        recyclerUsers.setAdapter(adapterUserList);

    }

    @Override
    public void onClickPost(int id) {
        Intent intent = new Intent(getApplicationContext(),PostActivity.class);
        intent.putExtra(ID_USER,id);
        startActivity(intent);
    }

    public List<User> filterUsers(String name,List<User> userList){
        List<User> userListFilter =  new ArrayList<User>();
        for (User user:userList) {
            if(user.getName().toLowerCase().contains(name.toLowerCase())){
                userListFilter.add(user);
            }
        }
        return userListFilter;
    }

    public void isEmptyList(){
        new CountDownTimer(5000,1000){

            @Override
            public void onTick(long l) {
                setContentView(R.layout.empty_view);
            }

            @Override
            public void onFinish() {
                setContentView(R.layout.activity_main);
                init();
            }
        }.start();
    }

}