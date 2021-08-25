package com.example.demo.util.log;

import java.io.Serializable;

/**
 * Created by 程祥 on 16/8/25.
 * Function：
 */
public class ServitizationLogObj implements Serializable {
    private static final long serialVersionUID = 1L;

    private String appname;
    private String params;
    private String url;
    private String httpMethod;
    private long execTime;

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("");
        sb.append("traceId='").append(traceId).append('\'');
        sb.append("\t");
        sb.append("responseCode='").append(responseCode).append('\'');
        sb.append("\t");
        sb.append("elapsedTime='").append(elapsedTime).append("ms").append('\'');
        sb.append("\t");
        sb.append("requestBody='").append(requestBody).append('\'');
        sb.append("\t");
        sb.append("responseBody='").append(responseBody).append('\'');
        sb.append("\t");
        sb.append("serverName='").append(serverName).append('\'');
        sb.append("\t");
        sb.append("serverIp='").append(serverIp).append('\'');
        sb.append("\t");
        sb.append("serviceName='").append(serviceName).append('\'');
        return sb.toString();
    }

    private int status;
    private String content;
    private String logTime;
    private long logTimeStamp;
    //  add by
    private String requestHeader;
    private String requestBody;
    private String serverName;
    private String serverIp;
    private String appName;
    private long startTime;
    private String serviceName;
    private String imei;
    private String method;
    private String responseCode;

    private String responseBody;

    public String getResponseBody() {
        return responseBody;
    }

    public void setResponseBody(String responseBody) {
        this.responseBody = responseBody;
    }

    public String getElapsedTime() {
        return elapsedTime;
    }

    public void setElapsedTime(String elapsedTime) {
        this.elapsedTime = elapsedTime;
    }

    private String traceId;

    private String elapsedTime;
    public String getTraceId() {
        return traceId;
    }

    public void setTraceId(String traceId) {
        this.traceId = traceId;
    }

    public String getRequestHeader() {
        return requestHeader;
    }

    public void setRequestHeader(String requestHeader) {
        this.requestHeader = requestHeader;
    }

    public String getRequestBody() {
        return requestBody;
    }

    public void setRequestBody(String requestBody) {
        this.requestBody = requestBody;
    }

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }



    public String getLogTime() {
        return logTime;
    }

    public void setLogTime(String logTime) {
        this.logTime = logTime;
    }

    public long getLogTimeStamp() {
        return logTimeStamp;
    }

    public void setLogTimeStamp(long logTimeStamp) {
        this.logTimeStamp = logTimeStamp;
    }

    public String getServerIp() {
        return serverIp;
    }

    public void setServerIp(String serverIp) {
        this.serverIp = serverIp;
    }

    public String getAppname() {
        return appname;
    }

    public void setAppname(String appname) {
        this.appname = appname;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getHttpMethod() {
        return httpMethod;
    }

    public void setHttpMethod(String httpMethod) {
        this.httpMethod = httpMethod;
    }

    public long getExecTime() {
        return execTime;
    }

    public void setExecTime(long execTime) {
        this.execTime = execTime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
