package com.scu.naive.main;

import com.scu.naive.algorithm.NaiveBayes;

public class algorithmMain {

	public static void main(String[] args) {
		
		String textSource = "江苏苏宁回到主场迎战上海上港，首回合的较量，洪正好两连击先是被颜骏凌挡出，随后又击中立柱，第34分钟，苏宁防线送礼，埃尔克森打破僵局，第42分钟，杨笑天解围却自摆乌龙。第67分钟，戈伟头球攻门击中立柱，第74分钟，洪正好为苏宁扳回一球";
		System.out.println("分类文本：\n"+textSource);
		NaiveBayes naiveBayes = new NaiveBayes();
		String result = naiveBayes.TextClassify(textSource);
		System.out.println("所属分类是："+result);
	}
}
