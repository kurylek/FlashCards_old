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
        System.out.println("You have added new FlashCard!");
    }
    /*
    public static void removeFlashCard(int index){
        for(int i=index; i<flashCardsCount; i++){
            flashCards[i].word= flashCards[i+1].word;
            flashCards[i].translation= flashCards[i+1].translation;
        }
    }
    */
    public static void listOneFlashCard(int index){
        System.out.println(index+1+". "+flashCards[index].word+" - "+flashCards[index].translation);
    }

    public static void listFlashCards(){
        System.out.println("Your FlashCards: ");
        for(int i=0;i<flashCardsCount;i++){
            listOneFlashCard(i);
        }
    }

    public static void menu(){
        System.out.println("0. Exit");
        System.out.println("1. Add new FlashCard");
        System.out.println("2. List your FlashCards");
        System.out.println("3. Remove FlashCard");
    }

    public static void main(String[] args){
        int menuOption;
        do{
            menu();
            menuOption= input.nextInt();

            switch(menuOption){
                case 0:
                    System.out.println("Goodbye!");
                    break;
                case 1:
                    addFlashCard();
                    break;
                case 2:
                    listFlashCards();
                    break;
                case 3:
                    int indexOfFlashCard= input.nextInt();
                    //removeFlashCard(indexOfFlashCard);
                    break;
            }
        }while(menuOption!=0);


    }
}
