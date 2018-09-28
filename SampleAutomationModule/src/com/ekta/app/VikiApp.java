package com.ekta.app;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class VikiApp {
    public static void main(String[] args) {
        String baseUrl = "http://api.viki.io/v4/videos.json?app=100250a&per_page=10&page=%d";
        String keyToSearch = "{\"more\":true";
        String keyHd="\"hd\":true";
        String keyHdFalse = "\"hd\":false";
        URL url;
        boolean flag = true;
        int counter = 0;
        String data = null;
        Integer pageIndex = 0;
        try {
            do {
                pageIndex = pageIndex + 1;
                String urlStr = String.format(baseUrl, pageIndex);
                System.out.println("Running URL :" + urlStr);
                url = new URL(urlStr);
                data = getResponseFromHttpUrl(url);
                if (data.startsWith(keyToSearch) && data.contains(keyHd)) {
                    counter++;
                    System.out.println("Total count of hd key true are " +counter);

                } else if (data.startsWith(keyToSearch) && data.contains(keyHdFalse)) {
                    counter++;
                    System.out.println("Total count of hd key false are "+counter);
                }

                else if(data == null || !data.startsWith(keyToSearch)) {
                    flag = false;
                    System.out.println("Done..........");
                }

            } while (flag);
        }

        catch (MalformedURLException e)
        {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();
            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");
            boolean hasInput = scanner.hasNext();
            String response = null;
            if (hasInput) {
                response = scanner.next();
            }
            scanner.close();
            return response;
        }

        finally{
            urlConnection.disconnect();
        }

    }
}
