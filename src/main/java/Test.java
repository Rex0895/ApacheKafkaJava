import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Test {

	public static void main(String[] args) {
		Map<String,String>map = new HashMap<String,String>();
		StringBuilder sb = new StringBuilder();
		for(int i=1;i<=10;i++) {
			sb.append("Word ");
			map.put(Integer.toString(i), sb.toString());
		}
		for(String s:map.values())
			System.out.println(s);
//		Map<String,Long>wordsCount=map.entrySet().stream()
//                .collect(Collectors.toMap(e->e.getKey().toString(),e ->(Arrays.asList(e.getValue().split("\\W+")).size())));
		
	}

}
