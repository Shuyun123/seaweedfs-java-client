package net.anumbrella.seaweedfs.util;

import com.google.gson.Gson;
import net.anumbrella.seaweedfs.exception.SeaweedfsException;
import net.anumbrella.seaweedfs.exception.SeaweedfsFileNotFoundException;

import java.lang.reflect.Type;

public class Utils {
    /**
     * @param statusCode         Server response code.
     * @param url                Server url.
     * @param ignoreNotFound     Ignore http response not found status.
     * @param ignoreRedirect     Ignore http response redirect status.
     * @param ignoreRequestError Ignore http response request error status.
     * @param ignoreServerError  Ignore http response server error status.
     * @throws SeaweedfsException Http connection is fail or server response within some error message.
     */
    public static void convertResponseStatusToException(int statusCode, String url,
                                                  boolean ignoreNotFound,
                                                  boolean ignoreRedirect,
                                                  boolean ignoreRequestError,
                                                  boolean ignoreServerError) throws SeaweedfsException {

        switch (statusCode / 100) {
            case 1:
            case 2:
                return;
            case 3:
                if (ignoreRedirect) {
                    return;
                }
                throw new SeaweedfsException(
                        "fetch file from [" + url + "] is redirect, " +
                                "response stats code is [" + statusCode + "]");
            case 4:
                if (statusCode == 404 && ignoreNotFound) {
                    return;
                } else if (statusCode == 404) {
                    throw new SeaweedfsFileNotFoundException(
                            "fetch file from [" + url + "/" + "] is not found, " +
                                    "response stats code is [" + statusCode + "]");
                }
                if (ignoreRequestError) {
                    return;
                }
                throw new SeaweedfsException(
                        "fetch file from [" + url + "/" + "] is request error, " +
                                "response stats code is [" + statusCode + "]");
            case 5:
                if (ignoreServerError) {
                    return;
                }
                throw new SeaweedfsException(
                        "fetch file from [" + url + "/" + "] is request error, " +
                                "response stats code is [" + statusCode + "]");
            default:
                throw new SeaweedfsException(
                        "fetch file from [" + url + "/" + "] is error, " +
                                "response stats code is [" + statusCode + "]");
        }
    }

    /**
     * @param statusCode         Server response code.
     * @param url                Server url.
     * @param fid                File id.
     * @param ignoreNotFound     Ignore http response not found status.
     * @param ignoreRedirect     Ignore http response redirect status.
     * @param ignoreRequestError Ignore http response request error status.
     * @param ignoreServerError  Ignore http response server error status.
     * @throws SeaweedfsException Http connection is fail or server response within some error message.
     */
    public static void convertResponseStatusToException(int statusCode, String url, String fid,
                                                  boolean ignoreNotFound,
                                                  boolean ignoreRedirect,
                                                  boolean ignoreRequestError,
                                                  boolean ignoreServerError) throws SeaweedfsException {

        switch (statusCode / 100) {
            case 1:
            case 2:
                return;
            case 3:
                if (ignoreRedirect) {
                    return;
                }
                throw new SeaweedfsException(
                        "fetch file from [" + url + "/" + fid + "] is redirect, " +
                                "response stats code is [" + statusCode + "]");
            case 4:
                if (statusCode == 404 && ignoreNotFound) {
                    return;
                } else if (statusCode == 404) {
                    throw new SeaweedfsFileNotFoundException(
                            "fetch file from [" + url + "/" + fid + "] is not found, " +
                                    "response stats code is [" + statusCode + "]");
                }
                if (ignoreRequestError) {
                    return;
                }
                throw new SeaweedfsException(
                        "fetch file from [" + url + "/" + fid + "] is request error, " +
                                "response stats code is [" + statusCode + "]");
            case 5:
                if (ignoreServerError) {
                    return;
                }
                throw new SeaweedfsException(
                        "fetch file from [" + url + "/" + fid + "] is request error, " +
                                "response stats code is [" + statusCode + "]");
            default:
                throw new SeaweedfsException(
                        "fetch file from [" + url + "/" + fid + "] is error, " +
                                "response stats code is [" + statusCode + "]");
        }
    }

    public static <T> T convertJsonToEntity(String json, Class<T> entity) {
        Gson gson = new Gson();
        return gson.fromJson(json, entity);
    }
}
