import ADTPackage.QueueInterface;
import GraphPackage.UndirectedGraph;

import java.util.ArrayList;
import java.util.Iterator;

public class ProfileManager {
   //
   private UndirectedGraph<Profile> network;
   private Profile origin;

   /**
    * Creates a new ProfileManager instance with a specified origin profile.
    *
    * @param origin The profile representing the starting point of the network.
    */
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

   /**
    * Connect adds a profile to another profile making them friends
    * via a adge.
    * @param vertex, vertex of person
    * @return friend and vertx attached by edge
    *
    * runtime is O(n) where n is the number of friends that vertex has
    */
   /
   public void connect(Profile vertex1){
      ArrayList<Profile> friends = vertex1.getFriends();
      for(Profile friend : friends){
         network.addEdge(vertex1, friend);
      }
   }

   /**
    * Display takes the current profile and prints it
    * @param profile needs a current profile to display
    * @return displays a profile
    * runtime is O(n) where n is the number of friends
    */
   /
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
            if(friend.getName() != null) {
               System.out.println("  - " + friend.getName());
            }
         }
         System.out.println();

      }
   }

   /**
    * displays all the profiles in BFS style format
    * @param needs profiles in the network
    * @return displays all the profiles in BFS format
    *
    * rutime is O(n+m) where n is the number of profile and m is the number of friends
    * the person has
    */
   /
   public void DisplayBFS(){

      System.out.println("----Network BFS Profiles----");
      QueueInterface<Profile> profiles;
      profiles =  network.getBreadthFirstTraversal(origin);
      while(!profiles.isEmpty()){
         Profile current = profiles.dequeue();
         if(current.getName() == null) {
            network.vertices.remove(current);
            if(!profiles.isEmpty()) {
               current = profiles.dequeue();
            }

         }
         if(current.getName() != null) {
            System.out.println(current);
         }

      }

   }

   /**
    * Removes a profile clears and removes a specific profile
    * @param profile needs a profile to remove
    * @return removes profile from network
    *
    *O(n) where n is profiles in the network
    * for worst case cause it has to iterate through all the profiles
    */
   /
   public void removeProfile(Profile profile){
      if(profile == origin){
         Iterator networkIT = network.vertices.getKeyIterator();
         if(networkIT.hasNext()){
            origin = (Profile) networkIT.next();
         }
      }
      profile.clear();
      network.vertices.remove(profile);

   }



}
