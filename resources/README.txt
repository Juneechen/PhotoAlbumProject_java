>>> HW 9 design notes
This Photo Album is implemented with the MVC pattern, and it consists of five packages:
    - model: a system capable of storing and modifying `Snapshots` of `IShapes`.
        Model exists on its own and need not be aware of any outside packages other than an `IShapeVisitor` Interface.
    - views: contains a `SwingView` and a `WebView`, both implements `IView`, which is capable of displaying `IShapes` in some way.
        Does not communicate directly with the model.
        Both views render shapes through their own inner Renderer class that implements IRender,
        which is capable of rendering `IShape` of a few known types. The actual rendering result depends on the concrete Renderer.
        IRender interface extends `IShapeVisitor`, allowing it to visit `IShapes`, know their runtime type, and render them differently.
    - controller: communicates between model and views by receiving notification/requests from the view,
        retrieving needed info from the model, and passing them back to the view.
        the concrete controller also implements a `Features` interface,
        letting the view know about things it can ask the controller to do (like getting the next or prev snapshot).
    - utilities: provide a 'ArgsReader' class for parsing and pre-checking command line arguments,
        and a `CommandReader` class for reading the input command and set up the model accordingly.
    - photoalbum: the main entrance of a photo album. It gets needed information with an 'ArgsReader', initialize a model,
        sets up the model with `CommandReader`, then initialize the requested view.
        Finally, it creates a controller and activates the program through the controller.

>>> changes made
    - Removed my `Color` enum, replaced with Java.awt.Color.
      Since Java.awt.Color can be initialized directly with RGB values,
      it's a more appropriate choice when we want to construct a Color with its RGB values.
    - Removed `Point` class as it doesn't really provide any useful functionality;
      change to using two variables x and y to represent a shape's position,
      which can be more easily accessed without the need of going through a `Point`.
    - Add getID() and getDescription() to Snapshot class, as they need to be retrieved and displayed in the View.
    - Add an IShapeVisitor interface and an accept(IShapeVisitor) method to IShape
      for an IRender to visit different type of shapes as the Visitor and render them differently.
      Although the IShapeVisitor is not part of the model package,
      by having the model objects only aware of the visitor interface,
      we can swap out different implementations of the visitor (different type of IRender)
      without affecting the model objects.
    - Add an getSnapshotAt(int index) method to IAlbum, for retrieving snapshot by index.

>>> previously on HW 8
The model package overview:

IShape Interface and the ShapesAlbum class:
    the model representing a model application system with the following main functionality:
    - stores a collections of IShapes objects, each is mapped to a unique String identifier for retrieval.
    - allows 2D shapes to be generated and added into the collection given some required shapes property description.
    - allows shapes in the collection to be modified per request.
    - uses the ShapeFactory class to handle all shape creation and modification requests.
    - take a snapshot that captures the current state of all shapes in the collection.
    - keep a record of all Snapshot objects and show them when requested.
    - present all shapes in the collection in text format.

ShapeFactory:
    knows all about the current valid type, color, position, size for shapes that go into the ShapeAlbum.
    If criteria on any of the above changes, this ShapeFactory should be the only place need to be updated.
    All shape creation and modification requested by the ShapeAlbum is delegated to this ShapeFactory,
    and it should check the criteria before creating and making changes to shapes.

IShape Interface, abstract ShapeImpl, Oval, and Rectangle:
    IShape represents 2D shapes that are placed on a 2D plane, each shape should have:
    - type, Color, x dimension, y dimension, position.
    - all attributes have public setter method, but should only be used by the ShapeFactory
      that checks for argument validity before making the changes.
    - Oval and Rectangle are the currently available types.

Snapshot:
    Each snapshot is a "freeze frame" of the ShapesAlbum model,
    it basically captures the properties of all IShapes in the system.
    All snapshots follow the same format for an auto generated timestamp and snapshot ID.

Color enum:
    records the current supported colors, and makes uses of the java.awt.Color to get
    the RBG representation of existing colors, and stores that information within each color value.



