package urChatBasic;

import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

public class IRCPrivate extends JPanel implements Runnable {
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

	//Private Text Area
	private JTextPane privateTextArea = new JTextPane();
	private JScrollPane privateTextScroll = new JScrollPane(privateTextArea);
	public JTextField privateTextBox = new JTextField();
	private String name; 
	
	public IRCPrivate(IRCUser user){
		this.setLayout(new BorderLayout());
		this.add(privateTextScroll, BorderLayout.CENTER);
		this.add(privateTextBox, BorderLayout.PAGE_END);
		privateTextBox.addActionListener(new sendPrivateText());
		setName(user.getName());
		
		Image tempIcon = null;
		try {
			tempIcon = ImageIO.read(new File("User.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		icon = new ImageIcon(tempIcon);
	}
	
	public void setName(String userName){	
		this.name = userName;
	}
	
	public String getName(){
		return this.name;
	}

	private class sendPrivateText implements ActionListener
	   {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					if(!privateTextBox.getText().trim().isEmpty()){
						 String messagePrefix = "";
						if(!privateTextBox.getText().startsWith("/"))
							messagePrefix = "/msg "+getName()+" ";
					Connection.sendClientText(messagePrefix+privateTextBox.getText(),getName());
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				privateTextBox.setText("");
			}
	   }
	
	public void printText(String line){
		StyledDocument doc = (StyledDocument) privateTextArea.getDocument();
		Style style = doc.addStyle("StyleName", null);
	
	    StyleConstants.setItalic(style, true);
	
	    try {
			doc.insertString(doc.getLength(), line+"\n", style);
		} catch (BadLocationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	    privateTextArea.setCaretPosition(privateTextArea.getDocument().getLength());
	}
	
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
	
}