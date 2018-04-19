/**
 * 
 */
package com.hy.sys.utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串工具类
 * 
 */
public class StringTools {

	public static final String EMPTY = "";

	public static final int INDEX_NOT_FOUND = -1;

	/**
	 * <p>
	 * 判断是否为空 a CharSequence is empty ("") or null.
	 * </p>
	 * 
	 * <pre>
	 * StringTools.isEmpty(null)      = true
	 * StringTools.isEmpty("")        = true
	 * StringTools.isEmpty(" ")       = false
	 * StringTools.isEmpty("bob")     = false
	 * StringTools.isEmpty("  bob  ") = false
	 * </pre>
	 * 
	 * <p>
	 * NOTE: This method changed in Lang version 2.0. It no longer trims the
	 * CharSequence. That functionality is available in isBlank().
	 * </p>
	 * 
	 * @param cs
	 *            the CharSequence to check, may be null
	 * @return {@code true} if the CharSequence is empty or null
	 */
	public static boolean isEmpty(CharSequence cs) {
		return cs == null || cs.length() == 0;
	}

	public static boolean isNotEmpty(CharSequence cs) {
		return !StringTools.isEmpty(cs);
	}

	/**
	 * <p>
	 * Checks if a CharSequence is whitespace, empty ("") or null.
	 * </p>
	 * 
	 * <pre>
	 * StringTools.isBlank(null)      = true
	 * StringTools.isBlank("")        = true
	 * StringTools.isBlank(" ")       = true
	 * StringTools.isBlank("bob")     = false
	 * StringTools.isBlank("  bob  ") = false
	 * </pre>
	 * 
	 * @param cs
	 *            the CharSequence to check, may be null
	 * @return {@code true} if the CharSequence is null, empty or whitespace
	 */
	public static boolean isBlank(CharSequence cs) {
		int strLen;
		if (cs == null || (strLen = cs.length()) == 0) {
			return true;
		}
		for (int i = 0; i < strLen; i++) {
			if (Character.isWhitespace(cs.charAt(i)) == false) {
				return false;
			}
		}
		return true;
	}

	/**
	 * <p>
	 * Checks if a CharSequence is not empty (""), not null and not whitespace
	 * only.
	 * </p>
	 * 
	 * <pre>
	 * StringTools.isNotBlank(null)      = false
	 * StringTools.isNotBlank("")        = false
	 * StringTools.isNotBlank(" ")       = false
	 * StringTools.isNotBlank("bob")     = true
	 * StringTools.isNotBlank("  bob  ") = true
	 * </pre>
	 * 
	 * @param cs
	 *            the CharSequence to check, may be null
	 * @return {@code true} if the CharSequence is not empty and not null and
	 *         not whitespace
	 */
	public static boolean isNotBlank(CharSequence cs) {
		return !StringTools.isBlank(cs);
	}

	/**
	 * @see 去掉两端空格 <pre>
	 * StringTools.trim(null)          = null
	 * StringTools.trim("")            = ""
	 * StringTools.trim("     ")       = ""
	 * StringTools.trim("abc")         = "abc"
	 * StringTools.trim("    abc    ") = "abc"
	 * </pre>
	 * @param str
	 * @return 如果 str 为 null 返回 空串
	 */
	public static String trim(String str) {
		return str == null ? null : str.trim();
	}

	/**
	 * @see 解析字符串 如果字符串为空 返回 空串
	 * 
	 *      <pre>
	 * StringTools.parseNull(null)          = ""
	 * StringTools.parseNull("")            = ""
	 * StringTools.parseNull("     ")       = ""
	 * StringTools.parseNull("abc")         = "abc"
	 * StringTools.parseNull("    abc    ") = "abc"
	 * </pre>
	 * @param str
	 * @return String 空串
	 */
	public static String parseString(String str) {
		return str == null ? EMPTY : str.trim();
	}

