package cs131.pa1.filter.sequential;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

import cs131.pa1.filter.Message;

public class CD extends SequentialFilter{
	
	// constructor to add change of directory into input and initialize feild userCommand
	// for error message
	public CD(String change, String userCommand) {
		input.add(change);
		this.userCommand = userCommand;
		
		
	}
	// process input queue to add on currentWorkingDirectory
	public String process(String currentWorkingDirectory) {// contain returns to update directroy
		String changes= input.poll();
		// case of no subcommand, cd to the most basic directory
		if(changes.equals("")) {
			int count=0;
			int space =2;// most basic directory should only have 2/
			for(int i=0; i < currentWorkingDirectory.length(); i++) {
				char ch = currentWorkingDirectory.charAt(i);
				if(ch == '/' && space !=0) {// if char is /, and has only reach the first two /
					count = i;
					space--;
				}
			}
			if(space == 0) {
				output.add(currentWorkingDirectory);
				return currentWorkingDirectory;
			}else {
				output.add(currentWorkingDirectory.substring(0, count));
				return currentWorkingDirectory.substring(0, count);
			}
			
		// case of one period as subcommand, stay in same working directory	
		}else if(changes.equals(".")) {
			output.add(currentWorkingDirectory);
			return currentWorkingDirectory; 
		
		}else if(changes.equals("..")) {// case of tow period, return to the previous directory
			
			int count=0;// counting the number of /
			for(int i=0; i < currentWorkingDirectory.length(); i++) {
				char ch = currentWorkingDirectory.charAt(i);
				if(ch == '/') {
					count = i;
				}
			}
			output.add(currentWorkingDirectory.substring(0, count));
			return currentWorkingDirectory.substring(0, count);// cut the string before the last /
		
		}else {// case of going into a directory
			Path newPath = Paths.get(currentWorkingDirectory+changes);// initialize the path 
			if(Files.exists(newPath)) {// check if directory exist
				output.add(currentWorkingDirectory+changes);
				return currentWorkingDirectory+changes;
			}else {// else, error massage print out, stay in same directory
				System.out.println(Message.DIRECTORY_NOT_FOUND.with_parameter(userCommand));
				output.add(currentWorkingDirectory);
				return currentWorkingDirectory;
			}
		}
	}

	@Override
	protected String processLine(String line) {
		// TODO Auto-generated method stub
		return null;
	} 
}
