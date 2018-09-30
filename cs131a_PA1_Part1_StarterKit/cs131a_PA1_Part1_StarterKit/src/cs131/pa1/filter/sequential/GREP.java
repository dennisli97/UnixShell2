package cs131.pa1.filter.sequential;

import java.util.LinkedList;
import java.util.Scanner;

import cs131.pa1.filter.Message;

public class GREP extends SequentialFilter {
	
	String searchString;// feild to hold the searching string
	// constructor to initialize  searchString
	public GREP(String searchString) {
		this.searchString = searchString;
		output = new LinkedList<String>();
	}
	
	
	
	public void process(){
		if(input == null) {
			System.out.print(Message.REQUIRES_INPUT.with_parameter("grep "+ searchString));
			return;
		}
		while (!input.isEmpty()){
			String line = input.poll();
			String processedLine = processLine(line);
			if (processedLine != null){
				output.add(processedLine);
			}
		}	
	}
	@Override
	protected String processLine(String line) {
		Scanner lineScan = new Scanner(line);// scanner to scan the line
		String words;
		while(lineScan.hasNext()) {
			words = lineScan.next();
			if(words.contains(searchString)) {
				// if any word in the line match up with the searchString, return line
				return line;
			}
		}
		return null;// else, return null
	}

}