package com.example.myrecyclerview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdapterClass extends RecyclerView.Adapter<MyAdapterClass.ViewHolder> {

    List<MyModelClass> listData;
    LayoutInflater layoutInflater;
     Context context;


    public MyAdapterClass(Context context,List<MyModelClass>listData){
        this.context = context;
        this.listData = listData;
        this.layoutInflater = LayoutInflater.from(context);

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view=layoutInflater.inflate(R.layout.custom_layout,parent,false);

        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {

            MyModelClass data= listData.get(position);

            String save=data.getWebPages().toString();
            String checkNull= data.getState();

            holder.user_name.setText(data.getName());
            holder.country.setText(data.getCountry());
            holder.state.setText(data.getState());
            holder.alph_code.setText(data.getAlphaCode());
            holder.domain.setText(data.getDomain());


            if(checkNull.equals("null")){
                holder.state.setText("N/A");
            }else {
            }
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    String url = save;
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(url));
                    context.startActivity(i);

                    Toast.makeText(v.getContext(),  "You Clicked on "+
                            listData.get(position), Toast.LENGTH_SHORT).show();
                }
            });
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

 //   public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView user_name,country,state,alph_code,domain,web_page;
       // OnUniClickListener onUniClickListener;
       // public ViewHolder(@NonNull View itemView, OnUniClickListener onUniClickListener)
        public ViewHolder(@NonNull View itemView){
            super(itemView);
            user_name=itemView.findViewById(R.id.Names);
            country=itemView.findViewById(R.id.Country);
            state=itemView.findViewById(R.id.State);
            alph_code=itemView.findViewById(R.id.AlphaCode);
          //  web_page=itemView.findViewById(R.id.WebView);
            domain=itemView.findViewById(R.id.Domain);

//            this.onUniClickListener = onUniClickListener;
//            itemView.setOnClickListener(this);
        }
//
//        @Override
//        public void onClick(View v) {
//            MyModelClass myModelClass = listData.get(getAdapterPosition());
//            onUniClickListener.productcClick(myModelClass);
//        }
    }
//
//    interface OnUniClickListener{
//        void productcClick(MyModelClass myModelClass);
//    }
}
