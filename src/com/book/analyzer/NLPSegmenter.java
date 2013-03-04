package com.book.analyzer;

import java.io.Reader;
import java.util.LinkedList;
import org.apache.commons.io.IOUtils;

public class NLPSegmenter {

	private static NLPIR nlp = new NLPIR();

	@SuppressWarnings("static-access")
	public String nlpSiplt(Reader reader) {
		try{
			String argu = "";
			@SuppressWarnings("unused")
			String encoding = "utf-8";
			if (nlp.NLPIR_Init(argu.getBytes("utf-8"), 1) == false) {
				System.err.println("Init Fail!");
				return null;
			}
			
			byte nativeBytes[] = nlp.NLPIR_ParagraphProcess(IOUtils.toByteArray(reader), 1);
			return new String(nativeBytes, 0, nativeBytes.length, "utf-8").trim();
		
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

	public LinkedList<NLPWord> getLink(String str) {
		str = str.replaceAll("[+[/]*[A-Za-z]*[0-9]]", "");
		String arr[] = str.split(" ");
		LinkedList<NLPWord> list = new LinkedList<NLPWord>();
		int index = 0;
		for (String a : arr) {
			System.out.println(a);
			if (index == 0 && a.length() == 1) {
				list.add(new NLPWord(index, index + 1, a));
				index = index + a.length();
			} else {
				list.add(new NLPWord(index, index + a.length(), a));
				index = index + a.length();
			}
		}
		return list;
	}
}
