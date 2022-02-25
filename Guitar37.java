// Hawo Issa
// CSE 143 EG with Khushi Chaudhari
// Homework 2
// Guitar37 creates a virtual guitar with 37 strings
// that can be played using a computer's keyboard.

public class Guitar37 implements Guitar {
   
   private GuitarString[] elementStrings;
   private int counts;
   
   public static final String KEYBOARD =
      "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' "; 
   
   // post: contructs a guitar by specializing each guitar string
   //       with different frequencies
   public Guitar37() {
      counts = 0;
      elementStrings = new GuitarString[KEYBOARD.length()];
      for (int i = 0; i < KEYBOARD.length(); i++) {
         elementStrings[i] = new GuitarString(440 * Math.pow(2,(i - 24.0)/12.0));
      }
   }
   
   // post: play the specific string using the pitch 
   // param: int pitch - pitch of guitar string
   public void playNote(int pitch) {
      if (pitch <= 12 && pitch >= -24) {
         pluck(KEYBOARD.charAt(pitch + 24));
      }    
   }

   // post: returns true or false if key has a corresponding
   //       string that can be played
   // param: char string - given string of guitar
   public boolean hasString(char string) {
      return KEYBOARD.indexOf(string) != -1;
   }
    
   // pre: String has to be on of the 37 strings that is 
   //      designed to be play (throws IllegalArgumentException otherwise)
   // post: plays a note with the specified string
   // param: char string - given string of guitar
   public void pluck(char string) {
      if (!hasString(string)) {
         throw new IllegalArgumentException();
      } 
      elementStrings[KEYBOARD.indexOf(string)].pluck();     
   } 
    
   // post: returns the sum of all samples
   //       from the 37 strings of the guitar 
   public double sample() {
      double sampleSum = 0.0;
      for (int i = 0; i < KEYBOARD.length(); i++) {
         sampleSum += elementStrings[i].sample(); 
      }
      return sampleSum;
   }

   // post: this method advance the time forward one “tic”
   public void tic() {
      for (int i = 0; i < KEYBOARD.length(); i++) {
         elementStrings[i].tic();
      }
      counts++;
   }

   // post: returns the number that is the current time
   public int time() {
      return counts;  
   }

}