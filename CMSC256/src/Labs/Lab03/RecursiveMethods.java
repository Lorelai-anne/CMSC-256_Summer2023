package Labs.Lab03;
/*
* Lab 03 : Recursion
* This class is implements several recursion methods
* Programmer #1: Lorelai Davis
* 31 May 2023
* CMSC 256 Section C01
*/

public class RecursiveMethods {
	/*
	 * @returns a String of character, ch. The length is determined
	 * 			by the second parameter, length.
	 */
	public static String buildStringOfCharacters(char ch, int length) {
		String str = "";
		for(int i=0;i<length;i++){
			str += ch;
		}
		return str;
	}
	/*
	 * returns an int array that has the elements in reverse order
	 * 			of the parameter array, nums.
	 * Process this recursively beginning with the last element.
	 */
	public static int[] reverseNumArray(int[] nums, int backIndex) {
		int[] smun = new int[nums.length];
		int i = backIndex;
		while(i >= 0){
			for(int j=0;j<nums.length;j++){
				smun[j] = nums[i];
				i--;
			}
		}
		return smun;
	}

	/*
	 * returns true if the int array parameter is sorted from smallest
	 * 			to largest, false otherwise.
	 * Process this recursively beginning with the first element.
	 */
	public static boolean isSmallestToLargest(int[] values, int firstIndex) {
		int temp = values[0];
		for(int i=1;i<values.length;i++){
			if(!(temp < values[i]) && temp != values[i]){
				return false;
			}temp = values[i];
		}return true;
	}

	/*
	 * @returns true if the parameter String, str is a palindrome
	 * 			false otherwise
	 */
	public static boolean isPalindrome(String str, int begin, int end) {
		int i = 0;
		while(i < str.length()){
			if(!(str.charAt(begin) == str.charAt(end))){
				return false;
			}i++;
		}return true;
	}


	public static void main(String[] args) {
		// single test of buildStringOfCharacters method
		String testStr = buildStringOfCharacters('*', 5);
		String expectedStr = "*****";
		if(expectedStr.equals(testStr))
			System.out.println("Sample test of buildStringOfCharacters method passed.");
		else
			System.out.println("Sample test of buildStringOfCharacters method fails.");


		// single test of reverseNumArray method
		int[] inputArray = {1, 3, 5, 7, 9, 11};
		int[] expectedResult = {11, 9, 7, 5, 3, 1};
		int[] testArray = reverseNumArray(inputArray, inputArray.length-1);

		boolean testPassed = true;

		for(int i = 0; i < inputArray.length; i++)
			if(testArray[i] != expectedResult[i])
				testPassed = false;

		if(testPassed)
			System.out.println("Sample test of reverseNumArray method passed.");
		else
			System.out.println("Sample test of reverseNumArray method fails.");


		// tests for isSmallestToLargest
		int[] ascendingArray = {2, 6, 10, 14, 18, 22};
		int[] descendingArray = {22, 16, 14, 10, 8, 2};
		if(isSmallestToLargest(ascendingArray, 0) && !isSmallestToLargest(descendingArray, 0))
			System.out.println("Sample test of isSmallestToLargest method passed.");
		else
			System.out.println("Sample test of isSmallestToLargest method fails.");


		// tests for isPalindrone
		if(isPalindrome("kayak", 0, 4) && isPalindrome("noon", 0, 3))
			System.out.println("Sample test of isPalindrome method passed.");
		else
			System.out.println("Sample test of isPalindrome method fails.");

	}
}
