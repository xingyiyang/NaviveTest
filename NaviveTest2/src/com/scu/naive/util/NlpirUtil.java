package com.scu.naive.util;

import com.scu.interfa.CLibrary;

/**
 * @author xing
 * ʹ���п�Ժ��NLPIR���߽������ķִ�
 * ��ѵ�������ı���ֳɴ���
 */
public class NlpirUtil {

	/**
	 * @param textsource ��Ҫ���зִʵ��ı�����
	 * @return �ִʺõ��ı��еĴ�����һ���ո���������������ַ���
	 */
	public static String chineseSplit(String textSource){
		
		String splitresult = null;
		String argu = System.getProperty("user.dir");
		int charset_type = 1;
		
		int init_flag = CLibrary.Instance.NLPIR_Init(argu, charset_type, "0");

		if (0 == init_flag) {
			splitresult = CLibrary.Instance.NLPIR_GetLastErrorMsg();
			System.err.println("��ʼ��ʧ�ܣ�fail reason is "+splitresult);
			return null;
		}
		//����0��ʾ�������ԣ�����1��ʾ���д���
		splitresult = CLibrary.Instance.NLPIR_ParagraphProcess(textSource, 0);
		return splitresult;
	}
}
