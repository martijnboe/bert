/**
 * Deze klasse gaat over items
 *
 * @author Martijn Boer & Stijn Prins
 * @version 1.0
 */

public class Item
{
    private String itemNaam;
    private String description;
    private int gewicht;

    /**
     * Methode waardoor de items worden opgeslagen.
     */
    public Item(String itemNaam, String description, int gewicht){
        this.itemNaam = itemNaam;
        this.description = description;
        this.gewicht = gewicht;
    }

    /**
     * Methode die de beschrijving van een item retourneert
     */

    public String getDescription(){
        return description;
    }

    /**
     * Methode die het gewicht van een item teruggeeft
     */
    public int getItemGewicht(){
        return gewicht;
    }
}