import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.InputMismatchException;


/**
 * MemeMagic Graphical User Interface 
 * 
 * This class contains the graphical user interface for the Meme Magic Software
 * 
 * You will need to implement certain portions of this class, marked with comments starting with "TODO" to connect 
 * it with your existing code. 
 * 
 * This class provides an example layout for the GUI. You are encouraged to be creative in your design. 
 * More information about Swing is online at: 
 * https://docs.oracle.com/javase/tutorial/uiswing/components/componentlist.html.
 */
public class MemeMagic extends JFrame {

    /**
     * Serialization string required by extending JFrame
     */
    private static final long serialVersionUID = 1L;
    
    private User user;
    private GraphicalMeme currentMeme;
    
    private String backgroundImageFilename;

    private BorderLayout panelLayout;
    private JLabel backgroundImageFileNameLabel;
    private JLabel imageDisplayLabel;
    private JPanel controlPanel;
    private JPanel memeViewPanel;
    private JPanel panelPane;
    private JTextField memeCaptionInput;
    private JTextField backgroundImageDescInput; 
    private JTextField backgroundImageTitleInput;
    private JComboBox<String> vertAlignInput;
    private JLabel backgroundImageDesc;
    private JLabel backgroundImageTitle;
    private JLabel memeCaption;
    private JLabel vertAlignLabel;
    
    
    public MemeMagic() {
        this.user = new User();
    }
    
    public MemeMagic(User user) {
        this.user = user;
    }


    /**
     * Main method.  This method initializes a PhotoViewer, loads images into a PhotographContainer, then
     * initializes the Graphical User Interface.
     * 
     * @param args  Optional command-line arguments
     */
    public static void main(String[] args) {
        
        // Create a User object for this instance of Meme Magic
        User user = new User();

        // Instantiate the PhotoViewer Class
        MemeMagic myViewer = new MemeMagic(user);
        
        // Invoke and start the Graphical User Interface
        javax.swing.SwingUtilities.invokeLater(() -> myViewer.initialize());
    }

