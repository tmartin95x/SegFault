import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class ClassDiagram {
	
	/***
	 * This creates a class diagram and places the rectangle(field) based on which field needs to be added.
	 * @param numberOfFields
	 * @return
	 */
	public Rectangle createClassDiagram(int numberOfFields)
	{
		Rectangle r = new Rectangle();
		
		if(numberOfFields == 1)
		{
			r.setX(200);
			r.setY(75);
			r.setWidth(200);
			r.setHeight(50);
			r.setArcWidth(20);
			r.setArcHeight(20);
			r.setFill(Color.TRANSPARENT);
			r.setStroke(Color.BLACK);
			r.setStrokeWidth(4);
		}
		
		if(numberOfFields == 2)
		{	
			r.setX(200);
			r.setY(125);
			r.setWidth(200);
			r.setHeight(100);
			r.setArcWidth(20);
			r.setArcHeight(20);
			r.setFill(Color.TRANSPARENT);
			r.setStroke(Color.BLACK);
			r.setStrokeWidth(4);
		}
		
		if(numberOfFields == 3)
		{	
			r.setX(200);
			r.setY(225);
			r.setWidth(200);
			r.setHeight(100);
			r.setArcWidth(20);
			r.setArcHeight(20);
			r.setFill(Color.TRANSPARENT);
			r.setStroke(Color.BLACK);
			r.setStrokeWidth(4);
		}
		
		if(numberOfFields == 4)
		{	
			r.setX(200);
			r.setY(325);
			r.setWidth(200);
			r.setHeight(100);
			r.setArcWidth(20);
			r.setArcHeight(20);
			r.setFill(Color.TRANSPARENT);
			r.setStroke(Color.BLACK);
			r.setStrokeWidth(4);
		}
		
		return r; 
	}
}