	/**
	 * @see 解析字符串 <pre>
	 * StringTools.parseNull(null)          = null
	 * StringTools.parseNull("")            = null
	 * StringTools.parseNull("     ")       = null
	 * StringTools.parseNull("abc")         = "abc"
	 * StringTools.parseNull("    abc    ") = "abc"
	 * </pre>
	 * @param str
	 * @return 如果为空串 返回null, 否则返回 去掉两端空格str,
	 */
	public static String parseNull(String str) {
		String ts = trim(str);
		return isEmpty(ts) ? null : ts;
	}

	/**
	 * <p>
	 * 去掉两端空格
	 * </p>
	 * 
	 * <pre>
	 * StringTools.strip(null)     = null
	 * StringTools.strip("")       = ""
	 * StringTools.strip("   ")    = ""
	 * StringTools.strip("abc")    = "abc"
	 * StringTools.strip("  abc")  = "abc"
	 * StringTools.strip("abc  ")  = "abc"
	 * StringTools.strip(" abc ")  = "abc"
	 * StringTools.strip(" ab c ") = "ab c"
	 * </pre>
	 * 
	 * @param str
	 *            the String to remove whitespace from, may be null
	 * @return "" or null or str
	 */
	public static String strip(String str) {
		return strip(str, null);
	}

	/**
	 * <p>
	 * 去掉两端空格
	 * </p>
	 * 
	 * <p>
	 * 如果参数是null 直接返回null, 如果参数是空串或空格 返回null
	 * </p>
	 * 
	 * <pre>
	 * StringTools.stripToNull(null)     = null
	 * StringTools.stripToNull("")       = null
	 * StringTools.stripToNull("   ")    = null
	 * StringTools.stripToNull("abc")    = "abc"
	 * StringTools.stripToNull("  abc")  = "abc"
	 * StringTools.stripToNull("abc  ")  = "abc"
	 * StringTools.stripToNull(" abc ")  = "abc"
	 * StringTools.stripToNull(" ab c ") = "ab c"
	 * </pre>
	 * 
	 * @param str
	 *            the String to be stripped, may be null
	 * @return null or str
	 */
	public static String stripToNull(String str) {
		if (str == null) {
			return null;
		}
		str = strip(str, null);
		return str.length() == 0 ? null : str;
	}

	/**
	 * <p>
	 * 去掉两端空格
	 * </p>
	 * 
	 * <p>
	 * 如果参数是null或空串返回 "", 否则返回str
	 * </p>
	 * 
	 * <pre>
	 * StringTools.stripToEmpty(null)     = ""
	 * StringTools.stripToEmpty("")       = ""
	 * StringTools.stripToEmpty("   ")    = ""
	 * StringTools.stripToEmpty("abc")    = "abc"
	 * StringTools.stripToEmpty("  abc")  = "abc"
	 * StringTools.stripToEmpty("abc  ")  = "abc"
	 * StringTools.stripToEmpty(" abc ")  = "abc"
	 * StringTools.stripToEmpty(" ab c ") = "ab c"
	 * </pre>
	 * 
	 * @param str
	 *            the String to be stripped, may be null
	 * @return the trimmed String, or an empty String if {@code null} input
	 * @since 2.0
	 */
	public static String stripToEmpty(String str) {
		return str == null ? EMPTY : strip(str, null);
	}

	/**
	 * <p>
	 * Strips any of a set of characters from the start and end of a String.
	 * This is similar to {@link String#trim()} but allows the characters to be
	 * stripped to be controlled.
	 * </p>
	 * 
	 * <p>
	 * A {@code null} input String returns {@code null}. An empty string ("")
	 * input returns the empty string.
	 * </p>
	 * 
	 * <p>
	 * If the stripChars String is {@code null}, whitespace is stripped as
	 * defined by {@link Character#isWhitespace(char)}. Alternatively use
	 * {@link #strip(String)}.
	 * </p>
	 * 
	 * <pre>
	 * StringTools.strip(null, *)          = null
	 * StringTools.strip("", *)            = ""
	 * StringTools.strip("abc", null)      = "abc"
	 * StringTools.strip("  abc", null)    = "abc"
	 * StringTools.strip("abc  ", null)    = "abc"
	 * StringTools.strip(" abc ", null)    = "abc"
	 * StringTools.strip("  abcyx", "xyz") = "  abc"
	 * </pre>
	 * 
	 * @param str
	 *            the String to remove characters from, may be null
	 * @param stripChars
	 *            the characters to remove, null treated as whitespace
	 * @return the stripped String, {@code null} if null String input
	 */
	public static String strip(String str, String stripChars) {
		if (isEmpty(str)) {
			return str;
		}
		str = stripStart(str, stripChars);
		return stripEnd(str, stripChars);
	}

