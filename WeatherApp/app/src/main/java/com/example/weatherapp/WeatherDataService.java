package com.example.weatherapp;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import com.android.volley.toolbox.JsonObjectRequest;
import com.example.weatherapp.WeatherReportModel;

public class WeatherDataService {

    public static final String QUERY_FOR_CITY_ID = "https://www.metaweather.com/api/location/search/?query=";
    public static final String QUERY_FOR_CITY_WEATHER_BY_ID = "https://www.metaweather.com/api/location/";
    Context context;
    String cityID;

    public WeatherDataService(Context context) {
        this.context = context;
    }

    public interface VolleyResponseListener {
        void onError(String message);

        void onResponse(String cityID);
    }

    public void getCityID(String cityName, final VolleyResponseListener volleyResponseListener) {
        String url = QUERY_FOR_CITY_ID + cityName;

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                cityID = "";
                try {
                    JSONObject mainstring = response.getJSONObject(0);
                    cityID = mainstring.getString("woeid");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                volleyResponseListener.onResponse(cityID);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //Toast.makeText(context, "Something is wrong", Toast.LENGTH_SHORT).show();
                volleyResponseListener.onError("Something is Wrong");
            }
        });

        MySingleton.getInstance(context).addToRequestQueue(request);

    }

    public interface ForecastByIDResponse {
        void onError(String message);

        void onResponse( List<WeatherReportModel> weatherReportModel);
    }

    public void getCityForecastByID(String cityID,ForecastByIDResponse forecastByIDResponse) {
        List<WeatherReportModel> weatherReport  = new ArrayList<>();

        //get the json object
        String url = QUERY_FOR_CITY_WEATHER_BY_ID + cityID;

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                // Toast.makeText(context, response.toString(), Toast.LENGTH_SHORT).show();

                try {
                    JSONArray consolidated_weather_list = response.getJSONArray("consolidated_weather");
                    // get the data of the first array element


                    for(int i = 0; i< consolidated_weather_list.length();i++) {
                        WeatherReportModel One_day = new WeatherReportModel();
                        JSONObject one_day_from_api = (JSONObject) consolidated_weather_list.get(i);
                        One_day.setId(one_day_from_api.getInt("id"));
                        One_day.setApplicable_date(one_day_from_api.getString("applicable_date"));
                        One_day.setWeather_state_name(one_day_from_api.getString("weather_state_name"));
                        One_day.setGetWeather_state_abbr(one_day_from_api.getString("weather_state_abbr"));
                        One_day.setWind_direction_compass(one_day_from_api.getString("wind_direction_compass"));
                        One_day.setMin_temp((float) one_day_from_api.getDouble("min_temp"));
                        One_day.setMax_temp((float) one_day_from_api.getDouble("max_temp"));
                        One_day.setWind_speed((float) one_day_from_api.getDouble("wind_speed"));
                        One_day.setWind_direction((float) one_day_from_api.getDouble("wind_direction"));
                        One_day.setAir_pressure((float) one_day_from_api.getDouble("air_pressure"));
                        One_day.setHumidity(one_day_from_api.getInt("humidity"));

                        weatherReport.add(One_day);
                    }

                    forecastByIDResponse.onResponse(weatherReport);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show();
            }
        });
        MySingleton.getInstance(context).addToRequestQueue(request);

    }

    public interface getWeatherByCityName
    {
        void onError(String message);

        void onResponse(List<WeatherReportModel> weatherReportModels);

    }
   public void getCityForecastByName(String cityName, getWeatherByCityName getWeatherByCityName)
   {
        getCityID(cityName, new VolleyResponseListener() {
            @Override
            public void onError(String message) {

            }

            @Override
            public void onResponse(String cityID) {
                //Now we have our city ID then

                getCityForecastByID(cityID, new ForecastByIDResponse() {
                    @Override
                    public void onError(String message) {

                    }

                    @Override
                    public void onResponse(List<WeatherReportModel> weatherReportModel) {
                        //Now we have our weather view model

                        getWeatherByCityName.onResponse(weatherReportModel);

                    }
                });
            }
        });
   }


}