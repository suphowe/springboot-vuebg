package com.soft.sys;

import com.soft.util.SysUtil;

/**
 * 静态常量
 * @author suphowe
 */
public class PrivateConstants {

    public static final String[]  WHITE_URLS = SysUtil.getProperties("config/constants", "WHITE_URLS").split(",");

}
