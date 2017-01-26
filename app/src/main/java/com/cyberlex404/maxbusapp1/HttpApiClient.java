package com.cyberlex404.maxbusapp1;


import android.app.Application;

import retrofit2.Retrofit;
import com.cyberlex404.maxbusapp1.api.UmoriliApi;
import com.cyberlex404.maxbusapp1.api.TicketsApi;
import retrofit2.converter.gson.GsonConverterFactory;


public class HttpApiClient extends Application{

    private static UmoriliApi umoriliApi;

    private static TicketsApi ticketsApi;

    public static final String API_BASE_URL = "http://braslav-minsk.by";

    private Retrofit retrofit;

    @Override
    public void onCreate() {
        super.onCreate();

        retrofit = new Retrofit.Builder()
                .baseUrl("http://www.umori.li") //Базовая часть адреса
                .addConverterFactory(GsonConverterFactory.create()) //Конвертер, необходимый для преобразования JSON'а в объекты
                .build();
        umoriliApi = retrofit.create(UmoriliApi.class); //Создаем объект, при помощи которого будем выполнять запросы

        retrofit = new Retrofit.Builder()
                .baseUrl(API_BASE_URL) //Базовая часть адреса
                .addConverterFactory(GsonConverterFactory.create()) //Конвертер, необходимый для преобразования JSON'а в объекты
                .build();
        ticketsApi = retrofit.create(TicketsApi.class); //Создаем объект, при помощи которого будем выполнять запросы
    }

    public static UmoriliApi getExApi() {
        return umoriliApi;
    }

    public static TicketsApi getApi() {
        return ticketsApi;
    }

}
