//Varun Pavuloori
//CS 2110


//import array list
import java.util.ArrayList;
import java.util.TreeSet;

public class User implements Comparable<User> {

	//instance variables
	private String userName;
	private ArrayList<Meme> memesCreated;
	private TreeSet<Meme> memesViewed;
 
	@Override public int compareTo(User userCompare) {
			//if either is null return null
			if (this == null || userCompare == null)
				return 0;
		
			//returning value
			int returnVal = 0;
			
			//compare the two users by username using string compareTo
			returnVal = this.getUserName().compareTo(userCompare.getUserName());
			//if they are not the same return the result
			if (returnVal!=0)
				return returnVal;
			
			//compare the two users by by size of memescreated using Integer wrapper class
			Integer callingCreate = this.getMemesCreated().size();
			Integer userCompCreate = userCompare.getMemesCreated().size();
			returnVal = callingCreate.compareTo(userCompCreate);
			//if they are not the same return the result
			if (returnVal!=0) {
				//descending order to reversing value
				returnVal = -returnVal;
				return returnVal;
			}
	
			//catchall return 0
			else
				return returnVal;
	}
	
	//default constructor
	public User(){
		//assigns no value and creats new arraylists
		userName = "";
		memesCreated = new ArrayList<Meme>();
		memesViewed = new TreeSet<Meme>();
	}
	
	//overloaded constructor
	public User(String UserName) {
		//sets username to given paramater
		userName = UserName;
		memesCreated = new ArrayList<Meme>();
		memesViewed = new TreeSet<Meme>();
	}

	//getters and setters for the instance variables
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public ArrayList<Meme> getMemesCreated() {
		return memesCreated;
	}

	public void setMemesCreated(ArrayList<Meme> memesCreated) {
		this.memesCreated = memesCreated;
	}

	public ArrayList<Meme> getMemesViewed() {
		ArrayList<Meme> getViewed = new ArrayList<Meme>(this.memesViewed);
		return getViewed;
	}

	public void setMemesViewed(ArrayList<Meme> memesViewed) {
		TreeSet<Meme> setViewed = new TreeSet<Meme>();
		setViewed.addAll(memesViewed);
		this.memesViewed = setViewed;
	}
	
	//rate meme method
	
	//instance variable to count how many mems have been rated for counting purposes
	private int count = 0;
	public void rateMeme(Meme pepe, int rating) {
		//record meme as seen and give it a rating
		this.memesViewed.add(pepe);
		
		//making new rating object with this as user and rating parameter as score
		Rating pepeRating = new Rating(this, rating);
		//add rating
		pepe.addRating(pepeRating);
		//increment count for toString
		count++;
	}
	//create meme method
	public Meme createMeme(BackgroundImage BckgImg, String caption) {
		//makes a meme with parameters and adds it it to the right arraylist
		Meme newMeme = new Meme(BckgImg, caption, this);
		//adds it to current users memes created arraylist
		this.memesCreated.add(newMeme);
		return newMeme;
	}
	
	//delete meme method
	public boolean deleteMeme(Meme pepe) {
		//deletes meme if it is found in memes created arraylist and shared is false
		//traverse array list while it has a value
		for (int x=0; x<this.memesCreated.size(); x++) {
			//makes sure value is not null
			if (this.memesCreated.get(x)!=null) {
				//sees if meme is a created meme and if it is not shared
				if (this.memesCreated.get(x).equals(pepe) && pepe.getShared()==false) {
					memesCreated.remove(x);
					return true;
				}
			}
		}
		//catch all indicating not possible to remove
		return false;
	}
	
	//share meme method
	public void shareMeme(Meme pepe, Feed scroll) {
		//sets meme to shared and copies to arraylist of memes on feed
		pepe.setShared(true);
		
		//adding to arraylist of memes on feed

		if (scroll.getMemes()!=null)
		scroll.getMemes().add(pepe);
		else
		scroll.getMemes().add(0, pepe);
	}
	
	//rateNextMemeFromFeed method
	public boolean rateNextMemeFromFeed(Feed scroll, int ratingScore) {
		
		//getting a new meme from feed method
		Meme nextMeme = scroll.getNewMeme(this);
		//check if nextMeme has a null value indicating that there are no new memes and returning false if thats the case
		if (nextMeme == null) {
			return false;
		}
		//if next meme has been seen already
		if (this.getMemesViewed().contains(nextMeme))
			return false;
		//assuming the meme is not null and unviewed by User
		//record the meme as viewed
		this.memesViewed.add(nextMeme);
		//create a rating for the meme
		Rating nextMemeRating = new Rating(this, ratingScore);
		//adding the rating to the meme
		nextMeme.addRating(nextMemeRating);
		//add to the counter for memes rated
		count++;
		//return true for succesfull add
		return true;
	}
	
	//method to return number of memes rated
	public int memesRated() {
		return this.count;
	}
	
	//calculate rep method
	public double calculateReputation() {
		//if user has not created any memes or had any memes rated then return 0.0
		if (this.memesCreated.size()==0) {
		return 0.0;
		}
		//holds sum for meme ratings
		double sum = 0.0;
		//traverse created memes and add ratings
		for (int x=0; x<this.memesCreated.size(); x++) {
				sum = sum + this.memesCreated.get(x).calculateOverallRating();
		} 
		//check if user has no rated memes (sum of 0.0) to return 0.0 reputation
		if (sum==0.0) {
			return 0.0;
		}
		
		//otherwise return average of sum divided by total created memes
		return sum / ((double) this.memesCreated.size());
		
	}
	
	@Override public String toString() {
		//returns the username, how many memes they have rated and their reputation
		return this.userName + " has rated (" + count + ") memes, (" + calculateReputation() + ")";
	}
	
	@Override public boolean equals(Object obj) {
		//tests if two objects are users and share the same paramater
		//same obj true
		if (this == obj) {
			return true;
		}
		//if its null false
		if (obj == null) {
			return false;
		}
		//different class false
		if (this.getClass() != obj.getClass()) {
			return false;
		}
		//same username true
		if (this.userName.equals(((User) obj).userName) && this.getClass() == obj.getClass()) {
			return true;
		//catch all false
		} else {
			return false;
		}
	}

}
//completed User.java