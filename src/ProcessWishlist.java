//--------------------------------------------------------
//Part: 2
//Written by:Alexandra Spyridakos, 40175280
//--------------------------------------------------------

import java.io.PrintWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class ProcessWishlist {

	/**
	 * Driver class to test ShowList methods and process TV guide
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the name of the TVGuide file:");
		String tvGuide = sc.nextLine();
//		String tvGuide = "TVGuide.txt";			//for testing only
		System.out.println("Enter the  name of the file containing the user's interests:");
		String interest = sc.nextLine();
//		String interest = "Interest.txt";		//for testing only 
		sc.close();

		PrintWriter pw = null;
		ShowList list1 = new ShowList();

		Scanner guideIn = null;
		Scanner interestIn = null;
		String line = null;
		try {
			File processedFile = new File("ProcessedTVGuide.txt");
			if (!processedFile.exists()){
				processedFile.createNewFile();
			}
			pw = new PrintWriter(new FileOutputStream("ProcessedTVGuide.txt"));
			guideIn = new Scanner(new FileInputStream(tvGuide));
			String showID = null;
			String showName = null;
			double startTime = 0;
			double endTime = 0;
			int count = 1;
			while (guideIn.hasNextLine()) {

				line = guideIn.nextLine();
				if (!line.equals("")) {
					if (line.substring(0,1).equals("S")) 
						startTime = Double.parseDouble(line.split(" ")[1]);
					else if (line.substring(0,1).equals("E")) 
						endTime = Double.parseDouble(line.split(" ")[1]);

					else {
						String[] lineArr = line.split(" ");
						showID = lineArr[0];
						showName = lineArr[1];
					}
				}

				if (count%4==0) {
					TVShow node = new TVShow(showID, showName, startTime, endTime);
					if (!list1.contains(showID)) {
						list1.addToStart(node);	
					}
					showID = null;
					showName = null;
					startTime = 0;
					endTime = 0;
				}	
				count++;
			}
			guideIn.close();

			interestIn = new Scanner(new FileInputStream(interest));
			ArrayList<String> arrWatching = new ArrayList<String>();
			ArrayList<String> arrWishlist = new ArrayList<String>();
			ArrayList<String> sameTime = new ArrayList<String>();
			ArrayList<String> someOverlap = new ArrayList<String>();
			ArrayList<String> differentTime = new ArrayList<String>();
			TVShow currentShow = null;
			TVShow wishlistShow = null;
			String category = "";

			//creating arrWatching and arrWishlist from Interest.txt file
			while (interestIn.hasNextLine()) {
				line = interestIn.nextLine();
				if (line.equals("Watching")) {
					category = "Watching";
				}
				else if (line.equals("Wishlist")) {
					category = "Wishlist";
				}
				else if (category.equals("Watching")) {
					arrWatching.add(line);
				}
				else if (category.equals("Wishlist")) {
					arrWishlist.add(line);
				}
			}
			interestIn.close();
			//removing any empty lines from the text file added to the arrays mistakenly
			for(int i=0; i<arrWishlist.size(); i++) {
				if (arrWishlist.get(i).equals("")) {
					arrWishlist.remove(i);
				}
			}
			for(int i=0; i<arrWatching.size(); i++) {
				if (arrWatching.get(i).equals("")) {
					arrWatching.remove(i);
				}
			}
			//Testing watchlist and wishlist arrays
			/*for (int i=0; i<arrWatching.size();i++) {
				System.out.println(arrWatching.get(i));
			}
			System.out.println();
			for (int i=0; i<arrWishlist.size();i++) {
				System.out.println(arrWishlist.get(i));
				if (list1.find(arrWishlist.get(i))!= null) {
					System.out.println(list1.find(arrWishlist.get(i)).getNodeData());
				}
			}*/	
			for (int i=0; i<arrWatching.size();i++) {	
				for (int j=0; j<arrWishlist.size();j++) {
					if (list1.find(arrWatching.get(i))!=null) {
						currentShow = list1.find(arrWatching.get(i)).getNodeData();
					}
					if (list1.find(arrWishlist.get(j))!= null) {
						wishlistShow = list1.find(arrWishlist.get(j)).getNodeData();
					}
					if(currentShow.isOnSameTime(wishlistShow).equals("Same time."))
						sameTime.add(arrWishlist.get(j));
					if(currentShow.isOnSameTime(wishlistShow).equals("Some overlap.")) 
						someOverlap.add(arrWishlist.get(j));
					if(currentShow.isOnSameTime(wishlistShow).equals("Different time.")) {
						differentTime.add(arrWishlist.get(j));
					}
				}
			}
			System.out.println();
			for (int i=0; i<arrWishlist.size(); i++) {
				if (sameTime.contains(arrWishlist.get(i))) {
					System.out.println("User can't watch show " + arrWishlist.get(i) + " as he/she will begin another show at the same time.");
					pw.println("User can't watch show " + arrWishlist.get(i) + " as he/she will begin another show at the same time.");
				}
				else if (someOverlap.contains(arrWishlist.get(i))) {
					System.out.println("User can't watch show " + arrWishlist.get(i) + " as he/she is not finished with a show he/she is watching.");
					pw.println("User can't watch show " + arrWishlist.get(i) + " as he/she is not finished with a show he/she is watching.");
				}
				else if (differentTime.contains(arrWishlist.get(i))) {
					System.out.println("User can watch show " + arrWishlist.get(i) + " as he/she is not watching anything else during that time.");
					pw.println("User can watch show " + arrWishlist.get(i) + " as he/she is not watching anything else during that time.");
				}
			}
			System.out.println("\nCopied processed TV guide to text file 'ProcessedTVGuide.txt'.\nSequence completed...\n");
		}
		catch (FileNotFoundException e) {
			System.out.println(e.getMessage() + "\nExiting program.");
			System.exit(0);
		}
		catch (IOException e) {
			System.out.println(e.getMessage() + "\nExiting program.");
			e.printStackTrace();
		}
		pw.close();
		sc.close();

		System.out.println("Displaying list1 : \n" + list1);
		ShowList list2 = new ShowList(list1);
		System.out.println("-------------Testing ShowList Copy Constructor--------------------");
		System.out.println("Displaying list2 : \n" + list2);
		Scanner input = new Scanner(System.in);
		
		//test 1
		System.out.println("\nEnter a show ID:");
