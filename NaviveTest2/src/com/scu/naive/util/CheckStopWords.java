package com.scu.naive.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Vector;

public class CheckStopWords {
	
	private static String filepath = System.getProperty("user.dir")+"\\stopword.txt";
	private static String stopwordList[] = null;
	
	/**
	 * @param filePath stopword���ļ�·��
	 * @return ��stopword�ļ������ݶ����������һ������
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static String[] getStopWord(String filePath) throws FileNotFoundException,IOException{
		
		File file = new File(filePath);
		InputStreamReader isReader =new InputStreamReader(new FileInputStream(file),"UTF-8");
		BufferedReader reader = new BufferedReader(isReader);
		String aline;
		StringBuilder sb = new StringBuilder();
		while ((aline = reader.readLine()) != null)
		{
			sb.append(aline + " ");
		}
		
		String sbString = sb.toString();
		String[] str = sbString.split(" ");
		isReader.close();
		reader.close();
		return str;
	}
	
	/**
	 * @param word
	 * @return true/false
	 * �жϸô��Ƿ�����ͣ�ôʣ��Ǿͷ���true
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	public static boolean IsStopWord(String word) throws FileNotFoundException, IOException{
		
		//����ļ��е�ͣ�ôʣ�ת��������
		stopwordList = getStopWord(filepath);
		for(int i=0;i<stopwordList.length;i++)
		{
			if(word.equalsIgnoreCase(stopwordList[i])){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * @param textArray
	 * @return ɾ������ͣ�ôʣ����ٺ�������ļ�����
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	public static String[] deleteStopWord(String[] textArray) throws FileNotFoundException, IOException{
		
		//Vector ��ʵ���Զ������Ķ�������
		Vector<String> vector = new Vector<String>();
		for(int i=0;i<textArray.length;i++){
			if(IsStopWord(textArray[i])==false){
				vector.add(textArray[i]);
			}
		}
		String[] newTextArray = new String[vector.size()];
		vector.toArray(newTextArray);
		return newTextArray;
	}
}
