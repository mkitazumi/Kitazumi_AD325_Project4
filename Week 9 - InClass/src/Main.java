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

        //trying to add people
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
                //joining network, making pf. Name only
                case 1:
                    System.out.print("please enter name: ");
                    String name = myObj.nextLine();
                    //n
                    break;
                //modifying pf
                case 2:
                    System.out.print("");
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
