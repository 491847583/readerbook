package com.book.analyzer;

import java.io.Reader;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;

public class NLPAnalyzer extends Analyzer{

	@Override
	public TokenStream tokenStream(String field, Reader reader) {
		// TODO Auto-generated method stub
		return new NLPTokenizer(reader);
	}
}
