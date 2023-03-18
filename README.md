# CANNON FODDER
**Text based game:** A text game or text-based game is an electronic game that uses a text-based user interface, that is, the user interface employs a set of encodable characters, such as ASCII, instead of bitmap or vector graphics.[[1]](https://en.wikipedia.org/wiki/Text-based_game)  

**Role playing game:** A role-playing game (sometimes spelled roleplaying game; abbreviated RPG) is a game in which players assume the roles of characters in a fictional setting. Players take responsibility for acting out these roles within a narrative, either through literal acting or through a process of structured decision-making regarding character development. Actions taken within many games succeed or fail according to a formal system of rules and guidelines.[[2]](https://en.wikipedia.org/wiki/Role-playing_game)

---
## Table of Contents
  * [Table of Contents](#table-of-contents)
  * [General Info](#general-info)
  * [Technologies](#technologies)
  * [Setup](#setup)
  * [Features](#features)
  * [Status](#status)
  * [Inspiration](#inspiration)
  * [Version](#version)
  * [Contributors](#contributors)
  * [Licence & Copyright](#licence--copyright)

---
## General Info
* This application has been made by **adhering to a document**.
* This project is *simple* **text-based role-playing** game and the first game I made with *Java*.
* This project **only** works on the *console*.

---

## Technologies
* java version "17.0.2" 2022-01-18 LTS
* Java(TM) SE Runtime Environment (build 17.0.2+8-LTS-86)
* Java HotSpot(TM) 64-Bit Server VM (build 17.0.2+8-LTS-86, mixed mode, sharing)

---
## Setup

#### For Windows:
If you don't have a java and javac version, download and install it from here:  
Download Java SE Development Kit from: https://www.oracle.com/java/technologies/downloads  
Open CMD (Command Prompt) and type this command:  
```
>cd (src path)
>javac -encoding ISO-8859-1 file_name.java
>java Library
```

#### For Linux:
##### To install java and javac (if you don't have):
If you don't have a java and javac version, download from here:  
Download Java from: https://www.oracle.com/java/technologies/downloads  
Download "x64 Compressed Archive" or "x64 Debian Package"  

Remove current openjdk version command is
```
~# sudo apt-get remove openjdk*
```
If you download compressed package then extract it:
```
# cd /(the location where the package was downloaded)
# tar n-xvzf (your package)
```
Move and copy jdk file to /opt folder command is
```
# mv jdk-(version of your package) /opt
```
Go to opt/jdk-(version of your package)
```
# cd /opt/jdk-(version of your package)
```
Run following commands as it is (changing your jdk version)
```
# update-alternatives --install /usr/bin/java java /opt/jdk-(version of your package)/bin/java 1
# update-alternatives --install /usr/bin/javac javac /opt/jdk-(version of your package)/bin/javac 1
# update-alternatives --set javac /opt/jdk-(version of your package)/bin/javac
```
for checking version ```# java -version```

Now your jdk is sucessfully installed.
##### If you already have a java and javac version, you can continue from this point.
For run this project:
```
# cd (src path)
# javac file_name.java
# java Library
```
#### For MacOS:
If you don't have a java and javac version, download and install it from here:  
Download Java SE Development Kit from: https://www.oracle.com/java/technologies/downloads  
Open CMD (Command Prompt) and type this command:  
```
>cd (src path)
>javac Library.java
>java Library
```

---
## Features

#### Attack

* This action allows you to **attack** the *enemy*, *your teammate*, or *yourself*. You will get **different feedback** depending on the target.

#### SpecialAction

* This action allows you to use the **special action** of *your character's weapon*.  

    The special action of the sword is to immobilize enemies for 1 turn and level + 1 turn the character wielding the sword cannot take damage (the enemy can hit this character, but takes no damage. So if the enemy hits this character, it is to the player's advantage) and rendering it invulnerable.  

    The wand's special action is to inflict a certain amount of health on the target warrior. The health given does not exceed the maximum health.

    The shield is passive. The shield has no special action.

#### Examine

* With this action, you can **examine** the item on the *ground*, in *your hand* or in *your inventory*.

#### Pick

* With this action, you can **pick up** the item on the *ground*. If the item on the ground *is not in your possession or inventory*, it goes to *your inventory*.

#### Wield

* With this action, you can **wield** the weapon in *your inventory*.

#### Wear

* With this action, you can **wear** the clothing in *your inventory*.

#### ListInventory

* With this action, you can **see the properties** of the items in *your inventory* and in *your possession/on you*.  

#### NEXT

* If you are going to the **next round**, just write "NEXT".

    NOTE: The next round can be passed when there are no enemies left.


Once you pick up an item from the ground, you can not leave it back. When you change your weapon, the weapon in your hand goes to the inventory. You cannot carry more weapons than your total strength. That's why you should choose the weapons you buy very carefully. Otherwise, at some point, you may become unable to pick up a weapon from the ground.  

---
## Status
With the exception of bug fixes (if any), development of this project is complete.

---
## Inspiration
Software Engineering 116 (SE 116) Project Instructions (Izmir University of Economics, 2022, SE 116 course, Spring semester project document)
  
As a student of the Izmir University of Economics, I made this project in accordance with the SE 116 Project Instructions for the spring semester project of the SE 116 course in 2022.
  
The project conforms almost exactly to the document.
  
Since the content of the SE 116 projects that will be given in the next periods may be similar to this document, I do not share the document publicly. If you need the document, send an e-mail with the reason to the following address: <ejderaysunn@gmail.com>

---
## Version
**Version 1.0.0**  

---
## Contributors
Ejder Aysun <ejderaysunn@gmail.com>

---
## Licence & Copyright
© Ejder Aysun, Cannon Fodder  
Licensed under the [MIT Licence](https://github.com/EjderAysun/Cannon-Fodder/blob/main/LICENCE)