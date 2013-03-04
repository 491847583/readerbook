package com.book.analyzer;

import java.io.IOException;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.util.LinkedList;

import org.apache.lucene.analysis.Tokenizer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.analysis.tokenattributes.OffsetAttribute;
import org.apache.lucene.analysis.tokenattributes.TypeAttribute;

public class NLPTokenizer extends Tokenizer{
	//词元文本属性
	private final CharTermAttribute termAtt;
	//词元位移属性
	private final OffsetAttribute offsetAtt;
	//词元分类属性（该属性分类参考org.wltea.analyzer.core.Lexeme中的分类常量）
	private final TypeAttribute typeAtt;
	//记录最后一个词元的结束位置
	private int endPosition;
	private NLPSegmenter nlpSegmenter;
	private LinkedList<NLPWord> link;
	
	public NLPTokenizer(Reader reader){
	    offsetAtt = addAttribute(OffsetAttribute.class);
	    termAtt = addAttribute(CharTermAttribute.class);
	    typeAtt = addAttribute(TypeAttribute.class);
	    nlpSegmenter = new NLPSegmenter();
	    link = nlpSegmenter.getLink(nlpSegmenter.nlpSiplt(reader));
	}
	
	@Override
	public boolean incrementToken() throws IOException {
		// TODO Auto-generated method stub
		clearAttributes();
		if(link.size()==0){
			return false;
		}
		NLPWord word = this.next();
		if(this.link.size()!=0){
			this.link.removeFirst();
		}else{
			return false;
		}
		if(word==null){
			return false;
		}else{
			//设置词元文本
			termAtt.append(word.getTrem());
			//设置词元长度
			termAtt.setLength(word.getTrem().length());
			//设置词元位移
			offsetAtt.setOffset(word.getStart(),word.getEnd());
			//记录分词的最后位置
			endPosition = word.getEnd();
			//记录词元分类
			typeAtt.setType(word.getType());
			return true;
		}
	}
	
	public LinkedList<NLPWord> getLink() {
		return link;
	}

	public void setLink(LinkedList<NLPWord> link) {
		this.link = link;
	}
	
	public NLPWord next(){
		return this.link.getFirst();
	}
}
