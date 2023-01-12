import java.util.Comparator;

//varun pavuloori

public class CompareMemeByRating implements Comparator<Meme>{

	@Override public int compare(Meme A, Meme B) {
		int returnVal = 0;
		
		//compare the memes by overall rating using the double wrapper class
		returnVal = -Double.compare(A.calculateOverallRating(), B.calculateOverallRating());
		//if they are the same keep going
		if(returnVal==0) {
			//compare memes by their captions using string compare to
			returnVal = A.getCaption().compareTo(B.getCaption());
			//if they are the same keep going
			if(returnVal==0) {
				//compare memes by their background image using Background image compare to
				returnVal = A.getBackgroundImage().compareTo(B.getBackgroundImage());
				//if they are the same keep going
				if(returnVal==0) {
					//compare memes by the user compare To
					returnVal = A.getCreator().compareTo(B.getCreator());
					//if they are the same return 0
					if(returnVal==0) {
						return 0;
					}
				}
			}
		}
		return returnVal;
	}
}

	