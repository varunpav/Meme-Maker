import java.util.Comparator;

//varun pavuloori

public class CompareMemeByCreator implements Comparator<Meme>{

	@Override public int compare(Meme A, Meme B) {
		int returnVal = 0;
		
		//compare memes by creator using user compare to
		returnVal = A.getCreator().compareTo(B.getCreator());
		//if they are the same keep going
		if (returnVal==0) {
			//compare memes by rating using double wrapper class
			returnVal = -Double.compare(A.calculateOverallRating(), B.calculateOverallRating());
			//if they are the same keep going
			if (returnVal==0) {
				//compare memes by caption using string compareTo
				returnVal = A.getCaption().compareTo(B.getCaption());
				//if they are the same keep going
				if (returnVal==0) {
					//compare memes by background image
					returnVal = A.getBackgroundImage().compareTo(B.getBackgroundImage());
					//if they are teh same keep going
					if (returnVal==0) {
						//compare memes by their shared status and use the Boolean wrapper
						returnVal = -Boolean.compare(A.getShared(), B.getShared());
						//if they are the same return 0
						if (returnVal==0) {
							return 0;
						}
					}
				}
			}
		}
		return returnVal;
	}
}

	