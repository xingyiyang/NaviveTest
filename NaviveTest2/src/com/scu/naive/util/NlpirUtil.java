package com.scu.naive.util;

import com.scu.interfa.CLibrary;

/**
 * @author xing
 * 使用中科院的NLPIR工具进行中文分词
 * 把训练集的文本拆分成词组
 */
public class NlpirUtil {

	/**
	 * @param textsource 需要进行分词的文本内容
	 * @return 分词好的文本中的词组用一个空格隔开，返回整个字符串
	 */
	public static String chineseSplit(String textSource){
		
		String splitresult = null;
		String argu = System.getProperty("user.dir");
		int charset_type = 1;
		
		int init_flag = CLibrary.Instance.NLPIR_Init(argu, charset_type, "0");

		if (0 == init_flag) {
			splitresult = CLibrary.Instance.NLPIR_GetLastErrorMsg();
			System.err.println("初始化失败！fail reason is "+splitresult);
			return null;
		}
		//参数0表示不带词性，参数1表示带有词性
		splitresult = CLibrary.Instance.NLPIR_ParagraphProcess(textSource, 0);
		return splitresult;
	}
}
