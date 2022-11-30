/* The Connection Handler Class - Written by Derek Molloy for the EE402 Module
 * See: ee402.eeng.dcu.ie
 */

// package ee402;
package Assignment2;

import java.net.*;
import java.util.Queue;
import java.util.Vector;
import java.io.*;

public class ThreadedConnectionHandler extends Thread {
    private Socket clientSocket = null; // Client socket object
    private ObjectInputStream is = null; // Input stream
    private ObjectOutputStream os = null; // Output stream

    private DateTimeService theDateService;
    // private Queue<Robot> robot;
    private Vector<Robot> robot;
    private ServerGUI gui;

    // The constructor for the connection handler
    public ThreadedConnectionHandler(Socket clientSocket, ServerGUI _gui) {
        this.clientSocket = clientSocket;
        // Set up a service object to get the current date and time
        theDateService = new DateTimeService();
        gui = _gui;
    }

    // Will eventually be the thread execution method - can't pass the exception
    // back
    public void run() {
        try {
            this.is = new ObjectInputStream(clientSocket.getInputStream());
            this.os = new ObjectOutputStream(clientSocket.getOutputStream());
            while (this.readCommand()) {
            }
        } catch (IOException e) {
            System.out.println("XX. There was a problem with the Input/Output Communication:");
            e.printStackTrace();
        }
    }

    // Receive and process incoming string commands from client socket
    private boolean readCommand() {
        String s = null;
        // Robot r = null;

        try {
            robot = (Vector<Robot>) is.readObject();
            System.out.println("IN TRY");
            gui.addRobotsToList(robot);
            send("reciept");
        } catch (Exception e) { // catch a general exception
            this.closeSocket();
            return false;
        }
        for (Robot current : robot) {
            System.out.println("01. <- Received an object from the client ");
            System.out.println("X cord = " + current.getX());
            System.out.println("Y cord = " + current.getY());
            System.out.println("Velocity = " + current.getVelocity());
            System.out.println("Direction = " + current.getDirection());
        }
        // At this point there is a valid String object
        // invoke the appropriate function based on the command
        if (robot != null) {
            this.getDate();
        } else {
            this.sendError("Invalid command: " + s);
        }
        return true;
    }

    // Use our custom DateTimeService Class to get the date and time
    private void getDate() { // use the date service to get the date
        String currentDateTimeText = theDateService.getDateAndTime();
        this.send(currentDateTimeText);
    }

    // Send a generic object back to the client
    private void send(Object o) {
        try {
            System.out.println("02. -> Sending (" + o + ") to the client.");
            this.os.writeObject(o);
            this.os.flush();
        } catch (Exception e) {
            System.out.println("XX." + e.getStackTrace());
        }
    }

    // Send a pre-formatted error message to the client
    public void sendError(String message) {
        this.send("Error:" + message); // remember a String IS-A Object!
    }

    // Close the client socket
    public void closeSocket() { // gracefully close the socket connection
        try {
            this.os.close();
            this.is.close();
            this.clientSocket.close();
        } catch (Exception e) {
            System.out.println("XX. " + e.getStackTrace());
        }
    }

    public Vector<Robot> getRobots() {
        return robot;
    }
}