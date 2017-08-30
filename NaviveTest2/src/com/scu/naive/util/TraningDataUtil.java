package com.scu.naive.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author xing
 * ��ȡ�ļ������Ϣ�ķ�������Ҫ���ڻ�ȡѵ����������
 */
public class TraningDataUtil {

	private String[] traningFileClassifications;//ѵ�������༯��(IT ���� ���� ���� ��Ƹ ���� �Ļ� ���� ���� �ƾ� )
	private String[] paths; //һ���������������ѵ�����ı���·��
	private File traningTextDir;//ѵ�����ĸ�Ŀ¼(Categories)
	private static String defaultPath = System.getProperty("user.dir")+"\\Categories";
	
	/**
	 * ���캯������ʼ��ʱ��ѵ������Ŀ¼
	 */
	public TraningDataUtil() {
		
		traningTextDir = new File(defaultPath);
		if (!traningTextDir.isDirectory()) 
		{
			throw new IllegalArgumentException("ѵ��������ʧ�ܣ� [" +defaultPath + "]");
		}
		this.traningFileClassifications = traningTextDir.list();
		System.out.println("���з��ࣺ");
		for(String s:this.traningFileClassifications){
			System.out.print(s+ " ");
		}
		System.out.println();
	}
	
	/**
	* ����ѵ���ı�������������Ŀ¼��
	* @return ѵ���ı����
	*/
	public String[] getTraningClassifications() {
		return this.traningFileClassifications;
	}
	
	/**
	* ����ѵ���ı���𷵻��������µ�����ѵ������·��
	* @param classification �����ķ���
	* @return ���������������ļ���·����full path��
	*/
	public String[] getFilesPath(String classification) 
	{
		File classDir = new File(traningTextDir.getPath() +File.separator +classification);
		String[] ret = classDir.list();
		for (int i = 0; i < ret.length; i++) 
		{
			ret[i] = traningTextDir.getPath() +File.separator +classification +File.separator +ret[i];
		}
		return ret;
	}

	/**
	* ���ظ���·���ı����ļ�����
	* @param filePath �������ı��ļ�·��
	* @return �ı�����
	* @throws java.io.FileNotFoundException
	* @throws java.io.IOException
	*/
	public static String getText(String filePath) throws FileNotFoundException,IOException 
	{
		
		InputStreamReader isReader =new InputStreamReader(new FileInputStream(filePath),"GBK");
		BufferedReader reader = new BufferedReader(isReader);
		String aline;
		StringBuilder sb = new StringBuilder();
	
		while ((aline = reader.readLine()) != null)
		{
			sb.append(aline + " ");
		}
		isReader.close();
		reader.close();
		return sb.toString();
	}

	/**
	* ����ѵ���ı��������е��ı���Ŀ
	* @return ѵ���ı��������е��ı���Ŀ
	*/
	public int getAllFileCountofClassification()
	{
		
		int count = 0;
		for (int i = 0; i < traningFileClassifications.length; i++)
		{
			count +=FileCountOfClassification(traningFileClassifications[i]);
		}
		return count;
	}

	/**
	* ����ѵ���ı������ڸ��������µ�ѵ���ı���Ŀ
	* @param classification �����ķ���
	* @return ѵ���ı����ڸ��������µ�ѵ���ı���Ŀ
	*/
	public int FileCountOfClassification(String classth)
	{
		File classDir = new File(traningTextDir.getPath() +File.separator +classth);
		return classDir.list().length;
	}

	/**
	* ���ظ��������а����ؼ���/�ʵ�ѵ���ı�����Ŀ
	* @param classification �����ķ���
	* @param key �����Ĺؼ���/��
	* @return ���������а����ؼ���/�ʵ�ѵ���ı�����Ŀ
	*/
	public int getCountContainKeyOfClassification(String word,String classth) 
	{
		int result = 0;
		try 
		{
			String[] filePath = getFilesPath(classth);
			
			for (int j = 0; j < filePath.length; j++) 
			{
				String text = getText(filePath[j]);
				if (text.contains(word)) 
				{
					result++;
				}
			}
		}
		catch (FileNotFoundException ex) 
		{
			Logger.getLogger(TraningDataUtil.class.getName()).log(Level.SEVERE, null,ex);
	
		} 
		catch (IOException ex)
		{
			Logger.getLogger(TraningDataUtil.class.getName()).log(Level.SEVERE, null,ex);
	
		}
		return result;
	}
	
	/**
	 * @return ��Ŀ¼�µ��ļ�����
	 */
	public int traningFileClassificationsLength(){
		
		return traningFileClassifications.length;
	}
}
