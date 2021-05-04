//--------------------------------------------------------
//Part: 2
//Written by:Alexandra Spyridakos, 40175280
//--------------------------------------------------------

/**
 * TVShow class creates TVShow objects to be used as ShowNodes in the linked list (ShowList)
 */
public class TVShow implements Watchable, Cloneable {
	private String showID;
	private String showName;
	private double startTime;
	private double endTime;

	/**
	 * Default Constructor
	 */
	public TVShow() {		//should never have to use this because the linked list should not have empty or near empty values in it
		showID = null;
		showName = null;
		startTime = 0;
		endTime = 0;
	}

	/**
	 * Parameterized Constructor
	 * @param showID
	 * @param showName
	 * @param startTime
	 * @param endTime
	 */
	public TVShow(String showID, String showName, double startTime, double endTime) {
		this.showID = showID;
		this.showName = showName;
		this.startTime = startTime;
		this.endTime = endTime;
	}

	/**
	 * Copy Constructor
	 * @param someShow
	 * @param id
	 */
	public TVShow(TVShow someShow,String id) {
		if (someShow == null) {				//checking that object attributes exist
			System.out.println("Fatal error");
			System.exit(0);
		}
		showID = id;
		this.showName = someShow.getShowName();
		this.startTime = someShow.getStartTime();
		this.endTime = someShow.getEndTime();
	}

	/**
	 * Clne method for TVShow object
	 * @param id
	 * @return TVShow show
	 */
	public TVShow clone(String id) {
		return new TVShow(this, id);
	}

	/**
	 *Converts object to printable string
	 * @return String of TV show attributes 
	 */
	public String toString() {
		return (showName + " on " + showID + " starts at " + startTime + " and ends at " + endTime + ".");
	}

	/**
	 * Checks equality of shows 
	 * @return boolean of equality of shows
	 */
	public boolean equals(Object someShow) {
		if ((someShow.getClass() != getClass()) || (someShow == null)) {
			return false;
		}			
		else {
			TVShow s = (TVShow) someShow;
			return (s.getShowName().equals(showName) && s.getStartTime() == startTime && s.getEndTime() == endTime);
		}
	}

	/**
	 * Checks if show time are overlapping or not
	 * @return String of show viewing status 
	 */
	public String isOnSameTime(TVShow someShow) {
		double start = someShow.getStartTime();
		double end = someShow.getEndTime();
		if (start == startTime && end == endTime)
			return ("Same time.");
		else if (start>=endTime || end<=startTime)
			return ("Different time.");
		else
			return ("Some overlap.");
	}

	//Getters
	/**
	 * @return showID
	 */
	public String getShowID() {	
		return showID;
	}
	/**
	 * @return showName
	 */
	public String getShowName() {	
		return showName;
	}
	/**
	 * @return startTime
	 */
	public double getStartTime() {	
		return startTime;
	}
	/**
	 * @return endTime
	 */
	public double getEndTime() {	
		return endTime;
	}

	//Setters
	/**
	 * @param id
	 */
	private void setShowID(String id) {
		showID = id;
	}
	/**
	 * @param name
	 */
	private void setShowName(String name) {
		showName = name;
	}

	/**
	 * @param startTime
	 */
	private void setStartTime(double startTime) {
		this.startTime = startTime;
	}

	/**
	 * @param endTime
	 */
	private void setEndTime(double endTime) {
		this.endTime = endTime;
	}
}		

