package com.example.weather_api;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

public class ApiTest {

    private final Api api = ApiFactory.createApi();

    @Test
    public void testCurrentWeathr() throws IOException {

        Call<CurrentWeather> call = api.getCerruntWeather(
                35,
                139,
                Constants.APY_KEY,
                Constants.DEFAULT_UNITS);

        // делаем запрос
        Response<CurrentWeather> response = call.execute();

        Assert.assertTrue(response.isSuccessful());

        CurrentWeather body = response.body();

        Assert.assertNotNull(body);

        String cityName = body.getCityName();

        Assert.assertNotNull(cityName);
        Assert.assertTrue(cityName.length()>0);


        ForecastMain forecastMain = body.getMain();

        Assert.assertNotNull(forecastMain);

        Assert.assertNotNull(forecastMain.getTemp());
        Assert.assertNotNull(forecastMain.getPressure());
        Assert.assertNotNull(forecastMain.getHimidity());
        Assert.assertNotNull(forecastMain.getMaxTemp());
        Assert.assertNotNull(forecastMain.getMinTemp());

       ForecastWind forecastWind = body.getWind();

        Assert.assertNotNull(forecastWind);
// сравниваем с 0
        Assert.assertNotSame(0, forecastWind.getDegree());
        Assert.assertNotSame(0, forecastWind.getSpeed());

        // https://proselyte.net/tutorials/junit/assertions/
    }
}
