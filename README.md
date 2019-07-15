# LearnYourTekk
Android application to showcase frame data for characters of Tekken 7 and also provide 
character overview information for all 45 characters.

Introduction
------------
Tekken 7 is a fighting game for the PC, PS4 and XBOX ONE and consists of 45 different characters.
The game itself has multiple layers of intricate, complicated concepts that users use to improve their play
and become a better overall player. One of the most important of these concepts is Frame Data which represents the data
for each move that a character possesses. Be it a specific punch or kick, each move is laid out with its corresponding 
unique data in a character's table that players can study and view in order to improve at the game.
Along with the frame data, descrptions, move lists and overall information for all 45 characters in the game is also displayed for
players to research and study.

Application Overview
------------
This app aims to demostrate the use of the Realm API in order to create a local RELATIONAL database from JSON files
that consists of the large dataset of all 45 character's moves(OVER 6000 tuples), their attributes and to 
allow the user to view the data for a specific character in an easy-to-read, organized table.
* Each character has its own table filled with hundreds of moves and their frame data info
* Each table can be filtered to look for specific special characteristics that moves can have (i.e. if a move is Homing or a Rage Art)
* Every character also has a general overview with their Top 15(ish) moves and Standing/WS punishment tools

The application aims to have a simple, clean UI where the user can easily traverse the menus and 
go through the table to see any specific move they have in mind to look up. Speed and simplicity was the goal.

Local Database Setup:
* Database was based off a JSON object that contained all the moves of a character. It consists of the CharacterData and BasicMoves table
where CharacterData contains the name and difficulty of a character. BasicMoves consisted of the notation of the move(i.e. a punch or kick)
and its frame data information(start up frame time, frames on hit, on block, on counter hit, etc. All relevant terms understood by advanced
players of the Tekken 7 game). The CharacterData and BasicMoves table had a one to many relationship where one character would be linked
through reference to multiple tuples in BasicMoves table(just like a one-to-many relationship using a foreign key).
However, with Realm, it keeps track of the reference to the move by itself efficiently for easy access to any specific character's move data.


Some of the UI widgets and design patterns used in the mobile app include:
* Use of custom themes including defintion of primary and accent colors
* Use of a custom Circlular ImageView widget 
* Use of the RecyclerView for several components such as Menus and the Frame Data table
* Activity transitions based off user selections
* CoordinatorLayout, with a combined HorizonalScrollable and VerticalScrollable layouts in order to allow the user to traverse the LARGE table each character has

Screenshots
-------------

<img src="screenshots/composite-1.png" height="400" alt="Screenshot"/> 

Pre-requisites
--------------

- Latest version of Android Studio

Getting Started
---------------

This sample uses the Gradle build system. To build this project, use the
"gradlew build" command or use "Import Project" in Android Studio.

Support
-------

- Stack Overflow: http://stackoverflow.com/questions/tagged/android

If you've found an error in this sample, please file an issue:
https://github.com/FerreyraGD/LearnYourTekk

Feel free to use my code for your projects or to contribute to improve the application. Requests may be submitted by forking this project and
submitting a pull request through GitHub.
