package com.example.cnv_money;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import com.google.gson.JsonObject;

public interface RetrofitAPI {

    @POST("conv/price")
    Call<JsonObject> convertMoney(@Body Money money);
}