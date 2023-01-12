//Varun Pavuloori
//CS 2110

//import arraylist
import java.util.ArrayList;

public class Feed {

	//instance variable
	private ArrayList<Meme> memes;
	
	//default constructor
	public Feed() {
		memes = new ArrayList<Meme>();
	}

	//getter and setter for instance variable
	public ArrayList<Meme> getMemes() {
		return memes;
	}

	public void setMemes(ArrayList<Meme> memes) {
		this.memes = memes;
	}
	
	//get new meme method
	public Meme getNewMeme(User user) {
	if (memes.equals(null))
		return null;
		
	//traverse the feed
	for (int i=0; i<memes.size(); i++) {
		if (!user.getMemesCreated().contains(memes.get(i)) && !user.getMemesViewed().contains(memes.get(i))){
			return memes.get(i);
		}
	}
	
	//catch all false
	return null;
	}
	
	//tostring method
	@Override public String toString() {
		String returnValue = "";
		for(int x=0; x<memes.size(); x++) {
			returnValue = returnValue.concat(memes.get(x).toString() + "\n");
		}
		return returnValue;
	}
	
}
//Completed Feed.java
