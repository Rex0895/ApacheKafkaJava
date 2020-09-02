package streams;

import java.util.Arrays;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KTable;
import org.apache.kafka.streams.kstream.Produced;

public class WordsCountStream {

public static void main(String[] args) {
	//public void startStream() {
		Properties config = new Properties();
		config.put(StreamsConfig.APPLICATION_ID_CONFIG, "wordCountStream");
		config.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
		config.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
		config.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
		config.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.Long().getClass());

		StreamsBuilder builder = new StreamsBuilder();
		KStream<String, String> wordCountInput = builder.stream("word-count-input");

		KTable<String, Long> wordCounts = wordCountInput.flatMapValues(value ->Arrays.asList(value.split(" ")))
				.groupBy((key, value) -> value).count();
		wordCounts.toStream().to("word-count-output", Produced.with(Serdes.String(), Serdes.Long()));

		KafkaStreams streams = new KafkaStreams(builder.build(), config);
		streams.start();

		// printing the topology
		System.out.println(streams.toString());

		// shutdown hook to close the streams application correctly
		Runtime.getRuntime().addShutdownHook(new Thread(streams::close));

	}

}
