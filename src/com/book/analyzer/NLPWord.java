package com.book.analyzer;

public class NLPWord {

	private int start;
	
	private int end;
	
	private String trem;
	
	private String type;
	
	public NLPWord(){}
	
	public NLPWord(int start,int end,String trem,String type){
		this.start = start;
		this.end = end;
		this.trem = trem;
		this.type = type;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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
