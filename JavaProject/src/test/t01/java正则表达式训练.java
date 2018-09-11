package test.t01;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class java正则表达式训练 {
	public static void main(String[] args) {
		String str = "1-2";
		//正则表达式字符串
		String pattrn ="[1-5]-[1-7]";
		//编译正则表达式
		Pattern p1 = Pattern.compile(pattrn);
		//进行配对，返回一个matcer
		Matcher matcher = p1.matcher(str);
		//判断是否合法
		System.out.println(matcher.lookingAt());
		System.out.println(matcher.matches());
		
	}
}
