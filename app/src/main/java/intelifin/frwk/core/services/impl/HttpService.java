package intelifin.frwk.core.services.impl;

/**
 * Created by AdrianRodriguez on 06/03/17.
 */

import android.os.AsyncTask;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.TreeMap;

import javax.net.ssl.HttpsURLConnection;

import intelifin.frwk.core.services.base.IHttpService;
import intelifin.frwk.core.services.exception.HttpServiceException;

public class HttpService implements IHttpService  {

    private final String USER_AGENT = "Mozilla/5.0";

    /*public JSONObject post(String _endpoint, TreeMap<String, String> _headers, JSONObject _payload) throws HttpServiceException {
        HttpsURLConnection conn = null;
        BufferedReader reader = null;
        int _responseCode = -1;
        System.out.println("@HttpService: 1");

        try {
            URL url = new URL(_endpoint);

            conn = (HttpsURLConnection) url.openConnection();
            System.out.println("@HttpService: 2");

            //conn.setConnectTimeout(60);

            if (_headers != null) {
                for (java.util.Map.Entry<String, String> e : _headers.entrySet()) {
                    conn.setRequestProperty(e.getKey(), e.getValue());
                }
            }
            System.out.println("@HttpService: 3");

            conn.setRequestProperty("Content-Type", "application/json");
           *//* conn.setRequestProperty("Connection", "close");*//*

           *//* byte[] requestString;
            requestString = (url != null) ? url.toString().getBytes("utf-8") : "".getBytes();
            conn.setRequestProperty("Content-Length", "" + requestString.length + 1);*//*

            conn.setRequestMethod("POST");
            conn.setDoInput(true);
            conn.setDoOutput(true);

            System.out.println("@HttpService: 4");

            JSONObject _data = new JSONObject();
            _data.put("data", _payload);

            java.io.OutputStream os = conn.getOutputStream();
            java.io.BufferedWriter writer = new java.io.BufferedWriter(
                    new java.io.OutputStreamWriter(os, "UTF-8"));
            writer.write(_data.toString());
            writer.flush();
            writer.close();
            os.close();

            conn.connect();

            System.out.println("@HttpService: 5");

            _responseCode = conn.getResponseCode();

            if (_responseCode >= 200 && _responseCode <= 399){
                java.io.InputStream inputStream = conn.getInputStream();
                StringBuffer buffer = new StringBuffer();
                if (inputStream == null) {
                    // Nothing to do.
                    throw new HttpServiceException("The inputstream is null", _responseCode);
                }
                reader = new BufferedReader(new InputStreamReader(inputStream));

                String line;
                while ((line = reader.readLine()) != null) {
                    buffer.append(line + "\n");
                }

                System.out.println(buffer.toString());
                return new JSONObject(buffer.toString());
            }

            throw new HttpServiceException("The execution shouldnt be here", _responseCode);
        }
        catch (Exception e) {
            throw new HttpServiceException(e.getMessage(), _responseCode);
        }
        finally {
            if (conn != null) {
                conn.disconnect();
            }
        }
    }*/

    @Override
    public JSONObject get(String _endpoint, TreeMap<String, String> _headers) throws HttpServiceException {
        int responseCode = -1;

        try {
            URL obj = new URL(_endpoint);
            HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();

            con.setRequestMethod("GET");

            //add request header
            //con.setRequestProperty("User-Agent", USER_AGENT);
            con.setConnectTimeout(60);

            if (_headers != null) {
                for (java.util.Map.Entry<String, String> e : _headers.entrySet()) {
                    con.setRequestProperty(e.getKey(), e.getValue());
                }
            }

            con.setRequestProperty("Content-Type", "application/json");
            con.setRequestProperty("Connection", "close");

            byte[] requestString;
            requestString = (obj != null) ? obj.toString().getBytes("utf-8") : "".getBytes();
            con.setRequestProperty("Content-Length", "" + requestString.length + 1);


            responseCode = con.getResponseCode();
            //System.out.println("\nSending 'GET' request to URL : " + url);
            System.out.println("Response Code : " + responseCode);

            if (responseCode >= 200 && responseCode <= 399) {
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(con.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                System.out.println(response.toString());

                return new JSONObject(response.toString());
            }

            throw new HttpServiceException("The http GET to the service had an error", responseCode);
        }
        catch(Exception e){
            throw new HttpServiceException(e.getMessage(), responseCode);
        }
    }



    @Override
    public JSONObject post(String _endpoint, TreeMap<String, String> _headers, JSONObject _payload) throws HttpServiceException {
        int responseCode = -1;
        try {
            System.out.println("1");
            URL obj = new URL(_endpoint);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            //add reuqest header
            con.setRequestMethod("POST");

            con.setConnectTimeout(60);
            System.out.println("2");

            if (_headers != null) {
                for (java.util.Map.Entry<String, String> e : _headers.entrySet()) {
                    con.setRequestProperty(e.getKey(), e.getValue());
                }
            }
            System.out.println("3");

            con.setRequestProperty("Content-Type", "application/json");
            con.setRequestProperty("Connection", "close");

            byte[] requestString;
            requestString = (obj != null) ? obj.toString().getBytes("utf-8") : "".getBytes();
            con.setRequestProperty("Content-Length", "" + requestString.length + 1);

            System.out.println("4");

            // Send post request
            con.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            //wr.writeBytes(urlParameters);
            wr.writeBytes(_payload.toString());
            wr.flush();
            wr.close();

            responseCode = con.getResponseCode();
            System.out.println("\nSending 'POST' request to URL : " + _endpoint);
            //System.out.println("Post parameters : " + urlParameters);
            System.out.println("Response Code : " + responseCode);

            if (responseCode >= 200 && responseCode <= 399) {
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(con.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                //print result
                System.out.println(response.toString());

                return new JSONObject(response.toString());
            }

            throw new HttpServiceException("The http GET to the service had an error", responseCode);
        }
        catch(Exception e){
            throw new HttpServiceException(e.getMessage(), responseCode);
        }
    }

}
