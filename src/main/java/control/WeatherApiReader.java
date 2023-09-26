package control;

import DTO.WeatherApiDTO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import java.io.IOException;

public class WeatherApiReader {
    String sky;
    int humidity;

    static Gson gson = new  GsonBuilder().setPrettyPrinting().create();
    public WeatherApiReader() {
        String url = "https://vejr.eu/api.php?location=KongensLyngby&degree=C";
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        Request request = new Request.Builder()
                .url(url)
                .method("GET", null)
                .build();
        Response response;
        try {
            response = client.newCall(request).execute();
            String res = response.body().string();
            WeatherApiDTO weatherApiDTO = gson.fromJson(res, WeatherApiDTO.class);
            sky=weatherApiDTO.getCurrentData().getSkyText();
            humidity= Integer.parseInt(weatherApiDTO.getCurrentData().getHumidity());
        } catch (
                IOException e)
        {
            throw new RuntimeException(e);
        }
        }
    }
