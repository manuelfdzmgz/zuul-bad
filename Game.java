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
 * @author  Michael KÃ¶lling and David J. Barnes
 * @version 2011.07.31
 */

public class Game 
{
    private Parser parser;
    private Room currentRoom;

    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        createRooms();
        parser = new Parser();
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
    {
        Room derecha, izquierda, centro, arriba, abajo, porteriaArriba, porteriaAbajo;

        // create the rooms
        derecha = new Room("La banda derecha buena zona para intentar un regate");
        izquierda = new Room("La banda izquierda prueba a hacer un dribling");
        centro = new Room("El centro del campo buena posicion para centrar a la banda");
        arriba= new Room("Estas en el area contraria prueba a realizar un tiro, !!!!!oooooo!!!! es un balón no un melón ");
        abajo = new Room("Estas en el area propia defensa ferrea y sin fisuras");
        porteriaArriba = new Room("El balon ha entrado en la porteria fuera telarañas");
        porteriaAbajo = new Room("Estas en tu porteria ten cuidado y no metas gol en propia puerta");

        // initialise room exits
        derecha.setExits(null, null, null, centro, null,null);
        izquierda.setExits(null, centro, null, null,abajo,arriba);
        centro.setExits(arriba, derecha, abajo, izquierda,null,null);
        arriba.setExits(porteriaArriba, null, centro, null,null,null);
        abajo.setExits(centro, null, porteriaAbajo , null,null,derecha);
        porteriaAbajo.setExits(abajo,null,null,null,null,null);
        porteriaArriba.setExits(null,null,arriba,null,null,null);

        currentRoom = porteriaAbajo;  // start game outside
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
        System.out.println("Thank you for playing.  Good bye.");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Estas en el campo de juego hola Maldini que nos deparara el partido");
        System.out.println("Bueno yo creo que sera un buen partido Julio Maldonado ");
        System.out.println("Type 'help' if you need help.");
        System.out.println();
        printLocationInfo();

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
            System.out.println("I don't know what you mean...");
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
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around at the university.");
        System.out.println();
        System.out.println("Your command words are:");
        System.out.println("   go quit help");
    }

    /** 
     * Try to go in one direction. If there is an exit, enter
     * the new room, otherwise print an error message.
     */
    private void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom = currentRoom.getExit(direction);
        if (nextRoom == null) {
            System.out.println("There is no door!");
        }
        else {
            currentRoom = nextRoom;
            printLocationInfo();
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
            System.out.println("Quit what?");
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }

    private void printLocationInfo()
    {
        System.out.println("You are " + currentRoom.getDescription());
        System.out.print("Exits: ");
        if(currentRoom.getExit("north") != null) {
            System.out.print("north ");
        }
        if(currentRoom.getExit("east")  != null) {
            System.out.print("east ");
        }
        if(currentRoom.getExit("south") != null) {
            System.out.print("south ");
        }
        if(currentRoom.getExit("west")  != null) {
            System.out.print("west ");
        }
        if (currentRoom.getExit("southwest") != null)
        System.out.print("southEast");
        if (currentRoom.getExit("northeast")!=null)
        System.out.print("northeast");
        System.out.println();
        currentRoom.getExitString();
    }
}
