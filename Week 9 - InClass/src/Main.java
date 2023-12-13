import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Profile me = new Profile("Ashtin");
        Profile Mika = new Profile("Mika");
        Profile Janna = new Profile("Janna");
        ProfileManager manager = new ProfileManager(me);
        manager.addProfile(Mika);
        manager.addProfile(Janna);
        me.addFriend(Janna);
        me.addFriend(Mika);
        manager.connect(me);

        //manager.Display();
        manager.DisplayBFS();
//----------------------------------------------------------------------------------------------------------------------
        //asking user to make pf
        Scanner myObj = new Scanner(System.in);
        System.out.print("1.Join the network\n" +
                "2.Modify the profile\n" +
                "3.Display all the profiles (add friend from displayed list)\n" +
                "4.Ensure another profile can be added, not just the initial one\n" +
                "5.Add a friend\n" +
                "6.See list of current user's friends\n" +
                "7.Delete a profile\n" +
                "8.See a list of the current user's friends friends\n" +
                "Please enter a number: ");

        int number = myObj.nextInt();
        while(number>=1 && number <= 8){
            switch (number){
                //joining network, making pf. Name only-------------------------------------------
                case 1:
                    System.out.print("please enter name: ");
                    String name = myObj.next();
                    Profile newProf = new Profile(name);
                    manager.addProfile(newProf);
                    manager.getNetwork().addVertex(newProf);

                    System.out.print(" \n"+
                            "please enter another number from the main menu: ");
                    number = myObj.nextInt();
                    break;
                //modifying pf. 1 will be changing name, 2 will be profile picture
                //3 will be status. This will be done with another switch---------------------
                case 2:
                    // Modify the profile
                    Scanner scanner = new Scanner(System.in); // Initialize the Scanner here
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

                //display all pf
                case 3:
                    break;
                //adding another pf
                case 4:
                    break;
                //adding friend
                case 5:
                    break;
                //see list of friends
                case 6:
                    break;
                //delete pf
                case 7:
                    break;
                //see users friend's friends
                case 8:
                    break;
            }//switch
        }//while
        System.out.println("thank you :>");



    }
}
