package com.scu.naive.test;

import java.io.FileNotFoundException;
import java.io.IOException;

import com.scu.interfa.CLibrary;
import com.scu.naive.util.CheckStopWords;
import com.scu.naive.util.TraningDataUtil;

public class Test {

	public static void main(String[] args) {
		
		String string = "����";
		String text = "�ʼ��ܾ��ѽ������й�����ٴ�ͨ��������Ҫ��������ǿ���仪���׵Ĳ�����Դ�����估�ִ��Ȼ��ڵĹܿش�ʩ";
		String str=null;
//		try {
//			boolean bl = CheckStopWords.IsStopWord(string);
//			System.out.println(bl);
//			
//			String argu = System.getProperty("user.dir");
//			//String system_charset = "GBK";//GBK----0
//			String system_charset = "UTF-8";
//			int charset_type = 1;
//			int init_flag = CLibrary.Instance.NLPIR_Init(argu, charset_type, "0");
//			if (0 == init_flag) {
//				str = CLibrary.Instance.NLPIR_GetLastErrorMsg();
//				System.err.println("��ʼ��ʧ�ܣ�fail reason is "+str);
//				return;
//			}
//			str = CLibrary.Instance.NLPIR_ParagraphProcess(text, 0);
//			System.out.println(str);
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
	}
}
