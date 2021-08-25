package com.example.demo.util.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Date 2020/7/18
 * @Author tiger
 */

public class LogTuoMinUtil {
    private static final Logger logger = LoggerFactory.getLogger(LogTuoMinUtil.class);

    private static List<PatternModel> patterns = null;

    static {
        try {
            System.out.println("=================初始化加载日志脱敏配置文件 tuomin-pattern.xml start======================");
            patterns = XmlParseUtil.getTuoMinPatternsFromXml();
            System.out.println("=================初始化加载日志脱敏配置文件 tuomin-pattern.xml end======================");
        } catch (Exception e) {
            logger.error("==========加载日志脱敏模式失败============");
            e.printStackTrace();
        }
    }

    private static Set<String> getMatcher(String regex, String source) {
        Set<String> set = new HashSet<>();
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(source);
        while (matcher.find()) {
            set.add(matcher.group(0));
        }
        return set;
    }

    public static String getTuoMinMsg(String source) {
        if (source == null || "".equals(source)) {
            return source;
        }
        String temp = source;
        try {
            if (patterns != null && patterns.size() > 0) {
                for (PatternModel pm : patterns) {
                    //String name = pm.getName();
                    String regular = pm.getRegular();
                    //获取匹配的字符串
                    Set<String> set = getMatcher(regular, temp);
                    for (String s : set) {
                        String start = "0";
                        String end = "0";
                        if (pm.isDifferInLen()) {
                            //说明有长度区分
                            for (Iterator<EncryptModel> it = pm.getEncrypts().iterator(); it.hasNext();) {
                                EncryptModel em = it.next();
                                if (em.getLength() != null && !"".equals(em.getLength())
                                        && Integer.parseInt(em.getLength()) == s.length()) {
                                    //说明正则匹配有长度的区分，不同长度的字符串加密的位数
                                    start = em.getStart();
                                    end = em.getEnd();
                                    break;
                                } else {
                                    continue;
                                }
                            }
                            if ("0".equals(start) && "0".equals(end)) {
                                EncryptModel em = pm.getEncrypts().get(0);
                                start = em.getStart();
                                end = em.getEnd();
                            }
                        } else {
                            EncryptModel em = pm.getEncrypts().get(0);
                            start = em.getStart();
                            end = em.getEnd();
                        }

                        int startInt = 0;
                        int endInt = 0;
                        if (isNumeric(start)) {
                            startInt = Integer.parseInt(start);
                        } else {
                            startInt = s.indexOf(start);
                        }
                        if (isNumeric(end)) {
                            endInt = Integer.parseInt(end);
                        } else {
                            endInt = s.indexOf(end) - 1;
                        }

                        String tuomin = tuomin(s, startInt, endInt);
                        temp = temp.replace(s, tuomin);
                    }
                }
            }else {
                logger.info("=========脱敏模式为空==========");
                throw new RuntimeException("=========脱敏模式为空==========");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return source;
        }
        return temp;

    }

    private static String tuomin(String submsg, int start, int end) {
        StringBuffer sbResult = new StringBuffer();
        if (submsg != null && submsg.length() > 0) {
            int len = submsg.length();

            for (int i = len - 1; i >= 0; i--) {
                if (i < start || i > end) {
                    sbResult.insert(0, submsg.charAt(i));
                } else {
                    sbResult.insert(0, '*');
                }
            }
        }
        return sbResult.toString();
    }

    /**
     * 利用正则表达式判断字符串是否是数字
     * @param str
     * @return
     */
    public static boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("[0-9]+");
        Matcher isNum = pattern.matcher(str);
        if (!isNum.matches()) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) throws Exception {
        /* String str =
         "asfdsdfsdf210403199105020018sdfsdfsdf210403199105020019";
         Set<String> matcher = getMatcher(card_regx, str);
         System.out.println(matcher);
        //
        // String tuomin_idcard = tuomin_idcard("210403199105020018");
        // String tuomin_idcard2 = tuomin_idcard("130503670401001");
        // System.out.println(tuomin_idcard);
        // System.out.println(tuomin_idcard2);*/
        //String s = "sss idcard1:210403199105020018===============idcard2:130503670401001===========ssssss;;;;ninghao@126.comffff===========phone:18500486244;;17044559955;6214830107676776";
        String s = "idcard=13109088753******************ygdOverdue=false";
        String str = "idcard=付莹******************ygdOverdue=false";
        String tuoMinMsg = getTuoMinMsg(s);
        String tuoMinMsg1 = getTuoMinMsg(str);
        System.out.println(tuoMinMsg);
        System.out.println(tuoMinMsg1);
        //		String s2 = "银行卡号：6214830107676776====== 62148366554455956;";
        //		String tuoMinMsg2 = getTuoMinMsg(s2);
        //		System.out.println(tuoMinMsg2);

    }

}
