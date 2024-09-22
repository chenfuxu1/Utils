package com.webutils;

import com.logutils.Logit;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.net.URLDecoder;

/**
 * Project: Utils
 * Create By: Chen.F.X
 * DateTime: 2024-09-22 10:54
 * <p>
 * Desc:
 */
public class WebUtils {
    private static final String TAG  = "WebUtils";

    /**
     * 将字符串转化为 int 值，如果失败，转换为默认值返回
     * @param strNum
     * @param defaultVal
     * @return
     */
    public static int parseInt(String strNum, int defaultVal) {
        if (strNum == null || "".equals(strNum)) {
            return defaultVal;
        }
        try {
            return Integer.parseInt(strNum);
        } catch (NumberFormatException e) {
            System.out.println(TAG + " NumberFormatException: " + e);
            return defaultVal;
        }
    }

    /**
     * 先从文件中读取文件输入流，然后写入输出流中
     *
     * @param fileName 文件名
     * @param outputStream 输出流
     * @return
     */
    public static boolean writeStaticResource(String fileName, OutputStream outputStream) {
        if (fileName == null || "".equals(fileName)) {
            return false;
        }
        BufferedReader bufferedReader = null;
        try {
            /**
             * 1.先获取 target/classes 的路径
             * /D:/F/%e5%ae%89%e5%8d%93%e8%b5%84%e6%96%99/JAVA/JAVA%e5%90%8e%e7%ab%af/%e9%9f%a9%e9%a1%ba%e5%b9%b3_JAVA_%e8%b7%af%e7%ba%bf/2.JAVA_WEB/6.%e5%ae%9e%e7%8e%b0Tomcat%e5%ba%95%e5%b1%82%e6%9c%ba%e5%88%b6_%e8%87%aa%e5%b7%b1%e8%ae%be%e8%ae%a1Servlet/%e4%bb%a3%e7%a0%81/CfxTomcat/target/classes/
             */
            String baseUrl = WebUtils.class.getResource("/").getPath();
            /**
             * 2.将 / 都替换成 \
             * \D:\F\%e5%ae%89%e5%8d%93%e8%b5%84%e6%96%99\JAVA\JAVA%e5%90%8e%e7%ab%af\%e9%9f%a9%e9%a1%ba%e5%b9%b3_JAVA_%e8%b7%af%e7%ba%bf\2.JAVA_WEB\6.%e5%ae%9e%e7%8e%b0Tomcat%e5%ba%95%e5%b1%82%e6%9c%ba%e5%88%b6_%e8%87%aa%e5%b7%b1%e8%ae%be%e8%ae%a1Servlet\%e4%bb%a3%e7%a0%81\CfxTomcat\target\classes\
             */
            baseUrl = baseUrl.replace("/", "\\");
            /**
             * 3.第一个位置的 \ 不需要
             * D:\F\%e5%ae%89%e5%8d%93%e8%b5%84%e6%96%99\JAVA\JAVA%e5%90%8e%e7%ab%af\%e9%9f%a9%e9%a1%ba%e5%b9%b3_JAVA_%e8%b7%af%e7%ba%bf\2.JAVA_WEB\6.%e5%ae%9e%e7%8e%b0Tomcat%e5%ba%95%e5%b1%82%e6%9c%ba%e5%88%b6_%e8%87%aa%e5%b7%b1%e8%ae%be%e8%ae%a1Servlet\%e4%bb%a3%e7%a0%81\CfxTomcat\target\classes\
             */
            baseUrl = baseUrl.substring(1);
            /**
             * 4.可能有中文，需要解码一下
             * D:\F\安卓资料\JAVA\JAVA后端\韩顺平_JAVA_路线\2.JAVA_WEB\7.WEB路径专题_会话技术\代码\CookieSession\out\artifacts\CookieSession_war_exploded\WEB-INF\classes\
             */
            String decodeUrl = URLDecoder.decode(baseUrl, "utf-8");

            /**
             * 5.找到 WEB-INF 的位置
             */
            int indexOf = decodeUrl.indexOf("WEB-INF");

            /**
             * 6.截取 WEB-INF 所在路径
             * D:\F\安卓资料\JAVA\JAVA后端\韩顺平_JAVA_路线\2.JAVA_WEB\7.WEB路径专题_会话技术\代码\CookieSession\out\artifacts\CookieSession_war_exploded\
             */
            decodeUrl = decodeUrl.substring(0, indexOf);

            /**
             * 7.找到文件的绝对路径
             * D:\F\安卓资料\JAVA\JAVA后端\韩顺平_JAVA_路线\2.JAVA_WEB\7.WEB路径专题_会话技术\代码\CookieSession\out\artifacts\CookieSession_war_exploded\fileName
             */
            String path = decodeUrl + fileName;
            Logit.d(TAG, "path: " + path);
            File file = new File(path);
            if (!file.exists()) {
                return false;
            }
            bufferedReader = new BufferedReader(new FileReader(file));
            String line = null;
            while ((line = bufferedReader.readLine()) != null && !"".equals(line)) {
                outputStream.write(line.getBytes());
            }
            outputStream.flush();
            Logit.d(TAG, "输出文件: " + fileName + "完毕");
        } catch (Exception e) {
            Logit.d(TAG, " Exception: " + e.getMessage());
            return false;
        } finally {
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
            } catch (IOException e) {
                Logit.d(TAG, " Exception: " + e.getMessage());
            }
        }
        return true;
    }

    /**
     * 先从文件中读取文件输入流，然后写入输出流中
     *
     * @param fileName 文件名
     * @param writer 输出流
     * @return
     */
    public static boolean writeStaticResource(String fileName, Writer writer) {
        if (fileName == null || "".equals(fileName)) {
            return false;
        }
        BufferedReader bufferedReader = null;
        try {
            /**
             * 1.先获取 target/classes 的路径
             * /D:/F/%e5%ae%89%e5%8d%93%e8%b5%84%e6%96%99/JAVA/JAVA%e5%90%8e%e7%ab%af/%e9%9f%a9%e9%a1%ba%e5%b9%b3_JAVA_%e8%b7%af%e7%ba%bf/2.JAVA_WEB/6.%e5%ae%9e%e7%8e%b0Tomcat%e5%ba%95%e5%b1%82%e6%9c%ba%e5%88%b6_%e8%87%aa%e5%b7%b1%e8%ae%be%e8%ae%a1Servlet/%e4%bb%a3%e7%a0%81/CfxTomcat/target/classes/
             */
            String baseUrl = WebUtils.class.getResource("/").getPath();
            /**
             * 2.将 / 都替换成 \
             * \D:\F\%e5%ae%89%e5%8d%93%e8%b5%84%e6%96%99\JAVA\JAVA%e5%90%8e%e7%ab%af\%e9%9f%a9%e9%a1%ba%e5%b9%b3_JAVA_%e8%b7%af%e7%ba%bf\2.JAVA_WEB\6.%e5%ae%9e%e7%8e%b0Tomcat%e5%ba%95%e5%b1%82%e6%9c%ba%e5%88%b6_%e8%87%aa%e5%b7%b1%e8%ae%be%e8%ae%a1Servlet\%e4%bb%a3%e7%a0%81\CfxTomcat\target\classes\
             */
            baseUrl = baseUrl.replace("/", "\\");
            /**
             * 3.第一个位置的 \ 不需要
             * D:\F\%e5%ae%89%e5%8d%93%e8%b5%84%e6%96%99\JAVA\JAVA%e5%90%8e%e7%ab%af\%e9%9f%a9%e9%a1%ba%e5%b9%b3_JAVA_%e8%b7%af%e7%ba%bf\2.JAVA_WEB\6.%e5%ae%9e%e7%8e%b0Tomcat%e5%ba%95%e5%b1%82%e6%9c%ba%e5%88%b6_%e8%87%aa%e5%b7%b1%e8%ae%be%e8%ae%a1Servlet\%e4%bb%a3%e7%a0%81\CfxTomcat\target\classes\
             */
            baseUrl = baseUrl.substring(1);
            /**
             * 4.可能有中文，需要解码一下
             * D:\F\安卓资料\JAVA\JAVA后端\韩顺平_JAVA_路线\2.JAVA_WEB\7.WEB路径专题_会话技术\代码\CookieSession\out\artifacts\CookieSession_war_exploded\WEB-INF\classes\
             */
            String decodeUrl = URLDecoder.decode(baseUrl, "utf-8");

            /**
             * 5.找到 WEB-INF 的位置
             */
            int indexOf = decodeUrl.indexOf("WEB-INF");

            /**
             * 6.截取 WEB-INF 所在路径
             * D:\F\安卓资料\JAVA\JAVA后端\韩顺平_JAVA_路线\2.JAVA_WEB\7.WEB路径专题_会话技术\代码\CookieSession\out\artifacts\CookieSession_war_exploded\
             */
            decodeUrl = decodeUrl.substring(0, indexOf);

            /**
             * 7.找到文件的绝对路径
             * D:\F\安卓资料\JAVA\JAVA后端\韩顺平_JAVA_路线\2.JAVA_WEB\7.WEB路径专题_会话技术\代码\CookieSession\out\artifacts\CookieSession_war_exploded\fileName
             */
            String path = decodeUrl + fileName;
            Logit.d(TAG, "path: " + path);
            File file = new File(path);
            if (!file.exists()) {
                return false;
            }
            bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "utf-8"));
            String line = null;
            while ((line = bufferedReader.readLine()) != null && !"".equals(line)) {
                writer.write(line);
            }
            writer.flush();
            Logit.d(TAG, "输出文件: " + fileName + "完毕");
        } catch (Exception e) {
            Logit.d(TAG, " Exception: " + e.getMessage());
            return false;
        } finally {
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
            } catch (IOException e) {
                Logit.d(TAG, " Exception: " + e.getMessage());
            }
        }
        return true;
    }

    /**
     * 该方法用于获取 src 路径下文件的绝对路径(兼容中文)
     * @param fileName 文件名
     * @return
     */
    public static String getAbsolutePath(String fileName) {
        String path = "";
        try {
            /**
             * 1.先获取根路径
             * /D:/F/%e5%ae%89%e5%8d%93%e8%b5%84%e6%96%99/JAVA/JAVA%e5%90%8e%e7%ab%af/%e9%9f%a9%e9%a1%ba%e5%b9%b3_JAVA_%e8%b7%af%e7%ba%bf/2.JAVA_WEB/11.JSON_Ajax_ThreadLocal_%e4%b8%8a%e4%bc%a0%e4%b8%8b%e8%bd%bd/%e4%bb%a3%e7%a0%81/Ajax/out/production/Ajax/
             */
            String baseUrl = WebUtils.class.getResource("/").getPath();
            /**
             * 2.将 / 都替换成 \
             * \D:\F\%e5%ae%89%e5%8d%93%e8%b5%84%e6%96%99\JAVA\JAVA%e5%90%8e%e7%ab%af\%e9%9f%a9%e9%a1%ba%e5%b9%b3_JAVA_%e8%b7%af%e7%ba%bf\2.JAVA_WEB\11.JSON_Ajax_ThreadLocal_%e4%b8%8a%e4%bc%a0%e4%b8%8b%e8%bd%bd\%e4%bb%a3%e7%a0%81\Ajax\out\production\Ajax\
             */
            baseUrl = baseUrl.replace("/", "\\");
            // System.out.println(baseUrl);
            /**
             * 3.第一个位置的 \ 不需要
             * D:\F\%e5%ae%89%e5%8d%93%e8%b5%84%e6%96%99\JAVA\JAVA%e5%90%8e%e7%ab%af\%e9%9f%a9%e9%a1%ba%e5%b9%b3_JAVA_%e8%b7%af%e7%ba%bf\2.JAVA_WEB\11.JSON_Ajax_ThreadLocal_%e4%b8%8a%e4%bc%a0%e4%b8%8b%e8%bd%bd\%e4%bb%a3%e7%a0%81\Ajax\out\production\Ajax\
             */
            baseUrl = baseUrl.substring(1);
            /**
             * 4.可能有中文，需要解码一下
             * D:\F\安卓资料\JAVA\JAVA后端\韩顺平_JAVA_路线\2.JAVA_WEB\11.JSON_Ajax_ThreadLocal_上传下载\代码\Ajax\out\production\Ajax\
             */
            String decodeUrl = URLDecoder.decode(baseUrl, "utf-8");
            // System.out.println(decodeUrl);

            /**
             * 6.找到文件的绝对路径
             * D:\F\安卓资料\JAVA\JAVA后端\韩顺平_JAVA_路线\2.JAVA_WEB\11.JSON_Ajax_ThreadLocal_上传下载\代码\Ajax\out\production\Ajax\druid.properties
             */
            path = decodeUrl + fileName;

        } catch (UnsupportedEncodingException e) {
            System.out.println(e);
        }
        return path;
    }

    /**
     * 判断请求是否为 ajax 请求
     * @param httpServletRequest
     * @return
     */
    public static boolean isAjaxRequest(HttpServletRequest httpServletRequest) {
        if (httpServletRequest == null) {
            return false;
        }
        String requestedWith = httpServletRequest.getHeader("X-Requested-With");
        if (requestedWith == null || "".equals(requestedWith)) {
            return false;
        }
        return "XMLHttpRequest".equals(requestedWith);
    }
}
