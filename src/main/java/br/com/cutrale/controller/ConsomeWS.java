package br.com.cutrale.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Wheslley Nycolas da Silva - sc703450
 */
public class ConsomeWS {

    public ConsomeWS() {

    }

    public void consomeGetWS() {
        try {
            URL url = new URL("http://localhost:7001/ScheduleLibre/execute/test");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("ERROR CODE : "
                        + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));

            String output;
            while ((output = br.readLine()) != null) {
                System.out.println(output);
            }

            conn.disconnect();

        } catch (MalformedURLException | ProtocolException ex) {
            Logger.getLogger(ConsomeWS.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ConsomeWS.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void consomePostWS(String xml, String user, Integer seqPendAplic) {

        try {

            String urlParameters = "xml=" + xml + "&user=" + user + "&seq_pend_aplic=" + seqPendAplic;
            
            byte[] postData = urlParameters.getBytes(StandardCharsets.UTF_8);
            int postDataLength = postData.length;
            
            String request = "http://localhost:7001/ScheduleLibre/execute/upload";
            URL url = new URL(request);
            
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setInstanceFollowRedirects(false);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setRequestProperty("charset", "utf-8");
            conn.setRequestProperty("Content-Length", Integer.toString(postDataLength));
            conn.setUseCaches(false);

            OutputStream os = conn.getOutputStream();
            os.write(postData);

            StringBuilder responseSB = new StringBuilder();
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            String line;
            while ((line = br.readLine()) != null) {
                responseSB.append(line);
            }

            br.close();
            os.close();

            System.out.println(responseSB.toString());

        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }
    
}
