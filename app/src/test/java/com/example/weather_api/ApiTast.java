package com.example.weather_api;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

public class ApiTast {

    private final Api api=ApiFactory.createApi();

    @Test
    public void testCurrentWether() throws IOException {

        Call<CurrentWeather> call = api.getCerruntWeather(
                35,
                139,
                Constants.APY_KEY,
                Constants.DEFAULT_UNITS);


        Response<CurrentWeather> response = call.execute();


        Assert.assertTrue(response.isSuccessful());

    }

}
