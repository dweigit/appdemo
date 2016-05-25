package com.xsoft.appdemo.jetty;

/**
 * Created by dengwei06015 on 2016/3/2.
 */

import com.xsoft.appdemo.util.ValueUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.Properties;

public class COOASProperties {

    private static final String FILE_PATH_ENV = "COOAS_PROPERTIES";

    private static final String FILE_NAME = "cooas.properties";

    private static final Logger LOG = LoggerFactory.getLogger(COOASProperties.class);

    private static Properties prop;

    static {
        init();
    }

    private static void init() {
        String filePath = System.getenv(FILE_PATH_ENV);
        InputStream is = null;
        try {
            File file = null;
            if (StringUtils.isBlank(filePath)) {
                file = new File(COOASProperties.class.getClass().getResource("/" + FILE_NAME).getPath());
                System.out.println(file);
            }
            is = new FileInputStream(file);
            prop = new Properties();
            prop.load(is);
        } catch (FileNotFoundException e) {
            LOG.error("Can not find cooas properties, it may result working problem. path: " + filePath, e);
        } catch (Exception e) {
            LOG.error("init cooas properties failed", e);
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static String getProperty(String key) {
        if (null != prop) {
            return prop.getProperty(key);
        }
        return null;
    }

    public static String getProperty(String key, String defaultValue) {
        if (null != prop) {
            return prop.getProperty(key, defaultValue);
        }
        return defaultValue;
    }

    public static String getString(String key) {
        return getProperty(key);
    }

    public static String getString(String key, String defaultValue) {
        return ValueUtil.getString(getProperty(key), defaultValue);
    }

    public static int getInt(String key) {
        return ValueUtil.getInt(getProperty(key));
    }

    public static int getInt(String key, int defaultValue) {
        return ValueUtil.getInt(getProperty(key), defaultValue);
    }

    public static long getLong(String key) {
        return ValueUtil.getLong(getProperty(key));
    }

    public static long getLong(String key, long defaultValue) {
        return ValueUtil.getLong(getProperty(key), defaultValue);
    }
}
