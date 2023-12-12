import GraphPackage.UndirectedGraph;

public class ProfileManager {
   private UndirectedGraph<Profile> network;
   private Profile origin;

   public ProfileManager(Profile origin){
      network = new UndirectedGraph<>();
      this.origin = origin;

   }

   /**
    * Adds a Profile to the Graph
    * @param profile profile wanting to be added
    */
   public void addProfile(Profile profile){
      if (network.isEmpty()){
         origin = profile;
         network.addVertex(origin);

      }
      network.addVertex(profile);
   }

   public void connect(Profile vertex1, Profile vertex2){
      network.addEdge(vertex1, vertex2);
   }

   public void Display(){
   }

   public void DisplayBFS(){

   }

   public Profile removeProfile(Profile profile){
      Profile removedProfile = null;
      return removedProfile;
   }



}
