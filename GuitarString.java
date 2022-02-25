// Hawo Issa
// CSE 143 EG with Khushi Chaudhari
// Homework 2
// GuitarString creates a musical sound
// using a key on a keyboard.

import java.util.*;

public class GuitarString {
   
   private Queue<Double> ringBuffer; 
   public static final double ENERGY_DECAY = 0.996;
   
   // pre: frequency has to be greater than zero and the desire capacity
   //      N has to be more than two (throws IllegalArgumentException otherwise)
   // post: Constructs a guitar string of the given frequency 
   // param: double frequency - given frequency of guitar string
   public GuitarString(double frequency) {
      int desiredCapacity = (int)(Math.round(StdAudio.SAMPLE_RATE / frequency)); 
      if (frequency <= 0 || desiredCapacity < 2) {
         throw new IllegalArgumentException();
      }
      ringBuffer = new LinkedList<Double>();
      for (int i = 0; i < desiredCapacity; i++) {
         ringBuffer.add(0.0); 
      }   
   }
   
   // pre: array has to have more than 2 elements
   //      (throws IllegalArgumentException otherwise)
   // post: Initializes the values of the ring buffer,  
   //       constructor is used only for testing purposes.
   // param: double[] init - given array
   public GuitarString(double[] init) {
      if (init.length < 2) {
         throw new IllegalArgumentException();
      }
      ringBuffer = new LinkedList<Double>();
      for (int i = 0; i < init.length; i++) {
         ringBuffer.add(init[i]);
      }
   }
   
   // post: plucks the guitar string
   public void pluck() {
      Random random = new Random();
      for (int i = 0; i < ringBuffer.size(); i++) {
         double randomNumber = random.nextDouble(1) - 0.5;
         ringBuffer.remove();
         ringBuffer.add(randomNumber);
      }
   } 
   
   // post: adds the average of the two values to the ring buffer
   public void tic() {
      double front = ringBuffer.remove();
      double frontSecond = ringBuffer.peek();
      double average = ((front + frontSecond) / 2) * ENERGY_DECAY;
      ringBuffer.add(average);
   } 
   
   // post: returns the key in front of ring buffer
   public double sample() {
      return ringBuffer.peek();
   } 
   
}