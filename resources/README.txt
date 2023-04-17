HW 9
changes:
    - shape constructor takes Java.awt.Color object instead of my custom Color enum.
    - add getID() and getDescription() to Snapshot class, since they need to be retrieved and displayed in the View.
    - add an accept(IShapeVisitor) method to IShape
      for the Controller as the Visitor to visit different type of shapes
      and send different message to the View for rendering.
      Justification: the IShapeVisitor is not part of the model package.
      However, by having the model objects only aware of the visitor interface,
      we can swap out different implementations of the visitor (the controller or view)
      without affecting the model objects.

question:
    Command reader in the controller package with some class method to parse input.txt command into
        - should this reader be passed the model?
        - or it is better passin the controller and have reader send back the parsed info through a method
            - controller.receive()

    what is a good way to check for type:
        - getType() method in IShape
        - double dispatch: render(Controller c) {c.render(this)
          but this way shape needs to know about controller
        - visitor: overkill? like I'm passing myself to the visitor, why dont i just provide a get type method
            - should IVisitor be something provided by the model then get implemented by outside pacakge?

    should shape or snapshot object be stored in View for paintComponents()
        - don't think so

    way to get prev
        - iterator doesn't go backwards
        - save the current index as a variable in Controller?










HW 8
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



