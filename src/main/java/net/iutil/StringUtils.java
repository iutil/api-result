package net.iutil;

/**
 * 该类非工具类，只为ApiResult调用，
 * 如想使用，请使用工具类提供的字符串工具类
 * @author Erwin Feng
 * @since 2019-05-26 01:22
 */
class StringUtils {

    /**
     * 判断字符串是否不是空字符串
     * 请参考 isEmpty()
     * @param str 待检测的字符串
     * @return true：非空字符串；false：空字符串
     */
    static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }

    /**
     * 判断字符串是否为空字符串
     * 如果是空，则返回true，不是空，则返回false
     * 如字符串是null，则返回true
     * 如字符串是""，则返回true
     * 如字符串是" "，则返回false
     * 如字符串是"abc"，则返回false
     * @param str 待检测的字符串
     * @return true：空字符串；false：非空字符串
     */
    private static boolean isEmpty(String str) {
        if (str == null)
            return true;
        return str.isEmpty();
    }

}
