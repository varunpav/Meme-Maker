//Varun Pavuloori
//CS 2110

public class Meme implements Comparable<Meme> {

	// instance variable
	private User creator;
	private BackgroundImage backgroundImage;
	private Rating[] ratings;
	private String caption;
	private String captionVerticalAlign;
	private boolean shared;

	//comparable interface
	@Override public int compareTo(Meme memeCompare) {
		//compare the two memes by caption using string compareTo
		int returnVal = this.getCaption().compareTo(memeCompare.getCaption());
		//if they are equal keep going
			if (returnVal==0) {
				//compare the memes by background Image compare to
				returnVal = this.getBackgroundImage().compareTo(memeCompare.getBackgroundImage());
				//if they are equal keep going
				if (returnVal==0) {
					//use double wrapper class to compare based on reputation
					returnVal = -Double.compare(this.calculateOverallRating(), memeCompare.calculateOverallRating());
					//if they are equal keep going
					if (returnVal==0) {
						//use boolean wrapper class to compare based on shared status
						returnVal = -Boolean.compare(this.getShared(), memeCompare.getShared());
					}
				}
			}
			return returnVal;
	} 
	
	// default constructor
	public Meme() {
		//setting most values to blank either by using default constructor or giving blank value
		creator = new User();
		backgroundImage = new BackgroundImage();
		ratings = new Rating[10];
		caption = "";
		captionVerticalAlign = "bottom";
		shared = false;
	}

	// overloaded constructor
	public Meme(BackgroundImage bckgImg, String capt, User creator) {
		//setting instance variables to values given by parameters
		backgroundImage = bckgImg;
		caption = capt;
		this.creator = creator;
		ratings = new Rating[10];
		captionVerticalAlign = "bottom";
		
	}

	// getter and setters for all instance variables
	public User getCreator() {
		return creator;
	}

	public void setCreator(User creator) {
		this.creator = creator;
	}

	public BackgroundImage getBackgroundImage() {
		return backgroundImage;
	}

	public void setBackgroundImage(BackgroundImage backgroundImage) {
		this.backgroundImage = backgroundImage;
	}

	public Rating[] getRatings() {
		return ratings;
	}

	public void setRatings(Rating[] ratings) {
		this.ratings = ratings;
	}

	public String getCaption() {
		return caption;
	}

	public void setCaption(String caption) {
		this.caption = caption;
	}

	public String getCaptionVerticalAlign() {
		return captionVerticalAlign;
	}

	//check if caption vertical align is an allowed value 
	public boolean setCaptionVerticalAlign(String captionVerticalAlign) {
		//switch statement to accept either capatalized or lower case accepted values
		switch (captionVerticalAlign) {
		case "top":
		case "Top":
		case "middle":
		case "Middle":
		case "bottom":
		case "Bottom":
			this.captionVerticalAlign = captionVerticalAlign;
			return true;
		//otherwise return false condition
		default:
			return false;
		}
	}

	public boolean getShared() {
		return shared;
	}

	public void setShared(boolean shared) {
		this.shared = shared;
	}

	// add rating method
	public boolean addRating(Rating NewRating) {
		//if ratings is not full by a null value in array 
		if (ratings.length == 10 && ratings[ratings.length - 1] == null) {
			//traverse array
			for (int x = 0; x < ratings.length; x++) {
				//when a null value is encountered add the new rating and return true
				if (ratings[x] == null) {
					ratings[x] = NewRating;
					return true;
				}
			}
		//otherwise if the array is full
		} else if (ratings.length == 10 && ratings[ratings.length - 1] != null) {
			//traverse array
			for (int y = 0; y < ratings.length - 1; y++) {
				//shift values up 1 subsequently deleting the first value
				ratings[y] = ratings[y + 1];
			}
			//last value is given the new value
			ratings[ratings.length - 1] = NewRating;
			return true;
		}
		//catch all false to indicate rating addition failed
		return false;
	}

	// calculate overall rating method
	
	// stored instance variables for upvotes and downvotes used in tostring
	private int upvote = 0;
	private int downvote = 0;
	
	public double calculateOverallRating() {
		//local variables
		double sum = 0.0;
		int count = 0;
		int rate = 0;

		//for loop to traverse ratings
		for (int i = 0; i < ratings.length; i++) {

			//if it is not null, print the score and add the score to the sum
			if (ratings[i] != null) {
				rate = ratings[i].getScore();
				sum += rate;

			//otherwise increase the counter
			} else {
				count++;
			}
		}
		//if empty ratings return 0.0
		if (count == ratings.length) {
			sum = 0.0;
		}
		//otherwise return sum
		return sum;
	}
	
	//methods to calculate upvotes and downvotes
	public int calculateDownvotes() {
		int downvotes = 0;
		//traverse array
		for (int i = 0; i<ratings.length; i++) {
			//if value is not null then count upvotes
			if (ratings[i] != null) {
				if (ratings[i].getScore() == -1)
					downvotes--;
		}
	}
		return downvotes;
}
	public int calculateUpvotes() {
		int upvotes = 0;
		//traverse array
		for (int i = 0; i<ratings.length; i++) {
			//if value is not null then count upvotes
			if (ratings[i] != null) {
				if (ratings[i].getScore() == 1)
					upvotes++;
		}
	}
		return upvotes;
}
	
	
	
	// to string
	@Override
	public String toString() {
		//returns the background image, caption, overall rating and number of upvotes and downvotes
		return backgroundImage + " '" + caption + "' " + calculateOverallRating() + " [+1: " + upvote + ", -1: "
				+ Math.abs(downvote) + "] - created by " + this.getCreator().getUserName();
	}


	@Override
	public boolean equals(Object obj) {
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
		//everything same true
		if (this.getClass() == obj.getClass() && ((Meme) obj).getCaption() == this.getCaption()
				&& ((Meme) obj).getCreator() == this.getCreator()
				&& ((Meme) obj).getBackgroundImage() == this.getBackgroundImage()) {
			return true;
		//catch all false
		} else {
			return false;
		}
	}
}
//completed Meme.java