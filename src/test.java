
public class test {
	public static void reverseArr(int[] arr, int end) {
		if (end<0 || end>=arr.length) {
			System.out.println("\ndone.");
		}
		else {
			System.out.print(arr[end] + "\t");
			reverseArr(arr, --end);
		}
	}
	public static void main(String[] args) {
		System.out.println("Reversing values of array starting at chosen ending index:");
		int[] arr = {1,2,3,4,5,6,7,8,9,10};
		reverseArr(arr, 7);
	}
}
