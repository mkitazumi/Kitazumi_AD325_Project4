import java.util.ArrayList;

/**
 * Profile should be able to:
 * Create a profile with a picture (optional), name, status and a list of friendProfiles.
 * Feel free to add whatever you like here including location, relationship status, age, occupation - whatever you want!
 * Retrieve the profile picture (optional)
 * Set and get the name of the profile
 * Set and get the status
 * Add a friend
 * Print out all the details of the profile including the list of friends
 */
public class Profile {
    /**
     *
     */
    private String name;
    private String pictureUrl; // Optional
    private String status;
    private ArrayList<Profile> friends;

    /**
     *contructors to create a profile
     */
    public Profile(String name) {
        this.name = name;
        this.pictureUrl = null; // Default to no picture
        this.status = ""; // Default to empty status
        this.friends = new ArrayList<>();
    }

    /**
     *more conctructors
     */
    public Profile(String name, String pictureUrl) {
        this.name = name;
        this.pictureUrl = pictureUrl;
        this.status = "";
        this.friends = new ArrayList<>();
    }

    /**
     * Mehtod to get name of profile
     * @return name of profile
     */
    public String getName() {
        return name;
    }

    /**
     * Method to get pictureURL of profile
     * @return picture's Url
     */

    public String getPictureUrl() {
        return pictureUrl;
    }

    /**
     * Method to get status of the profile
     * @return status of profile
     */

    public String getStatus() {
        return status;
    }

    /**
     * Method to get the ArrayList of friends for the profile
     * @return ArrayList of friends
     */

    public ArrayList<Profile> getFriends() {
        return friends;
    }

    /**
     * Method to set the pictureUrl for the profile
     * @param pictureUrl picture's URL
     */

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    /**
     * Method to set name for a profile
     * @param name name of profile
     */

    public void setName(String name) {
        this.name = name;
    }

    /**
     * Method to set Status of profile
     * @param status status of profile
     */

    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Method to add a friend
     * @param friend friend wanting to be added
     */

    public void addFriend(Profile friend) {
        if(friends.contains(friend)){
            return;
        }
        this.friends.add(friend);
        friend.addFriend(this);
        // Add this profile as a friend to the other profile (bi-directional friendship)
    }

    /**
     * Method to remove a friend
     * @param friend friend wanting to be removed
     */

    public void removeFriend(Profile friend) {
        if(!friends.contains(friend)){
            return;
        }
        this.friends.remove(friend);
        friend.getFriends().remove(this); // Remove this profile as a friend from the other profile
    }

    /**
     * method to make a profile print its data as a string
     * @return String representing profile data
     */

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Name: " + name + "\n");
        if (pictureUrl != null) {
            sb.append("Picture URL: " + pictureUrl + "\n");
        }
        sb.append("Status: " + status + "\n");
        sb.append("Friends: \n");
        for (Profile friend : friends) {
            sb.append("  - " + friend.getName() + "\n");
        }
        return sb.toString();
    }
}