    /**
     * Initialize all the GUI components.  This method will be called by
     * SwingUtilities when the application is started.
     */
    private void initialize() {

        // Tell Java to exit the program when the window is closed
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Tell Java to title the window to Meme Magic
        this.setTitle("Meme Magic");

        // We will use border layout on the main panel, since it is much easier for organizing panels.
        panelLayout = new BorderLayout();
        panelPane = new JPanel(panelLayout);

        // Create a label to display the full image.
        imageDisplayLabel = new JLabel();
        imageDisplayLabel.setHorizontalAlignment(JLabel.CENTER);
        imageDisplayLabel.setPreferredSize(new Dimension(550, 550));

        // Create a panel on which to display the full image
        memeViewPanel = new JPanel(new BorderLayout());
        memeViewPanel.setPreferredSize(new Dimension(550, 550));
        memeViewPanel.add(imageDisplayLabel, BorderLayout.CENTER);


        // Create a panel on which to display the controls for building a Meme
        controlPanel = new JPanel(new BorderLayout());
        
        // Create a panel that holds BackgroundImage information and give it a title
        JPanel backgroundImagePanel = new JPanel(new BorderLayout());
        backgroundImagePanel.setBorder(BorderFactory.createTitledBorder("Background Image"));

        // Create a panel that provides input for the BackgroundImage fileName
        JPanel backgroundImageFilePanel = new JPanel();
        
        // Label
        JLabel backgroundImageFileLabel = new JLabel("Filename: ");
        backgroundImageFileLabel.setPreferredSize(new Dimension(100, 20));
        backgroundImageFilePanel.add(backgroundImageFileLabel);
        
        // Button
        JButton backgroundImageButton = new JButton("Browse");
        backgroundImageFilePanel.add(backgroundImageButton);
        backgroundImageButton.setPreferredSize(new Dimension(85, 20));
        // TODO The button needs a listener
        backgroundImageButton.addActionListener(new OpenButtonListener());
        // Label that will contain the filename of the image
        backgroundImageFileNameLabel = new JLabel("<choose>");
        backgroundImageFilePanel.add(backgroundImageFileNameLabel);
        backgroundImageFileNameLabel.setPreferredSize(new Dimension(265, 20));
        
        // Add the panel about the BackgroundImage fileName to the BackgroundImage information panel
        backgroundImagePanel.add(backgroundImageFilePanel, BorderLayout.NORTH);
     
     
        // TODO Complete the Control Panel implementation (with Background Image and Meme panels)
        
        //BACKGROUND IMAGE PANELS
        //Creating new JPanels to hold the title and description parts for the meme
        JPanel backgroundTitlePanel = new JPanel();
        JPanel descriptionPanel = new JPanel();
        //Jlabel for title and Jtextfield for the new input
        backgroundImageTitle = new JLabel("Title: ");
        backgroundImageTitleInput = new JTextField();
        //Jlabel for description and jtextfield  for new input
        backgroundImageDesc = new JLabel("Description: ");
        backgroundImageDescInput = new JTextField();
        //setting sizes of the jlabels and jtextfields
        backgroundImageTitle.setPreferredSize(new Dimension(100, 20));
        backgroundImageTitleInput.setPreferredSize(new Dimension(360, 20));
        backgroundImageDesc.setPreferredSize(new Dimension(100, 20));
        backgroundImageDescInput.setPreferredSize(new Dimension(360, 20));
        //adding label and textfield to the new panels
        backgroundTitlePanel.add(backgroundImageTitle); 
        backgroundTitlePanel.add(backgroundImageTitleInput);
        descriptionPanel.add(backgroundImageDesc);
        descriptionPanel.add(backgroundImageDescInput);
        //adding the title panel and the description panel to the main background panel
        backgroundImagePanel.add(backgroundTitlePanel, BorderLayout.CENTER);
        backgroundImagePanel.add(descriptionPanel, BorderLayout.SOUTH);
        
        //MEME PANELS
        //making the main meme panel in which the caption and vertical line will be added
        JPanel MainMemePanel = new JPanel(new BorderLayout());
        MainMemePanel.setBorder(BorderFactory.createTitledBorder("Meme"));
        //creating the sub JPanels for caption and vertical align
        JPanel memeCaptionPanel = new JPanel();
        JPanel memeVerticalAlignPanel = new JPanel();
        //creating the JLabels
        memeCaption = new JLabel("Caption: ");
        vertAlignLabel = new JLabel("Vertical Align: ");
        //creating the the Jtextfield
        memeCaptionInput = new JTextField();
        //creating the JComboBox
        String[] vertOptions = {"top","middle","bottom"};
        vertAlignInput = new JComboBox<String>(vertOptions);
        //setting the sizes of the JLabels, Jtextfield, and JComboBox
        memeCaption.setPreferredSize(new Dimension(100, 20));
        vertAlignLabel.setPreferredSize(new Dimension(100, 20));
        memeCaptionInput.setPreferredSize(new Dimension(360, 20));
        vertAlignInput.setPreferredSize(new Dimension(360, 20));
        //adding componenets to respective panels
        memeCaptionPanel.add(memeCaption);
        memeCaptionPanel.add(memeCaptionInput);
        memeVerticalAlignPanel.add(vertAlignLabel);
        memeVerticalAlignPanel.add(vertAlignInput);
        //adding the meme panel and the vertical align panel to the main meme panel
        MainMemePanel.add(memeCaptionPanel, BorderLayout.NORTH);
        MainMemePanel.add(memeVerticalAlignPanel, BorderLayout.CENTER);
        
        //Generate Button
        JButton generateImageButton = new JButton("Generate");
        generateImageButton.setPreferredSize(new Dimension(85, 20));
        generateImageButton.addActionListener(new GenerateButtonListener());
        JPanel generatePanel = new JPanel();
        generatePanel.add(generateImageButton);
        
        //Save Button
        JButton saveImageButton = new JButton("Save");
        saveImageButton.setPreferredSize(new Dimension(85, 20));
        saveImageButton.addActionListener(new SaveButtonListener());
        JPanel savePanel = new JPanel();
        savePanel.add(saveImageButton);
        
        //adding buttons to control panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(generatePanel);
        buttonPanel.add(savePanel);
        controlPanel.add(buttonPanel, BorderLayout.SOUTH);
        
        // Add the BackgroundImage information panel to the control panel
        controlPanel.add(backgroundImagePanel, BorderLayout.NORTH);
        //add the mainmeme panel to the control panel
        controlPanel.add(MainMemePanel, BorderLayout.CENTER);
        
        // Add all the panels to the main display based on BorderLayout
        controlPanel.setPreferredSize(new Dimension(500,570));
        panelPane.add(controlPanel, BorderLayout.WEST);
        panelPane.add(memeViewPanel, BorderLayout.CENTER);

        // Add the panelPane to the contentPane of the Frame (Window)
        this.getContentPane().add(panelPane);

        // Set the preferred size and show the main application window
        this.setPreferredSize(new Dimension(1150, 570));
        this.pack();
        this.setVisible(true);
    }
    
    
    /**
     * ActionListener for the open button.  When the button is pressed, this ActionListener
     * opens a FileChooser, asks the user to choose a JPG image file, then
     * sets the field backgroundImageFilename in the main class.
     */
    private class OpenButtonListener implements ActionListener {
        /**
         * Action performed operation.  Opens a save FileChooser, asks the user to choose a JPG image file, then
         * sets the field backgroundImageFilename in the main class.
         * 
         * @param evt The event that was performed
         */
        @Override
        public void actionPerformed(ActionEvent evt) {
            JFileChooser chooser2 = new JFileChooser();
            chooser2.setDialogTitle("Choose a Background Image");
            chooser2.setFileFilter(new FileNameExtensionFilter("JPEG Images", "jpg", "jpeg"));
            int returnVal = chooser2.showOpenDialog(null);
            if(returnVal == JFileChooser.APPROVE_OPTION) {
                backgroundImageFilename = chooser2.getSelectedFile().getAbsolutePath();
                backgroundImageFileNameLabel.setText(backgroundImageFilename);
            }

        }
    }
    
