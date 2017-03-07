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
    /*
    public static void main(String[] args) throws HttpServiceException, Exception {

        HttpService http = new HttpService();

        //System.out.println("Testing 1 - Send Http GET request");
        //http.sendGet();

        TreeMap<String, String> _headers = new TreeMap<String, String>();

        JSONObject _payload = new JSONObject();
        _payload.put("id", "33");
        _payload.put("search", "j");

        JSONObject _json = http.post("http://app.cpeguide.com/findfriends", _headers, _payload);

        System.out.println(_json.toString());

    }*/

    @Override
    public JSONObject get(String _endpoint, TreeMap<String, String> _headers) throws HttpServiceException {
        int responseCode = -1;

        try {
            URL obj = new URL(_endpoint);
            HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();

            // optional default is GET
            con.setRequestMethod("GET");

            //add request header
            /*con.setRequestProperty("User-Agent", USER_AGENT);*/
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

    @Override
    public JSONObject post(String _endpoint, TreeMap<String, String> _headers, JSONObject _payload) throws HttpServiceException {
        int responseCode = -1;
        try {
            System.out.println("1");
            URL obj = new URL(_endpoint);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            //add reuqest header
            con.setRequestMethod("POST");
            /*con.setRequestProperty("User-Agent", USER_AGENT);
            con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");*/

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
    }

}
