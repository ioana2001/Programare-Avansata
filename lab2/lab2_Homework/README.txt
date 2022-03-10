Homework 

Class Problem contains a vector of events and a vector of rooms. You can create an instance of Schedule (along with a problem instance) to assign each event a room.

----- problem text:

Homework (2p)

Create a class that describes an instance of the problem.
Override the equals method form the Object class for the Event, Room classes. The problem should not allow adding the same event or room twice.
Instead of using an enum, create dedicated classes for lecture halls and computer labs. Room will become abstract. The course room may have an additional property indicating if there is a video projector, and the lab room may indicate the operating system of its computers.
Create a class to describe the solution.
Implement a simple algorithm for creating a feasible solution to the problem, "trying" to minimize the number of used rooms.
Write doc comments in your source code and generate the class documentation using javadoc.