package com.coba.dhl.api_dhl;

import com.squareup.okhttp.*;
import org.apache.wink.json4j.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

@SpringBootApplication
public class ApiDhlApplication {

public static void main(String[] args) throws IOException {
    coba();
SpringApplication.run(ApiDhlApplication.class, args);
}



public static void coba() throws IOException
{

OkHttpClient client = new OkHttpClient();

MediaType mediaType = MediaType.parse("application/json");
    HttpUrl httpUrl = new HttpUrl.Builder()
            .scheme("https")
            .host("api-eu.dhl.com")
            .addPathSegment("track")
            .addPathSegment("shipments")
            .addQueryParameter("trackingNumber", "7777777770")
            .addQueryParameter("service", "express")
               .build();

    Request request = new Request.Builder()
            .addHeader("content-type", "application/json")
            .addHeader("Connection","close")
            .addHeader("DHL-API-Key", "demo-key")
            .addHeader("ConsumerKey", "sESkHK7RV9nEHui001coROhMoeZgwSiv")
            .addHeader("ConsumerSecret", "ZFCJKQlCGf491Fuf")
            .url(httpUrl) // <- Finally put httpUrl in here
            .build();



    try {
Response response = client.newCall(request).execute();

System.out.println(response.body().string());
} catch (Exception e) {
System.err.println("Caught IOException: " + e.getMessage());
}
}



}
