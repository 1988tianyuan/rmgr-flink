package com.liugeng.flinkdemo;

import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

/**
 * @author Liu Geng liu.geng@navercorp.com
 * @date 2020/3/8 16:58
 */
public class MainJob {
	
	public static void main(String[] args) throws Exception {
		final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
		
		DataStream<Data> stream = env
			.socketTextStream("10.34.130.39", 9000, "\n")
			.map(line -> new Data(line, 1));
		
		DataStream<Data> result = stream.countWindowAll(5).sum("count");
		
		result.print().setParallelism(1);
		
		result.print();
		
		env.execute();
	}
}
