/**
 * Created by Christian on 9/28/2016.
 */
package slots;

import java.io.*;

class machine {
    int counter;
    int quarters;
    String instanceName;

    public machine (String instance){
        instanceName = instance;
    }

    public boolean playCheck(int quarters){
        if(quarters > 0) {
            return true;
        } else {
            return false;
        }

    }//end playCheck

    boolean playable = playCheck(quarters);

    public int play(int quarters, int payout, int amount) throws IOException{
        if(playCheck(quarters)){
            counter++;
            if ((counter % payout) == 0) {
                quarters += amount;
                System.out.println("You won $" + (amount*.25) + " on " + instanceName + ".");
                System.out.println("You have " +quarters+ " quarters remaining which equals $" + (quarters*.25)+".");
                try{
                    File file = new File("src/results.txt");
                    if(!file.exists()) {
                        file.createNewFile();
                    }
                    FileWriter fw = new FileWriter(file,true);
                    BufferedWriter bw = new BufferedWriter(fw);
                    PrintWriter pw = new PrintWriter(bw);
                    pw.println("You won $" + (amount*.25) + " on " + instanceName + ".");
                    pw.println("You have " +quarters+ " quarters remaining which equals $" + (quarters*.25)+".");
                    pw.close();
                }//end of try

                catch(IOException ex){
                    System.out.println("File could not be used.....");
                }//end catch
                //System.out.println(quarters);
                }
        }
        else {
            System.out.println("You don't have quarters to play with.");
            return 0;
        }
        return quarters;
    }//end play

    public int getTotal(){
        return counter;
    }//end getTotal

}// end machine



public class slots {

    public static void main (String[] args) throws IOException {
        int quarters = 100;
        machine slot1 = new machine("Machine 1");
        machine slot2 = new machine("Machine 2");
        machine slot3 = new machine("Machine 3");

        //System.out.println(quarters);

        while(quarters > 0){
            quarters--;
            quarters = slot1.play(quarters,33,25);
            //System.out.println(quarters);
            quarters--;
            quarters = slot2.play(quarters,99,75);
            //System.out.println(quarters);
            quarters--;
            quarters = slot3.play(quarters,9,7);
            //System.out.println(quarters);
        }

        System.out.println("You where able to play " + (slot1.getTotal() + slot2.getTotal() + slot3.getTotal()) + " times.");

        try{
            File file = new File("src/results.txt");
            if(!file.exists()) {
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file,true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            pw.println("You where able to play " + (slot1.getTotal() + slot2.getTotal() + slot3.getTotal()) + " times.");

            pw.close(); //this closes the input
        }//end of try

        catch(IOException ex){
            System.out.println("File could not be used.....");
        }//end catch
    }//end main
}//end slots