	/**
	 * <p>
	 * Strips any of a set of characters from the start of a String.
	 * </p>
	 * 
	 * <p>
	 * A {@code null} input String returns {@code null}. An empty string ("")
	 * input returns the empty string.
	 * </p>
	 * 
	 * <p>
	 * If the stripChars String is {@code null}, whitespace is stripped as
	 * defined by {@link Character#isWhitespace(char)}.
	 * </p>
	 * 
	 * <pre>
	 * StringTools.stripStart(null, *)          = null
	 * StringTools.stripStart("", *)            = ""
	 * StringTools.stripStart("abc", "")        = "abc"
	 * StringTools.stripStart("abc", null)      = "abc"
	 * StringTools.stripStart("  abc", null)    = "abc"
	 * StringTools.stripStart("abc  ", null)    = "abc  "
	 * StringTools.stripStart(" abc ", null)    = "abc "
	 * StringTools.stripStart("yxabc  ", "xyz") = "abc  "
	 * </pre>
	 * 
	 * @param str
	 *            the String to remove characters from, may be null
	 * @param stripChars
	 *            the characters to remove, null treated as whitespace
	 * @return the stripped String, {@code null} if null String input
	 */
	public static String stripStart(String str, String stripChars) {
		int strLen;
		if (str == null || (strLen = str.length()) == 0) {
			return str;
		}
		int start = 0;
		if (stripChars == null) {
			while (start != strLen && Character.isWhitespace(str.charAt(start))) {
				start++;
			}
		} else if (stripChars.length() == 0) {
			return str;
		} else {
			while (start != strLen && stripChars.indexOf(str.charAt(start)) != INDEX_NOT_FOUND) {
				start++;
			}
		}
		return str.substring(start);
	}

	/**
	 * <p>
	 * Strips any of a set of characters from the end of a String.
	 * </p>
	 * 
	 * <p>
	 * A {@code null} input String returns {@code null}. An empty string ("")
	 * input returns the empty string.
	 * </p>
	 * 
	 * <p>
	 * If the stripChars String is {@code null}, whitespace is stripped as
	 * defined by {@link Character#isWhitespace(char)}.
	 * </p>
	 * 
	 * <pre>
	 * StringTools.stripEnd(null, *)          = null
	 * StringTools.stripEnd("", *)            = ""
	 * StringTools.stripEnd("abc", "")        = "abc"
	 * StringTools.stripEnd("abc", null)      = "abc"
	 * StringTools.stripEnd("  abc", null)    = "  abc"
	 * StringTools.stripEnd("abc  ", null)    = "abc"
	 * StringTools.stripEnd(" abc ", null)    = " abc"
	 * StringTools.stripEnd("  abcyx", "xyz") = "  abc"
	 * StringTools.stripEnd("120.00", ".0")   = "12"
	 * </pre>
	 * 
	 * @param str
	 *            the String to remove characters from, may be null
	 * @param stripChars
	 *            the set of characters to remove, null treated as whitespace
	 * @return the stripped String, {@code null} if null String input
	 */
	public static String stripEnd(String str, String stripChars) {
		int end;
		if (str == null || (end = str.length()) == 0) {
			return str;
		}

		if (stripChars == null) {
			while (end != 0 && Character.isWhitespace(str.charAt(end - 1))) {
				end--;
			}
		} else if (stripChars.length() == 0) {
			return str;
		} else {
			while (end != 0 && stripChars.indexOf(str.charAt(end - 1)) != INDEX_NOT_FOUND) {
				end--;
			}
		}
		return str.substring(0, end);
	}

