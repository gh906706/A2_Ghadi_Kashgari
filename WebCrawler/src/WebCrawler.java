import java.net.*;
import java.util.Scanner; 
import java.io.*;

public class WebCrawler {
	public static void main(String[] args) throws IOException {
		String webpage = "https://csgs.cs.dal.ca/";
		URL current = new URL(webpage); 
		
		Scanner in = new Scanner(current.openStream());
		
		
		int linkCounter = findURLs(in, current); 
		String [] links = new String[linkCounter];
			//String line = in.next();
		
		/*current = new URL(address);
		in = new Scanner(current.openStream());
		for (int i = 0; i < linkCounter; i++) {
			int temp = 0; 
			while (in.hasNext()) {
				String line = in.next();
				if (line.contains("href=\"http://") || line.contains("href=\"https://")) {
					linkCounter++;
					int from = line.indexOf("\"");
					int to = line.lastIndexOf("\"");
					String href = line.substring(from + 1, to); 
					links[temp] = href; 
					temp++; 
				}
			}
		}*/
		
		in.close();
	}
	public static int findURLs(Scanner in, URL current) throws IOException { 
		int linkCounter = 0; 
		while (in.hasNext()) {		
			String line = in.next();
			String title = line; 
			String tempo = line;
			
			if (title.contains("<title>")) {
				int from; 
				from = title.indexOf(">");
				tempo = in.next();
				int length = title.length(); 
				System.out.print(title.substring(from+1,length));
				while (!tempo.contains("</title>")) {
					System.out.print(" "); 
					tempo = in.next(); 
					length = tempo.length();
					String str = tempo.toString();
					if (str.contains("</title>")) {
						System.out.print(tempo.substring(0,length-8));
					}
					else { 
						System.out.print(tempo.substring(0,length));
					}
				}
				System.out.println(); 
			}

			if (line.contains("href=\"http://") || line.contains("href=\"https://")) {
				linkCounter++;
				int from = line.indexOf("\"");
				int to = line.lastIndexOf("\"");
				String href = line.substring(from + 1, to); 
				System.out.println("\turl: "+href); 
			}
			
		}
		return linkCounter; 
	}
}
