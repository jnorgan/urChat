package urChatBasic.base;

import urChatBasic.frontend.IRCChannel;
import urChatBasic.frontend.IRCPrivate;
import urChatBasic.frontend.IRCUser;

public interface IRCServerBase {

	public abstract String getNick();

	/**
	 * Saves all the information from the text boxes to the connection
	 * 
	 */
	public abstract void serverConnect(String nick, String login,String portNumber, Class connection);

	public abstract String toString();

	public abstract void setName(String serverName);

	public abstract String getName();

	/**
	 * Check to see if there are any channels at all.
	 * @param channelName
	 * @return IRCChannel
	 */
	public abstract Boolean isCreatedChannelsEmpty();

	/**
	 * Get the IRCUser object from the userName - if 
	 * the IRCUser isn't found, then create it.
	 * @param userName
	 * @return
	 */
	public abstract IRCUser getIRCUser(String userName);

	/**
	 * Return the appropriate created server
	 * @param serverName
	 * @return IRCServer
	 */
	public abstract IRCPrivate getCreatedPrivateRoom(String privateRoom);

	/**
	 * Closes and removes all channels that have been created.
	 */
	public abstract void quitChannels();

	/**
	 * Closes and removes all channels that have been created.
	 */
	public abstract void quitChannel(String channelName);

	/**
	 * Closes and removes all private rooms that have been created.
	 */
	public abstract void quitPrivateRooms();

	/**
	 * Closes and removes a selected private room that have been created.
	 */
	public abstract void quitPrivateRooms(String roomName);

	/**
	 * Return the appropriate created channel
	 * @param channelName
	 * @return IRCChannel
	 */
	public abstract IRCChannel getCreatedChannel(String channelName);

	/**
	 * Creates a new channel based on name
	 * @param channelName
	 */
	public abstract void addToCreatedChannels(String channelName);

	/**
	 * Creates a new Private Room based on name
	 * @param serverName
	 */
	public abstract void addToPrivateRooms(String privateRoom);

	/**
	 * Creates a new Private Room based on IRCUser
	 * @param serverName
	 */
	public abstract void addToPrivateRooms(IRCUser privateRoom);

	/**
	 * Prints the text to the appropriate channels main text window.
	 * @param channelName
	 * @param line
	 */
	public abstract void printChannelText(String channelName, String line,
			String fromUser);

	/**
	 * Prints the text to the appropriate channels main text window. Checks the user
	 * exists first and if they are muted else if they don't exist then just create it
	 * and print the private text.
	 * @param channelName
	 * @param line
	 */
	public abstract void printPrivateText(String userName, String line,
			String fromUser);

	public abstract void printServerText(String line);

	public abstract void printEventTicker(String channelName, String eventText);

	//Adds users to the list in the users array[]
	public abstract void addToUsersList(String channelName, String[] users);

	//Adds a single user, good for when a user joins the channel
	public abstract void addToUsersList(String channelName, String user);

	/**
	 * Removes a single user from the specified channel. If the call is from "Server"
	 * as the channelName it will loop through all createdChannels and remove the user.
	 * But only if they were in there to begin with.
	 * @param channelName
	 * @param user
	 */
	public abstract void removeFromUsersList(String channelName, String user);

	public abstract void sendClientText(String line, String source);

	public abstract void doLimitLines();

	public abstract void printText(Boolean dateTime, String line);

	public abstract String getChannelTopic(String channelName);

	public abstract void setChannelTopic(String channelName, String channelTopic);

	/**
	 * This is a forwarding method used to direct the call to the IRCChannel,
	 * filters through 
	 * @param channelName
	 * @param user
	 * @param newUser
	 */
	public abstract void renameUser(String oldUserName, String newUserName);

	public abstract String getServer();

}