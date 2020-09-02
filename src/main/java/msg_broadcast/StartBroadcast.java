package msg_broadcast;

import java.io.IOException;

import streams.WordsCountStream;

public class StartBroadcast {
	public static void main(String[] args) throws IOException, InterruptedException{
		WordsCountStream wcs = new WordsCountStream();
		Producers prd = new Producers();
		//Consumer cons = new Consumer();
	//	wcs.startStream();
		Thread.sleep(1000);
//		cons.startConsumer();
//		Thread.sleep(1000);
		//prd.startProducers();
	}
}