//		String id = input.next();	//throws NoSuchElementException for some weird reason that i could not figure out (used hard-coded values for testing instead)
		String id = "ABC2030";
		System.out.println(id);
		System.out.println("\n-------------Testing ShowList contains() method-----------------\n" + list1.contains(id)+ "\n\nNumber of Iterations: " + list1.numIterations);		//tests find and contain method simultaneously
		System.out.println("\n-------------Testing ShowList find() method-----------------\n" + list1.find(id).getNodeData()+ "\n\nNumber of Iterations: " + list1.numIterations);
		//test 2 
		System.out.println("\nEnter a show ID:");
//		id = input.next();
		id = null;
		System.out.println(id);
		System.out.println("\n-------------Testing ShowList contains() method-----------------\n" + list1.contains(id)+ "\n\nNumber of Iterations: " + list1.numIterations);		//tests find and contain method simultaneously
		System.out.println("\n-------------Testing ShowList find() method-----------------\n" + list1.find(id)+ "\n\nNumber of Iterations: " + list1.numIterations);
		//test 3
		System.out.println("\nEnter a show ID:");
//		id = input.next();
		id = "some invalid id";
		System.out.println(id);
		System.out.println("\n-------------Testing ShowList contains() method-----------------\n" + list1.contains(id)+ "\n\nNumber of Iterations: " + list1.numIterations);		//tests find and contain method simultaneously

		System.out.println("\n-------------Testing ShowList find() method-----------------\n" + list1.find(id)+ "\n\nNumber of Iterations: " + list1.numIterations);
		
		input.close();	
		System.out.println("\n---------------------Testing ShowList equals() method------------------\n" + list1.equals(list2) + "\n\nNumber of Iterations: " + list1.numIterations);
		
		TVShow show1 = new TVShow("CTV2", "CTV News", 18.0, 19.0);
		list1.replaceAtIndex(2, show1);
		System.out.println("\n---------------------Testing replaceAtIndex(int index, TVShow newNode) method---------------------\n" + list1);
		TVShow show2 = new TVShow("CBC3", "The Nature of Things", 19.5, 21.0);
		list1.replaceAtIndex(0, show2);
		System.out.println("\n---------------------Testing replaceAtIndex(int index, TVShow newNode) method---------------------\n" + list1);
		
		System.out.println("\n---------------------Testing ShowList equals() method------------------\n" + list1.equals(list2) + "\n\nNumber of Iterations: " + list1.numIterations);

		list1.deleteFromStart();
		System.out.println("\n---------------------Testing ShowList deletFromStart() method------------------\n" + list1 + "\n\nNumber of Iterations: " + list1.numIterations);
		list1.deleteFromIndex(2);
		System.out.println("\n---------------------Testing ShowList deletFromIndex() method------------------\n" + list1 + "\n\nNumber of Iterations: " + list1.numIterations);
		TVShow show3 = new TVShow("CBC5", "The Nature of Things", 20.5, 22.0);
		list1.insertAtIndex(5, show3);
		System.out.println("\n---------------------Testing insertAtIndex(int index, TVShow newNode) method---------------------\n" + list1);
		
		TVShow show4 = show1.clone("CTV1");
		System.out.println("\n---------------------Testing clone(String id) method for TVShow---------------------\nShow:" + show1 + "\nClone of show: " + show4);
		
		System.out.println("\nExiting program.");
	}

}