	/**
	 * @see <p>
	 *      判断两个字符串是否相等
	 *      </p>
	 * 
	 *      <pre>
	 * StringTools.equals(null, null)   = true
	 * StringTools.equals(null, "abc")  = false
	 * StringTools.equals("abc", null)  = false
	 * StringTools.equals("abc", "abc") = true
	 * StringTools.equals("abc", "ABC") = false
	 * </pre>
	 * @param cs1
	 * @param cs2
	 * @return
	 */
	public static boolean equals(CharSequence cs1, CharSequence cs2) {
		return cs1 == null ? cs2 == null : cs1.equals(cs2);
	}

	/**
	 * @see <p>
	 *      截取某个字符串之前的所有字符
	 *      </p>
	 * 
	 *      <pre>
	 * StringTools.substringBefore(null, *)      = null
	 * StringTools.substringBefore("", *)        = ""
	 * StringTools.substringBefore("abc", "a")   = ""
	 * StringTools.substringBefore("abcba", "b") = "a"
	 * StringTools.substringBefore("abc", "c")   = "ab"
	 * StringTools.substringBefore("abc", "d")   = "abc"
	 * StringTools.substringBefore("abc", "")    = ""
	 * StringTools.substringBefore("abc", null)  = "abc"
	 * </pre>
	 * 
	 * @param str
	 *            the String to get a substring from, may be null
	 * @param separator
	 *            the String to search for, may be null
	 * @return the substring before the first occurrence of the separator,
	 *         {@code null} if null String input
	 * @since 2.0
	 */
	public static String substringBefore(String str, String separator) {
		if (isEmpty(str) || separator == null) {
			return str;
		}
		if (separator.length() == 0) {
			return EMPTY;
		}
		int pos = str.indexOf(separator);
		if (pos == INDEX_NOT_FOUND) {
			return str;
		}
		return str.substring(0, pos);
	}

	/**
	 * @see <p>
	 *      截取某个字符串之后的所有字符
	 *      </p>
	 * 
	 *      <pre>
	 * StringTools.substringAfter(null, *)      = null
	 * StringTools.substringAfter("", *)        = ""
	 * StringTools.substringAfter(*, null)      = ""
	 * StringTools.substringAfter("abc", "a")   = "bc"
	 * StringTools.substringAfter("abcba", "b") = "cba"
	 * StringTools.substringAfter("abc", "c")   = ""
	 * StringTools.substringAfter("abc", "d")   = ""
	 * StringTools.substringAfter("abc", "")    = "abc"
	 * </pre>
	 * 
	 * @param str
	 *            the String to get a substring from, may be null
	 * @param separator
	 *            the String to search for, may be null
	 * @return the substring after the first occurrence of the separator,
	 *         {@code null} if null String input
	 * @since 2.0
	 */
	public static String substringAfter(String str, String separator) {
		if (isEmpty(str)) {
			return str;
		}
		if (separator == null) {
			return EMPTY;
		}
		int pos = str.indexOf(separator);
		if (pos == INDEX_NOT_FOUND) {
			return EMPTY;
		}
		return str.substring(pos + separator.length());
	}

	/**
	 * @see <p>
	 *      截取某个字符串之后的所有字符
	 *      </p>
	 * 
	 *      <pre>
	 * StringTools.substringBetween(null, *)            = null
	 * StringTools.substringBetween("", "")             = ""
	 * StringTools.substringBetween("", "tag")          = null
	 * StringTools.substringBetween("tagabctag", null)  = null
	 * StringTools.substringBetween("tagabctag", "")    = ""
	 * StringTools.substringBetween("tagabctag", "tag") = "abc"
	 * </pre>
	 * 
	 * @param str
	 *            the String containing the substring, may be null
	 * @param tag
	 *            the String before and after the substring, may be null
	 * @return the substring, {@code null} if no match
	 * @since 2.0
	 */
	public static String substringBetween(String str, String tag) {
		return substringBetween(str, tag, tag);
	}

