package com.example.weatherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.nio.file.Watchable;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    Button btn_getCityId,btn_getWeatherByID,btn_getWeatherByName;
    EditText et_dataInput;
    ListView lv_WeatherReport;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_getCityId = findViewById(R.id.btn_getCityID);
        btn_getWeatherByID = findViewById(R.id.getWeatherByID);
        btn_getWeatherByName = findViewById(R.id.getWeatherByName);
        
        et_dataInput = findViewById(R.id.et_dataInput);
        lv_WeatherReport = findViewById(R.id.lv_WeatherReports);

       final WeatherDataService weatherDataService = new WeatherDataService(MainActivity.this);
        
        btn_getCityId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                 weatherDataService.getCityID(et_dataInput.getText().toString(), new WeatherDataService.VolleyResponseListener() {
                    @Override
                    public void onError(String message) {
                        Toast.makeText(MainActivity.this,"Something is wrong",Toast.LENGTH_SHORT).show();
                    }
                    @Override
                    public void onResponse(String cityID) {
                        if(weatherDataService.cityID=="")
                            Toast.makeText(MainActivity.this, "Please enter a valid city name Or your local City may not be available now" , Toast.LENGTH_SHORT).show();

                        else {
                            Toast.makeText(MainActivity.this, "The City Id for " + et_dataInput.getText().toString() + " is " + cityID, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
        
        btn_getWeatherByID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                weatherDataService.getCityForecastByID(et_dataInput.getText().toString(), new WeatherDataService.ForecastByIDResponse() {
                    @Override
                    public void onError(String message) {
                        Toast.makeText(MainActivity.this, "Something is wrong ", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(List<WeatherReportModel> weatherReportModel) {
                        // Put the entire list in the listview
                        ArrayAdapter<WeatherReportModel> arrayAdapter = new ArrayAdapter<WeatherReportModel>(MainActivity.this, android.R.layout.simple_list_item_1,weatherReportModel);
                        lv_WeatherReport.setAdapter(arrayAdapter);
                        Toast.makeText(MainActivity.this, "Made Using MetaWeather API", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        
        btn_getWeatherByName.setOnClickListener((new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                weatherDataService.getCityForecastByName(et_dataInput.getText().toString(), new WeatherDataService.getWeatherByCityName() {
                    @Override
                    public void onError(String message) {
                        Toast.makeText(MainActivity.this, "Something is wrong ", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(List<WeatherReportModel> weatherReportModel) {
                        // Put the entire list in the listview
                        ArrayAdapter<WeatherReportModel> arrayAdapter = new ArrayAdapter<WeatherReportModel>(MainActivity.this, android.R.layout.simple_list_item_1,weatherReportModel);
                        lv_WeatherReport.setAdapter(arrayAdapter);
                        Toast.makeText(MainActivity.this, "Made Using MetaWeather API", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }));

    }
}