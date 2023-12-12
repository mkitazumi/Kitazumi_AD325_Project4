public class Main {
    public static void main(String[] args) {
        Profile me = new Profile("Ashtin");
        ProfileManager manager = new ProfileManager(me);
        manager.addProfile(me);
        System.out.println();
    }
}
