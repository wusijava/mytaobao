package com.taobao.taobao.base.controller;


import com.alibaba.fastjson.JSONObject;
import com.taobao.taobao.utils.DataUtil;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * 初始
 *
 * @author duchong
 * @date 2019-06-11 9:41
 **/
public abstract class BaseController {

    /**
     * 字符集类型
     */
    protected static final String UTF_8 = "utf-8";

    /**
     * 获取request
     *
     * @return
     */
    protected HttpServletRequest getRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }

    /**
     * 获取response
     *
     * @return
     */
    protected HttpServletResponse getResponse() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
    }

    /**
     * 获取session
     *
     * @return
     */
    protected HttpSession getSession() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getSession();
    }

    /**
     * 获取请求里的参数
     *
     * @param request
     * @return
     */
    public static Map<String, String> getAllRequestParam(HttpServletRequest request) {
        Map<String, String> map = new HashMap<>();
        Enumeration request_BodyNames = request.getParameterNames();
        while (request_BodyNames.hasMoreElements()) {
            String name = (String) request_BodyNames.nextElement();
            String value = request.getParameter(name);
            map.put(name, value);
        }
        return map;
    }

    /**
     * 获取请求里的参数
     *
     * @param request
     * @return
     */
    public static Map<String, String> getParams(HttpServletRequest request) {
        Map<String, String> params = new HashMap<String, String>();
        Map requestParams = request.getParameterMap();
        for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext(); ) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            params.put(name, valueStr);
        }
        return params;
    }


    /**
     * 转换成JSON字符串
     *
     * @param object
     * @return
     */
    public static String toJSONString(Object object) {
        return JSONObject.toJSONString(object);
    }

    /**
     * 空判断
     *
     * @param object
     * @return
     */
    public static Boolean isEmpty(Object object) {
        return DataUtil.isEmpty(object);
    }

    /**
     * 非空判断
     *
     * @param object
     * @return
     */
    public static Boolean isNotEmpty(Object object) {
        return DataUtil.isNotEmpty(object);
    }

    public static JSONObject getRequestJsonObject(HttpServletRequest request) {
        String json = getRequestJsonString(request);
        return JSONObject.parseObject(json);
    }

    /***
     * 获取 request 中 json 字符串的内容
     *
     * @param request
     * @return : <code>byte[]</code>
     * @throws IOException
     */
    public static String getRequestJsonString(HttpServletRequest request) {
        try {
            String submitMehtod = request.getMethod();
            // GET
            if (submitMehtod.equals("GET")) {
                return new String(request.getQueryString().getBytes("iso-8859-1"), "utf-8").replaceAll("%22", "\"");
                // POST
            } else {
                return getRequestPostStr(request);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static byte[] getRequestPostBytes(HttpServletRequest request)
            throws IOException {
        int contentLength = request.getContentLength();
        if (contentLength < 0) {
            return null;
        }
        byte buffer[] = new byte[contentLength];
        for (int i = 0; i < contentLength; ) {
            int readlen = request.getInputStream().read(buffer, i,
                    contentLength - i);
            if (readlen == -1) {
                break;
            }
            i += readlen;
        }
        return buffer;
    }

    /**
     * 描述:获取 post 请求内容
     * <pre>
     * 举例
     * </pre>
     *
     * @param request
     * @return
     * @throws IOException
     */
    public static String getRequestPostStr(HttpServletRequest request)
            throws IOException {
        byte buffer[] = getRequestPostBytes(request);
        String charEncoding = request.getCharacterEncoding();
        if (charEncoding == null) {
            charEncoding = "UTF-8";
        }
        return new String(buffer, charEncoding);
    }

}
