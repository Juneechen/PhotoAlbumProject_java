The album package overview:

ShapesAlbum class:
    the model representing a album application system with the following main functionality:
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



