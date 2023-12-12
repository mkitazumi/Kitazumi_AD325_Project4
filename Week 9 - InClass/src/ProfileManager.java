import GraphPackage.UndirectedGraph;

import java.util.ArrayList;
import java.util.Iterator;

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
         return;
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
      Iterator networkIT = network.vertices.getKeyIterator();
      System.out.println("----- Network Profiles -----");

      while(networkIT.hasNext()){
         Profile current = (Profile) networkIT.next();
         System.out.println("Profile Name: " + current.getName());
         if (current.getPictureUrl() != null) {
            System.out.println("Picture URL: " + current.getPictureUrl());
         }
         System.out.println("Status: " + current.getStatus());
         System.out.println("Friends: ");
         for (Profile friend : current.getFriends()) {
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
