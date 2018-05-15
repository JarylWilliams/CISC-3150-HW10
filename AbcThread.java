/*
 * CISC.3150 HW10
 * Printing the letters of the alphabet using 4 threads
 */

/**
 *
 * @author Jaryl
 */
public class AbcThread {
    
    //http://javabypatel.blogspot.in/2017/06/running-threads-in-sequence-in-java.html
    public static void  main(String[] args){
        //placed letters in 4 arrays
            char[] group1 = new char[]{'a', 'e', 'i', 'm', 'q', 'u', 'y'};
            char[] group2 = new char[]{'b', 'f', 'j', 'n', 'r', 'v', 'z'};
            char[] group3= new char[]{'c', 'g', 'k', 'o', 's', 'w'};
            char[] group4 = new char[]{'d', 'h', 'l', 'p', 't', 'x'};

            myThread thread1 = new myThread(group1);
            myThread thread2 = new myThread(group2);
            myThread thread3 = new myThread(group3);
            myThread thread4 = new myThread(group4);
       
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
    }
}
  //http://www.tutorialsdesk.com/2016/01/3-threads-to-print-alternate-values-in.html
  //http://www.devinline.com/2016/01/print-pattern-1-2-3-using-three-threads.html

    class myThread extends Thread{
        static int total;    //Total number of threads
         int threadID;
        static int turn = 1 ; //Which thread turn it is
        char[] chars;
     
    //Constructor
    myThread(char[] chars){
        total++;        
        threadID = total;  
        this.chars = chars;}
    
         @Override
     public void run(){
          printLetters(chars);
        total--;        
        }
     //synchronizes the printing of the letters
      synchronized void printLetters(char[] chars) {
        try {
            for (char letters : chars) {
                // makesures that it is that specific thread turn to go
                while (turn != threadID){
                    wait(3);
                }
                System.out.println(letters);
                if (turn >= total) {
                    turn = 1;
                } else {
                    turn++;
                }
                     notifyAll();
            }
        } catch (InterruptedException e){}
    }
}
