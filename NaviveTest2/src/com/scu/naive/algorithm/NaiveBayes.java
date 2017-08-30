package com.scu.naive.algorithm;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.scu.naive.util.CheckStopWords;
import com.scu.naive.util.NlpirUtil;
import com.scu.naive.util.TraningDataUtil;

public class NaiveBayes {

	private TraningDataUtil traningData;
	private static double MutipleTen = 10.0f; //把结果放大1000倍
	private static final double M = 0.0d; //方便把整型转换成double型
	private DecimalFormat dFormat;
	
	public NaiveBayes(){
		traningData = new TraningDataUtil();
		dFormat = new DecimalFormat("0.0000000");
	}
	
	public String TextClassify(String textSource){
		
		String[] textArray = null;
		textArray = NlpirUtil.chineseSplit(textSource).split(" ");
		System.out.println("文本分词：");
		for(String s:textArray){
			System.out.print(s+" ");
		}
		System.out.println();
		String[] newTextArray = null;
		try {
			newTextArray = CheckStopWords.deleteStopWord(textArray);
			System.out.println("删除停用词后的文本：");
			for(String s:newTextArray){
				System.out.print(s+" ");
			}
			System.out.println();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		//获得所有的类别名(IT 体育 健康 军事 招聘 教育 文化 旅游 汽车 财经 )
		String[] Classes = traningData.getTraningClassifications();
		double probility = 0.0d;
		//定义分类结果存储集合
		List<ClassifyResult> resultList = new ArrayList<ClassifyResult>();
		//遍历所有类别
		for(int i=0; i<Classes.length; i++){
			probility = CalculateAllText(newTextArray,Classes[i]);
			//保存分类结果
			ClassifyResult classifyResult = new ClassifyResult();
			classifyResult.classification = Classes[i]; //类别
			classifyResult.probility = probility; //关键字在类别中的条件概率
			resultList.add(classifyResult);
		}
		for(int i=0;i<resultList.size();i++){
			System.out.println(resultList.get(i).classification+" : "+resultList.get(i).probility);
		}
		Collections.sort(resultList, new Comparator<ClassifyResult>() {

			@Override
			public int compare(ClassifyResult o1, ClassifyResult o2) {
				
				if(o1.probility < o2.probility){
					return 1;
				}else{
					return -1;
				}
			}
			
		});
		return resultList.get(0).classification;
	}

	/**
	 * @param newTextArray 删除停用词后的文本内容数组
	 * @param classth 类别名称
	 * @return 计算整个文本在一个类别中出现的概率
	 */
	public double CalculateAllText(String[] newTextArray, String classth) {
		
		double calresult = 1.0d;
		for(int i=0; i<newTextArray.length; i++){
			//将每个词组出现在某个类别的概率累乘
			calresult *= (CalculateOneWord(newTextArray[i], classth)*MutipleTen);
		}
		//再乘以先验概率
		calresult *= PrioriProbability(classth);
		return Double.parseDouble(dFormat.format(calresult));
	}
	
	/**
	 * @param classth
	 * @return 先验概率
	 */
	public double PrioriProbability(String classth) {
		
		double prioriprobaility = 0.0d;
		double Pc = traningData.FileCountOfClassification(classth);
	    double P =  traningData.getAllFileCountofClassification();
	    prioriprobaility = Pc / P;
		return prioriprobaility;
	}

	/**
	 * @param word 单个词组
	 * @param classth 类别名称
	 * @return 计算一个词组在单个类别中的概率
	 */
	public double CalculateOneWord(String word,String classth){
		
		double wordprobaility = 0.0d;
		double Pc = traningData.getCountContainKeyOfClassification(word, classth);
		double P = traningData.FileCountOfClassification(classth);
		double Nc = traningData.traningFileClassificationsLength();
		wordprobaility = (Pc+1.0)/(P+Nc+M);
		return wordprobaility;
	}
}
