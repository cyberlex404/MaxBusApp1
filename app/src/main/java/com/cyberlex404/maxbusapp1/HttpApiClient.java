package com.cyberlex404.maxbusapp1;


import android.app.Application;

import retrofit2.Retrofit;
import com.cyberlex404.maxbusapp1.api.UmoriliApi;
import retrofit2.converter.gson.GsonConverterFactory;


public class HttpApiClient extends Application{

    private static UmoriliApi umoriliApi;
    private Retrofit retrofit;

    @Override
    public void onCreate() {
        super.onCreate();

        retrofit = new Retrofit.Builder()
                .baseUrl("http://www.umori.li/") //Базовая часть адреса
                .addConverterFactory(GsonConverterFactory.create()) //Конвертер, необходимый для преобразования JSON'а в объекты
                .build();
        umoriliApi = retrofit.create(UmoriliApi.class); //Создаем объект, при помощи которого будем выполнять запросы
    }

    public static UmoriliApi getApi() {
        return umoriliApi;
    }

}
