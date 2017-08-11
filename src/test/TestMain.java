package test;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestMain {

	public static void main(String[] args) {

		String s = "333,3";

		String spl[] = s.split(",");

		
		
		Pattern r = Pattern.compile(".");
		Matcher m = r.matcher(spl[0]);
		while (m.find()) {
			String str = m.group();
			System.out.println(str);
		}
		
		System.out.println("FIM ");
	}

}
