/**
 * Class Room - a room in an adventure game.
 *
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 *
 * A "Room" represents one location in the scenery of the game.  It is 
 * connected to other rooms via exits.  The exits are labelled north, 
 * east, south, west.  For each direction, the room stores a reference
 * to the neighboring room, or null if there is no exit in that direction.
 * 
 * @author  Michael Kölling and David J. Barnes
 * @version 2011.07.31
 */
public class Room 
{
    private String description;
    private Room northExit;
    private Room southExit;
    private Room eastExit;
    private Room westExit;
    private Room southEastExit;
    private Room northEastExit;

    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The room's description.
     */
    public Room(String description) 
    {
        this.description = description;
    }

    /**
     * Define the exits of this room.  Every direction either leads
     * to another room or is null (no exit there).
     * @param north The north exit.
     * @param east The east east.
     * @param south The south exit.
     * @param west The west exit.
     */
    public void setExits(Room north, Room east, Room south, Room west, Room southEast,Room northEast) 
    {
        if(north != null)
            northExit = north;
        if(east != null)
            eastExit = east;
        if(south != null)
            southExit = south;
        if(west != null)
            westExit = west;
        if(southEast != null)
            southEastExit = southEast;
         if (northEast!= null)
         northEastExit = northEast;
    }

    /**
     * @return The description of the room.
     */
    public String getDescription()
    {
        return description;
    }

    public  Room getExit(String salida)
    {
        Room direccion = null;
        switch(salida)
        {
            case "north":
            direccion = northExit;
            break;
            case "south":
            direccion = southExit;
            break;
            case "east":
            direccion = eastExit;
            break;
            case "west":
            direccion = westExit;
            break;
            case "southeast":
            direccion = southEastExit;
            break;
            case "northeast":
            direccion = northEastExit;

        }
        return direccion;
    }

    /**
     * Return a description of the room's exits.
     * For example: "Exits: north east west"
     *
     * @ return A description of the available exits.
     */
    public String getExitString()
    {
        String cadena = "";
        if (northExit!=null)
            cadena+= "north";
        if (southExit!=null)
            cadena+= "south";
        if (eastExit!=null)
            cadena+= "east";
        if (westExit!=null)
            cadena+= "west";
        if (southEastExit!=null)
            cadena+= "southEast";
        if (northEastExit!=null)
            cadena+="northeast";
        return  cadena;
    }

}
