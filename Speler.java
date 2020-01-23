import java.util.Stack;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
/**
 * class Speler - geef hier een beschrijving van deze class
 *
 * @author Martijn Boer & Stijn Prins
 * @version 1.0
 */
public class Speler
{
    // instance variables - vervang deze door jouw variabelen
    private int maxGewicht;
    private int huiGewicht;

    private HashMap<String, Item> inventory;
    private HashSet<Item> usedItems;

    public Speler(int huiGewicht, int maxGewicht){

        this.huiGewicht = huiGewicht;
        this.maxGewicht = maxGewicht;

        inventory = new HashMap<>();
        usedItems = new HashSet<>();
    }

    public boolean pickUpItem(String itemNaam, Item item){
        if(canPickUpItem(item)){
            inventory.put(itemNaam, item);
            huiGewicht += item.getItemGewicht();
            return true;
        }

        else{
            return false;
        }
    }

    public boolean dropItem(String itemNaam){
        if(inventory.containsKey(itemNaam)){
            Item itemToDrop = inventory.get(itemNaam);
            huiGewicht -= itemToDrop.getItemGewicht();
            inventory.remove(itemNaam);
            return true;
        }
        else{
            return false;
        }

    }

    public String getInventory(){
        String returnString = "Your current items are:";

        if(inventory.isEmpty()){
            returnString += " " + "no items in inventory";
        }else{
            Set<String> items = inventory.keySet();
            for(String object : items){
                returnString += " " + object;
            }
        }

        return returnString;
    }

    public boolean isInInventory(String itemName){
        if(inventory.containsKey(itemName)){
            return true;
        }
        return false;
    }

    public int getCurrentWeight(){
        return huiGewicht;
    }

    public boolean canPickUpItem(Item item) {
        if ((huiGewicht + item.getItemGewicht()) > maxGewicht) {
            return false;
        }
        return true;
    }

    public void setUsedItem(Item item){
        usedItems.add(item);
    }

    public boolean itemIsUsed(Item item){
        if(usedItems.contains(item)){
            return true;
        }  
        return false;
    }
}
