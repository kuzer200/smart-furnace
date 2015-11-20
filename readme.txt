Smart Furnace
by Eric Kuz and Clayton Brezinski
ENSE 374 Fall 2015

This program implements the smart furnace system based on the class diagram in /doc.  The current implementation
strictly builds the ventilation system and implements rudimentary thermometer functionality (the temperature of 
each room changes linearly and is changed by the controller to demonstrate how it would work).

Essentially, the system is a tree of different nodes, namely the furnace, the junction, and the room.   
The furnace is the head node, junctions are middle nodes, and rooms are leaf nodes. Each node is connected
to another via a gate object which acts as a vertex.
A junction can have multiple gates leading from it, and those gates can lead to either another junction or a room.
Gates can be opened or closed; when closed, no air can flow though it, meaning any nodes beyond that gate are not 
affected by the furnace.  
When a room is opened, all paths leading to the room will have their gates opened.
The gate to a junction will close if and only if all other gates in that junction are closed.

The Gate object differs from the class diagram in that there is only one kind of gate.
It either leads to a room or another junction - never both.

The Room object also differs slightly in that it has two extra parameters - the target temperature,
and the tolerance level (how willing we are to let it fluctuate).

The sensors were not implemented because right now, they serve no purpose in the system.  Had we implemented this system
physically, we would be able to get real values that we could work with.  Even with the thermometers, we have to simulate a change
in room temperature.
