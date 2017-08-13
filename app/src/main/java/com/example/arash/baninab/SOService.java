package com.example.arash.baninab;


import com.example.arash.baninab.data.contactsData.AllContact;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface SOService {
     String url = "https://api.androidhive.info/contacts/";

    @GET(url)
    Call<AllContact> getAnswers();

    @GET(url)
    Call<AllContact> getAnswers(@Query("tagged") String tags);
}