	/**
	 * @see <p>
	 *      截取两个字符串之间的所有字符
	 *      </p>
	 * 
	 *      <pre>
	 * StringTools.substringBetween("wx[b]yz", "[", "]") = "b"
	 * StringTools.substringBetween(null, *, *)          = null
	 * StringTools.substringBetween(*, null, *)          = null
	 * StringTools.substringBetween(*, *, null)          = null
	 * StringTools.substringBetween("", "", "")          = ""
	 * StringTools.substringBetween("", "", "]")         = null
	 * StringTools.substringBetween("", "[", "]")        = null
	 * StringTools.substringBetween("yabcz", "", "")     = ""
	 * StringTools.substringBetween("yabcz", "y", "z")   = "abc"
	 * StringTools.substringBetween("yabczyabcz", "y", "z")   = "abc"
	 * </pre>
	 * 
	 * @param str
	 *            the String containing the substring, may be null
	 * @param open
	 *            the String before the substring, may be null
	 * @param close
	 *            the String after the substring, may be null
	 * @return the substring, {@code null} if no match
	 * @since 2.0
	 */
	public static String substringBetween(String str, String open, String close) {
		if (str == null || open == null || close == null) {
			return null;
		}
		int start = str.indexOf(open);
		if (start != INDEX_NOT_FOUND) {
			int end = str.indexOf(close, start + open.length());
			if (end != INDEX_NOT_FOUND) {
				return str.substring(start + open.length(), end);
			}
		}
		return null;
	}

	/**
	 * @see <p>
	 *      截取两个字符串之间的所有字符数组
	 *      </p>
	 * 
	 *      <pre>
	 * StringTools.substringsBetween("[a][b][c]", "[", "]") = ["a","b","c"]
	 * StringTools.substringsBetween(null, *, *)            = null
	 * StringTools.substringsBetween(*, null, *)            = null
	 * StringTools.substringsBetween(*, *, null)            = null
	 * StringTools.substringsBetween("", "[", "]")          = []
	 * </pre>
	 * 
	 * @param str
	 *            the String containing the substrings, null returns null, empty
	 *            returns empty
	 * @param open
	 *            the String identifying the start of the substring, empty
	 *            returns null
	 * @param close
	 *            the String identifying the end of the substring, empty returns
	 *            null
	 * @return a String Array of substrings, or {@code null} if no match
	 * @since 2.3
	 */
	public static String[] substringsBetween(String str, String open, String close) {
		if (str == null || isEmpty(open) || isEmpty(close)) {
			return null;
		}
		int strLen = str.length();
		if (strLen == 0) {
			return new String[0];
		}
		int closeLen = close.length();
		int openLen = open.length();
		List<String> list = new ArrayList<String>();
		int pos = 0;
		while (pos < strLen - closeLen) {
			int start = str.indexOf(open, pos);
			if (start < 0) {
				break;
			}
			start += openLen;
			int end = str.indexOf(close, start);
			if (end < 0) {
				break;
			}
			list.add(str.substring(start, end));
			pos = end + closeLen;
		}
		if (list.isEmpty()) {
			return null;
		}
		return list.toArray(new String[list.size()]);
	}

	/**
	 * @see 转换为大写
	 * 
	 *      <pre>
	 * StringTools.upperCase(null)  = null
	 * StringTools.upperCase("")    = ""
	 * StringTools.upperCase("aBc") = "ABC"
	 * </pre>
	 * 
	 *      <p>
	 *      <strong>Note:</strong> As described in the documentation for
	 *      {@link String#toUpperCase()}, the result of this method is affected
	 *      by the current locale. For platform-independent case
	 *      transformations, the method {@link #lowerCase(String, Locale)}
	 *      should be used with a specific locale (e.g. {@link Locale#ENGLISH}).
	 *      </p>
	 * 
	 * @param str
	 *            the String to upper case, may be null
	 * @return the upper cased String, {@code null} if null String input
	 */
	public static String upperCase(String str) {
		if (str == null) {
			return null;
		}
		return str.toUpperCase();
	}

