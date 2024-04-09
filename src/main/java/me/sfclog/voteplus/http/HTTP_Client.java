package me.sfclog.voteplus.http;


import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import java.io.IOException;

public class HTTP_Client {


    public static String get_query(String url)  {
        try {
            CloseableHttpClient httpClient = HttpClients.createDefault();
            try {
                HttpGet request = new HttpGet(url);
                CloseableHttpResponse response = httpClient.execute(request);
                try {
                    HttpEntity entity = response.getEntity();
                    if (entity != null) {
                        String result = EntityUtils.toString(entity);
                        if (result != null) {
                            return result;
                        }
                    }
                } finally {
                    response.close();
                }
            } finally {
                httpClient.close();
            }
        } catch (IOException var4) {
            return null;
        }
        return null;
    }

}
