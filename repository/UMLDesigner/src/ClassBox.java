//import java.awt.Color;
import java.awt.MouseInfo;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.paint.Color;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

/***
 * ClassBox class
 * This class maintains two lists, rectangles and text areas.
 * Various methods handle the objects in the lists and return
 * data from them
 * 
 */
public class ClassBox{
	
	//Initalize lists
	private ArrayList<Rectangle> rects = new ArrayList<>();
	private ArrayList<TextArea> textAreas = new ArrayList<>();
	private ArrayList<Anchor> anchors = new ArrayList<>();
	
	//Defaults for rectangles sizes
	
	private static double DEFAULT_RECT_X = 400;
	private static double DEFAULT_RECT_Y = 200;
	private static double DEFAULT_RECT_ONE_H = 50;
	private static double DEFAULT_RECT_TWO_H = 100;
	private static double DEFAULT_RECT_THREE_H = 100;
	private static double DEFAULT_RECT_FOUR_H = 100;
	private static double DEFAULT_RECT_WIDTH = 200;
	private static double DEFAULT_ARC_DIM = 20;
	private static double DEFAULT_STROKE_WIDTH = 2;
	private static Color FILL_COLOR = Color.TRANSPARENT;
	private static Color STROKE_COLOR = Color.BLACK;
	private int size = 1;
	
	private boolean isSelected = false;
	
	/***
	 * Constructor
	 * @param size, pane
	 */
	public ClassBox(int size, Pane pane) {
		this.size = size;
		initRects(size, pane);
	}
	
	/***
	 * Constructor for tests
	 * @param size, pane
	 */
	public ClassBox(int size) {
		this.size = size;
	}
	/***
	 * This creates a rectangle at the specified location
	 * @param posX, posY, width, height, arc dimension, fill color, stroke color, stroke width
	 * @return the rectangle
	 */
	private Rectangle createRect(double posX, double posY, double width, double height, double arcDim, Color fill, Color stroke, double strokeWidth) {
		Rectangle r = new Rectangle(posX,posY,width,height);
		r.setArcHeight(arcDim);
		r.setArcWidth(arcDim);
		r.setFill(fill);
		r.setStroke(stroke);
		r.setStrokeWidth(strokeWidth);
		
		r.setOnMouseDragged(event -> {
			drag();
		});
		return r;
	}
	
	/***
	 * This creates a textArea at the specified location
	 * @param posX, posY, width, height
	 * @return the textArea
	 */
	private TextArea createText (double posX, double posY, double width, double height) {
		TextArea ta = new TextArea();
		ta.setLayoutX(posX);
		ta.setLayoutY(posY);
		ta.setPrefWidth(width);
		ta.setPrefHeight(height);
		ta.setWrapText(true);
		
		return ta;
	}
	
	/***
	 * When a new ClassBox is created, this creates the rectangles and textAreas based on the default dimensions
	 * and the initial size of the ClassBox
	 * @param size, pane
	 */
	private void initRects(int size, Pane pane) {
		double currX = DEFAULT_RECT_X;
		double currY = DEFAULT_RECT_Y;
		
		for (int i = 0; i < size; i++) {
			switch(i) {
			
			case 0: //Create the first rectangle

				Rectangle rect1 = createRect(currX, currY, DEFAULT_RECT_WIDTH, DEFAULT_RECT_ONE_H, DEFAULT_ARC_DIM, FILL_COLOR, STROKE_COLOR, DEFAULT_STROKE_WIDTH);
				
				TextArea ta = createText(currX, currY, DEFAULT_RECT_WIDTH, DEFAULT_RECT_ONE_H-20);
				
				textAreas.add(ta);
				rects.add(rect1);
				
				pane.getChildren().add(ta);
				pane.getChildren().add(rect1);
				
				break;
			case 1: //Create the second rectangle
				
				currY = currY + DEFAULT_RECT_ONE_H;

				Rectangle rect2 = createRect(currX, currY, DEFAULT_RECT_WIDTH, DEFAULT_RECT_TWO_H, DEFAULT_ARC_DIM, FILL_COLOR, STROKE_COLOR, DEFAULT_STROKE_WIDTH);
				
				TextArea ta1 = createText(currX, currY, DEFAULT_RECT_WIDTH, DEFAULT_RECT_TWO_H-20);
				
				textAreas.add(ta1);
				rects.add(rect2);
				
				pane.getChildren().add(ta1);
				pane.getChildren().add(rect2);

				break;
			case 2: //Create the third rectangle
				
				currY = currY + DEFAULT_RECT_TWO_H;

				Rectangle rect3 = createRect(currX, currY, DEFAULT_RECT_WIDTH, DEFAULT_RECT_THREE_H, DEFAULT_ARC_DIM, FILL_COLOR, STROKE_COLOR, DEFAULT_STROKE_WIDTH);
				
				TextArea ta2 = createText(currX, currY, DEFAULT_RECT_WIDTH, DEFAULT_RECT_THREE_H-20);
				
				textAreas.add(ta2);
				rects.add(rect3);
				
				pane.getChildren().add(ta2);
				pane.getChildren().add(rect3);
				
				break;
				
			default: //Create the fourth rectangle
				
				currY = currY + DEFAULT_RECT_THREE_H;

				Rectangle rect4 = createRect(currX, currY, DEFAULT_RECT_WIDTH, DEFAULT_RECT_FOUR_H, DEFAULT_ARC_DIM, FILL_COLOR, STROKE_COLOR, DEFAULT_STROKE_WIDTH);
				
				TextArea ta3 = createText(currX, currY, DEFAULT_RECT_WIDTH-6, DEFAULT_RECT_FOUR_H-20);
				
				textAreas.add(ta3);
				rects.add(rect4);
				
				pane.getChildren().add(ta3);
				pane.getChildren().add(rect4);

				break;
			}	
		}
		anchorUpdate(pane, false, false, false);
	}
	
