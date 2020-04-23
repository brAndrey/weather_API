package com.example.weather_api;

import org.junit.Test;

import java.io.IOException;

import retrofit2.Call;

public class ApiTast {

    private final Api api=ApiFactory.createApi();

    @Test
    public void testCurrentWether() throws IOException {

        Call<CurrentWeather> call = api.getCerruntWeather(
                35,
                139,
                Constants.APY_KEY,
                Constants.DEFAULT_UNITS);
    }

}
