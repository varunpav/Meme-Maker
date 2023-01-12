//Varun Pavuloori
//CS 2110

public class BackgroundImage implements Comparable<BackgroundImage>{

	// instance variables
	private String imageFileName;
	private String title;
	private String description;

	//implementing the comparable interface
	@Override public int compareTo(BackgroundImage bckgCompare) {
		//if either is null return null
		if (this.equals(null) || bckgCompare.equals(null))
			return 0;
		
		
		int returnVal = this.getImageFileName().compareTo(bckgCompare.getImageFileName());
		if (returnVal==0) {
			returnVal = this.getTitle().compareTo(bckgCompare.getTitle());
				if (returnVal==0) {
					returnVal = this.getDescription().compareTo(bckgCompare.getDescription());
				}
		}
		return returnVal;	
	}
	
	
	// default constructor
	public BackgroundImage() {
		//setting instance variables to blank values
		imageFileName = "";
		title = "";
		description = "";
	}

	// overloaded constructor
	public BackgroundImage(String FileName, String title, String desc) {
		//setting instance variables to given parameters
		imageFileName = FileName;
		this.title = title;
		description = desc;
	}

	// getter and setters for instance variables
	public String getImageFileName() {
		return imageFileName;
	}

	public void setImageFileName(String imageFileName) {
		this.imageFileName = imageFileName;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	// to string
	@Override
	public String toString() {
		//returns title with description
		return title + " <" + description + ">";
	}

	// equals
	@Override
	public boolean equals(Object obj) {
		// checks if obj is a background image and its paramaters are equal
		// background image object for test purposes
		
		//same obj true
		if (this == obj) {
			return true;
		}
		//either obj or compare is null false
		if(obj == null || this == null) {
			return false;
		}
		//if different class false
		if (getClass() != obj.getClass()) {
			return false;
		}
		//if everything is the same return true
		if (getClass() == obj.getClass() && this.title.equals(((BackgroundImage) obj).getTitle())
				&& this.description.equals(((BackgroundImage) obj).getDescription())
				&& this.imageFileName.equals(((BackgroundImage) obj).getImageFileName())) {
			return true;
		//catch all false 
		} else
			return false;
	}
}
//completed BackgroundImage.java