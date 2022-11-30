package Assignment2;

import java.net.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Vector;
import java.io.*;

//Client gets latest robot and then sends three latest clones 
public class Client {

	private static int portNumber = 5050;
	private Socket socket = null;
	private ObjectOutputStream os = null;
	private ObjectInputStream is = null;

	private ClientGUI gui;
	private Vector<Robot> robots = new Vector<Robot>();
	private Queue<Robot> output = new LinkedList<>();
	private Robot robot;

	// Opens a socket connection to the specified server.
	public Client(String serverIP, String robotName) {
		if (!connectToServer(serverIP)) {
			System.out.println("XX. Failed to open socket connection to: " + serverIP);
		}
		robot = new Robot(10, robotName); // Make robot and size
		gui = new ClientGUI(robot);
	}

		// Connect to a server.
	private boolean connectToServer(String serverIP) {
		try { // open a new socket to the server
			this.socket = new Socket(serverIP, portNumber);
			this.os = new ObjectOutputStream(this.socket.getOutputStream());
			this.is = new ObjectInputStream(this.socket.getInputStream());
			System.out.println("00. -> Connected to Server:" + this.socket.getInetAddress()
					+ " on port: " + this.socket.getPort());
			System.out.println("    -> from local address: " + this.socket.getLocalAddress()
					+ " and port: " + this.socket.getLocalPort());
		} catch (Exception e) {
			System.out.println("XX. Failed to Connect to the Server at port: " + portNumber);
			System.out.println("    Exception: " + e.toString());
			return false;
		}
		return true;
	}

		// Sends a robot to the server
	private void sendRobot() throws CloneNotSupportedException {
		String reciept;
		System.out.println("Sending Robot Objects to Server");
		robot.update();
		robots.add(robot.clone());
		if (robots.size() == 4)
			robots.remove(0);
		this.send(robots);

		System.out.println("test");
		try {
			reciept = (String) receive();
			System.out.println("05. <- The Server responded with: ");
			System.out.println("    <- " + reciept);
		} catch (Exception e) {
			System.out.println("XX. There was an invalid object sent back from the server");
		}
	}

		// Sends an object to the client.
	private void send(Object o) {
		try {
			System.out.println("02. -> Sending an object...");
			os.writeObject(o);
			os.flush();
		} catch (Exception e) {
			System.out.println("XX. Exception Occurred on Sending:" + e.toString());
		}
	}

	
		// Receives an object from the robot.
	private Object receive() {
		Object o = null;
		try {
			System.out.println("03. -- About to receive an object...");
			o = is.readObject();
			System.out.println("04. <- Object received...");
			os.reset();
			gui.update(robot.getTime(),true);
		} catch (Exception e) {
			System.out.println("XX. Exception Occurred on Receiving:" + e.toString());
			gui.update(robot.getTime(),false);

		}
		return o;
	}

	public static void main(String args[]) throws InterruptedException, CloneNotSupportedException {
		System.out.println("**. Java Client Application - EE402 OOP Module, DCU");

		if (args.length == 2) {
			Client theApp = new Client(args[0], args[1]);
			boolean i = true;
			while (i = true) {
				// theApp.getDate();
				theApp.sendRobot();
				// gui.update();
				Thread.sleep(1000);
			}

		} else {
			System.out.println("Error: you must provide the address of the server");
			System.out.println("Usage is:  java Client x.x.x.x  (e.g. java Client 192.168.7.2)");
			System.out.println("      or:  java Client hostname (e.g. java Client localhost)");
		}
		System.out.println("**. End of Application.");
	}
}