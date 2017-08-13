package com.example.arash.baninab;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import java.util.ArrayList;
import com.example.arash.baninab.data.contactsData.AllContact;
import com.example.arash.baninab.data.contactsData.Contact;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {

    private RecyclerViewAdapter mAdapter;
    private RecyclerView mRecyclerView;
    private SOService mService;


    public void loadContacts() {
        mService.getAnswers().enqueue(new Callback<AllContact>() {
            @Override
            public void onResponse(Call<AllContact> call, Response<AllContact> response) {
                if (response.isSuccessful()) {
                    mAdapter.updateAnswers(response.body().getContacts());
                    Log.d("MainActivity", "posts loaded from API");
                } else {
                    int statusCode = response.code();
                    // handle request errors depending on status code
                }
            }

            @Override
            public void onFailure(Call<AllContact> call, Throwable t) {
                Log.d("MainActivity", "error loading from API");
            }
        });

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mService = ApiUtils.getSOService();
        mRecyclerView = (RecyclerView) findViewById(R.id.recyler);
        mAdapter = new RecyclerViewAdapter(this, new ArrayList<Contact>(0), new RecyclerViewAdapter.PostItemListener() {
            @Override
            public void onPostClick(Contact contact) {
//                Uri number = Uri.parse();
//                Toast.makeText(MainActivity.this, "Post id is" + id, Toast.LENGTH_SHORT).show();
                Intent i = new Intent(MainActivity.this , ContactInfo.class);
                i.putExtra("name" , contact.getName());
                i.putExtra("email",contact.getEmail());
                i.putExtra("address",contact.getAddress());
                i.putExtra("gender",contact.getGender());
                i.putExtra("mobile",contact.getPhone().getMobile());
                i.putExtra("home",contact.getPhone().getHome());
                i.putExtra("office",contact.getPhone().getOffice());
                startActivity(i);
            }
        });

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setHasFixedSize(true);
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        mRecyclerView.addItemDecoration(itemDecoration);

       loadContacts();
    }



}