package com.liugeng.flinkdemo;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.apache.flink.api.common.functions.AggregateFunction;
import org.apache.flink.api.common.functions.RichAggregateFunction;
import org.apache.flink.api.common.functions.RuntimeContext;
import org.apache.flink.streaming.api.functions.KeyedProcessFunction;
import org.apache.flink.util.Collector;

import com.google.common.collect.Maps;

import com.navercorp.ncp.rmgrmodel.resourcedata.v1.ResourceData;
import com.navercorp.ncp.rmgrsdk.model.Resource;

/**
 * @author Liu Geng liu.geng@navercorp.com
 * @date 2020/4/2 13:52
 */
public class ResourceAgg implements AggregateFunction<Resource, Map<String, Integer>, Map<String, Integer>> {
	@Override
	public Map<String, Integer> createAccumulator() {
		return new HashMap<>();
	}
	
	@Override
	public Map<String, Integer> add(Resource resource, Map<String, Integer> countMap) {
		if (countMap.containsKey(resource.getProductName())) {
			int count = countMap.getOrDefault(resource.getProductName(), 0);
			countMap.put(resource.getProductName(), ++count);
		} else {
			countMap.putIfAbsent(resource.getProductName(), 1);
		}
		return countMap;
	}
	
	@Override
	public Map<String, Integer> getResult(Map<String, Integer> countMap) {
		return countMap;
	}
	
	@Override
	public Map<String, Integer> merge(Map<String, Integer> countMap1, Map<String, Integer> countMap2) {
		for (Map.Entry<String, Integer> entry : countMap1.entrySet()) {
			Integer count = countMap2.getOrDefault(entry.getKey(), 0);
			entry.setValue(entry.getValue() + count);
		}
		countMap1.putAll(Maps.difference(countMap1, countMap2).entriesOnlyOnRight());
		return countMap1;
	}
}
