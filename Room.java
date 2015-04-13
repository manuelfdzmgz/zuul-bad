import java.util.HashMap;
import java.util.Set;
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
    private HashMap<String, Room> exits;

    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The room's description.
     */
    public Room(String description) 
    {
        this.description = description;
        exits = new HashMap<>();
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
            exits.put("north",north);
        if(east != null)
            exits.put("east", east);
        if(south != null)
           exits.put("south", east);
        if(west != null)
            exits.put("west", east);
        if(southEast != null)
           exits.put("southeast", east);
        if (northEast!= null)
         exits.put("northeast", east);
    }
    public void setExit(String direction, Room nextRoom)
    {
        exits.put(direction,nextRoom);
    }
    public  Room getExit(String direction)
    
    {
        return exits.get(direction);
    }

    /**
     * @return The description of the room.
     */
    public String getDescription()
    {
        return description;
    }

    public  Room getExits(String salida)
    {
        Room direccion = null;
        switch(salida)
        {
            case "north":
            direccion = exits.get("north");
            break;
            case "south":
            direccion = exits.get("south");
            break;
            case "east":
            direccion = exits.get("east");
            break;
            case "west":
            direccion = exits.get("west");
            break;
            case "southeast":
            direccion = exits.get("southeast");
            break;
            case "northeast":
            direccion = exits.get("northeast");

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
        if (exits.get("north")!=null)
            cadena+= "north";
        if (exits.get("south")!=null)
            cadena+= "south";
        if (exits.get("east")!=null)
            cadena+= "east";
        if (exits.get("west") !=null)
            cadena+= "west";
        if (exits.get("southeast") !=null)
            cadena+= "southEast";
        if (exits.get("northeast")!=null)
            cadena+="northeast";
        return  cadena;
    }
    public String getExitsString()
    {
        Set<String>namesOfDirection = exits.keySet();
        String exitsDescription = "Exit ";
        for (String direction : namesOfDirection)
        {
            exitsDescription +=direction + "";
        }
    
        return exitsDescription;
    } 

}
