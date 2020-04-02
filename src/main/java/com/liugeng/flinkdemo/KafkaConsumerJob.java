package com.liugeng.flinkdemo;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Pattern;

import org.apache.flink.api.common.functions.AggregateFunction;
import org.apache.flink.api.common.functions.ReduceFunction;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.api.java.functions.KeySelector;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.KeyedProcessFunction;
import org.apache.flink.streaming.api.functions.windowing.ProcessWindowFunction;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.streaming.api.windowing.windows.TimeWindow;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer;
import org.apache.flink.util.Collector;

import com.navercorp.ncp.rmgrmodel.resourcedata.Nrn;
import com.navercorp.ncp.rmgrmodel.resourcedata.ResourceDataRequestBody;
import com.navercorp.ncp.rmgrmodel.resourcedata.v1.ResourceData;
import com.navercorp.ncp.rmgrsdk.model.Resource;

/**
 * @author Liu Geng liu.geng@navercorp.com
 * @date 2020/4/2 11:25
 */
public class KafkaConsumerJob {
	
	public static final String groupId = "liugeng-flink-test";
	
	public static void main(String[] args) throws Exception {
		final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
		Properties properties = new Properties();
		properties.setProperty("bootstrap.servers", "10.250.36.88:9093,10.250.36.89:9093,10.250.36.90:9093");
		properties.setProperty("group.id", groupId);
		properties.put("security.protocol", "SASL_PLAINTEXT");
		properties.put("sasl.mechanism", "SCRAM-SHA-256");
		properties.put("sasl.jaas.config", 
			"org.apache.kafka.common.security.scram.ScramLoginModule required username=\"rmgr\" password=\"rmgr123\";");
		FlinkKafkaConsumer<String> consumer = 
			new FlinkKafkaConsumer<>(Pattern.compile("resource-v1-.*"), new SimpleStringSchema(), properties);
		env.addSource(consumer)
			.setParallelism(1)
			.flatMap(new ResourceMapper())
			.keyBy(new ResourceKeySelector())
			.timeWindow(Time.seconds(5))
			.aggregate(new ResourceAgg(), new ResourceAggProcess())
			.print();
		env.execute();
	}
}
