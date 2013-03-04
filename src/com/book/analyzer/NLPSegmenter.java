package com.book.analyzer;

import java.io.Reader;
import java.util.LinkedList;

import kevin.zhang.NLPIR;

import org.apache.commons.io.IOUtils;

public class NLPSegmenter {

	private static NLPIR testNLPIR = new NLPIR();

	public String nlpSiplt(Reader reader) {
		try{
			String argu = "";
			if (testNLPIR.NLPIR_Init(argu.getBytes("UTF-8"),1) == false)
			{
				System.out.println("Init Fail!");
				return null;
			}
			byte nativeBytes[] = testNLPIR.NLPIR_ParagraphProcess(IOUtils.toByteArray(reader), 1);
			String nativeStr = new String(nativeBytes, 0, nativeBytes.length, "UTF-8");
			//System.out.println("分词结果为："+nativeStr);
			return nativeStr;
		
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

	public LinkedList<NLPWord> getLink(String str) {
		if(str==null){
			return null;
		}
		str = str.trim();
		String arr[] = str.split(" ");
		LinkedList<NLPWord> list = new LinkedList<NLPWord>();
		int index = 0;
		for (String a : arr) {
			String arr1[] = a.split("[/]");
			if (index == 0 && a.length() == 1) {
				list.add(new NLPWord(index, index + 1, arr1[0],arr1[1]));
				index = index + a.length();
			} else {
				list.add(new NLPWord(index, index + a.length(),arr1[0],arr1[1]));
				index = index + a.length();
			}
		}
		return list;
	}
}
