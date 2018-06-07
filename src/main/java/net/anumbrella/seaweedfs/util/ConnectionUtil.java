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
        CloseableHttpResponse response = null;
        HttpGet request = new HttpGet(url);
        try {
            response = client.execute(request, HttpClientContext.create());
            result = response.getStatusLine().getStatusCode() == HttpStatus.SC_OK;
        } catch (IOException e) {
            return false;
        } finally {
            if (response != null) {
                try {
                    response.close();
                } catch (IOException ignored) {
                }
            }
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


    /**
     * 检查是否为ipv4格式的host,当master为集群模式，使用nginx做负载均衡时，必须使用域名形式
     * server_name见 nginx 9333端口对应的设置
     */
    public static boolean checkHostIPV4Fromat(String host){
        if(host==null || host.length()==0){
            return false;//字符串为空或者空串
        }
        String[] parts=host.split("\\.");//因为java doc里已经说明, split的参数是reg, 即正则表达式, 如果用"|"分割, 则需使用"\\|"
        if(parts.length!=4){
            return false;//分割开的数组根本就不是4个数字
        }
        for(int i=0;i<parts.length;i++){
            try{
                int n=Integer.parseInt(parts[i]);
                if(n<0 || n>255){
                    return false;//数字不在正确范围内
                }
            }catch (NumberFormatException e) {
                return false;//转换数字不正确
            }
        }
        return true;
    }
}
