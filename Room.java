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
    private String itemToUnlock;
    private HashMap<String, Room> exits; // stores exits of this room.
    private HashMap<String, Item> items;

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
        items = new HashMap<>();

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
            return getItemString() + ".\n" + getExitString();
        } else if(getShortDescription() == "open ruimte in een grot"){
            System.out.println("Je loopt door de gangen heen en komt in een grote open ruimte.");
            System.out.println("Je kijkt om je heen en ziet in de verte meer gangen die mogelijk leiden naar meer.");
            getItemString();
            System.out.println("Misschien kan je hier om hulp roepen... ");
            return getItemString() + ".\n" + getExitString();
        } else if(getShortDescription() == "het begin van de mijn"){
            System.out.println("Je klimt door gangen heen en je ziet wat planken voor je");
            System.out.println("Je schuift de planken aan de kant en je ziet dat je terecht bent gekomen in een mijn!");
            return getItemString() + ".\n" + getExitString();
        } else if(getShortDescription() == "het midden van de mijn"){
            System.out.println("Je loopt door de gangen heen en komt in een grote mijnschacht.");
            System.out.println("Je kijkt om je heen en ziet dat de mijn verderop verdergaat maar je ziet ook dat je.");
            System.out.println("Rechts kan afdalen naar een grot.");
            System.out.println("En links zie je een kapotte muur je denkt had ik maar iets om het open te breken...");
            System.out.println("In de ruimte zie je ook (items).");
            return getItemString() + ".\n" + getExitString();
        } else if(getShortDescription() == "het einde van de mijn"){
            System.out.println("Je loopt door de gangen heen en komt in een grote open ruimte.");
            System.out.println("Je kijkt om je heen en ziet in de verte meer gangen die mogelijk leiden naar meer.");
            System.out.println("In de ruimte zie je ook (items).");
            return getItemString() + ".\n" + getExitString();
        } else if(getShortDescription() == "kamer met het touw"){
            System.out.println("Je breekt de muur open en ziet een open ruimte.");
            System.out.println("De ruimte lijkt op een oude kantine");
            System.out.println("Je ziet een paar skeleten en ziet dat er één een (item TOUW) heeft");
            System.out.println("Misschien kan je het (ITEM TOUW) gebruiken om door een gat te klimmen ");
            return getItemString() + ".\n" + getExitString();
        } else if(getShortDescription() == "grot met vleermuizen"){
            System.out.println("Je loopt door de gangen heen en");
            System.out.println("Je kijkt om je heen en ziet in dat de grond bezaait is met keutels.");
            System.out.println("Zouden hier vleermuizen zitten?..");
            System.out.println("In de ruimte zie je ook (items).");
            System.out.println("Misschien kan je hier om hulp roepen... ");
            return getItemString() + ".\n" + getExitString();
        } 
        else {
            System.out.println("*&KAMER NIET GEVONDEN ERROR&*");
        }
        return " ";
    }

    /**
     * Retourneer alle items in een ruimte, van de vorm:
     *     Items in this room: apple
     * Of wanneer er geen items zijn:
     *     Items in this room: no items in this room
     * @return Een lijst van alle items in de ruimte
     */
    public String getItemString(){
        String returnString = "Voorwerpen in de kamer: ";

        if(items.isEmpty()){
            returnString += " " + "Er ligt niks in deze kamer..";
        }else{
            Set<String> item = items.keySet();
            for(String object : item){
                returnString += " " + object;
            }
        }

        return returnString;
    }

    /**
     * Geef een item op waarmee de exit unlocked kan worden.
     * 
     * @param itemName De naam van het item.
     */
    public void setItemToUnlock(String itemNaam){
        itemToUnlock = itemNaam;
    }
    
        public String getItemToUnlock(){
        return itemToUnlock;
    }
    
        public void addItem(String itemNaam, Item item){
        items.put(itemNaam, item);
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

