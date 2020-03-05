package net.anumbrella.seaweedfs.util;

import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.CloseableHttpClient;

import java.io.IOException;

public class ConnectionUtil {

    /**
     * Check uri link is alive, the basis for judging response status code.
     *
     * @param client httpClient
     * @param url    check url
     * @return When the response status code is 200, the result is true.
     */
    public static boolean checkUriAlive(CloseableHttpClient client, String url) {
        boolean result = false;
        HttpGet request = new HttpGet(url);
        try (CloseableHttpResponse response = client.execute(request, HttpClientContext.create())) {
            result = response.getStatusLine().getStatusCode() == HttpStatus.SC_OK;
        } catch (IOException e) {
            return false;
        } finally {
            request.releaseConnection();
        }
        return result;
    }

    /**
     * Convert url with scheme match seaweedfs server
     *
     * @param serverUrl url without scheme
     * @return result
     */
    public static String convertUrlWithScheme(String serverUrl) {
        boolean startHttp = serverUrl.startsWith("http");
        return startHttp ? serverUrl : "http://" + serverUrl;
    }
}