    //Action Listener for the Generate Button
    private class GenerateButtonListener implements ActionListener {
    	//overriding action performed method
    	@Override
    	public void actionPerformed(ActionEvent evt) {
    		//implementation for creating meme with the data given
    		BackgroundImage userMemeBKimg = new BackgroundImage(backgroundImageFileNameLabel.getText(), backgroundImageTitleInput.getText(), backgroundImageDescInput.getText());
    		currentMeme = new GraphicalMeme(userMemeBKimg, memeCaptionInput.getText(), user);
    		currentMeme.setCaptionVerticalAlign((String) vertAlignInput.getSelectedItem());
    	
    		//try catch block for exceptions when compiling meme
    		try {
    			//displaying meme on image display label
				imageDisplayLabel.setIcon(new ImageIcon(currentMeme.compileMeme()));
			//catch the exceptions
			} catch (IOException ie) {
				System.out.println("IO Exception has occurred " + ie.getMessage());
			} catch (InputMismatchException ime) {
				System.out.println("Input Mismatch Exception has occurred " + ime.getMessage());
			} catch (RuntimeException re) {
				System.out.println("Runtime exception has occurred " + re.getMessage());
			} catch (Exception e) {
				System.out.println("unknown exception has occurred " + e.getMessage());
			}
    		//refreshing the meme view panel
    		memeViewPanel.repaint();
    		}
    }
    
    
    
    
    
    /**
     * ActionListener for the save button.  When the button is pressed, this ActionListener
     * opens a save FileChooser, asks the user to choose a location and filename, then
     * writes the graphical meme data to a PNG image file.
     */
    private class SaveButtonListener implements ActionListener {
        /**
         * Action performed operation.  Opens a save FileChooser, asks the user to choose
         * a location and filename, then writes the graphical meme data to a PNG file.
         * 
         * @param evt The event that was performed
         */
        @Override
        public void actionPerformed(ActionEvent evt) {
            JFileChooser chooser2 = new JFileChooser();
            chooser2.setDialogTitle("Save Meme");
            chooser2.setFileFilter(new FileNameExtensionFilter("PNG Images", "png"));
            int returnVal = chooser2.showSaveDialog(null);
            if(returnVal == JFileChooser.APPROVE_OPTION) {
                String destinationFile = chooser2.getSelectedFile().getAbsolutePath();
                if (!destinationFile.contains(".png"))
                    destinationFile += ".png";
                
                // TODO: Writing an image throws a checked exception that must be handled.
                //       Catch the exceptions and provide the user with an appropriate message
                try {
					ImageIO.write(currentMeme.compileMeme(), "png", new File(destinationFile));
            	} catch (IOException ie) {
    				System.out.println("IO Exception has occurred " + ie.getMessage());
    			} catch (InputMismatchException ime) {
    				System.out.println("Input Mismatch Exception has occurred " + ime.getMessage());
    			} catch (RuntimeException re) {
    				System.out.println("Runtime exception has occurred " + re.getMessage());
    			} catch (Exception e) {
    				System.out.println("unknown exception has occurred " + e.getMessage());
    			}
            }

        }
    }
}