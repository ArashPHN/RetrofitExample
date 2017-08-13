package com.example.arash.baninab;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.arash.baninab.data.contactsData.Contact;

public class ContactInfo extends AppCompatActivity {
   // private Contact mContat;
    private TextView name , gender, email , address , homePhone , mobilePhone , officePhone ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_info);
        name = (TextView)findViewById(R.id.text_info_name);
        name.setText("Name : " +getIntent().getStringExtra("contact"));
        gender = (TextView)findViewById(R.id.text_info_gender);
        gender.setText("gender : " +getIntent().getStringExtra("gender"));
        email = (TextView)findViewById(R.id.text_info_email);
        email.setText("email : " +getIntent().getStringExtra("email"));
        address = (TextView)findViewById(R.id.text_info_address);
        address.setText("address : " +getIntent().getStringExtra("address"));
        homePhone = (TextView)findViewById(R.id.text_info_home_phone);
        homePhone.setText("home Number : " +getIntent().getStringExtra("home"));
        mobilePhone = (TextView)findViewById(R.id.text_info_mobile_phone);
        mobilePhone.setText("mobile Number : " +getIntent().getStringExtra("mobile"));
        officePhone = (TextView)findViewById(R.id.text_info_office_phone);
        officePhone.setText("office Number : " + getIntent().getStringExtra("office"));
    }
}
