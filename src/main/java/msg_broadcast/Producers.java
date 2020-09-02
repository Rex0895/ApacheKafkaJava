package msg_broadcast;

import java.util.Random;

public class Producers {
	private static Random rand = new Random();

	public static void main(String[] args) {
		// public void startProducers() {
		try {
			int interval = 2000;
			// System.out.println(getRandomString());
			for (int i = 1; i <= 10; i++) {
				// int msgCount=rand.nextInt(10);
				int msgCount = 10;
				// Thread hrt=new Thread(new ProducerThread(i,"test",msgCount,"Сообщение
				// №",interval));
				Thread hrt = new Thread(
						new ProducerThread(i, "word-count-input", msgCount, getRandomString(), interval));
				System.out.println(
						"Thread #" + i + " created with " + msgCount + " messages and " + interval + "ms interval");
				hrt.start();
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	private static String getRandomString() {
		StringBuilder sbString = new StringBuilder();
		char[] letters = new char[] { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P',
				'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };
		int wCount = rand.nextInt(26);

		for (int i = 0; i < wCount; i++) {
			int chCount = rand.nextInt(6);
			StringBuilder sbWord = new StringBuilder();
			if (chCount >= 1) {
				for (int j = 1; j <= chCount; j++) {
					sbWord.append(letters[i]);
					if (j == chCount) {
						sbString.append(sbWord + " ");
					}
				}
			} else {
				sbWord.append(letters[i] + " ");
			}
		}
		sbString.substring(sbString.toString().length() - 1);
		String result = sbString.toString();
		if (result.equals(null) || result.length() <= 0)
			return "A";
		else
			return result;
	}

}
