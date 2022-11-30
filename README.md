
# Assignment 2 2022/23: Java Client/Server Robot Navigation Application

In this assignment you are going to develop a Java client/server application that sends and receives data to/from a PC and an embedded Linux device such as the BeagleBone Black (BBB) or Raspberry Pi (RPi). Given the global chip crisis due to COVID19, the assignment can be completed entirely on a single desktop machine and all tasks are simulated.


## Server Requirements

In this application the desktop PC is the graphical robot display server. The server should have the following features:

- On execution it should open a server socket and wait for connections (e.g., on port 5050) from multiple robot client devices.
- The server should have a GUI that is built using Java Swing. It should display graphical top-down location information on an (x,y) graphical grid, where North is at the top. Velocity should be represented by a number at the robot location.
- The server should be able to accept connections from many robots (limited to a maximum of 3 for the purpose of testing).
- The display should show the (x,y) location, velocity and orientation of every robot client that is connected to the server. This is the current position. 
- Each robot has a name and dimension. This information needs to be displayed on the GUI when that robot is selected with the mouse.
- The server should calculate the distance between each robot and evaluate if two robots are in danger of colliding using the current position and the size of the robots in question. For example, this could be performed by asking if the distance between the two robots > sum of the radii + a safety margin that is related to velocity.
- The GUI should provide a display of the last three positions (e.g., using a different colour)
- The collision safety margin should be somewhat configurable.
- Add two other features of your choice that you deem necessary and/or useful. List them explicitly in your report.

## Client Requirements

In this application each robot application should run on the desktop device. The client should have the following features:

- You should pass the server address and the robot name (e.g., localhost) to the client as a command line argument when you execute it.
- The client application should have a GUI that allows you to push buttons to go forward, backward, rotate left, rotate right, and to choose a speed.
- The client should send an update every 10 seconds, regardless of whether the robot is moved or not.
- The client should display the current connection status and the last time that a message was successfully sent to the server.
- Add a novel feature of your own design.
