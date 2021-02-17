package com.coba.dhl.api_dhl;


import com.squareup.okhttp.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.json.JSONObject;

import java.io.IOException;

@RestController
public class DHL_tracker {
    @Autowired
    private RestTemplate restTemplate;
    Response response;
    JSONObject jo = new JSONObject();

    //for acces api http://localhost:1212/getData?trackingNumber=xxxxxxxxxxxx
    @RequestMapping("/getData")
    public String getAcc(@RequestParam("trackingNumber")  String trackingNumber) throws IOException
    {
       // try {

            OkHttpClient client = new OkHttpClient();
            MediaType mediaType = MediaType.parse("application/json");

            HttpUrl httpUrl = new HttpUrl.Builder()
                    .scheme("https")
                    .host("api-eu.dhl.com")
                    .addPathSegment("track")
                    .addPathSegment("shipments")
                    .addQueryParameter("trackingNumber", trackingNumber)
                    .addQueryParameter("service", "express")
                    .build();

            Request request = new Request.Builder()
                    .addHeader("content-type", "application/json")
                    .addHeader("Connection", "close")
                    .addHeader("DHL-API-Key", "xxxxxxxxxxxx")
                    .addHeader("ConsumerKey", "xxxxxxxxxxxx")
                    .addHeader("ConsumerSecret", "xxxxxxxxxxxx")
                    .removeHeader("Content-Encoding")
                    .removeHeader("Content-Length")
                    .url(httpUrl) // <- Finally put httpUrl in here
                    .build();
          response = client.newCall(request).execute();
        return response.body().string();
    }



}


