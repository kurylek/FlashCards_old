/*
TODO:
-play mode with tips
-better way to exit/continue play mode
-save/load flascards to file
-in playNormalMode: if index==flasCardsCount set index=0, randomize randomOrder and play again
 */

import java.util.Scanner;

public class Main {
    static int flashCardsCount=0;
    static FlashCard[] flashCards = new FlashCard[100];
    static Scanner input= new Scanner(System.in);

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
        int i= 0;
        int zeroToExit= 1;
        do{
            listOneFlashCard(i);
            System.out.println("Enter 0 to exit Learn Mode");
            zeroToExit= input.nextInt();
            i++;
            if(i>=flashCardsCount)i=0;
        }while(zeroToExit!=0);
    }

    public static void playNormalMode(){
        int[] randomOrder= new int[flashCardsCount];
        String inputAnswer;
        int score=0, index=0, exit=1; //exit==0 -> exit
        for(int i=0;i<flashCardsCount;i++){
            randomOrder[i]=i;
            /*
            TODO: fill randomOrder[] with values <0;flasCardsCount-1> randomly
             */
        }

        do{
            /*
            TODO: change the way it shows (more ~fancy~)
             */
            System.out.println("Word: "+flashCards[randomOrder[index]].word);
            System.out.println("Enter translation: ");
            inputAnswer= input.next();
            inputAnswer+= input.nextLine();

            if(flashCards[randomOrder[index]].translation.equals(inputAnswer)){
                score++;
                System.out.println("Good job! Your score is: "+score+"/"+(index+1));
            }else{
                System.out.println("Not these time.. Your score is: "+score+"/"+(index+1));
                System.out.println("Correct answer is: "+flashCards[randomOrder[index]].translation);
            }

            System.out.println("Enter 0 to exit.");
            exit= input.nextInt();
            if((index+1)>=flashCardsCount)exit=0;
            index++;
            if(exit==0){
                System.out.println("It's end! Your score is: "+score+"/"+index);
            }
        }while(exit!=0);
    }

    public static void menu(){
        System.out.println("0. Exit");
        System.out.println("1. Add new FlashCard");
        System.out.println("2. List your FlashCards");
        System.out.println("3. Remove FlashCard");
        System.out.println("4. Play- Learn mode");
        System.out.println("5. Play- Normal mode");
    }

    public static void clearTerminal(){
        for(int i=0;i<30;i++){
            System.out.println();
        }
    }

    public static void main(String[] args){
        int menuOption;
        clearTerminal();
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
                default:
                    clearTerminal();
                    System.out.println("Error- there is no option like that!");
                    break;
            }
        }while(menuOption!=0);
    }
}
