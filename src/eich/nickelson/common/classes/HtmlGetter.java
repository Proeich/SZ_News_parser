package eich.nickelson.common.classes;

import com.sun.net.httpserver.HttpsConfigurator;
import eich.nickelson.common.parsing.Controller;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.*;
import java.util.stream.Stream;


public class HtmlGetter implements Controller {

    @Override
    public String get(String urlToRead) {
        try {
            StringBuilder result = new StringBuilder();

            /*
            String paramValue =  urlToRead.substring(urlToRead.indexOf(".de/") + 4);
            String yourURLStr = urlToRead.substring(urlToRead.indexOf("http://") + 7, urlToRead.indexOf(".de") + 3) + java.net.URLEncoder.encode(paramValue, "UTF-8");
            java.net.URL url = new java.net.URL(yourURLStr);
            */

            //URL url = new URL(urlToRead);

            java.net.URL url = new java.net.URL(urlToRead);


            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();

            conn.setInstanceFollowRedirects(true);  //you still need to handle redirect manully.
            HttpURLConnection.setFollowRedirects(true);

            conn.setRequestMethod("GET");

            boolean redirect = false;
            int status = conn.getResponseCode();
            if (status != HttpsURLConnection.HTTP_OK) {
                if (status == HttpsURLConnection.HTTP_MOVED_TEMP
                        || status == HttpsURLConnection.HTTP_MOVED_PERM
                        || status == HttpsURLConnection.HTTP_SEE_OTHER)
                    redirect = true;
            }
            BufferedReader rd = new BufferedReader(new InputStreamReader((InputStream) conn.getContent()));

            if (redirect) {
                // get redirect url from "location" header field
                String newUrl = conn.getHeaderField("Location");
                System.out.println(newUrl);

                // get the cookie if need, for login
                String cookies = conn.getHeaderField("Set-Cookie");
                // open the new connnection again
                url = new URL(newUrl);
                URLConnection conn1 = url.openConnection();
                conn1.setRequestProperty("Cookie", cookies);
                conn1.addRequestProperty("Accept-Language", "en-US,en;q=0.8");
                conn1.addRequestProperty("User-Agent", "Mozilla");
                conn1.addRequestProperty("Referer", "google.com");
                System.out.println("Redirect to URL : " + newUrl);
                rd = new BufferedReader(new InputStreamReader((InputStream) conn1.getContent()));
            }


            //conn.getInputStream()



            StringBuilder stringBuilder = new StringBuilder();





            String ss =  conn.getResponseMessage();
            String ssss = conn.getURL().toString();
            String sss = conn.getContentType();

            while(true){
                String k  = rd.readLine();
                if(k == null){
                    break;
                }
                stringBuilder.append(k).append('\n');
            }

            rd.close();
            //parse the String input
            return stringBuilder.toString();
        }catch (MalformedURLException | ProtocolException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
}
