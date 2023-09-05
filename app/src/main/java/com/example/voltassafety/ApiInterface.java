package com.example.voltassafety;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ApiInterface {

    @Headers({"Accept: application/json"})
    @POST("User/signin")
    default Call<LoginResponse> validateUser(@Body LoginCredentials BASE_URL) {
        return null;
    }


}
