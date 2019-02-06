package cs131.pa1.filter.concurrent;

public class WcFilter extends ConcurrentFilter {
	private int linecount;
	private int wordcount;
	private int charcount;
	
	public WcFilter() {
		super();
	}
	
	public void process() {
		String line = "thisisanotherpoisonpilllollollolxxx";
		String processedLine= null;
		while (true) {
			try {
				line = inQ.take();
			} catch (InterruptedException e) {
				isInterrupted = true;
			}
			if (line.equals("xxxxthisisapoisonpill123123123hahahaha")) {
				processedLine = processLine(null);
				outQ.add(processedLine);
				return;
			} else if (!line.equals("thisisanotherpoisonpilllollollolxxx")) {
				processedLine = processLine(line);
			}
		}
//		if(isDone()) {
//			outQ.add(processLine(null));
//		} else {
//			while (!isDone()) {// haven't finish the process
//				String line= "thisisanotherpoisonpilllollollolxxx";
//				try {
//					if (inQ.peek() != null && !inQ.peek().equals("xxxxthisisapoisonpill123123123hahahaha")) {
//						line = inQ.take();
//					}
//				} catch (InterruptedException e1) {
//					e1.printStackTrace();
//				}
//				String processedLine = null;
//				if (!line.equals("thisisanotherpoisonpilllollollolxxx")) {
//					processedLine = processLine(line);
//				}
//				if (processedLine != null){
//					try {
//						outQ.put(processedLine);
//					} catch (InterruptedException e) {
//						e.printStackTrace();
//					}// add out put for next to process
//				}
//			}
//		}
	}
	
	public String processLine(String line) {
		//prints current result if ever passed a null
		if(line == null) {
			return linecount + " " + wordcount + " " + charcount;
		} 
		
		if(isDone()) {
			String[] wct = line.split(" ");
			wordcount += wct.length;
			String[] cct = line.split("|");
			charcount += cct.length;
			return ++linecount + " " + wordcount + " " + charcount;
		} else {
			linecount++;
			String[] wct = line.split(" ");
			wordcount += wct.length;
			String[] cct = line.split("|");
			charcount += cct.length;
			return null;
		}
	}

	@Override
	public void run() {
		process();
		try {
			outQ.put("xxxxthisisapoisonpill123123123hahahaha");//insert poison pill
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
}
