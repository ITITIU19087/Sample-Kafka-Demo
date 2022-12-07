package com.antnest.kafka.quartz.job;

import com.antnest.kafka.service.TestService;
import lombok.SneakyThrows;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;


public class QuartzJob implements Job {

    private static final String USER_AGENT = "Chrome/108.0";
    private static final String POST_PARAMS = "message=Test";

    @Autowired
    private TestService testService;
    @SneakyThrows
    @Override
    public void execute(JobExecutionContext jobExecutionContext) {
        URL obj = new URL("http://localhost:8080/test");
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("User-Agent", USER_AGENT);

        con.setDoOutput(true);
        OutputStream os = con.getOutputStream();
        os.write(POST_PARAMS.getBytes());
        os.flush();
        os.close();
        int responseCode = con.getResponseCode();

        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
        } else {
            System.out.println("POST request did not work.");
        }
        testService.testJob();
    }
}
