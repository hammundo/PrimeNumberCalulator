import java.io.Writer;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.List;
import java.util.ArrayList;

public class PrimeCalc {

    public static void main(String[]args){

        boolean inPlay = true;
        while(inPlay){
            Scanner input = new Scanner(System.in);
            double max = 2;

            //Get user input while catching any possible input errors.
            boolean success = false;
            while(!success){
                System.out.println("Enter number to check up to (Max 2 Billion):");
                try {
                    max = input.nextInt();
                    success = true;
                } catch (InputMismatchException e) {
                    System.out.println("Sorry, I didn't recognise that. \nPlease try again:");
                    input.next();
                }
            }

            List primeList = new ArrayList();

            //Iterate up until the users input, sending each value to the wrapper method
            for (int i = 2; i <= max; i++){
                if(checkPrime(i)){
                    System.out.println(i + " is prime!");
                    primeList.add(i);
                }
            }

            //All primes found now in arrayList, write arrayList to text file.
            try{
                FileWriter fw = new FileWriter("Prime_Numbers.txt");
                Writer output = new BufferedWriter(fw);
                int arraySize = primeList.size();
                for(int x = 0; x < arraySize; x++){
                    output.write(primeList.get(x).toString() + "\r\n");
                }
                System.out.println("\nTotal primes found: " + primeList.size());
                output.flush();
                output.close();
            } catch(IOException e){
                System.out.println("File cannot be created.");
            }

            System.out.println("Would you like to calculate more primes?");
            Scanner playAgain = new Scanner(System.in);

            //Ask the user if they would like to continue checking for prime numbers.
            boolean loop = true;
            while(loop){
                try{
                    char c = playAgain.next().charAt(0);
                    if(c == 'y' || c == 'Y'){
                        inPlay = true;
                        break;
                    }
                    if(c == 'n' || c == 'N'){
                        System.out.println("Program terminated.");
                        inPlay = false;
                        break;
                    }
                    else{
                        System.out.println("Input not recognised; Please try again:");
                        loop = true;
                    }
                } catch(InputMismatchException e){
                    System.out.println("Sorry, I didn't recognise that. \nPlease try again:");
                    playAgain.next();
                }
            }
        }
    }

    //Wrapper method - Test to see if parsed int is prime.
    private static boolean checkPrime(int n){

        if(n == 1){
            return false;
        }

        double sr = Math.sqrt(n);

        for(int i = 2; i <= sr; i++){
            if(n % i == 0){
                return false;
            }
        }
        return true;
    }

}