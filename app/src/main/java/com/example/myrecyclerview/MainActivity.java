package com.example.myrecyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.kaopiz.kprogresshud.KProgressHUD;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    MyAdapterClass myAdapter;
    List<MyModelClass> listData;
    KProgressHUD progBar;

    private static String JSON_URL= "http://universities.hipolabs.com/search?country=Pakistan";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        recyclerView=findViewById(R.id.recyclerView);
        progBar=KProgressHUD.create(MainActivity.this).setStyle(KProgressHUD.Style.SPIN_INDETERMINATE).setLabel("InProgress").setCancellable(true);
        progBar.show();
        listData=new ArrayList<>();
        extractData();

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        myAdapter=new MyAdapterClass(this,listData);
        recyclerView.setAdapter(myAdapter);

//        AsyncTaskUpload asyncTaskUpload=new AsyncTaskUpload();
//        asyncTaskUpload.execute("http://universities.hipolabs.com/search?country=Pakistan");

    }

    private void extractData() {
            RequestQueue queue = Volley.newRequestQueue(this);
            JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, JSON_URL, null, new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {
                    for (int i = 0; i < response.length(); i++) {
                        try {
                            JSONObject jsonObj = response.getJSONObject(i);
                            MyModelClass model = new MyModelClass();
                            model.setCountry(jsonObj.getString("country").toString());
                            model.setState(jsonObj.getString("state-province").toString());
                            model.setName(jsonObj.getString("name").toString());
                            model.setAlphaCode(jsonObj.getString("alpha_two_code").toString());
                            model.setWebPages(jsonObj.getJSONArray("web_pages"));

                            listData.add(model);
                            myAdapter.notifyDataSetChanged();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    progBar.dismiss();
                }

            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.d("Error","Getting Error ");
                }
            });
            queue.add(request);
        }
//    class AsyncTaskUpload extends AsyncTask<String,Void,String>
//    {
//        @Override
//        protected String doInBackground(String... strings) {
//
//            extractData();
//            return null;
//        }
//
//    }

}