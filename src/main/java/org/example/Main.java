package org.example;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);


        String httpUrl;
        int interval;
        System.out.print("Interval in seconds: ");
        interval = Integer.parseInt(scanner.nextLine())*100;
        System.out.print("HTTP URL for check: ");
        httpUrl = scanner.nextLine();


        while (true){
            int statusCode=-1;
            try(CloseableHttpClient httpClient = HttpClientBuilder.create().setDefaultRequestConfig(RequestConfig.DEFAULT).build()){
                CloseableHttpResponse response = httpClient.execute(new HttpGet(httpUrl));
                statusCode = response.getStatusLine().getStatusCode();
            } catch (IOException e){
                System.out.println("URL parsing error");
                break;
            }

            if(statusCode == 200){
                System.out.println(String.format("Result: OK(%d)", statusCode) );
            }else {
                System.out.println(String.format("Result: ERR(%d)", statusCode) );
            }

            Thread.sleep(interval);
        }


    }

}