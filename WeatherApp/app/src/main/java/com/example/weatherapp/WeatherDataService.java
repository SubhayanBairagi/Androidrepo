package com.example.weatherapp;

import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class WeatherDataService {

    public static final String QUERY_FOR_CITY_ID = "https://www.metaweather.com/api/location/search/?query=";

    public String getCityID(String cityName)
    {
        String url = QUERY_FOR_CITY_ID +cityName;

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                String cityID = "";
                try {
                    JSONObject mainstring = response.getJSONObject(0);
                    cityID = mainstring.getString("woeid");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                if(cityID=="")
                    Toast.makeText(MainActivity.this, "Please enter a valid city name Or your local City may not be available now ", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this,"The City Id for "+cityName+" is "+cityID, Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "Something is wrong", Toast.LENGTH_SHORT).show();
            }
        });

        MySingleton.getInstance(MainActivity.this).addToRequestQueue(request);
    }

//    public List<WeatherReportModel> getCityForecastByID(String cityID)
//    {
//
//    }
//    public List<WeatherReportModel> getCityForecastByName(String cityName)
//    {

    }
}
