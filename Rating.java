//Varun Pavuloori
//CS 2110

public class Rating {

	//instance variables
	private int score;
	private User User;
	
	//default constructor
	public Rating(){
		//setting to a blank or default constructered value
		score = 0;
		User = new User();
	}

	//overloaded constructor
	public Rating(User User,int score) {
		//setting instance variables to values decided by parameters
		setScore(score);
		this.User = User;
	}
	//generate getters and setters
	public int getScore() {
		return score;
	}
	
	//identifying if value is acceptable
	public boolean setScore(int score) {
		switch (score) {
		//make sure the score is acceptable and return true
		case 1:
		case -1:
		case 0:
			this.score = score;
			return true;
		//catch all false
		default:
			return false;
		}
	}

	public User getUser() {
		return User;
	}

	public void setUser(User user) {
		User = user;
	}
	
	//to string method
	@Override public String toString() {
		//identifies what the rating was depending on the score
		String vote = "";
		if (this.getScore()==1) 
			vote = "n upvote";
		else if (this.getScore()==-1) 
			vote = " downvote";
		else
			vote = " pass";
		
		return this.getUser().getUserName() + " rated as a" + vote;
	}
	
	//equals method
	@Override public boolean equals(Object obj) {
		//same obj true
		if (this == obj) {
			return true;
			}
		//null obj false
		if (obj == null) {
			return false;
			}
		//diff class false
		if (getClass() != obj.getClass()) {
			return false;
			}
		//everything same true
		if (getClass()==obj.getClass() && this.score==((Rating) obj).getScore() 
					&& this.User == ((Rating) obj).getUser()) {
			return true;
			}
		//catch all false
		else
			return false;
	}
}
//completed Rating.java