import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
public class Find_File_Location {
	public static void main(String[] args) {
		Scanner in=new Scanner(System.in);
		boolean b0=true;
		while(b0) {
		ArrayList<String> loc=null;
		boolean b=true;
		while(b) {
			System.out.println("Enter Directory to search.");
			String path=in.nextLine();
			System.out.println("Enter file name to search for. Case sensitive");
			String key=in.nextLine();
			loc=findFile(path,key);
			if(loc==null) {
				System.out.println("Major Error,File path entered could not be found.");
				System.out.println("Would you like to search again?");
				System.out.println("Y/N only");
				String a=in.nextLine();
				if(a.equalsIgnoreCase("y")) {
					continue;
				}else {
					System.out.println("You have selected to exit. Goodbye.");
					System.exit(0);
				}
			}else b=false;
		}
		
		if(!loc.isEmpty()) {
			System.out.println("Found the following locations.");
			System.out.println("------------------------------");
			for(int i=0;i<loc.size();i++) {
				System.out.println(loc.get(i));
			}
			System.out.println("------------------------------");
			System.out.println("Search Again?");
			System.out.println("Y for yes, anything else to exit.");
			String l=in.nextLine();
			if(l.equalsIgnoreCase("y")) continue;
			else {
				System.out.println("Exiting program");
				System.exit(0);
			  }
		}else {
			System.out.println("No locations found!");
			System.out.println("Search Again?");
			System.out.println("Y for yes, anything else to exit.");
			String l=in.nextLine();
			if(l.equalsIgnoreCase("y")) continue;
			else {
				System.out.println("Exiting program");
				System.exit(0);
			  }
			}
		}
	}

	public static ArrayList<String> findFile(String path,String key) {
		if(path==null)return null;
		File file=new File(path);
		if(!file.exists())return null;
		ArrayList<File> arr=new ArrayList<>();
		ArrayList<String> found=new ArrayList<>();
		int i=0;
		if(file.isFile()){
			if(file.toString().substring(file.toString().lastIndexOf("\\")).contains(key))found.add(file.toString());
			return found;
		}
		arr.add(file);
		while(!arr.isEmpty()) {
			if(arr.get(i).isDirectory()) {
				File[] t=arr.get(i).listFiles();
				for(int j=0;j<t.length;j++) {
					if(t[j]!=null)arr.add(t[j]);
				}
				if(arr.get(i).getAbsolutePath().substring(arr.get(i).getAbsolutePath().lastIndexOf("\\")).contains(key))found.add(arr.get(i).getAbsolutePath());
				arr.remove(i);
			}else {
				if(arr.get(i).getAbsolutePath().substring(arr.get(i).getAbsolutePath().lastIndexOf("\\")).contains(key))found.add(arr.get(i).getAbsolutePath());
				arr.remove(i);
			}
		}
		return found;
	}
}