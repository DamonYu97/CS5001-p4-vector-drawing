## CS5001 P4 Vector Drawing Program

[![Vector Drawing Program ScreenShot][program-screenshot]](https://github.com/DamonYuXXX/CS5001-p4-vector-drawing.git)

### All features
* There are 6 shapes that you can draw in this program: (line, rectangle, ellipse, cross, triangle and parallelogram).
* All shapes can be drawn with selected color and selected fill state.
* You can undo and redo your action, which include creating a shape, loading file, changing the location, color and fill state of a selected shape, cleaning the canvas.
* The ratio of both rectangle and ellipse can be locked with shift key when drawing, which make them square and circle respectively.
* All drawn shapes can be saved as .vec file, and the saved files can be loaded back to the canvas.
* All drawn shapes can be selected, and can be dragged to different position. The color and fill state of a drawn shape also can be changed after being selected.

## Getting Started

### Prerequisites
To run this program in your local machine, you should make sure you have set up java environment (jdk jre).
You can use the following command to check your jdk.
```sh
java -version
```
### Run this program
To run this program, you can choose one of the following ways:
* Open ths file in IntelliJ IDEA and run it.
* Use the following command after your set your command line tool (like terminal) to this folder:
```sh
sh run.sh
```
## Usage

### Draw a shape
* To draw a shape, you need to choose what kind of shape you want to draw by clicking related shape button. 
* The default color of a shape is black, and by default, the shape is not filled. You can change the color of the shape 
you want to draw by clicking the color in the toolbar, you will pop up a window to allow you to choose color. You can also change 
the fill state by clicking fill button.
* Once you have selected all features of the shape, now you can draw it with by dragging your mouse, and release it when you are done.
* To drag a square, you can select the Rectangle button, and draw it with the shift key pressed if you want it to be square. 
If you changed your mind during drawing it, just release the key, then it will be rectangle again.
* You can draw a circle using the same way, after select the Ellipse button.

### Change a shape
* To change the shape, you need first click the select button to be in SELECT MODE.
* After being SELECT MODE, to move a shape, you can move your mouse to the shape you want to move, then drag it to the place you want to move to.
* AFTER being SELECT MODE, to change the color of a shape, you need first move your mouse to the shape you want to change, then click the color button in toolbar. and choose a color in popped up window.
* After being SELECT MODE, to change the fill state of a shape, you need first move your mouse to the shape you want to change, then click the fill button to change the state.

### Undo and redo
To undo and redo an action, you just need to click undo or redo button respectively.

### Save, load file
* To save the drawing shapes, you can click the file menu and then click the save button to specify where you want it to be saved. Note that the file will be saved as .vec type. 
If you did not specify the filename with .vec, it will change it to be .vec file automatically.
* To load a file, you can click the file menu and click the load button. Then, you can choose the .vec file you want to load to this canvas. Note that this can be undone by clicking undo button.

## Test
There has been 40 tests written in this project to test the functionality of models. To run it, you can choose one of the following two ways:
* If you imported this project to your IntelliJ IDEA, you can just run all test suite runner under test folder.
* If you are using command line tool (like terminal), and set it to this folder, you can run those testes by using following command:
```sh
sh test.sh
```

## Extension
If you want to introduce more shapes into this program, you can simply create a class extending the Shape class in model.shape package.
And then add the shape name to ShapeType.java file in the following format:
```java
SHAPE_NAME("shapeClassName");
```
<!-- LINKS & IMAGES -->
[program-screenshot]: program-screenshot.png