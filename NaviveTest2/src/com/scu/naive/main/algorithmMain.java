package com.scu.naive.main;

import com.scu.naive.algorithm.NaiveBayes;

public class algorithmMain {

	public static void main(String[] args) {
		
		String textSource = "���������ص�����ӭս�Ϻ��ϸۣ��׻غϵĽ��������������������Ǳ��տ��赲��������ֻ�����������34���ӣ������������񣬰�����ɭ���ƽ��֣���42���ӣ���Ц���Χȴ�԰���������67���ӣ���ΰͷ���Ż�����������74���ӣ�������Ϊ�������һ��";
		System.out.println("�����ı���\n"+textSource);
		NaiveBayes naiveBayes = new NaiveBayes();
		String result = naiveBayes.TextClassify(textSource);
		System.out.println("���������ǣ�"+result);
	}
}
