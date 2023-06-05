package com.moutamid.servicebuying.Notifications;


import com.fxn.stash.Stash;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.HeaderMap;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface APIService {


    @POST("fcm/send")
    Call<MyResponse> sendNotification(@HeaderMap Map<String,String> hearderMap, @Body Sender body);

}
