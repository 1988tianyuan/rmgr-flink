package com.liugeng.flinkdemo;

import java.util.Map;

import org.apache.flink.streaming.api.functions.windowing.ProcessWindowFunction;
import org.apache.flink.streaming.api.windowing.windows.TimeWindow;
import org.apache.flink.util.Collector;

/**
 * @author Liu Geng liu.geng@navercorp.com
 * @date 2020/4/2 15:16
 */
public class ResourceAggProcess extends ProcessWindowFunction<Map<String, Integer>, ResourceAggResult, String, TimeWindow> {
	
	@Override
	public void process(String key, Context context, Iterable<Map<String, Integer>> elements, Collector<ResourceAggResult> out) {
		for (Map<String, Integer> countMap : elements) {
			ResourceAggResult result = new ResourceAggResult(key, System.currentTimeMillis(), countMap, context.currentWatermark());
			result.setProcessingTime(context.currentProcessingTime());
			result.setWindowEnd(context.window().getEnd());
			result.setWindowStart(context.window().getStart());
			out.collect(result);
		}
	}
}
