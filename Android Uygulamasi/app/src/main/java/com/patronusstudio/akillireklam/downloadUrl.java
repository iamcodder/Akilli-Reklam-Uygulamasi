package com.patronusstudio.akillireklam;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;


//bu sınıf haritadaki bulunan/aranan dükkanların bilgilerini json olark döndürüyor
public class downloadUrl
{

    public String ReadTheUrl(String placeURL) throws IOException {

        String data="";
        InputStream inputStream=null;
        HttpURLConnection httpURLConnection =null;


        try {
            URL url=new URL(placeURL);
            httpURLConnection=(HttpURLConnection)url.openConnection();
            httpURLConnection.connect();

            inputStream=httpURLConnection.getInputStream();
            BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream));

            StringBuilder stringBuffer=new StringBuilder();

            String line="";

            while ((line =bufferedReader.readLine()) != null){

                stringBuffer.append(line);

            }

            data =stringBuffer.toString();
            bufferedReader.close();

        }
        catch (Exception e) {
            e.printStackTrace();
        }

        finally {
            if (inputStream != null) {
                inputStream.close();
                httpURLConnection.disconnect();
            }
        }

        return data;

    }

}
