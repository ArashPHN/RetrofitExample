package com.example.arash.baninab;

/**
 * Created by Arash on 8/13/2017.
 */
public class ApiUtils {

    public static final String BASE_URL = "https://api.stackexchange.com/2.2/";

    public static SOService getSOService() {
        return RetrofitClient.getClient(BASE_URL).create(SOService.class);
    }
}