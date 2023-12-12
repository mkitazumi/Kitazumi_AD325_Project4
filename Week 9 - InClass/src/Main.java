public class Main {
    public static void main(String[] args) {
        Profile me = new Profile("Ashtin");
        Profile Mika = new Profile("Mika");
        Profile Janna = new Profile("Janna");
        ProfileManager manager = new ProfileManager(me);
        manager.addProfile(Mika);
        manager.addProfile(Janna);
        me.addFriend(Janna);
        manager.connect(me);

        //manager.Display();
        manager.DisplayBFS();
    }
}
