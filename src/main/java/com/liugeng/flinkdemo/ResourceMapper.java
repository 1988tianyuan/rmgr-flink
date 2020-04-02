package com.liugeng.flinkdemo;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.util.Collector;

import com.google.gson.reflect.TypeToken;

import com.navercorp.ncp.rmgrmodel.resourcedata.Nrn;
import com.navercorp.ncp.rmgrmodel.resourcedata.ResourceDataRequestBody;
import com.navercorp.ncp.rmgrmodel.resourcedata.code.DomainCode;
import com.navercorp.ncp.rmgrmodel.resourcedata.v1.ResourceData;
import com.navercorp.ncp.rmgrsdk.model.Resource;

/**
 * @author Liu Geng liu.geng@navercorp.com
 * @date 2020/4/2 13:57
 */
public class ResourceMapper implements FlatMapFunction<String, Resource> {
	
	@Override
	public void flatMap(String input, final Collector<Resource> collector) {
		ResourceDataRequestBody body = JsonParser.jsonToObject(input, ResourceDataRequestBody.class);
		List<ResourceData> resourceDataList = 
			JsonParser.typeConvert(body.getResourceData(), new TypeToken<List<ResourceData>>() { });
		resourceDataList.forEach(resourceData -> {
				String nrnStr = resourceData.getNrn();
				Nrn nrn = new Nrn(nrnStr);
				Resource resource = new Resource();
				resource.setDomainCode(DomainCode.valueOf(nrn.getDomainCode()));
				resource.setMemberNo(nrn.getMemberNo());
				resource.setProductName(nrn.getProductName());
				resource.setResourceId(nrn.getResourceId());
				resource.setResourceType(nrn.getResourceType());
				collector.collect(resource);
			});
	}
}
