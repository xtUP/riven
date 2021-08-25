package com.example.demo.entity;

/**
 * 适配响应类
 * @author symon
 */
public class AdaptResponseModel {

    /**
     * HTTP状态码
     */
    private int httpCode;

    /**
     * 错误信息，若返回中没有错误信息，则这个字段为空
     */
    private String errorDesc = "";

    /**
     * 有些协议会返回错误码
     */
    private String errorCode = "";

    /**
     * 返回的数据格式
     */
    private Object data;

    /**
     * 
     * @param code HTTP状态码
     * @param errorDesc 错误信息
     * @param errorCode api错误码
     * @param data 数据返回
     * @return
     */
    public static AdaptResponseModel createErrorModel(int code, String errorDesc, String errorCode, Object data) {
        return new AdaptResponseModel(code, errorDesc, errorCode, data);
    }

    /**
     * 跟api相关的错误响应类
     * @param code HTTP状态码
     * @param errorDesc 错误信息
     * @param errorCode api错误码
     * @return
     */
    public static AdaptResponseModel createErrorModel(int code, String errorDesc, String errorCode) {
        return new AdaptResponseModel(code, errorDesc, errorCode, null);
    }

    /**
     * 
     * @param code HTTP状态码
     * @param errorDesc 错误信息
     * @return
     */
    public static AdaptResponseModel createErrorModel(int code, String errorDesc) {
        return new AdaptResponseModel(code, errorDesc, null);
    }

    /**
     * 返回错误
     * @param errorDesc
     * @return
     */
    public static AdaptResponseModel create400Model(String errorDesc) {
        return createErrorModel(400, errorDesc);
    }

    public static AdaptResponseModel create403Model(String errorDesc) {
        return createErrorModel(403, errorDesc);
    }

    public static AdaptResponseModel create415Model(String errorDesc) {
        return createErrorModel(415, errorDesc);
    }

    public static AdaptResponseModel create500Model(String errorDesc) {
        return createErrorModel(500, errorDesc);
    }

    public static AdaptResponseModel default500Model() {
        return create500Model("服务器错误");
    }

    public static AdaptResponseModel createNormalModel(Object data) {
        return new AdaptResponseModel(200, null, data);
    }

    public AdaptResponseModel(int httpCode, String errorDesc, Object data) {
        super();
        this.httpCode = httpCode;
        this.errorDesc = errorDesc;
        this.data = data;
    }

    public AdaptResponseModel(int httpCode, String errorDesc, String errorCode, Object data) {
        super();
        this.httpCode = httpCode;
        this.errorDesc = errorDesc;
        this.errorCode = errorCode;
        this.data = data;
    }

    public AdaptResponseModel() {
        // TODO Auto-generated constructor stub
    }

    @Override
    public String toString() {
        return "AdaptResponseModel{" + "httpCode=" + httpCode + ", errorDesc='" + errorDesc + '\'' + ", errorCode='"
                + errorCode + '\'' + ", data=" + data + '}';
    }

    public int getHttpCode() {
        return httpCode;
    }

    public void setHttpCode(int httpCode) {
        this.httpCode = httpCode;
    }

    public String getErrorDesc() {
        return errorDesc;
    }

    public void setErrorDesc(String errorDesc) {
        this.errorDesc = errorDesc;
    }

    public Object getData() {
        return data;
    }

    public AdaptResponseModel setData(Object data) {
        this.data = data;
        return this;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }


}
