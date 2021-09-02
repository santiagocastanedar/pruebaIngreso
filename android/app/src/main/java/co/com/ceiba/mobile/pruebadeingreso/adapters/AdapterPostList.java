package co.com.ceiba.mobile.pruebadeingreso.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import co.com.ceiba.mobile.pruebadeingreso.R;
import co.com.ceiba.mobile.pruebadeingreso.pojo.Post;

import static co.com.ceiba.mobile.pruebadeingreso.utils.utils.inflate;

public class AdapterPostList extends RecyclerView.Adapter<AdapterPostList.MyViewPosts> {
    private List<Post> postList;

    public AdapterPostList(List<Post> postList) {
        this.postList = postList;
    }

    @NonNull
    @Override
    public MyViewPosts onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewPosts(inflate(parent.getContext(), R.layout.post_list_item,parent,false),parent.getContext(),getItemCount());
    }

    public class MyViewPosts extends RecyclerView.ViewHolder {
        TextView textViewTitle,textViewBody;
        public MyViewPosts(View itemView, Context context,int size) {
            super(itemView);
            textViewTitle = (TextView) itemView.findViewById(R.id.title);
            textViewBody = (TextView) itemView.findViewById(R.id.body);

        }
    }
    @Override
    public void onBindViewHolder(@NonNull MyViewPosts holder, int position) {

        holder.textViewTitle.setText(postList.get(position).getTitle());
        holder.textViewBody.setText(postList.get(position).getBody());
    }

    @Override
    public int getItemCount() {
        return postList.size();
    }


}
