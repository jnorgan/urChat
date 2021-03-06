package urChatBasic.frontend;

import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.text.StyledDocument;

import urChatBasic.base.Constants;

public class IRCPrivate extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7861645386733494089L;
	  ////////////////
	 //GUI ELEMENTS//
	////////////////
	//Icons
	public ImageIcon icon;
	//Private Properties

	private JTextPane privateTextArea = new JTextPane();
	private JScrollPane privateTextScroll = new JScrollPane(privateTextArea);
	public JTextField privateTextBox = new JTextField();
	private String name; 
	
	private UserGUI gui = DriverGUI.gui;
	private IRCServer myServer;
	
	
	public IRCPrivate(IRCServer serverName,IRCUser user){
		this.myServer = serverName;
		this.setLayout(new BorderLayout());
		this.add(privateTextScroll, BorderLayout.CENTER);
		this.add(privateTextBox, BorderLayout.PAGE_END);
		privateTextBox.addActionListener(new sendPrivateText());
		privateTextArea.setEditable(false);
		privateTextArea.setFont(gui.getFont());
		setName(user.getName());
		
		Image tempIcon = null;
		try {
			tempIcon = ImageIO.read(new File(Constants.RESOURCES_DIR+"User.png"));
		} catch (IOException e) {
			Constants.LOGGER.log(Level.SEVERE, "FAILED to load User.png! " + e.getLocalizedMessage());
		} 
		icon = new ImageIcon(tempIcon);
	}
	@Override
	public void setName(String userName){	
		this.name = userName;
	}
	
	@Override
	public String getName(){
		return this.name;
	}

	private class sendPrivateText implements ActionListener
	   {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(!privateTextBox.getText().trim().isEmpty()){
					 String messagePrefix = "";
					if(!privateTextBox.getText().startsWith("/"))
						messagePrefix = "/msg "+getName()+" ";
				myServer.sendClientText(messagePrefix+privateTextBox.getText(),getName());
				}
				privateTextBox.setText("");
			}
	   }
	
	public void printText(Boolean dateTime, String fromUser, String line){
		StyledDocument doc = (StyledDocument) privateTextArea.getDocument();

		DateFormat chatDateFormat = new SimpleDateFormat("HHmm");
		Date chatDate = new Date();
		String timeLine = "";

		if(dateTime)
			timeLine = "["+chatDateFormat.format(chatDate)+"]";
	    	new LineFormatter(gui.getFont(),myServer.getNick()).formattedDocument(doc,timeLine,fromUser,line);

	    privateTextArea.setCaretPosition(privateTextArea.getDocument().getLength());
	}
	

	public String getServer() {
		return myServer.getName();
	}
	
}
