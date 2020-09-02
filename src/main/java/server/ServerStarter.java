package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import msg_broadcast.Consumer;
import msg_broadcast.Producers;
import streams.WordsCountStream;

public class ServerStarter {
	public static void main(String[] args) throws IOException, InterruptedException {
		startZKandKafka();
	}

	private static void startZKandKafka() throws IOException, InterruptedException {
		ProcessBuilder builder = new ProcessBuilder("C:/ApacheKafka_250/zk_kafka_start.cmd");
		/*
		 * zk_kafka_start.cmd
		 * 
echo @off
rem Запуск zookeeper
start zkserver.cmd
rem Ожидание 10 сек
ping -n 11 127.0.0.1 >nul
rem Запуск kafka
start C:\ApacheKafka_250\bin\windows\kafka-server-start.bat C:\ApacheKafka_250\config\server.properties
		 */
//		    ProcessBuilder builder1 = new ProcessBuilder("C:/ApacheKafka_250/start_ZK.cmd");
//		    ProcessBuilder builder2 = new ProcessBuilder("C:/ApacheKafka_250/startKafka.cmd");
		builder.redirectErrorStream(true);
		Process process = builder.start();
//		    builder1.redirectErrorStream( true );
//		    Process process1 = builder1.start();
//		    builder2.redirectErrorStream( true );
//		    Process process2 = builder2.start();

		try (BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
			String line;
			while ((line = br.readLine()) != null) {
				System.out.println(line);
			}
		}
	}
}
