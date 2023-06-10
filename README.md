# Photo Album Application with Java and Swing
Author: Shujun Chen

## Overview
This photo album project is a Java application built using Swing and follows the MVC (Model-View-Controller) design principle. 
The program takes input from a text file containing instructions such as draw, move, and take snapshot. 
These instructions are parsed and used to create Shape and Snapshot objects within the ShapeAlbum. 
The ShapeAlbum stores all the created objects.

During program execution, the user can choose a view type through command-line arguments.
The chosen view will render and display the objects stored in the ShapeAlbum as images made of shapes. 
The project provides two views: Swing GUI view and static HTML view. The decoupling of classes and implementation of 
interfaces allow for flexibility in swapping different views.

The Swing GUI view utilizes the Java Swing library to create an interactive graphical interface. 
It reads the ShapeAlbum objects and renders each snapshot as an image composed of shapes. 
The user can navigate through the snapshots by clicking buttons or selecting a specific snapshot by its id.

The static HTML view is implemented using SVG and CSS. It reads the ShapeAlbum objects and renders the shapes 
in each snapshot as SVG images. The resulting HTML output can be opened in a web browser to view the snapshots.

## Technical Skills Utilized
The project incorporates the following technical skills:

**Java and JUnit Tests:**
The project is developed using the Java programming language, 
leveraging its object-oriented nature and extensive libraries. 
The project includes a comprehensive suite of JUnit tests to ensure 
the correctness and reliability of the implemented functionalities.

**Swing Library:**
The Swing library is used to create the GUI view of the application. 
It enables the rendering of images displayed and the creation of interactive graphical interfaces.

**MVC Design Principle:**
The project adheres to the MVC (Model-View-Controller) design principle, 
separating concerns and improving code organization. 
The model is represented by the ShapeAlbum class, which stores Snapshots containing Shapes. 
The views (Swing GUI and static HTML) are responsible for rendering shapes based on the model data. 
The controller coordinates between the model and views.

**Interfaces:**
The project implements interfaces to promote flexibility and ease of swapping different views.
The decoupling of classes through interfaces allows for seamless integration of alternative views.

**Visitor Pattern and Dynamic Dispatch:**
The project makes use of the visitor pattern and dynamic dispatch to allow the views 
to render different shapes differently at run time, providing flexibility and extensibility.

**Parsing and Command Processing:**
The program incorporates a command parsing mechanism that allows it to parse commands following a specific schema 
from a readable input source, such as a txt file. Instead of hardcoding the input source or pre-making objects 
in the model, this approach provides users the freedom to feed custom commands into the program, 
enabling the generation of different images based on their specific requirements. 
This flexibility allows for experimentation and customization without the need to modify the code itself.
The program uses the parsed commands to create Shape and Snapshot objects within the ShapeAlbum model.

**SOLID Principle:**
The project follows the SOLID design principles, ensuring that classes have a single responsibility 
and are decoupled. This improves code maintainability and flexibility.

## Project Run Instructions
To run the project, follow these steps:

**Step 1:**
Ensure that you have the Java Runtime Environment (JRE) installed on your machine.

**Step 2:**
In the `resources` folder in this repository, you can find the `PhotoAlbumProject.jar` artifact, 
and a few `.txt` sample command input files.
Save the `.jar` artifact provided and any of the provided sample input txt you wish to load, 
place them in the same directory.

**Step 3:**
Open a command prompt or terminal and navigate to the directory containing the `.jar` 
artifact and input files.

**Step 4:**

To launch the ***GUI Swing view**, run such command:

`java -jar PhotoAlbumProject.jar -in buildings.txt -view graphical 800 800`

Replace `buildings.txt` with the name of the desired input text file. 
This command will process the commands from the input file and open a GUI window displaying the photo album. 
The recommended width and height for the sample input images provided are `800` and `800`, respectively. 
Feel free to adjust these values based on your custom input file.


To generate a **static HTML** file with the web view, run the following command:

`java -jar PhotoAlbumProject.jar -in buildings.txt -view web -out out.html`

Replace `buildings.txt` with the name of the desired input text file. 
Replace `out.html` with a desired output file name. Note that it must not have the same name with an existing file.
This command will process the commands from the input file and generate a static web HTML file named `out.html`. 
You can open this file in a web browser to view the photo album.

If you have any further questions or need additional assistance, please let me know!

juneechen2021@outlook.com
