package com.liugeng.flinkdemo;

import java.util.Objects;

/**
 * @author Liu Geng liu.geng@navercorp.com
 * @date 2020/3/8 18:14
 */
public class Data {
	
	private String word;
	private int count;
	
	public Data() {
	}
	
	public Data(String word, int count) {
		this.word = word;
		this.count = count;
	}
	
	public String getWord() {
		return word;
	}
	
	public void setWord(String word) {
		this.word = word;
	}
	
	public int getCount() {
		return count;
	}
	
	public void setCount(int count) {
		this.count = count;
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Data data = (Data)o;
		return count == data.count &&
			Objects.equals(word, data.word);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(word, count);
	}
	
	@Override
	public String toString() {
		return "Data{" +
			"word='" + word + '\'' +
			", count=" + count +
			'}';
	}
}
