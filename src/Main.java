/*
TODO:
-improve menu
-play mode with tips
-better way to exit/continue play mode
 */

import java.io.*;
import java.util.Random;
import java.util.Scanner;

public class Main {
    static int flashCardsCount= 0;
    static FlashCard[] flashCards = new FlashCard[100];
    static Scanner input= new Scanner(System.in);
    static String currentFlashCardsFile= "default";
    static int firstRun=1;

    public static void saveFlashCards() throws IOException{
        PrintWriter printWriter= new PrintWriter(currentFlashCardsFile+".txt");

        printWriter.println(flashCardsCount);
        for(int i=0;i<flashCardsCount;i++){
            printWriter.println(flashCards[i].word);
            printWriter.println(flashCards[i].translation);
        }

        printWriter.close();
        System.out.println(flashCardsCount+" FlashCards saved.");
    }

    public static void loadFlashCards() throws IOException{
        clearFlashCards();

        if(firstRun!=1){
            System.out.println("Enter file name: ");
            currentFlashCardsFile= input.next();
        }
        clearTerminal();

        File flashCardsFile= new File(currentFlashCardsFile+".txt");
        boolean flashCardsFileExists= flashCardsFile.exists();

        if(flashCardsFileExists){
            FileReader fileReader= new FileReader(currentFlashCardsFile+".txt");
            BufferedReader reader= new BufferedReader(fileReader);

            //reading first file line- we expect number of FlashCards
            String tmp= reader.readLine();
            if(tmp==null || tmp.equals("0")){
                if(firstRun!=1){
                    System.out.println("There is no FlashCards in these fille!");
                }
            }else{
                flashCardsCount= Integer.parseInt(tmp);

                for(int i=0;i<flashCardsCount;i++){
                    String word= reader.readLine();
                    String translation= reader.readLine();
                    flashCards[i]= new FlashCard(word, translation);
                }

                reader.close();

                System.out.println("Loaded "+flashCardsCount+" FlashCards.");
            }
        }else{
            if(firstRun!=1){
                System.out.println("There is no file with that name!");
            }
        }

        firstRun=0;
    }

    public static void clearFlashCards(){
        flashCardsCount= 0;
        flashCards= new FlashCard[100];
    }

    public static void addFlashCard(){
        String word;
        String translation;

        System.out.println("Enter the word: ");
        word= input.next();
        word+= input.nextLine();

        System.out.println("Enter the translation: ");
        translation= input.next();
        translation+= input.nextLine();

        flashCards[flashCardsCount]= new FlashCard(word,translation);
        flashCardsCount++;
        clearTerminal();
        System.out.println("You have added new FlashCard!");
    }
    /*
    TODO: repair removing FlasCards

    public static void removeFlashCard(int index){
        for(int i=index; i<flashCardsCount; i++){
            flashCards[i].word= flashCards[i+1].word;
            flashCards[i].translation= flashCards[i+1].translation;
        }
    }
    */
    public static void listOneFlashCard(int index){
        System.out.println((index+1)+". "+flashCards[index].word+" - "+flashCards[index].translation);
    }

    public static void listFlashCards(){
        System.out.println("Your FlashCards: ");
        for(int i=0;i<flashCardsCount;i++){
            listOneFlashCard(i);
        }
        System.out.println("\n");
    }

    public static  void playLearnMode(){
        int i=0;
        String zeroToExit= "1";
        do{
            listOneFlashCard(i);
            System.out.println("Enter 0 to exit Learn Mode");
            zeroToExit= input.next();
            i++;
            if(i>=flashCardsCount)i=0;
        }while(zeroToExit!="0");
    }

    public static int[] randomizeArray(int[] array){
        Random rgen= new Random();
        for(int i=0;i<array.length;i++){
            int randomPosition= rgen.nextInt(array.length);
            int tmp= array[i];
            array[i]= array[randomPosition];
            array[randomPosition]= tmp;
        }

        return array;
    }

    public static void playNormalMode(){
        int[] randomOrder= new int[flashCardsCount];
        String inputAnswer;
        int score=0, index=0, tries=0;
        String exit= "1"; //exit==0 -> exit
        for(int i=0;i<randomOrder.length;i++){
            //Fill array with values 0-flashCardsCount
            randomOrder[i]= i;
        }
        randomOrder= randomizeArray(randomOrder);

        do{
            /*
            TODO: change the way it shows (more ~fancy~)
             */
            System.out.println("Word: "+flashCards[randomOrder[index]].word);
            System.out.println("Enter translation: ");
            inputAnswer= input.next();
            inputAnswer+= input.nextLine();
            tries++;

            if(flashCards[randomOrder[index]].translation.equals(inputAnswer)){
                score++;
                System.out.println("Good job! Your score is: "+score+"/"+tries);
            }else{
                System.out.println("Not these time.. Your score is: "+score+"/"+tries);
                System.out.println("Correct answer is: "+flashCards[randomOrder[index]].translation);
            }

            System.out.println("Enter 0 to exit.");
            exit= input.next();

            if((index+1) >= flashCardsCount){
                //if it shows all flashcards it'll shuffle it, and start again
                index= 0;
                randomOrder= randomizeArray(randomOrder);
            }else{
                index++;
            }

            if(exit.equals("0")){
                clearTerminal();
                System.out.println("It's end! Your score is: "+score+"/"+tries+"\n");
            }
        }while(!exit.equals("0"));
    }

    public static void menu(){
        System.out.println("0. Exit");
        System.out.println("1. Add new FlashCard");
        System.out.println("2. List your FlashCards");
        System.out.println("3. Remove FlashCard");
        System.out.println("4. Play- Learn mode");
        System.out.println("5. Play- Normal mode");
        System.out.println("6. Save FlashCards");
        System.out.println("7. Load FlashCards");
    }

    public static void clearTerminal(){
        for(int i=0;i<30;i++){
            System.out.println();
        }
    }

    public static void main(String[] args) throws IOException {
        int menuOption;
        clearTerminal();
        loadFlashCards();

        do{
            menu();
            menuOption= input.nextInt();

            switch(menuOption){
                case 0:
                    clearTerminal();
                    System.out.println("Goodbye!");
                    break;
                case 1:
                    clearTerminal();
                    addFlashCard();
                    break;
                case 2:
                    clearTerminal();
                    if(flashCardsCount==0){
                        System.out.println("You don't have any FlashCards!\n");
                    }else{
                        listFlashCards();
                    }
                    break;
                case 3:
                    clearTerminal();
                    //int indexOfFlashCard= input.nextInt();
                    //removeFlashCard(indexOfFlashCard);
                    System.out.println("ToDo");
                    break;
                case 4:
                    clearTerminal();
                    if(flashCardsCount==0){
                        System.out.println("You don't have any FlashCards!\n");
                    }else{
                        playLearnMode();
                    }
                    break;
                case 5:
                    clearTerminal();
                    if(flashCardsCount==0){
                        System.out.println("You don't have any FlashCards!\n");
                    }else{
                        playNormalMode();
                    }
                    break;
                case 6:
                    clearTerminal();
                    saveFlashCards();
                    break;
                case 7:
                    clearTerminal();
                    loadFlashCards();
                    break;
                default:
                    clearTerminal();
                    System.out.println("Error- there is no option like that!");
                    break;
            }
        }while(menuOption!=0);
    }
}