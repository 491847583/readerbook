package com.book.test;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;

import kevin.zhang.NLPIR;

import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.analysis.tokenattributes.OffsetAttribute;
import org.apache.lucene.analysis.tokenattributes.TypeAttribute;

import com.book.analyzer.NLPAnalyzer;

/**
 * @author yinpeng
 *
 */
public class AnalyzerDemo {

	public static void main(String[] args){
		String sInput = "张华平推出的NLPIR分词系统，又名ICTCLAS2013，新增新词识别、关键词提取、微博分词功能。";
		try{
			//test(sInput);
			NLPAnalyzer analyzer = new NLPAnalyzer();
			TokenStream ts = analyzer.tokenStream("test", new StringReader(
					"这是一个中文分词的例子，你可以直接运行它"));
			// 获取词元位置属性
			OffsetAttribute offset = ts.addAttribute(OffsetAttribute.class);
			// 获取词元文本属性
			CharTermAttribute term = ts.addAttribute(CharTermAttribute.class);
			// 获取词元文本属性
			TypeAttribute type = ts.addAttribute(TypeAttribute.class);
			while (ts.incrementToken()) {
				System.out.println(offset.startOffset() + " - "
						+ offset.endOffset() + " : " + term.toString() + " | "
						+ type.type());
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static void test(String sInput) throws UnsupportedEncodingException{
		NLPIR testNLPIR = new NLPIR();
		
		String argu = "";
		if (testNLPIR.NLPIR_Init(argu.getBytes("UTF-8"),1) == false)
		{
			System.out.println("Init Fail!");
			return;
		}
		byte nativeBytes[] = testNLPIR.NLPIR_ParagraphProcess(sInput.getBytes("UTF-8"), 1);
		String nativeStr = new String(nativeBytes, 0, nativeBytes.length, "UTF-8");

		System.out.println("分词结果为： " + nativeStr);
	}
}