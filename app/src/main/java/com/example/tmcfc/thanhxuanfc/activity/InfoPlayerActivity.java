package com.example.tmcfc.thanhxuanfc.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.tmcfc.thanhxuanfc.R;
import com.example.tmcfc.thanhxuanfc.adapter.InfoPlayerAdapter;
import com.example.tmcfc.thanhxuanfc.model.InfoCauThu;
import com.example.tmcfc.thanhxuanfc.model.Menu;
import com.example.tmcfc.thanhxuanfc.ultil.Server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class InfoPlayerActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private ListView listViewInfo;
    ArrayList<InfoCauThu> mangInfo;
    InfoPlayerAdapter infoPlayerAdapter;
    int id = 0;
    String tencauthu = "";
    String hinhanhcauthu = "";
    String namsinh = "";
    String chieucao = "";
    String cannang = "";
    String soao = "";
    String vitri = "";
    String diachi = "";
    String idmenu = "";
    String teamid = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_player);

        init();
        ActionBar();
        GetDuLieuInfoCauthu();
    }

    private void GetDuLieuInfoCauthu() {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Server.url_Info, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                if(response != null){
                    for(int i = 0; i < response.length(); i++){
                        try {
                            JSONObject jsonObject = response.getJSONObject(i);
                            id = jsonObject.getInt("id");
                            tencauthu = jsonObject.getString("tencauthu");
                            hinhanhcauthu = jsonObject.getString("hinhanhcauthu");
                            namsinh = jsonObject.getString("namsinh");
                            chieucao = jsonObject.getString("chieucao");
                            cannang = jsonObject.getString("cannang");
                            vitri = jsonObject.getString("vitri");
                            soao = jsonObject.getString("soao");
                            diachi = jsonObject.getString("diachi");
                            idmenu = jsonObject.getString("idmenu");
                            teamid = jsonObject.getString("teamid");
                            mangInfo.add(new InfoCauThu(id, tencauthu, hinhanhcauthu, namsinh, chieucao, cannang, vitri, soao, diachi, idmenu, teamid));
                            infoPlayerAdapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(InfoPlayerActivity.this, "ERROR!!!", Toast.LENGTH_SHORT).show();
            }
        });

        requestQueue.add(jsonArrayRequest);
    }

    private void ActionBar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(InfoPlayerActivity.this, MainActivity.class));
                finish();
            }
        });
    }

    private void init() {
        toolbar = findViewById(R.id.toolbarmanhinhinfoplayer);
        listViewInfo = findViewById(R.id.listviewInfo);
        mangInfo = new ArrayList<>();
        infoPlayerAdapter = new InfoPlayerAdapter(mangInfo, getApplicationContext());
        listViewInfo.setAdapter(infoPlayerAdapter);
    }
}
