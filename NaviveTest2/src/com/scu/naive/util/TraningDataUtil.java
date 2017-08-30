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
 * 获取文件相关信息的方法，主要用于获取训练集的内容
 */
public class TraningDataUtil {

	private String[] traningFileClassifications;//训练集分类集合(IT 体育 健康 军事 招聘 教育 文化 旅游 汽车 财经 )
	private String[] paths; //一个分类下面的所有训练集文本的路径
	private File traningTextDir;//训练集的根目录(Categories)
	private static String defaultPath = System.getProperty("user.dir")+"\\Categories";
	
	/**
	 * 构造函数，初始化时打开训练集的目录
	 */
	public TraningDataUtil() {
		
		traningTextDir = new File(defaultPath);
		if (!traningTextDir.isDirectory()) 
		{
			throw new IllegalArgumentException("训练集搜索失败！ [" +defaultPath + "]");
		}
		this.traningFileClassifications = traningTextDir.list();
		System.out.println("所有分类：");
		for(String s:this.traningFileClassifications){
			System.out.print(s+ " ");
		}
		System.out.println();
	}
	
	/**
	* 返回训练文本类别，这个类别就是目录名
	* @return 训练文本类别
	*/
	public String[] getTraningClassifications() {
		return this.traningFileClassifications;
	}
	
	/**
	* 根据训练文本类别返回这个类别下的所有训练本文路径
	* @param classification 给定的分类
	* @return 给定分类下所有文件的路径（full path）
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
	* 返回给定路径的本文文件内容
	* @param filePath 给定的文本文件路径
	* @return 文本内容
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
	* 返回训练文本集中所有的文本数目
	* @return 训练文本集中所有的文本数目
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
	* 返回训练文本集中在给定分类下的训练文本数目
	* @param classification 给定的分类
	* @return 训练文本集在给定分类下的训练文本数目
	*/
	public int FileCountOfClassification(String classth)
	{
		File classDir = new File(traningTextDir.getPath() +File.separator +classth);
		return classDir.list().length;
	}

	/**
	* 返回给定分类中包含关键字/词的训练文本的数目
	* @param classification 给定的分类
	* @param key 给定的关键词/字
	* @return 给定分类中包含关键字/词的训练文本的数目
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
	 * @return 总目录下的文件长度
	 */
	public int traningFileClassificationsLength(){
		
		return traningFileClassifications.length;
	}
}
