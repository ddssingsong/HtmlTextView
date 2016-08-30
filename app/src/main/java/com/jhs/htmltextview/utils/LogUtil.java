package com.jhs.htmltextview.utils;

import android.util.Log;

/**
 * 日志工具
 * 
 * @todo 项目内所有debug日志输出都用此类来操作[方便后期日志开关的管理]
 * 
 * @author yuanc
 */
public class LogUtil {

	/** DEBUG日志信息开关 */
	private static boolean isDebug = true;
	/** ERRO日志信息开关 */
	private static boolean isErro = true;

	public static void d(String tag, String msg) {
		if (isDebug) {
			Log.d(tag, msg);
		}
	}

	public static void e(String tag, String msg, Throwable tr) {
		if (isErro) {
			Log.e(tag, msg, tr);
		}
	}
}
