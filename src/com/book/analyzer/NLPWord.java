package com.book.analyzer;

public class NLPWord {

	private int start;
	
	private int end;
	
	private String trem;
	
	public NLPWord(){}
	
	public NLPWord(int start,int end,String trem){
		this.start = start;
		this.end = end;
		this.trem = trem;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getEnd() {
		return end;
	}

	public void setEnd(int end) {
		this.end = end;
	}

	public String getTrem() {
		return trem;
	}

	public void setTrem(String trem) {
		this.trem = trem;
	}
}