	/**
	 * @see 转换为大写
	 * 
	 *      <pre>
	 * StringTools.upperCase(null, Locale.ENGLISH)  = null
	 * StringTools.upperCase("", Locale.ENGLISH)    = ""
	 * StringTools.upperCase("aBc", Locale.ENGLISH) = "ABC"
	 * </pre>
	 * 
	 * @param str
	 *            the String to upper case, may be null
	 * @param locale
	 *            the locale that defines the case transformation rules, must
	 *            not be null
	 * @return the upper cased String, {@code null} if null String input
	 * @since 2.5
	 */
	public static String upperCase(String str, Locale locale) {
		if (str == null) {
			return null;
		}
		return str.toUpperCase(locale);
	}

	/**
	 * @see 转换为小写
	 * 
	 *      <pre>
	 * StringTools.lowerCase(null)  = null
	 * StringTools.lowerCase("")    = ""
	 * StringTools.lowerCase("aBc") = "abc"
	 * </pre>
	 * 
	 *      <p>
	 *      <strong>Note:</strong> As described in the documentation for
	 *      {@link String#toLowerCase()}, the result of this method is affected
	 *      by the current locale. For platform-independent case
	 *      transformations, the method {@link #lowerCase(String, Locale)}
	 *      should be used with a specific locale (e.g. {@link Locale#ENGLISH}).
	 *      </p>
	 * 
	 * @param str
	 *            the String to lower case, may be null
	 * @return the lower cased String, {@code null} if null String input
	 */
	public static String lowerCase(String str) {
		if (str == null) {
			return null;
		}
		return str.toLowerCase();
	}

	/**
	 * @see 转换为小写
	 * 
	 *      <pre>
	 * StringTools.lowerCase(null, Locale.ENGLISH)  = null
	 * StringTools.lowerCase("", Locale.ENGLISH)    = ""
	 * StringTools.lowerCase("aBc", Locale.ENGLISH) = "abc"
	 * </pre>
	 * 
	 * @param str
	 *            the String to lower case, may be null
	 * @param locale
	 *            the locale that defines the case transformation rules, must
	 *            not be null
	 * @return the lower cased String, {@code null} if null String input
	 * @since 2.5
	 */
	public static String lowerCase(String str, Locale locale) {
		if (str == null) {
			return null;
		}
		return str.toLowerCase(locale);
	}

	/**
	 * @see 首字母转换为大写
	 * 
	 *      <p>
	 *      For a word based algorithm, see
	 *      {@link org.apache.commons.lang3.text.WordUtils#capitalize(String)}.
	 *      A {@code null} input String returns {@code null}.
	 *      </p>
	 * 
	 *      <pre>
	 * StringTools.capitalize(null)  = null
	 * StringTools.capitalize("")    = ""
	 * StringTools.capitalize("cat") = "Cat"
	 * StringTools.capitalize("cAt") = "CAt"
	 * </pre>
	 * 
	 * @param str
	 *            the String to capitalize, may be null
	 * @return the capitalized String, {@code null} if null String input
	 * @see org.apache.commons.lang3.text.WordUtils#capitalize(String)
	 * @see #uncapitalize(String)
	 * @since 2.0
	 */
	public static String capitalize(String str) {
		int strLen;
		if (str == null || (strLen = str.length()) == 0) {
			return str;
		}
		return new StringBuilder(strLen).append(Character.toTitleCase(str.charAt(0))).append(str.substring(1)).toString();
	}