	/***
	 * flips the isSelected field and sets the rectangle colors based on the state of the rectangle.
	 * Only one ClassBox can be selected at a time
	 */
	public void toggleSelect() {
		if (isSelected) {
			setStrokeColor(STROKE_COLOR);
		}else {
			setStrokeColor(Color.RED);
		}
		isSelected = !isSelected;
	}
	
	/***
	 * returns the rectangle List of the ClassBox
	 * @return ArrayList<Rectangle>
	 */
	public ArrayList<Rectangle> getRects(){
		return rects;
	}
	
	/***
	 * returns the textArea List of the ClassBox
	 * @return ArrayList<TextArea>
	 */
	public ArrayList<TextArea> getTexts(){
		return textAreas;
	}
	
	/***
	 * returns whether or not the ClassBox is currently selected
	 * @return isSelected
	 */
	public boolean isSelected() {
		return isSelected;
	}
	
	/***
	 * deselects the ClassBox
	 */
	public void selectOff() {
		setStrokeColor(STROKE_COLOR);
		isSelected = false;
	}
	
	/***
	 * called in the on mouse dragged event of a rectangle.
	 * It moves all rectangles and textAreas with the mouse position.
	 */
	private void drag() {
		double prevHeight = 0;
		
		//Loop through rectangles and textAreas and set position of each
		for(int i = 0; i < rects.size(); i++)
		{
			int mouseX = MouseInfo.getPointerInfo().getLocation().x;
			int mouseY = MouseInfo.getPointerInfo().getLocation().y;
			
			rects.get(i).setX(mouseX-(rects.get(i).getWidth()/2));
			rects.get(i).setY(mouseY-65+prevHeight);
			
			textAreas.get(i).setLayoutX(mouseX-(rects.get(i).getWidth()/2));
			textAreas.get(i).setLayoutY(mouseY-65+prevHeight);

			prevHeight = prevHeight + rects.get(i).getHeight();
		}
	}
	
	/***
	 * sets the border (stroke) color of each rectangle in the ClassBox
	 * @param color
	 */
	private void setStrokeColor(Color color) {
		for (Rectangle rec : rects) {
			rec.setStroke(color);
		}
	}
	
	/***
	 * removes the last rectangle from a ClassBox
	 * @param pane
	 */
	public void removeLast (Pane pane) {
		pane.getChildren().remove(rects.get(rects.size()-1));
		pane.getChildren().remove(textAreas.get(textAreas.size()-1));
		
		rects.remove(rects.size()-1);
		textAreas.remove(textAreas.size() - 1);
		
	}
	
