import java.util.Iterator;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
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

        int number = myObj.nextInt();
        while(number > 0 && number < 8){
            switch (number){
                //joining network, making pf. Name only------------------------------------------------
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
                //modifying pf. 1 will be changing name, 2 will be profile picture
                //3 will be status. This will be done with another switch--------------------------------
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

                //display all profiles-------------------------------------------------------------------------------
                case 3:
                    manager.Display();

                    System.out.print(" \n"+
                            "please enter another number from the main menu: ");
                    number = myObj.nextInt();
                    break;
                //adding friend----------------------------------------------------------------------------------------
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
                //see current friends-----------------------------------------------------------------------------------
                case 5:
                    System.out.println("Your Friends:");
                    for (Profile friend : me.getFriends()) {
                        System.out.println("- " + friend.getName());
                    }

                    System.out.print(" \n"+
                            "please enter another number from the main menu: ");
                    number = myObj.nextInt();
                    break;
                //delete profile----------------------------------------------------------------------------------------
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
                //see friends of friends-------------------------------------------------------------------------------
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
