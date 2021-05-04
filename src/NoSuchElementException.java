//--------------------------------------------------------
//Part: 2
//Written by:Alexandra Spyridakos, 40175280
//--------------------------------------------------------

public class NoSuchElementException extends Exception{
	/**Default Constructor*/
	public NoSuchElementException() {
		super("Cannot execute program, no such element exists.");
	}
	/**Parameterized Constructor
	 *@param String message (exception message) 
	 */
	public NoSuchElementException(String message) {
		super(message);
	}

}
