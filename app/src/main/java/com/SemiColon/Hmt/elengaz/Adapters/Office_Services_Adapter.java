package com.SemiColon.Hmt.elengaz.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.SemiColon.Hmt.elengaz.API.Service.APIClient;
import com.SemiColon.Hmt.elengaz.API.Service.ServicesApi;
import com.SemiColon.Hmt.elengaz.Model.Office_Service_Model;
import com.SemiColon.Hmt.elengaz.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Office_Services_Adapter extends RecyclerView.Adapter<Office_Services_Adapter.Holder> {
    Context context;
    Office_Service_Model mmodel;
    List<Office_Service_Model> Array;

    String cost;
    public Office_Services_Adapter(Context context, List<Office_Service_Model> Array) {
        this.context = context;
        this.Array = Array;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_officer_service, parent, false);

        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        mmodel = Array.get(position);

        holder.state.setTag(position);

        holder.servicename.setText(mmodel.getClient_service_name());
        holder.detail.setText(mmodel.getClient_service_details());
        holder.phone.setText(mmodel.getClient_phone_number());
        holder.cost.setText(mmodel.getClient_service_cost());
        holder.state.setText(mmodel.getClient_service_status());


    }

    @Override
    public int getItemCount() {
        return Array.size();
    }

    class Holder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView  servicename, detail, phone,cost,state;
        LinearLayout lingrand;

        public Holder(View itemView) {
            super(itemView);

            servicename = itemView.findViewById(R.id.txt_service_name);
            detail = itemView.findViewById(R.id.txt_detail);
            phone = itemView.findViewById(R.id.txt_phone);
            cost=itemView.findViewById(R.id.txt_cost);
            state=itemView.findViewById(R.id.txt_state);
            lingrand = itemView.findViewById(R.id.linear);
            state.setOnClickListener(this);


        }

        @Override
        public void onClick(View view) {

            int position = (int) view.getTag();

            mmodel = Array.get(position);

          ServicesApi service=APIClient.getClient().create(ServicesApi.class);
          Call<Office_Service_Model> call=service.EndService(mmodel.getClient_service_id());
          call.enqueue(new Callback<Office_Service_Model>() {
              @Override
              public void onResponse(Call<Office_Service_Model> call, Response<Office_Service_Model> response) {
                  if (response.isSuccessful()){
                      Toast.makeText(context, " service ended", Toast.LENGTH_SHORT).show();

                  }else {

                  }
              }

              @Override
              public void onFailure(Call<Office_Service_Model> call, Throwable t) {

              }
          });


        }


    }
}
