package com.liugeng.flinkdemo;

import org.apache.flink.api.java.functions.KeySelector;

import com.navercorp.ncp.rmgrsdk.model.Resource;

/**
 * @author Liu Geng liu.geng@navercorp.com
 * @date 2020/4/2 14:32
 */
public class ResourceKeySelector implements KeySelector<Resource, String> {
	@Override
	public String getKey(Resource resource) throws Exception {
		if (resource.getMemberNo() == null) {
			return "0";
		}
		return resource.getMemberNo().toString();
	}
}
