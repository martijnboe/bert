import java.util.Set;
import java.util.HashMap;
import java.util.*;

/**
 * Class Room - a room in an adventure game.
 *
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 *
 * A "Room" represents one location in the scenery of the game.  It is 
 * connected to other rooms via exits.  For each existing exit, the room 
 * stores a reference to the neighboring room.
 * 
 * @author  Michael Kölling and David J. Barnes
 * @version 2016.02.29
 */

public class Room 
{
    private String description;
    private HashMap<String, Room> exits;        // stores exits of this room.

    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The room's description.
     */
    //int nummer is voor de back functie
    public Room(String description) 
    {
        this.description = description;
        exits = new HashMap<>();

    }

    /**
     * Define an exit from this room.
     * @param direction The direction of the exit.
     * @param neighbor  The room to which the exit leads.
     */
    public void setExit(String direction, Room neighbor) 
    {
        exits.put(direction, neighbor);
    }

    /**
     * @return The short description of the room
     * (the one that was defined in the constructor).
     */
    public String getShortDescription()
    {
        return description;
    }

    HashMap<String, Integer> items = new HashMap<String, Integer>();

    //ITEMS functie
    public void pushItems() {
        // Add keys and values (Naam,Gewicht)
        items.put("Pikhouweel", 12);
        items.put("Baked beans", 1);
        items.put("Touw", 3);
        items.put("Kaars", 5);
        items.put("Lantaarn", 6);
        items.put("Dynamiet", 5);
        items.put("Schep", 5);
        items.put("Diamanten", 8);
        items.put("Kauwgom", 1);
        System.out.println(items); 
    }

    /**
     * Return a description of the room in the form:
     *     You are in the kitchen.
     *     Exits: north west
     * @return A long description of this room
     */
    public String getLongDescription()
    {
        if(getShortDescription() == "beginkamer met water"){
            System.out.println("Je bent naar de kant gezwommen… ");
            System.out.println("je kijkt om je heen en ziet het grote gat boven je. ");
            System.out.println("Je denkt naar jezelf “Had ik maar iets om mijn weg weer naar buiten te maken. ");
            System.out.println("Je kijkt voor je uit en ziet dat er twee doorgangen zijn. ");
            System.out.println("Je kan naar het Noordwesten en naar het Noordoosten ");
            System.out.println("Maak je keuze. ");
            return getExitString();
        } else if(getShortDescription() == "open ruimte in een grot"){
            System.out.println("Je loopt door de gangen heen en komt in een grote open ruimte.");
            System.out.println("Je kijkt om je heen en ziet in de verte meer gangen die mogelijk leiden naar meer.");
            System.out.println("In de ruimte zie je ook (items).");
            System.out.println("Misschien kan je hier om hulp roepen... ");
            return getExitString();
        } else if(getShortDescription() == "het begin van de mijn"){
            System.out.println("Je klimt door gangen heen en je ziet wat planken voor je");
            System.out.println("Je schuift de planken aan de kant en je ziet dat je terecht bent gekomen in een mijn!");
            System.out.println("In de mijn zie je ook (items).");           
            return getExitString();
        } else if(getShortDescription() == "het midden van de mijn"){
            System.out.println("Je loopt door de gangen heen en komt in een grote mijnschacht.");
            System.out.println("Je kijkt om je heen en ziet dat de mijn verderop verdergaat maar je ziet ook dat je.");
            System.out.println("Rechts kan afdalen naar een grot.");
            System.out.println("En links zie je een kapotte muur je denkt had ik maar iets om het open te breken...");
            System.out.println("In de ruimte zie je ook (items).");

            return getExitString();
        } else if(getShortDescription() == "het einde van de mijn"){
            System.out.println("Je loopt door de gangen heen en komt in een grote open ruimte.");
            System.out.println("Je kijkt om je heen en ziet in de verte meer gangen die mogelijk leiden naar meer.");
            System.out.println("In de ruimte zie je ook (items).");
            return getExitString();
        } else if(getShortDescription() == "kamer met het touw"){
            System.out.println("Je breekt de muur open en ziet een open ruimte.");
            System.out.println("De ruimte lijkt op een oude kantine");
            System.out.println("Je ziet een paar skeleten en ziet dat er één een (item TOUW) heeft");
            System.out.println("Misschien kan je het (ITEM TOUW) gebruiken om door een gat te klimmen ");
            return getExitString();
        } else if(getShortDescription() == "grot met vleermuizen"){
            System.out.println("Je loopt door de gangen heen en");
            System.out.println("Je kijkt om je heen en ziet in dat de grond bezaait is met keutels.");
            System.out.println("Zouden hier vleermuizen zitten?..");
            System.out.println("In de ruimte zie je ook (items).");
            System.out.println("Misschien kan je hier om hulp roepen... ");
            return getExitString();
        } else if(getShortDescription() == "boven"){
            System.out.println("Je gooit het touw naar boven en hij blijft haken achter een boom");
            System.out.println("Je trekt aan het touw om te kijken of het stevig is");
            System.out.println("Het touw blijft gewoon hangen dus je grijpt het touw en klimt naar boven");
            System.out.println("Eenmaal boven pak je de rand vast en trek je jezelf uit het gat ");
            System.out.println("Het is gelukt je bent uit de grot! ");
            return getExitString();
        } else if(getShortDescription() == "dood"){
            System.out.println("Je steekt de staaf dynamiet aan en je rent gauw weg");
            System.out.println("Maar je voet blijft haken achter een steen en je valt achter de grond…");
            System.out.println("Dan hoor je ineens een luide explosie ");
            System.out.println("En het plafond stort boven je in en valt op je… ");
            System.out.println("Dit is het einde van Bert Schuringa 1976-2020  ");
            return getExitString();
        } else if(getShortDescription() == "een kelder"){
            System.out.println("Je pakt de beker met water en je gooit het over het gezicht van de slapende man…");
            System.out.println("Hij springt op en schreeuwt: Allee wat doet te gij nu?!?!?!");
            System.out.println("Je vraagt aan hem wat hij hier doet hij zegt: Ja iek verstop mij hier voor mijn vrouw");
            System.out.println("Je lacht en vraagt of hij de weg naar buiten weet. Hij zegt: A maar natuurlijk volg mij! ");
            System.out.println("Hij leid je naar een smalle gang waarin je uiteindelijk uitkomt in de kelder van de Belg ");
            System.out.println("En je bedankt hem en je bent weer buiten! ");
            return getExitString();
        }
    else {
        System.out.println("*&KAMER NIET GEVONDEN ERROR&*");
    }
    return " ";
}

/**
 * Return a string describing the room's exits, for example
 * "Exits: north west".
 * @return Details of the room's exits.
 */
private String getExitString()
{
String returnString = "Uitgangen:";
Set<String> keys = exits.keySet();
for(String exit : keys) {
returnString += " " + exit;
}
return returnString;
}

/**
 * Return the room that is reached if we go from this room in direction
 * "direction". If there is no room in that direction, return null.
 * @param direction The exit's direction.
 * @return The room in the given direction.
     */
    public Room getExit(String direction) 
    {
        return exits.get(direction);
    }

}

