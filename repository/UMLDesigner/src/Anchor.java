import java.util.ArrayList;

import javafx.beans.binding.DoubleBinding;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.StrokeType;

public class Anchor extends Circle {
	
	private ClassBoxConnector currConnector;
	private boolean hasConnector;
	private DoubleBinding doubleX;
	private DoubleBinding doubleY;
	private boolean mouseEntered;
	private Color fillColor;
	private int pos;
	
	Anchor(Color color, DoubleBinding x, DoubleBinding y, Pane pane, int pos) {
	      super(x.get(), y.get(), 5);
	      setFill(color.deriveColor(1, 1, 1, 0.5));
	      setStroke(color);
	      setStrokeWidth(2);
	      setStrokeType(StrokeType.OUTSIDE);
	      
	      this.centerXProperty().bind(x);
	      this.centerYProperty().bind(y);
	      eventHandling(pane);
	      
	      doubleX = x;
	      doubleY = y;
	      fillColor = color;
	      this.pos = pos;
	     
	      hideAnchor();
    }
	Anchor() {
	 
  }
    // Enables all event handling with the anchors - for use with the ClassBoxConnectors
    private void eventHandling(Pane pane) {
      final Delta dragDelta = new Delta();
      
      setOnMousePressed(new EventHandler<MouseEvent>() {
        @Override public void handle(MouseEvent mouseEvent) {
          // record a delta distance for the drag and drop operation.
          dragDelta.x = getCenterX() - mouseEvent.getX();
          dragDelta.y = getCenterY() - mouseEvent.getY();

          double x = doubleX.doubleValue();
          double y = doubleY.doubleValue();
          
          ClassBoxConnector connector = new ClassBoxConnector(x,y,mouseEvent.getX(),mouseEvent.getY(), pane);
          currConnector = connector;
          hasConnector = true;
//          System.out.println("Going off");
        }
      });
      
      setOnMouseReleased(new EventHandler<MouseEvent>() {
        @Override public void handle(MouseEvent mouseEvent) {        	  
        }
      });
      
      setOnMouseEntered(new EventHandler<MouseEvent>() {
        @Override public void handle(MouseEvent mouseEvent) {
          if (!mouseEvent.isPrimaryButtonDown()) {
            mouseEntered = true;
          }
        }
      });
      
      setOnMouseExited(new EventHandler<MouseEvent>() {
        @Override public void handle(MouseEvent mouseEvent) {
          if (!mouseEvent.isPrimaryButtonDown()) {
            mouseEntered = false;
          }
        }
      });
    }
    public boolean mouseEntered() {
    	return mouseEntered;
    }
    public boolean hasConnector () {
    	return hasConnector;
    }
    public ClassBoxConnector getConnector() {
    	return currConnector;
    }
    public void setHasConnector(boolean connecting) {
    	hasConnector = connecting;
    }
    public void setEntered (boolean isEntered) {
    	mouseEntered = isEntered;
    }
    public void resetFillColor() {
    	fillColor = fillColor.deriveColor(1, 1, 1, 0.5);
        setFill(fillColor);
    }
    public double getDoubleX() {
    	return doubleX.doubleValue();
    }
    public double getDoubleY() {
    	return doubleY.doubleValue();
    }
    public Integer getPosition() {
    	return pos;
    }
    public void hideAnchor() {
    	setOpacity(0);
    }
    public void showAnchor() {
    	setOpacity(100);
    }
    // records relative x and y co-ordinates.
    private class Delta { double x, y; }
   
}