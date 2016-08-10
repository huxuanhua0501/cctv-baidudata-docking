package com.busap.cctv.baidudata.docking.utility;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

/**
 * 汉字处理成拼音的工具基类
 * <p><blockquote>
 * 主要包括： 
 * 	汉字转换拼音大小写格式
 * </blockquote></p>
 * @author OAK <a href="mailto:shulong.li@busonline.com">shulong.li@busonline.com</a>
 * @since CCTV BaiDu 1.0
 * @version $Revision: 1.0 $ $Date: 2016/07/27 14:28:00 $ $LastModified 2016/07/27 14:28:00 $
 */
public class Pinyin {
	
	/**
	 * 汉语拼音格式枚举
	 * @author OAK
	 *	
	 */
    public static enum Type {  
        UPPERCASE,              //全部大写  
        LOWERCASE,              //全部小写  
        FIRSTUPPER              //首字母大写  
    }
	
    /**
     * 拼音工具类输出格式化变量
     */
	private HanyuPinyinOutputFormat format = null;
	
	/**
	 * 拼音工具类变量
	 */
	private static Pinyin instance;
	
	/**
	 * 单例模式获取拼音工具类实例
	 * @return 拼音工具类实例
	 */
	public static Pinyin tools() {
		if(instance == null){
			instance = new Pinyin();
		}
		return instance;
	}
	
    /**
     * 构造并初始化汉语拼音的格式
     */
    public Pinyin(){  
        format = new HanyuPinyinOutputFormat();  
        format.setCaseType(HanyuPinyinCaseType.UPPERCASE);  
        format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);  
    }  
  
    /**
     * 将initialChineseWord转换成拼音，如果不是汉字或者没有对应的拼音，则不作转换 
     * <blockquote><p>
     *  for example e.g. 
     * 		北京转换成 BEIJING 
     * </p></blockquote>
     * @param initialChineseWord 初始的汉字串 
     * @return 处理后的汉语拼音串
     * @throws BadHanyuPinyinOutputFormatCombination 格式化输出汉语拼音合并异常
     */
    public String toPinYin(String initialChineseWord) throws BadHanyuPinyinOutputFormatCombination{  
        return toPinYin(initialChineseWord, "", Type.UPPERCASE);  
    }  
  
    /**
     * 将initialChineseWord转换成拼音，如果不是汉字或者没有对应的拼音，则不作转换 
     * <blockquote><p>
     *  for example e.g. 
     * 		北京转换成 BEIJING
     * </p></blockquote>
     * @param initialChineseWord 初始的汉字串 
     * @param suffix 后缀
     * @return 处理后的汉语拼音串
     * @throws BadHanyuPinyinOutputFormatCombination 格式化输出汉语拼音合并异常
     */
    public String toPinYin(String initialChineseWord, String suffix) throws BadHanyuPinyinOutputFormatCombination{  
        return toPinYin(initialChineseWord, suffix, Type.UPPERCASE);  
    }  
  
    /** 
     * 将initialChineseWord转换成拼音，如果不是汉字或者没有对应的拼音，则不作转换 
     * <blockquote><p>
     *  for example e.g. 
     * 		北京转换成 BEIJING 
     * </p></blockquote> 
     * @param initialChineseWord 初始的汉字串 
     * @param suffix 后缀
     * @return 处理后的汉语拼音串
     * @throws BadHanyuPinyinOutputFormatCombination 格式化输出汉语拼音合并异常
     */  
    public String toPinYin(String initialChineseWord, String suffix, Type type) throws BadHanyuPinyinOutputFormatCombination {  
        int i = 0, wordLen = 0;
    	if(initialChineseWord == null || (wordLen = initialChineseWord.length()) == 0)  
            return "";  
        if(type == Type.UPPERCASE)  
            format.setCaseType(HanyuPinyinCaseType.UPPERCASE);  
        else  
            format.setCaseType(HanyuPinyinCaseType.LOWERCASE);  
  
        StringBuilder pinYin = new StringBuilder();  
        String temp = "";  
        String [] hpsa;  
        for(; i < wordLen; i++){  
            char c = initialChineseWord.charAt(i);  
            if((int)c <= 128)  
            	pinYin.append(c);  
            else{  
            	hpsa = PinyinHelper.toHanyuPinyinStringArray(c, format);  
                if(hpsa == null)  
                	pinYin.append(c);  
                else{  
                    temp = hpsa[0];  
                    if(type == Type.FIRSTUPPER)  
                        temp = hpsa[0].toUpperCase().charAt(0) + temp.substring(1);  
                    pinYin.append(temp + (i == (wordLen - 1) ? "" : suffix));  
                }  
            }  
        }  
        return pinYin.toString().trim();  
    }   
}
