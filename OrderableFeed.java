import java.util.Collections;

//Varun Pavuloori

public class OrderableFeed extends Feed{
	
	public void sortByCaption() {
	Collections.sort(this.getMemes());
	}
	
	public void sortByRating() {
	Collections.sort(this.getMemes(), new CompareMemeByRating());
	}

	public void sortByCreator() {
	Collections.sort(this.getMemes(), new CompareMemeByCreator());
	}

}
