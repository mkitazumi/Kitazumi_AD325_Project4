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
    private List<Profile> friends;

    /**
     *contructors
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

    /**s
     *getter
     */
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Profile> getFriends() {
        return friends;
    }

    public void addFriend(Profile friend) {
        this.friends.add(friend);
        friend.addFriend(this); // Add this profile as a friend to the other profile (bi-directional friendship)
    }

    public void removeFriend(Profile friend) {
        this.friends.remove(friend);
        friend.getFriends().remove(this); // Remove this profile as a friend from the other profile
    }

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
