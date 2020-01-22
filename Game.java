/**
 *  This class is the main class of the "World of Zuul" application. 
 *  "World of Zuul" is a very simple, text based adventure game.  Users 
 *  can walk around some scenery. That's all. It should really be extended 
 *  to make it more interesting!
 * 
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 * 
 * @author  Michael Kölling and David J. Barnes
 * @version 2016.02.29
 */
import java.util.*;
public class Game 
{
    private Parser parser;
    private Room currentRoom;
    // stack voor back functie
    private Stack <Room>kamer;

    public static void main(String[] args){
        Game obj = new Game();
        obj.play();
    }
    
    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        createRooms();
        parser = new Parser();
        kamer = new Stack<>();
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
    {
        Room begin, beginmijn, middenmijn, eindmijn, kamertouw, openruimte, grotvleer, kelder;

        // create the rooms
        begin = new Room("beginkamer met water");
        beginmijn = new Room("het begin van de mijn");
        middenmijn = new Room("het midden van de mijn");
        eindmijn = new Room("het einde van de mijn");
        kamertouw = new Room("kamer met het touw");
        openruimte = new Room("open ruimte in een grot");
        grotvleer = new Room("grot met vleermuizen");
        kelder = new Room("een kelder");

        // initialise room exits
        begin.setExit("Noordwest", beginmijn);
        begin.setExit("Noordoost", openruimte);

        // links
        beginmijn.setExit("Noord", middenmijn);

        middenmijn.setExit("Noord", eindmijn);
        middenmijn.setExit("Afdalen", grotvleer);
        middenmijn.setExit("West", kamertouw); //moet pas later tevoorschijn komen

        //eindmijn.setExit("sample", outside);

        //rechts
        openruimte.setExit("Afdalen", grotvleer);
        openruimte.setExit("Oost", kelder); //moet pas later tevoorschijn komen

        grotvleer.setExit("Klim_Omhoog", middenmijn);

        currentRoom = begin;  // start game outside
    }

    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play() 
    {            
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.

        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Bedankt voor het spelen. Joe Joe!");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welkom bij Bert zijn avontuur!");
        System.out.println("Jij bent Bart Schringa uit Klazienaveen die op vakantie is in de Ardennen.");
        System.out.println("Tijdens een wandeling moet je even de druk van de ketel werken.");
        System.out.println("Alleen tijdens het lopen naar een boom val je door een stuk grond 12.4 meter naar beneden.");
        System.out.println("Je valt hier in het water en zwemt naar de kant.");
        System.out.println("het is jouw taak om je weg terug te vinden naar buiten!");
        System.out.println();
        System.out.println(currentRoom.getLongDescription());
    }

    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        if(command.isUnknown()) {
            System.out.println("I don't wait nait...");
            return false;
        }

        String commandWord = command.getCommandWord();
        if (commandWord.equals("help")) {
            printHelp();
        }
        else if (commandWord.equals("go")) {
            goRoom(command);
        }
        else if (commandWord.equals("quit")) {
            wantToQuit = quit(command);
        }
        //back command
        else if (commandWord.equals("back")) {
            back();
        }
        //schreeuw command
        else if (commandWord.equals("schreeuw")) {
            schreeuw();
        }
        //inv command
        else if (commandWord.equals("inv")) {
            inv();
        }
        
        //drop command
        else if (commandWord.equals("drop")) {
            drop(command);
        }
        /*pak command
        else if (commandWord.equals("pak")) {
            pak(command);
        }
        */
        //menu command
        else if (commandWord.equals("menu")) {
            menu();
        }
        //about command
        else if (commandWord.equals("about")) {
            about();
        }
        // else command not recognised.
        return wantToQuit;
    }

    // implementations of user commands:

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    private void printHelp() 
    {
        System.out.println("Je bent gevallen door een gat");
        System.out.println("Je taak is om de uitgang te zoeken...");
        System.out.println();
        System.out.println("Je commands zijn:");
        parser.showCommands();
    }

    /** 
     * Try to in to one direction. If there is an exit, enter the new
     * room, otherwise print an error message.
     */
    private void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Waarheen?");
            return;
        }

        String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null) {
            System.out.println("Er is geen uitgang");
        }
        else {
            kamer.push(currentRoom);
            currentRoom = nextRoom;
            System.out.println(currentRoom.getLongDescription());
            mijnKar();
        }
    }
        
    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Wat willen jij???");
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }

    //back command
    private void back() 
    {
        if (kamer != null) {
            currentRoom = kamer.pop();
            System.out.println(currentRoom.getLongDescription());
        }
    }    
    
    //schreeuw command 
    private void schreeuw() 
    { 
        //check voor de kamers
        if(currentRoom.getShortDescription() == "open ruimte in een grot") {
            System.out.println("Je roept om hulp!");
            System.out.println("Achter je hoor je iemand mompelen en knorren");
            System.out.println("In de richting van het geluid zie je een dronken slapende man liggen");
            System.out.println("Was er maar een manier om hem wakker te maken...");
        }   else if (currentRoom.getShortDescription() == "grot met vleermuizen") {
            System.out.println("Je schreeuwt naar hulp!");
            System.out.println("Je hoort veel gefladder achter je");
            System.out.println("je kijkt naar achter en je ziet een grote groep vleermuizen op je afkomen");
            System.out.println("Je springt net op tijd aan de kant om niet geraakt te worden");
        }
        else {
            System.out.println("Je roept om hulp!");
            System.out.println("Je hoort de echo van je geschreeuw...");
        } 
        
    }

    //mijnkar functie

    //random
    Random rand = new Random();

    //functie voor een random getal
    private int randomGetal() 
    {
        int getal = rand.nextInt(5);
        return getal;
    }

    //hier word gekeken naar welke kamer het is en of het juiste getal gerolled is
    //zo ja dan word de speler verplaatst
    private void mijnKar()
    {
        if (currentRoom.getShortDescription() == "het begin van de mijn" && randomGetal() == 1) {
            back();
            System.out.println("Je struikelt en valt in een oude mijnkar!");
            System.out.println("Daardoor begint de mijnkar te rollen en rol je terug naar het begin!");
            System.out.println("Je opties zijn nu Noordoost en Noordwest");
        } else if (currentRoom.getShortDescription() == "het midden van de mijn" && randomGetal() == 1) {
            back();
            back();
            System.out.println("Je struikelt en valt in een oude mijnkar!");
            System.out.println("Daardoor begint de mijnkar te rollen en rol je terug naar het begin!");
            System.out.println("Je opties zijn nu Noordoost en Noordwest");
        } else if (currentRoom.getShortDescription() == "het eind van de mijn" && randomGetal() == 1) {
            back();
            back();
            System.out.println("Je struikelt en valt in een oude mijnkar!");
            System.out.println("Daardoor begint de mijnkar te rollen en rol je terug naar het begin van de mijn!");
            System.out.println("Je optie is nu Noord");
        }
    }

    //array voor inv
    //ArrayList<String> inventory = new ArrayList<String>();
    HashMap<String, String> inventory = new HashMap<String, String>();

    //inventory functie
    private void inv() {
        System.out.println(inventory);
    }
    

    //drop functie
    private void drop(Command command) {
        if(!command.hasSecondWord()) {
            //check voor item
            System.out.println("Selecteer een item");
            return;
        }
        String item = command.getSecondWord();
        inventory.remove(item);
    }

    /*pak functie
    private void pak(Command command) {
        if(!command.hasSecondWord()) {
            //check voor item
            System.out.println("Selecteer een item");
            return;
        }
        String item = command.getSecondWord();
        inventory.remove(item);
        inventory.add(item);
    }
    */
    
    private void menu() {
        System.out.println("Welkom in het menu van Bert's crazy adventures");
        System.out.println("U heeft de volgende opties");
        System.out.println("about, quit");
    }
    
    private void about() {
        System.out.println("Dit spel is gemaakt door Martijn Boer en Stijn Prins");
    }
}
