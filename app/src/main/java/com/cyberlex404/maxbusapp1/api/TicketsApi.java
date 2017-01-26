package com.cyberlex404.maxbusapp1.api;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import com.cyberlex404.maxbusapp1.TicketsModel;


public interface TicketsApi {

    @GET("/api/v1/tickets/{id}/load")
    Call<List<TicketsModel>> getTickets(@Path(value = "id") int id);
}