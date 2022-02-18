package com.example.testmenu.interfaces;

import com.example.testmenu.Model.NationalizeModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Generate HTTP Services
 */
public interface NationalizeApi {
    /**
     * @apiNote "https://api.nationalize.io/?name=Perenganito"
     * @apiNote GET
     * @param name
     * @serialData NationalizeModel
     * @return
     */
    @GET(".")
    Call<NationalizeModel> getNationalizeResult(@Query("name") String name);
}
