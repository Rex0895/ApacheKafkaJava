package msg_broadcast;

import java.io.IOException;
import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

public class ProducerThread implements Runnable {
	private int id, interval, msgCount;
	private String topicName, msgText;

	public ProducerThread(int id, String topicName, int msgCount, String msgText, int interval) {
		this.id = id;
		this.topicName = topicName;
		this.msgCount = msgCount;
		// String s = "Отправитель №"+id+" "+msgText;
		this.msgText = msgText;
		this.interval = interval;

	}

	public void run() {

//		getMessage("test");
		try {
			sendMessage();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
	}

	public void sendMessage() throws IOException {
		Properties properties = new Properties();
		properties.put("bootstrap.servers", "localhost:9092");
		properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

		KafkaProducer<String, String> kafkaProducer = new KafkaProducer<String, String>(properties);
		try {
			for (int i = 1; i <= msgCount; i++) {
				// System.out.println(i);
				kafkaProducer.send(
						new ProducerRecord<String, String>(topicName, java.time.LocalTime.now().toString(), msgText));/// get()
				if (interval != 0)
					Thread.sleep(interval);
			}
		} catch (Exception ex) {
			System.out.print(ex.getMessage());
			// throw new IOException(ex.toString());
		} finally {
			kafkaProducer.close();
		}

	}
}
