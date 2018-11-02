package cn.cloudworkshop.miaoding.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import java.util.List;

import cn.cloudworkshop.miaoding.R;
import cn.cloudworkshop.miaoding.bean.GoodsBean;

/**
 * Author：Libin on 2018/9/25 15:51
 * Email：1993911441@qq.com
 * Describe：
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private List<GoodsBean.DataBean.itemDataBean> userList;
    private LayoutInflater layoutInflater;
    private Context context;

    public MyAdapter(Context context, List<GoodsBean.DataBean.itemDataBean> userList) {
        this.userList = userList;
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(layoutInflater.inflate(R.layout.rv_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.tv.setText(userList.get(position).getName()+ userList.get(position).getSub_name());
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView tv;
        public MyViewHolder(View itemView) {
            super(itemView);
            tv= itemView.findViewById(R.id.tv_username);
        }
    }
}
