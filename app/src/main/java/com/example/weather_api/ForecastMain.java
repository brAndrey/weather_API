package com.example.weather_api;

import com.google.gson.annotations.SerializedName;

public class ForecastMain {

    private float temp;

    private float pressure;

    private float himidity;

    @SerializedName("temp_min")
    private float minTemp;

    @SerializedName("temp_max")
    private float maxTemp;

    // ************************************
    public float getTemp() {
        return temp;
    }

    public float getPressure() {
        return pressure;
    }

    public float getHimidity() {
        return himidity;
    }

    public float getMinTemp() {
        return minTemp;
    }

    public float getMaxTemp() {
        return maxTemp;
    }

}
