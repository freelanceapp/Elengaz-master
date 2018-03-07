package com.SemiColon.Hmt.elengaz.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.SemiColon.Hmt.elengaz.API.Service.APIClient;
import com.SemiColon.Hmt.elengaz.API.Service.ServicesApi;
import com.SemiColon.Hmt.elengaz.Activities.ServiceProvider_Home;
import com.SemiColon.Hmt.elengaz.Adapters.Office_Oreders_Adapter;
import com.SemiColon.Hmt.elengaz.Model.office_order_model;
import com.SemiColon.Hmt.elengaz.R;

import java.util.ArrayList;
import java.util.List;

import me.anwarshahriar.calligrapher.Calligrapher;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Delta on 02/03/2018.
 */

public class Fragment_Orders extends Fragment {
    ArrayList<office_order_model> model;
    Office_Oreders_Adapter adapter;
    RecyclerView recyclerView;
    private LinearLayoutManager mLayoutManager;

    //String id;
    ServiceProvider_Home home;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        
        View view = inflater.inflate(R.layout.fragment_order,container,false);
        home= (ServiceProvider_Home) getActivity();
        Calligrapher calligrapher = new Calligrapher(getContext());
        calligrapher.setFont(getActivity(), "JannaLT-Regular.ttf", true);




        recyclerView = view.findViewById(R.id.recyc_office_orders);
        model = new ArrayList<>();

        mLayoutManager=new LinearLayoutManager(getContext());
        mLayoutManager.setReverseLayout(true);
        mLayoutManager.setStackFromEnd(true);

        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setHasFixedSize(true);

        adapter = new Office_Oreders_Adapter(getContext(), model);
        recyclerView.setAdapter(adapter);
       // Toast.makeText(getContext(), ""+home.office_id, Toast.LENGTH_SHORT).show();


        ServicesApi service = APIClient.getClient().create(ServicesApi.class);
        Call<List<office_order_model>> call = service.Display_AllOfficeOrder(home.office_id);
        call.enqueue(new Callback<List<office_order_model>>() {
            @Override
            public void onResponse(Call<List<office_order_model>> call, Response<List<office_order_model>> response) {
                model.clear();
                model.addAll(response.body());

           //    if (response.body().get(0).getMessage().equals("no result"))
             //  {
                //   Toast.makeText(getContext(), "no result", Toast.LENGTH_SHORT).show();
             //  }else {

                   adapter.notifyDataSetChanged();
                  // Toast.makeText(getContext(), ""+home.office_id, Toast.LENGTH_SHORT).show();
                   //    Log.e("mmm", response.body().toString());

             //  }



            }

            @Override
            public void onFailure(Call<List<office_order_model>> call, Throwable t) {

                Log.e("mm",t+"");
                  Toast.makeText(getContext(), "" + t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });





        return view;
    }


}