	/***
	 * adds a rectangle to the end of the ClassBox as long as there are less than 4 rectangles
	 * @param pane
	 */
	public void add(Pane pane) {
		
		Rectangle r;
		TextArea ta;
		int size = rects.size();
		
		//posY = bottom of last rectangle
		double posY = rects.get(rects.size()-1).getY() + rects.get(rects.size()-1).getHeight();
		double posX = rects.get(0).getX();
		Color currStroke = (Color)rects.get(0).getStroke();
		
		switch (size) {
		
		case 1:
			r = createRect(posX,posY,DEFAULT_RECT_WIDTH,DEFAULT_RECT_ONE_H,DEFAULT_ARC_DIM, FILL_COLOR, currStroke, DEFAULT_STROKE_WIDTH);
			ta = createText(posX, posY, DEFAULT_RECT_WIDTH, DEFAULT_RECT_ONE_H);
		case 2:
			r = createRect(posX,posY,DEFAULT_RECT_WIDTH,DEFAULT_RECT_TWO_H,DEFAULT_ARC_DIM, FILL_COLOR, currStroke, DEFAULT_STROKE_WIDTH);
			ta = createText(posX, posY, DEFAULT_RECT_WIDTH, DEFAULT_RECT_ONE_H);
		case 3:
			r = createRect(posX,posY,DEFAULT_RECT_WIDTH,DEFAULT_RECT_THREE_H,DEFAULT_ARC_DIM, FILL_COLOR, currStroke, DEFAULT_STROKE_WIDTH);
			ta = createText(posX, posY, DEFAULT_RECT_WIDTH, DEFAULT_RECT_ONE_H);
		default:
			r = createRect(posX,posY,DEFAULT_RECT_WIDTH,DEFAULT_RECT_FOUR_H,DEFAULT_ARC_DIM, FILL_COLOR, currStroke, DEFAULT_STROKE_WIDTH);
			ta = createText(posX, posY, DEFAULT_RECT_WIDTH, DEFAULT_RECT_ONE_H);
		}
		
		rects.add(r);
		textAreas.add(ta);
		
		pane.getChildren().add(ta);
		pane.getChildren().add(r);
	}
	
	/***
	 * puts focus to the selected textArea if it's rectangle is double clicked
	 * @param rectangle
	 */
	public void editText (Rectangle rect) {
		
		int index = rects.indexOf(rect);

		textAreas.get(index).requestFocus();
	}
	
	/***
	 * clears the ClassBox and pane of all rectangles and textAreas.
	 * This needs to be called before deleting a ClassBox
	 * @param pane
	 */
	public void empty (Pane pane) {

		for (Rectangle r : rects) {
			pane.getChildren().remove(r);
		}
		for (TextArea ta : textAreas) {
			pane.getChildren().remove(ta);
		}
		rects.clear();
		textAreas.clear();
	}
	
	/***
	 * returns the size
	 * This needs to be called before deleting a ClassBox
	 * @param pane
	 */
	public int size () {
		return size;
	}
	
