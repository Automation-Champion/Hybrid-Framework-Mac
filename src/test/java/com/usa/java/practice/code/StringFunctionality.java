package com.usa.java.practice.code;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.StringTokenizer;

import org.apache.commons.lang3.RandomStringUtils;

public class StringFunctionality {

	public static void stringConcatValue() {
		// String is an immutable.
		// StringBuffer is a mutable and synchronized.
		// StringBuilder is also mutable but its not synchronized.
		String obj = new String("RABO BANK");
		obj.concat("  GROUP");
		System.out.println(obj);
	}

	public static void stringPerformances() {
		long startTime = System.currentTimeMillis();
		StringBuffer sb = new StringBuffer("Java");
		for (int i = 0; i < 10000; i++) {
			sb.append("Tpoint");
		}
		System.out.println("Time taken by StringBuffer: " + (System.currentTimeMillis() - startTime) + "ms");
		startTime = System.currentTimeMillis();
		StringBuilder sb2 = new StringBuilder("Java");
		for (int i = 0; i < 10000; i++) {
			sb2.append("Tpoint");
		}
		System.out.println("Time taken by StringBuilder: " + (System.currentTimeMillis() - startTime) + "ms");
	}

	public static void stringBufferConcatValue() {
		// StringBuffer is synchronized
		// add to value with StringBuffer
		// StringBuffer is thread-safe
		// StringBuilder performance is better than StringBuffer.
		StringBuffer buffer = new StringBuffer("hello");
		buffer.append("java");
		System.out.println(buffer);
	}

	public static void stringStringBuilderConcatValue() {
		// StringBuilder is not synchronized
		// StringBuilder is not thread-safe
		// StringBuilder performance is better than StringBuffer.
		// add to value with StringBuilder
		StringBuilder builder = new StringBuilder("hello");
		builder.append("java");
		System.out.println(builder);
	}

	public static void strungBufferReverse() {
		String s = new String("SMART TECH");
		String newobj = new StringBuffer(s).reverse().toString();
		System.out.println(newobj);
	}

	public static void getCharacter() {
		String name = "SmartTech";
		char ch = name.charAt(4);// returns the char value at the 4th index
		System.out.println(ch);
	}

	public static void compareString() {
		String s1 = "Smart";
		String s2 = "Tech";
		// false
		System.out.println(s1 == s2);
		// -1 because "S" is 1 times lower than "T"
		System.out.println(s1.compareTo(s2));
	}

	public static void equalsString() {
		String s1 = "Java";
		String s2 = "Java";
		// true because content and case is same
		System.out.println(s1.equals(s2));
	}

	public static void splitString() {
		String s1 = "United State Of America";
		String[] words = s1.split("\\s");// splits the string based on whitespace
		// using java for each loop to print elements of string array
		for (String w : words) {
			System.out.println(w);
		}
		System.out.println("Split array length: " + words.length);
	}

	public static void joinString() {
		String joinString = String.join(" ", "welcome", "to", "Java & Selenium");
		System.out.println(joinString);
		System.out.println(joinString.toLowerCase());
		System.out.println(joinString.toUpperCase());
	}

	public static void stringSubstring() {
		String s1 = "SmartTech";
		System.out.println(s1.substring(2, 4));// returns ar
		System.out.println(s1.substring(2));// returns artTech
	}

	public static String getCurrentDate() {
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		Date date = new Date();
		String currentdate = dateFormat.format(date);
		return currentdate;
	}

	public static String threeDaysBefore() {
		String threeDaysBefore = "";
		Date date = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		// System.out.println(date);
		cal.add(Calendar.DAY_OF_YEAR, -3);
		Date before = cal.getTime();
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
		threeDaysBefore = formatter.format(before);
		return threeDaysBefore;
	}

	public static void convartString() {
		// This is a int data
		int a = 1234;
		String str = Integer.toString(a);
		System.out.println("I am convert int to String  = " + str);
		// This is a String data
		String number = "10";
		int result = Integer.parseInt(number);
		System.out.println("I am convert String to int  = " + result);
	}

	public static void removeSpecialChar() {
		String text = "This - text ! has \\ /allot # of % special % characters";
		text = text.replaceAll("[^a-zA-Z0-9]", " ");
		System.out.println(text);

		String t2 = "120,99";
		t2 = t2.replaceAll("\\W+", "");
		System.out.println(t2);
	}

	// How to remove Extra Spaces
	public static void removeExtraSpaces() {
		String input = "Try    to    remove   extra   spaces.";
		StringTokenizer substr = new StringTokenizer(input, " ");
		StringBuffer sb = new StringBuffer();
		while (substr.hasMoreElements()) {
			sb.append(substr.nextElement()).append(" ");
		}
		System.out.println("Actual string: " + input);
		System.out.println("Processed string: " + sb.toString().trim());
	}

	// Created for generating random string for Unique email
	public static String randomestring() {
		String generatedString1 = RandomStringUtils.randomAlphabetic(8);
		return (generatedString1);
	}

	// Created for generating random string for Unique email
	public static String randomNumeric() {
		String generatedString = RandomStringUtils.randomNumeric(20);
		return (generatedString);
	}

	// Generate numeric Number
	public static String GenerateAirConNum() {
		String num = "AIRCON";
		String trackNum = null;
		File out = new File("PMCTestData\\airCon.txt");
		FileWriter fw = null;
		long n = 10000L;
		try {
			fw = new FileWriter(out);
			BufferedWriter writer = new BufferedWriter(fw);
			int line;
			Random random = new Random();
			while (n > 0) {
				line = random.nextInt(10000);
				writer.write(num + line + "\n");
				trackNum = num + line;
				System.out.println(" airCon Number :::" + trackNum);
				break;
			}
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(0);
		}
		return trackNum;
	}

	// Generated numeric Number Save & Return
	public static String storeAirConNum() {
		String filename = "./PMCTestData/airCon.txt";
		String content = null;
		File file = new File(filename);
		FileReader reader = null;
		try {
			reader = new FileReader(file);
			char[] chars = new char[(int) file.length()];
			reader.read(chars);
			content = new String(chars);
			System.out.println(" Copy airCon Num :" + content);
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return content;
	}

	public static void main(String[] args) {
		// getCharacter();
		// stringPerformances();
		// stringStringBuilderConcatValue();
		// strungBufferReverse();
		// stringConcatValue();
		// compareString();
		// equalsString();
		// splitString();
		// joinString();
		// stringSubstring();
		// System.out.println(getCurrentDate());
		// System.out.println(threeDaysBefore());
		// convartString();
		// removeSpecialChar();
		System.out.println(randomestring());
		System.out.println(randomNumeric());

	}

}
