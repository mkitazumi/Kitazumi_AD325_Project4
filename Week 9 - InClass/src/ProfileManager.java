import GraphPackage.UndirectedGraph;

import java.util.ArrayList;

public class ProfileManager {
   private UndirectedGraph<Profile> network;
   private Profile origin;

   public ProfileManager(Profile origin){
      network = new UndirectedGraph<>();
      this.origin = origin;
      network.addVertex(origin);

   }

   public UndirectedGraph<Profile> getNetwork() {
      return network;
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

   public void connect(Profile vertex1){
      ArrayList<Profile> friends = vertex1.getFriends();
      for(Profile friend : friends){
         network.addEdge(vertex1, friend);
      }
   }

   public void Display() {
      System.out.println("----- Network Profiles -----");

      for (Profile profile : network) {
         System.out.println("Profile Name: " + profile.getName());
         if (profile.getPictureUrl() != null) {
            System.out.println("Picture URL: " + profile.getPictureUrl());
         }
         System.out.println("Status: " + profile.getStatus());
         System.out.println("Friends: ");
         for (Profile friend : profile.getFriends()) {
            System.out.println("  - " + friend.getName());
         }
         System.out.println();
      }
   }


   public void DisplayBFS(){

   }

   public Profile removeProfile(Profile profile){
      Profile removedProfile = null;
      return removedProfile;
   }



}
