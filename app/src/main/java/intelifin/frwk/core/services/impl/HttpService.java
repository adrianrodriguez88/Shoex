package intelifin.frwk.core.services.impl;

/**
 * Created by AdrianRodriguez on 06/03/17.
 */

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

public class HttpService implements IHttpService {

    private final String USER_AGENT = "Mozilla/5.0";

    public JSONObject post(String url, TreeMap<String, String> _headers, JSONObject _payload) throws HttpServiceException{
        throw new HttpServiceException("", -1);
    }

    public String post(String... params) {
        // These two need to be declared outside the try/catch
        // so that they can be closed in the finally block.
        HttpsURLConnection urlConnection = null;
        BufferedReader reader = null;

        // Will contain the raw JSON response as a string.
        String forecastJsonStr = null;

        try {
            // Construct the URL for the OpenWeatherMap query
            // Possible parameters are avaiable at OWM's forecast API page, at
            // http://openweathermap.org/API#forecast
            URL url = new URL("https://mi.albo.mx/v1/iop/selfservice");

            // Create the request to OpenWeatherMap, and open the connection
            urlConnection = (HttpsURLConnection) url.openConnection();

                /*
                * "headers" : {
                       "Content-Type":"application/json",
                       "albo-tx": "deeplink@1:send",
                       "albo-identity": "back-off.9873892ABB23DFFBA9802717882CADD19901BC91A12C23D332BB99FF",
                       "albo-role": "albo::role::common-user"
                   }
                * */
            urlConnection.setRequestProperty("Content-Type", "application/json");
            urlConnection.setRequestProperty("albo-tx", "deeplink@1:send");
            urlConnection.setRequestProperty("albo-identity", "back-off.9873892ABB23DFFBA9802717882CADD19901BC91A12C23D332BB99FF");
            urlConnection.setRequestProperty("albo-role", "albo::role::common-user");

            urlConnection.setRequestMethod("POST");
            urlConnection.setDoInput(true);
            urlConnection.setDoOutput(true);

            JSONObject bodyJson = new JSONObject();
            JSONObject dataJson = new JSONObject();
            //     "data" : {email:email, phone:phone, cmd:"reinstall"}
            dataJson.put("email",params[0]);
            dataJson.put("phone",params[1]);
            dataJson.put("cmd","reinstall");
            bodyJson.put("data",dataJson);
            String body = bodyJson.toString();

            java.io.OutputStream os = urlConnection.getOutputStream();
            java.io.BufferedWriter writer = new java.io.BufferedWriter(
                    new java.io.OutputStreamWriter(os, "UTF-8"));
            writer.write(body);
            writer.flush();
            writer.close();
            os.close();

            urlConnection.connect();

            // Read the input stream into a String
            java.io.InputStream inputStream = urlConnection.getInputStream();
            StringBuffer buffer = new StringBuffer();
            if (inputStream == null) {
                // Nothing to do.
                return null;
            }
            reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null) {
                // Since it's JSON, adding a newline isn't necessary (it won't affect parsing)
                // But it does make debugging a *lot* easier if you print out the completed
                // buffer for debugging.
                buffer.append(line + "\n");
            }

            if (buffer.length() == 0) {
                // Stream was empty.  No point in parsing.
                return null;
            }
            forecastJsonStr = buffer.toString();

            System.out.println(forecastJsonStr);
            return forecastJsonStr;

        } catch (Exception e) {
            // If the code didn't successfully get the weather data, there's no point in attemping
            // to parse it.
            return null;
        } finally{
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (final java.io.IOException e) {

                }
            }
        }
    }

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

    /*@Override
    public JSONObject post(String _endpoint, TreeMap<String, String> _headers, JSONObject _payload) throws HttpServiceException {
        int responseCode = -1;
        try {
            System.out.println("1");
            URL obj = new URL(_endpoint);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            //add reuqest header
            con.setRequestMethod("POST");
            *//*con.setRequestProperty("User-Agent", USER_AGENT);
            con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");*//*

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

            //String urlParameters = "sn=C02G8416DRJM&cn=&locale=&caller=&num=12345";
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
                //System.out.println(response.toString());

                return new JSONObject(response.toString());
            }

            throw new HttpServiceException("The http GET to the service had an error", responseCode);
        }
        catch(Exception e){
            throw new HttpServiceException(e.getMessage(), responseCode);
        }
    }*/

}
