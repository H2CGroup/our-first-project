package com.example.tmcfc.thanhxuanfc.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.tmcfc.thanhxuanfc.R;
import com.example.tmcfc.thanhxuanfc.adapter.MenuAdapter;
import com.example.tmcfc.thanhxuanfc.model.Menu;
import com.example.tmcfc.thanhxuanfc.model.UserInformation;
import com.example.tmcfc.thanhxuanfc.ultil.CheckConnect;
import com.example.tmcfc.thanhxuanfc.ultil.Server;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Toolbar toolbar;
    private ViewFlipper viewFlipper;
    private RecyclerView recyclerViewmanhinhchinh;
    private NavigationView navigationView;
    private CircleImageView circleImageView;
    private TextView edtname, edtnickname;
    private ListView listViewmanhinhchinh;
    private DrawerLayout drawerLayout;
    DatabaseReference databaseReference;
    ArrayList<Menu> mangmenu;
    MenuAdapter menuAdapter;
    int id = 0;
    String tenmenu = "";
    String hinhanhmenu = "";


    private FirebaseAuth fAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        setProfile();
        if(CheckConnect.haveNetworkConnection(getApplicationContext())){
            updateUI();

            ActionBar();
            ActionViewFlipper();
            GetDuLieuMenu();
            CatchOnItemListview();
        }else{
            CheckConnect.ShowToast_short(getApplicationContext(), "Bạn hãy kiểm tra lại kết nối!");
            finish();
        }
    }

    private void setProfile(){
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    UserInformation uInfo = new UserInformation();
                    uInfo.setName(ds.getValue(UserInformation.class).getName());
                    uInfo.setNickname(ds.getValue(UserInformation.class).getNickname());
                    uInfo.setImgavatar(ds.getValue(UserInformation.class).getImgavatar());

                    Picasso.get().load(uInfo.getImgavatar()).into(circleImageView);
                    edtname.setText(uInfo.getName());
                    edtnickname.setText(uInfo.getNickname());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


    private void CatchOnItemListview() {
        listViewmanhinhchinh.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        if(CheckConnect.haveNetworkConnection(getApplicationContext())){
                            finish();
                            Intent intent = new Intent(MainActivity.this, MainActivity.class);
                            startActivity(intent);
                        }else{
                            CheckConnect.ShowToast_short(getApplicationContext(), "Bạn hãy kiểm tra lại kết nối!");
                        }
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case 1:
                        break;
                    case 2:
                        break;
                    case 3:
                        break;
                    case 4:
                        break;
                    case 5:
                        break;
                    case 6:
                        logout();
                }
            }
        });
    }

    private void logout() {
        fAuth.signOut();
        finish();
        startActivity(new Intent(MainActivity.this, StartActivity.class));
    }

    private void GetDuLieuMenu() {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Server.url_menu, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                if(response != null){
                    for(int i = 0; i < response.length(); i++){
                        try {
                            JSONObject jsonObject = response.getJSONObject(i);
                            id = jsonObject.getInt("id");
                            tenmenu = jsonObject.getString("tenmenu");
                            hinhanhmenu = jsonObject.getString("hinhanhmenu");
                            mangmenu.add(new Menu(id, tenmenu, hinhanhmenu));
                            menuAdapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    mangmenu.add(5, new Menu(0, "Liên hệ", "https://cdn0.iconfinder.com/data/icons/3-colors-outline/500/Phone-512.png"));
                    mangmenu.add(6, new Menu(0, "Đăng xuất", "https://cdn0.iconfinder.com/data/icons/customicondesign-office6-shadow/256/logout.png"));
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                CheckConnect.ShowToast_short(getApplicationContext(), error.toString());
            }
        });
        requestQueue.add(jsonArrayRequest);
    }


    private void ActionViewFlipper() {
        ArrayList<String> mangquangcao = new ArrayList<>();
        mangquangcao.add("https://image.vtc.vn/files/ngocyen/2018/05/20/u23-viet-nam-quang-hai-goi-cong-phuong-co-the-tra-loi-0918433.jpg");
        mangquangcao.add("https://cdn.24h.com.vn/upload/3-2018/images/2018-07-10/Lich-thi-dau-bong-da-U23-Viet-Nam-giai-quoc-te-2018-u23-viet-nam-bay-vao-ban-ket-chau-a-co-duoc-du-oly-1531194271-738-width660height382.jpg");
        mangquangcao.add("https://image.vtc.vn/files/ngocyen/2018/05/20/u23-viet-nam-quang-hai-goi-cong-phuong-co-the-tra-loi-0918433.jpg");
        mangquangcao.add("https://cdn.24h.com.vn/upload/3-2018/images/2018-07-10/Lich-thi-dau-bong-da-U23-Viet-Nam-giai-quoc-te-2018-u23-viet-nam-bay-vao-ban-ket-chau-a-co-duoc-du-oly-1531194271-738-width660height382.jpg");
        for(int i = 0; i < mangquangcao.size();i++){
            ImageView imageView = new ImageView(getApplicationContext());
            Picasso.get().load(mangquangcao.get(i)).into(imageView);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            viewFlipper.addView(imageView);
        }
        viewFlipper.setFlipInterval(5000);
        viewFlipper.setAutoStart(true);
        Animation animation_slide_in = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_in_right);
        Animation animation_slide_out = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_out_right);
        viewFlipper.setInAnimation(animation_slide_in);
        viewFlipper.setOutAnimation(animation_slide_out);
    }

    private void ActionBar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(android.R.drawable.ic_menu_sort_by_size);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
    }

    private void updateUI() {
        if(fAuth.getCurrentUser() != null){
            Log.i("MainActivity", "fAuth != null");
        }else{
            startActivity(new Intent(MainActivity.this, StartActivity.class));
            finish();
        }
    }

    private void init() {
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Users");
        toolbar = findViewById(R.id.toolbarmanhinhchinh);
        viewFlipper = findViewById(R.id.viewflipper);
        recyclerViewmanhinhchinh = findViewById(R.id.recyclerview);
        navigationView = findViewById(R.id.navigationview);
        edtname = findViewById(R.id.tvUsername);
        edtnickname = findViewById(R.id.tvUsernickname);
        listViewmanhinhchinh = findViewById(R.id.listviewmanhinhchinh);
        drawerLayout = findViewById(R.id.drawablelayout);
        circleImageView = findViewById(R.id.ivAvatar);
        circleImageView.setOnClickListener(this);
        mangmenu = new ArrayList<>();
        mangmenu.add(0, new Menu(0, "Trang chính", "https://cdn2.iconfinder.com/data/icons/free-basic-icon-set-2/300/8-512.png"));
        menuAdapter = new MenuAdapter(mangmenu, getApplicationContext());
        listViewmanhinhchinh.setAdapter(menuAdapter);
        fAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ivAvatar:
                startActivity(new Intent(MainActivity.this, ProfileActivity.class));
                finish();
        }
    }
}