	public void anchorUpdate (Pane pane, boolean updateAnchors, boolean remove, boolean removeAll)
	{	
		
		if (removeAll)
		{
			pane.getChildren().removeAll(anchors);
			anchors.clear();
			
			return;
		}
		
		int size = 0;
		
		if (rects.size() > 0)
		{
			size = rects.size();
		}
		
		if (updateAnchors)
		{
			pane.getChildren().removeAll(anchors);
			anchors.clear();
			if(remove)
			{
				size = size + 1;
				if (size == 1)
				{
					pane.getChildren().removeAll(anchors);
					anchors.clear();
					
					return;
				}
			}
			else
			{
				size = size - 1;
			}
		}
		
		Anchor anch1 = new Anchor(Color.DARKRED, rects.get(0).xProperty().add(DEFAULT_RECT_WIDTH / 2), rects.get(0).yProperty().add(0));
		Anchor anch2 = new Anchor(Color.DARKRED, rects.get(0).xProperty().add(DEFAULT_RECT_WIDTH), rects.get(0).yProperty().add(DEFAULT_RECT_ONE_H / 2));
		Anchor anch3 = new Anchor(Color.DARKRED, rects.get(0).xProperty().add(0), rects.get(0).yProperty().add(DEFAULT_RECT_ONE_H / 2));
		Anchor anch4 = new Anchor(Color.DARKRED, rects.get(0).xProperty().add(DEFAULT_RECT_WIDTH / 2), rects.get(0).yProperty().add(DEFAULT_RECT_ONE_H));
		
		switch (size) {
		
		case 1:
		{	
			if (updateAnchors)
			{
				if(remove && !removeAll)
				{
					// Do Nothing - removes anchors in above code
				}
				else if (!remove && !removeAll)
				{
					anch2 = new Anchor(Color.DARKRED, rects.get(0).xProperty().add(DEFAULT_RECT_WIDTH), rects.get(0).yProperty().add((DEFAULT_RECT_ONE_H + DEFAULT_RECT_TWO_H) / 2));
					anch3 = new Anchor(Color.DARKRED, rects.get(0).xProperty().add(0), rects.get(0).yProperty().add((DEFAULT_RECT_ONE_H + DEFAULT_RECT_TWO_H) / 2));
					anch4 = new Anchor(Color.DARKRED, rects.get(0).xProperty().add(DEFAULT_RECT_WIDTH / 2), rects.get(0).yProperty().add(DEFAULT_RECT_ONE_H + DEFAULT_RECT_TWO_H));
				}
			}
			break;
		}	
		case 2:
		{
			if (updateAnchors)
			{
				if(remove && !removeAll)
				{
					anch2 = new Anchor(Color.DARKRED, rects.get(0).xProperty().add(DEFAULT_RECT_WIDTH), rects.get(0).yProperty().add(DEFAULT_RECT_ONE_H / 2));
					anch3 = new Anchor(Color.DARKRED, rects.get(0).xProperty().add(0), rects.get(0).yProperty().add(DEFAULT_RECT_ONE_H / 2));
					anch4 = new Anchor(Color.DARKRED, rects.get(0).xProperty().add(DEFAULT_RECT_WIDTH / 2), rects.get(0).yProperty().add(DEFAULT_RECT_ONE_H));
				}
				else if (!remove && !removeAll)
				{
					anch2 = new Anchor(Color.DARKRED, rects.get(0).xProperty().add(DEFAULT_RECT_WIDTH), rects.get(0).yProperty().add((DEFAULT_RECT_ONE_H + DEFAULT_RECT_TWO_H + DEFAULT_RECT_THREE_H) / 2));
					anch3 = new Anchor(Color.DARKRED, rects.get(0).xProperty().add(0), rects.get(0).yProperty().add((DEFAULT_RECT_ONE_H + DEFAULT_RECT_TWO_H + DEFAULT_RECT_THREE_H) / 2));
					anch4 = new Anchor(Color.DARKRED, rects.get(0).xProperty().add(DEFAULT_RECT_WIDTH / 2), rects.get(0).yProperty().add(DEFAULT_RECT_ONE_H + DEFAULT_RECT_TWO_H + DEFAULT_RECT_THREE_H));
				}
			}
			else
			{
				anch1 = new Anchor(Color.DARKRED, rects.get(0).xProperty().add(DEFAULT_RECT_WIDTH / 2), rects.get(0).yProperty().add(0));
				anch2 = new Anchor(Color.DARKRED, rects.get(0).xProperty().add(DEFAULT_RECT_WIDTH), rects.get(0).yProperty().add((DEFAULT_RECT_ONE_H + DEFAULT_RECT_TWO_H) / 2));
				anch3 = new Anchor(Color.DARKRED, rects.get(0).xProperty().add(0), rects.get(0).yProperty().add((DEFAULT_RECT_ONE_H + DEFAULT_RECT_TWO_H) / 2));
				anch4 = new Anchor(Color.DARKRED, rects.get(0).xProperty().add(DEFAULT_RECT_WIDTH / 2), rects.get(0).yProperty().add(DEFAULT_RECT_ONE_H + DEFAULT_RECT_TWO_H));
			}
			
			break;
		}	
		case 3:
		{	
			if (updateAnchors)
			{
				if(remove && !removeAll)
				{
					anch2 = new Anchor(Color.DARKRED, rects.get(0).xProperty().add(DEFAULT_RECT_WIDTH), rects.get(0).yProperty().add((DEFAULT_RECT_ONE_H + DEFAULT_RECT_TWO_H) / 2));
					anch3 = new Anchor(Color.DARKRED, rects.get(0).xProperty().add(0), rects.get(0).yProperty().add((DEFAULT_RECT_ONE_H + DEFAULT_RECT_TWO_H) / 2));
					anch4 = new Anchor(Color.DARKRED, rects.get(0).xProperty().add(DEFAULT_RECT_WIDTH / 2), rects.get(0).yProperty().add(DEFAULT_RECT_ONE_H + DEFAULT_RECT_TWO_H));
				}
				else if (!remove && !removeAll)
				{
					anch2 = new Anchor(Color.DARKRED, rects.get(0).xProperty().add(DEFAULT_RECT_WIDTH), rects.get(0).yProperty().add((DEFAULT_RECT_ONE_H + DEFAULT_RECT_TWO_H + DEFAULT_RECT_THREE_H + DEFAULT_RECT_FOUR_H) / 2));
					anch3 = new Anchor(Color.DARKRED, rects.get(0).xProperty().add(0), rects.get(0).yProperty().add((DEFAULT_RECT_ONE_H + DEFAULT_RECT_TWO_H + DEFAULT_RECT_THREE_H + DEFAULT_RECT_FOUR_H) / 2));
					anch4 = new Anchor(Color.DARKRED, rects.get(0).xProperty().add(DEFAULT_RECT_WIDTH / 2), rects.get(0).yProperty().add(DEFAULT_RECT_ONE_H + DEFAULT_RECT_TWO_H + DEFAULT_RECT_THREE_H + DEFAULT_RECT_FOUR_H));
				}
			}
			else
			{
				anch1 = new Anchor(Color.DARKRED, rects.get(0).xProperty().add(DEFAULT_RECT_WIDTH / 2), rects.get(0).yProperty().add(0));
				anch2 = new Anchor(Color.DARKRED, rects.get(0).xProperty().add(DEFAULT_RECT_WIDTH), rects.get(0).yProperty().add((DEFAULT_RECT_ONE_H + DEFAULT_RECT_TWO_H + DEFAULT_RECT_THREE_H) / 2));
				anch3 = new Anchor(Color.DARKRED, rects.get(0).xProperty().add(0), rects.get(0).yProperty().add((DEFAULT_RECT_ONE_H + DEFAULT_RECT_TWO_H + DEFAULT_RECT_THREE_H) / 2));
				anch4 = new Anchor(Color.DARKRED, rects.get(0).xProperty().add(DEFAULT_RECT_WIDTH / 2), rects.get(0).yProperty().add(DEFAULT_RECT_ONE_H + DEFAULT_RECT_TWO_H + DEFAULT_RECT_THREE_H));
			}
				
			break;
		}	
		default:
		{	
			if (updateAnchors)
			{
				if(remove && !removeAll)
				{
					anch2 = new Anchor(Color.DARKRED, rects.get(0).xProperty().add(DEFAULT_RECT_WIDTH), rects.get(0).yProperty().add((DEFAULT_RECT_ONE_H + DEFAULT_RECT_TWO_H + DEFAULT_RECT_THREE_H) / 2));
					anch3 = new Anchor(Color.DARKRED, rects.get(0).xProperty().add(0), rects.get(0).yProperty().add((DEFAULT_RECT_ONE_H + DEFAULT_RECT_TWO_H + DEFAULT_RECT_THREE_H) / 2));
					anch4 = new Anchor(Color.DARKRED, rects.get(0).xProperty().add(DEFAULT_RECT_WIDTH / 2), rects.get(0).yProperty().add(DEFAULT_RECT_ONE_H + DEFAULT_RECT_TWO_H + DEFAULT_RECT_THREE_H));
				}
				else if (!remove && !removeAll)
				{
					// Can't add to a ClassBox with 4 fields - Do Nothing
				}
			}
			else
			{
				anch1 = new Anchor(Color.DARKRED, rects.get(0).xProperty().add(DEFAULT_RECT_WIDTH / 2), rects.get(0).yProperty().add(0));
				anch2 = new Anchor(Color.DARKRED, rects.get(0).xProperty().add(DEFAULT_RECT_WIDTH), rects.get(0).yProperty().add((DEFAULT_RECT_ONE_H + DEFAULT_RECT_TWO_H + DEFAULT_RECT_THREE_H + DEFAULT_RECT_FOUR_H) / 2));
				anch3 = new Anchor(Color.DARKRED, rects.get(0).xProperty().add(0), rects.get(0).yProperty().add((DEFAULT_RECT_ONE_H + DEFAULT_RECT_TWO_H + DEFAULT_RECT_THREE_H + DEFAULT_RECT_FOUR_H) / 2));
				anch4 = new Anchor(Color.DARKRED, rects.get(0).xProperty().add(DEFAULT_RECT_WIDTH / 2), rects.get(0).yProperty().add(DEFAULT_RECT_ONE_H + DEFAULT_RECT_TWO_H + DEFAULT_RECT_THREE_H + DEFAULT_RECT_FOUR_H));
			}
			
			break;
		}
		
		}
		
		if (!removeAll)
		{
			anchors.add (anch1);
			anchors.add (anch2);
			anchors.add (anch4);
			anchors.add (anch3);
		
			pane.getChildren().addAll (anch1, anch2, anch3, anch4);
		}
	}
}