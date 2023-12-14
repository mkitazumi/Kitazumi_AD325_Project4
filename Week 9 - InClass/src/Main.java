import java.util.Iterator;
import java.util.Scanner;
/**
 * Ashtin Rivada, Mika Kitazumi
 * 12/2023
 * AD325: Project 4: Social Media Network
 * This code is meant to help our understanding of abstract data types (lists, heaps
 * queues etc) and graphs. We are to make a rudementry social media network that makes use of graphs and lists
 * to store information. This allows users to join, modify, add and display friends, delete profiles, and see friends
 * of friends.
 * main class for social media network assignmet
 */

public class Main {

    public static void main(String[] args) {
        //test cases
        Profile me = new Profile("Ashtin");
        Profile Mika = new Profile("Mika");
        Profile Janna = new Profile("Janna");
        Profile Kona = new Profile("Kona");
        Profile Taro = new Profile("Taro");
        ProfileManager manager = new ProfileManager(me);
        manager.addProfile(Mika);
        manager.addProfile(Janna);
        manager.addProfile(Kona);
        manager.addProfile(Taro);
        me.addFriend(Janna);
        me.addFriend(Mika);
        manager.connect(me);
        Mika.addFriend(Kona);
        Mika.addFriend(Taro);

        //manager.Display();
        manager.DisplayBFS();
//----------------------------------------------------------------------------------------------------------------------
        //asking user to make pf
        Scanner myObj = new Scanner(System.in);
        System.out.print("1.Join the network\n" +
                "2.Modify the profile\n" +
                "3.Display all the profiles (add friend from displayed list)\n" +
                "4.Add a friend\n" +
                "5.See list of current user's friends\n" +
                "6.Delete a profile\n" +
                "7.See a list of the current user's friends friends\n" +
                "Or enter any other number to exit\n" +
                "Please enter a number: ");

        int number = myObj.nextInt(); //code breaks if i take this out so it stays
        while(number > 0 && number < 8){
            switch (number){
                /**
                 * joining network
                 * user creates a profile and will then be the one modified and is the
                 * current user profile. Name only
                 *
                 * @param name, the name of the new profile created
                 *
                 * runtime is O(n) where n is the number of profiles
                 */

                case 1:
                    System.out.print("Please enter name: ");
                    String name = myObj.next();

                    // Check if a profile with the same name already exists
                    boolean profileExists = false;

                    Iterator<Profile> iterator = manager.getNetwork().vertices.getKeyIterator();
                    while (iterator.hasNext()) {
                        Profile profile = iterator.next();
                        if (profile.getName().equalsIgnoreCase(name)) {
                            profileExists = true;
                            break;
                        }
                    }

                    if (profileExists) {
                        System.out.println("Error: Profile with the name '" + name + "' already exists. Please choose a different name.");
                    } else {
                        // If the profile does not exist, create and add it
                        Profile newProf = new Profile(name);
                        manager.addProfile(newProf);
                        manager.getNetwork().addVertex(newProf);

                        // Set the new profile as the current user ("me")
                        me = newProf;
                        System.out.println("Welcome, " + me.getName() + "!");
                    }
                    System.out.print(" \n"+
                            "please enter another number from the main menu: ");
                    number = myObj.nextInt();
                    break;
                /**
                 * case 2: modifying the profile
                 * the current user can modify their own name, picture, and status. uses a switch statment.
                 *
                 * @param modificationChoice, the users choice for modification
                 *
                 * O(1), only involves one profile
                 */

                case 2:
                    // Modify the profile
                    // Initialize the Scanner here. I dont know why but unless i initalize it here the code breaks
                    //tried different ways to fix it but unless its a new on then it dosent work
                    //so theres going to be lots of new redundant scanners.
                    Scanner scanner = new Scanner(System.in);
                    System.out.print(" \n" + "Choose what to modify:\n" +
                            "1. Name\n" +
                            "2. Picture\n" +
                            "3. Status\n" +
                            "please enter a number: ");

                    int modificationChoice = 0;

                    try {
                        modificationChoice = scanner.nextInt();
                    } catch (java.util.InputMismatchException e) {
                        System.out.println("Invalid input. Please enter a number.");
                        scanner.nextLine(); // Consume the invalid input
                        break;
                    }

                    scanner.nextLine(); // Consume the newline character

                    switch (modificationChoice) {
                        case 1:
                            System.out.print("Enter new name: ");
                            String updatedName = scanner.nextLine();
                            me.setName(updatedName);
                            System.out.println("Name updated to: " + updatedName);
                            break;

                        case 2:
                            System.out.print("Enter new picture URL: ");
                            String updatedPictureUrl = scanner.nextLine();
                            me.setPictureUrl(updatedPictureUrl);
                            System.out.println("Picture URL updated to: " + updatedPictureUrl);
                            break;

                        case 3:
                            System.out.print("Enter new status: ");
                            String updatedStatus = scanner.nextLine();
                            me.setStatus(updatedStatus);
                            System.out.println("Status updated to: " + updatedStatus);
                            break;

                        default:
                            System.out.println("Invalid choice for modification.");
                    }

                    // Close the scanner to prevent resource leaks
                    System.out.print(" \n"+
                            "please enter another number from the main menu: ");
                    number = myObj.nextInt();
                    break;

                /**
                 * displaying all profiles
                 * prints details of all profiles in a list form
                 *
                 * O(n) where n is the number of profiles
                 */

                case 3:
                    manager.Display();

                    System.out.print(" \n"+
                            "please enter another number from the main menu: ");
                    number = myObj.nextInt();
                    break;
                /**
                 * adding friends
                 * allows user to add friends to their profile. throwing a error if they dont exist
                 * @param name, name of existing person being added
                 *
                 * O(n) where n is the number of profiles
                 */

                case 4:
                    // Add a friend
                    Scanner scan = new Scanner(System.in);
                    System.out.print("Enter the name of the friend you want to add: ");
                    String friendName = scan.nextLine();

                    boolean friendAdded = false;
                    Iterator<Profile> iterator4 = manager.getNetwork().vertices.getKeyIterator();

                    while (iterator4.hasNext()) {
                        Profile profile = iterator4.next();
                        if (profile.getName().equalsIgnoreCase(friendName)) {
                            me.addFriend(profile);
                            System.out.println("Friend added: " + friendName);
                            friendAdded = true;
                            break;
                        }
                    }

                    if (!friendAdded) {
                        System.out.println("No profile found with the name: " + friendName);
                    }

                    System.out.print(" \n"+
                            "please enter another number from the main menu: ");
                    number = myObj.nextInt();
                    break;
                /**
                 * seeing current users friends
                 * display all friends of current users friends
                 *
                 * O(1) runtime because we only deal with the one profile
                 */

                case 5:
                    System.out.println("Your Friends:");
                    for (Profile friend : me.getFriends()) {
                        System.out.println("- " + friend.getName());
                    }

                    System.out.print(" \n"+
                            "please enter another number from the main menu: ");
                    number = myObj.nextInt();
                    break;
                /**
                 * deletes profiles
                 * lets user delete a profile from the network. The profile has to exist. if it exist then
                 * it will be removed
                 * @param profileToDelete the name of the profile to be deleted
                 *
                 * O(n) where n is the number of profiles
                 */

                case 6:
                    // Delete a profile
                    Scanner scan6 = new Scanner(System.in);
                    System.out.print("Enter the name of the profile you want to delete: ");
                    String profileToDelete = scan6.nextLine();

                    Profile profileToRemove = null;
                    Iterator<Profile> iterator6 = manager.getNetwork().vertices.getKeyIterator();

                    while (iterator6.hasNext()) {
                        Profile profile = iterator6.next();
                        if (profile.getName().equalsIgnoreCase(profileToDelete)) {
                            profileToRemove = profile;
                            break;
                        }
                    }

                    if (profileToRemove != null) {
                        // Remove the profile from the network
                        manager.removeProfile(profileToRemove);
                        System.out.println("Profile deleted: " + profileToDelete);
                    } else {
                        System.out.println("No profile found with the name: " + profileToDelete);
                    }

                    System.out.print(" \n"+
                            "please enter another number from the main menu: ");
                    number = myObj.nextInt();
                    break;
                /**
                 * sees the list of the current users friend's friend's
                 * prints out the friends of friends
                 *
                 * runtime O(m) where m is the number of friend and the friend's of friend's
                 */

                case 7:
                    System.out.println("Friends of Friends:");

                    for (Profile friend : me.getFriends()) {
                        System.out.println("Friends of " + friend.getName() + ":");

                        for (Profile friendOfFriend : friend.getFriends()) {
                            // Exclude the current user from the list
                            if (!friendOfFriend.equals(me)) {
                                System.out.println("- " + friendOfFriend.getName());
                            }
                        }

                        System.out.println();
                    }

                    System.out.print(" \n"+
                            "please enter another number from the main menu: ");
                    number = myObj.nextInt();
                    break;

            }//switch
        }//while
        System.out.println("thank you :>. Exiting");



    }
}
