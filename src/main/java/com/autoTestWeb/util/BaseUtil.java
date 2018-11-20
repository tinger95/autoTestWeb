package com.autoTestWeb.util;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BaseUtil {
    private static final Logger Logger = LoggerFactory.getLogger(BaseUtil.class);

    public static  String toJson(int sEcho, int count, JSONArray json) {
        String jsonStr = "";
        jsonStr += "{\"sEcho\":" + sEcho + ",\"iTotalRecords\":" + count + ",\"iTotalDisplayRecords\":" + count
                + ",\"aaData\":" + json.toString() + "}";
        Logger.info(jsonStr);
        return jsonStr;
    }

    public static void writeJson(int size, JSONArray json, HttpServletResponse response) {
        try {
            response.setContentType("text/json");
            response.setCharacterEncoding("UTF-8");
            PrintWriter writer;

            writer = response.getWriter();

            writer.print(toJson(0, size, json));
            writer.flush();
            writer.close();
        } catch (IOException e) {
            Logger.info(e.toString());
            throw new RuntimeException(e.toString());
        }
    }

    public static void writeJson(JSONArray json, HttpServletResponse response) {
        try {
            Logger.info(json.toString());
            response.setContentType("text/json");
            response.setCharacterEncoding("UTF-8");
            PrintWriter writer;

            writer = response.getWriter();

            writer.print(json.toString());
            writer.flush();
            writer.close();
        } catch (IOException e) {
            Logger.info(e.toString());
            throw new RuntimeException(e.toString());
        }
    }

    public static void writeJson(JSONObject jsonObject, HttpServletResponse response) {
        response.setContentType("text/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter writer;
        try {
            writer = response.getWriter();
            writer.print(jsonObject.toString());
            writer.flush();
            writer.close();
        } catch (IOException e) {
            Logger.info(e.toString());
            throw new RuntimeException(e.toString());
        }

    }

    public static void WriteInteger(int i, HttpServletResponse response) {
        PrintWriter writer;
        try {
            writer = response.getWriter();
            writer.print(i);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            Logger.info(e.toString());
            throw new RuntimeException(e.toString());
        }

    }

}
