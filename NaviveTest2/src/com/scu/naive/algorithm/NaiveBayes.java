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
	private static double MutipleTen = 10.0f; //�ѽ���Ŵ�1000��
	private static final double M = 0.0d; //���������ת����double��
	private DecimalFormat dFormat;
	
	public NaiveBayes(){
		traningData = new TraningDataUtil();
		dFormat = new DecimalFormat("0.0000000");
	}
	
	public String TextClassify(String textSource){
		
		String[] textArray = null;
		textArray = NlpirUtil.chineseSplit(textSource).split(" ");
		System.out.println("�ı��ִʣ�");
		for(String s:textArray){
			System.out.print(s+" ");
		}
		System.out.println();
		String[] newTextArray = null;
		try {
			newTextArray = CheckStopWords.deleteStopWord(textArray);
			System.out.println("ɾ��ͣ�ôʺ���ı���");
			for(String s:newTextArray){
				System.out.print(s+" ");
			}
			System.out.println();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		//������е������(IT ���� ���� ���� ��Ƹ ���� �Ļ� ���� ���� �ƾ� )
		String[] Classes = traningData.getTraningClassifications();
		double probility = 0.0d;
		//����������洢����
		List<ClassifyResult> resultList = new ArrayList<ClassifyResult>();
		//�����������
		for(int i=0; i<Classes.length; i++){
			probility = CalculateAllText(newTextArray,Classes[i]);
			//���������
			ClassifyResult classifyResult = new ClassifyResult();
			classifyResult.classification = Classes[i]; //���
			classifyResult.probility = probility; //�ؼ���������е���������
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
	 * @param newTextArray ɾ��ͣ�ôʺ���ı���������
	 * @param classth �������
	 * @return ���������ı���һ������г��ֵĸ���
	 */
	public double CalculateAllText(String[] newTextArray, String classth) {
		
		double calresult = 1.0d;
		for(int i=0; i<newTextArray.length; i++){
			//��ÿ�����������ĳ�����ĸ����۳�
			calresult *= (CalculateOneWord(newTextArray[i], classth)*MutipleTen);
		}
		//�ٳ����������
		calresult *= PrioriProbability(classth);
		return Double.parseDouble(dFormat.format(calresult));
	}
	
	/**
	 * @param classth
	 * @return �������
	 */
	public double PrioriProbability(String classth) {
		
		double prioriprobaility = 0.0d;
		double Pc = traningData.FileCountOfClassification(classth);
	    double P =  traningData.getAllFileCountofClassification();
	    prioriprobaility = Pc / P;
		return prioriprobaility;
	}

	/**
	 * @param word ��������
	 * @param classth �������
	 * @return ����һ�������ڵ�������еĸ���
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