	/**
	 * @see 首字母转换为小写
	 * 
	 *      <p>
	 *      For a word based algorithm, see
	 *      {@link org.apache.commons.lang3.text.WordUtils#uncapitalize(String)}
	 *      . A {@code null} input String returns {@code null}.
	 *      </p>
	 * 
	 *      <pre>
	 * StringTools.uncapitalize(null)  = null
	 * StringTools.uncapitalize("")    = ""
	 * StringTools.uncapitalize("Cat") = "cat"
	 * StringTools.uncapitalize("CAT") = "cAT"
	 * </pre>
	 * 
	 * @param str
	 *            the String to uncapitalize, may be null
	 * @return the uncapitalized String, {@code null} if null String input
	 * @see org.apache.commons.lang3.text.WordUtils#uncapitalize(String)
	 * @see #capitalize(String)
	 * @since 2.0
	 */
	public static String uncapitalize(String str) {
		int strLen;
		if (str == null || (strLen = str.length()) == 0) {
			return str;
		}
		return new StringBuilder(strLen).append(Character.toLowerCase(str.charAt(0))).append(str.substring(1)).toString();
	}

	/**
	 * @see Object 转换成 String
	 * @param obj
	 * @return 如果obj==null 返回空串
	 */
	public static String objToStr(Object obj) {
		return obj == null ? "" : obj.toString();
	}

	/**
	 * @see 去掉字符串尾部所有的 "0"
	 * @param str
	 * @return
	 */
	public static String removeLastZero(String str) {
		if (StringTools.isEmpty(str)) {
			return null;
		}
		String rs = "";
		for (int i = 0, len = str.length(); i < len; i++) {
			String temp = str.substring(len - i - 1, len - i);
			if (!temp.equals("0")) {
				rs = str.substring(0, len - i);
				break;
			}
		}
		return rs;
	}

	public static void main(String[] args) {
		String str = "5201010000";
		if (StringTools.isEmpty(str)) {
			// return null;
		}
		String rs = "";
		for (int i = 0, len = str.length(); i < len; i++) {
			String temp = str.substring(len - i - 1, len - i);
			if (!temp.equals("0")) {
				rs = str.substring(0, len - i);
				break;
			}
		}
		System.out.println(rs);
	}
	
	/**
	 * 字符串解码
	 *@author lignlin
	 *@Time  2017年3月31日 上午10:24:07
	 */
	public static String getDecodeStr(String str){
		if(isEmpty(str)){
			//为空
			return "";
		}
		try {
			String temp=new String(new String(str.getBytes("iso-8859-1"),"UTF-8"));
			return temp;
		} catch (Exception e) {
			e.printStackTrace();
		  return "";
		}
	}
	
	/**
	 * 根据key判断Map中的值 是否为空
	 *@param params
	 *@param key
	 *@return boolean 
	 *@author lignlin
	 *@Time  2017年3月31日 下午3:38:08
	 */
	public static boolean mapGetKeyIsEmpty(Map<String, Object> params,String key){
		if(params.get(key)!=null&&!"".equals(params.get(key).toString())){
			return true;
		}
		return false;
	}
	
	 
	/**
	 * 传入YMD日期格式字符串，返回YMDhhmmss日期格式的字符串(一天的开始和结束时间)
	 *@param ymd YMD日期格式字符串
	 *@param type start或end
	 *@return String 
	 *@author lignlin
	 *@Time  2017年3月30日 下午4:09:26
	 */
	public static String getStartOrEndDateDay(String ymd,String type){
		if("start".equals(type)){
			return ymd+" 00:00:00";
		}else if("end".equals(type)){
			return ymd+" 23:59:59";
		}
		return ymd;
	}
	
	/**
	 * 获取当前时间字符串
	 *@return String 
	 *@author lignlin
	 *@Time  2017年4月25日 下午5:05:11
	 */
	public static String getYMDhms(){
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HHmmss");
		  return formatter.format(new Date());
	}
	
    /**
     * 判断是否是数字
     * @param str
     * @return
     */
    public static boolean isNumeric(String str){
           Pattern pattern = Pattern.compile("[0-9]*");
           Matcher isNum = pattern.matcher(str);
           if( !isNum.matches() ){
               return false;
           }
           return true;
    }
}
