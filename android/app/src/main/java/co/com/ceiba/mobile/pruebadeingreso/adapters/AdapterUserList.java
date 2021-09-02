package co.com.ceiba.mobile.pruebadeingreso.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import co.com.ceiba.mobile.pruebadeingreso.R;
import co.com.ceiba.mobile.pruebadeingreso.pojo.User;
import co.com.ceiba.mobile.pruebadeingreso.view.MainView;

import static co.com.ceiba.mobile.pruebadeingreso.utils.utils.inflate;

public class AdapterUserList extends RecyclerView.Adapter<AdapterUserList.MyView> {
    private List<User> userList;
    private MainView.ViewMain mainView;

    public AdapterUserList(List<User> userList, MainView.ViewMain mainView) {
        this.userList = userList;
        this.mainView = mainView;
    }

    @NonNull
    @Override
    public MyView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyView(inflate(parent.getContext(), R.layout.user_list_item,parent,false),parent.getContext(),getItemCount());

    }

    public class MyView extends RecyclerView.ViewHolder {
        TextView tvName,tvEmail,tvPhone;
        Button btnViewPost;

        public MyView(View itemView, Context context,int size) {
            super(itemView);
            tvName = (TextView) itemView.findViewById(R.id.name);
            tvEmail = (TextView) itemView.findViewById(R.id.email);
            tvPhone = (TextView) itemView.findViewById(R.id.phone);
            btnViewPost = (Button) itemView.findViewById(R.id.btn_view_post);


        }
    }

    @Override
    public void onBindViewHolder(@NonNull final MyView holder, int position) {
        holder.tvName.setText(userList.get(position).getName());
        holder.tvEmail.setText(userList.get(position).getEmail());
        holder.tvPhone.setText(userList.get(position).getPhone());
        holder.btnViewPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainView.onClickPost(userList.get(holder.getAdapterPosition()).getId());
            }
        });
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }


    public void filter(List<User> usersFilter){
        this.userList = usersFilter;
        notifyDataSetChanged();
    }




}
