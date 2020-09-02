package msg_broadcast;

import java.util.Arrays;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

public class Consumer {

	public static void main(String[] args) {
		// public void startConsumer() {
		Properties properties = new Properties();
		properties.put("bootstrap.servers", "localhost:9092");
		properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		properties.put("value.deserializer", "org.apache.kafka.common.serialization.LongDeserializer");
		properties.put("group.id", "test-group");
		properties.put("client.id", "A");
		properties.put("enable.auto.commit", "true");
		properties.put("auto.commit.interval.ms", "1000");
		properties.put("session.timeout.ms", "30000");

		KafkaConsumer<String, String> kafkaConsumer = new KafkaConsumer<String, String>(properties);

		kafkaConsumer.subscribe(Arrays.asList("word-count-output"));
		System.out.println("Subscribed");
		try {
			// Duration duration = Duration.ofSeconds(2);
			while (true) {
				ConsumerRecords<String, String> records = kafkaConsumer.poll(100);
				for (ConsumerRecord<String, String> record : records) {
					System.out.println(String.format("Received: Topic - %s, Partition - %d, Value: %s", record.topic(),
							record.partition(), record.value()));
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			kafkaConsumer.close();
		}
	}

}
