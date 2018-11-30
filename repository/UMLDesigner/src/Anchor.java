import javafx.beans.binding.DoubleBinding;
import javafx.beans.property.DoubleProperty;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.StrokeType;

public class Anchor extends Circle {
	Anchor(Color color, DoubleBinding x, DoubleBinding y) {
	      super(x.get(), y.get(), 5);
	      setFill(color.deriveColor(1, 1, 1, 0.5));
	      setStroke(color);
	      setStrokeWidth(2);
	      setStrokeType(StrokeType.OUTSIDE);
	      
	      this.centerXProperty().bind(x);
	      this.centerYProperty().bind(y);
	      eventHandling();
    }

    // Enables all event handling with the anchors - for use with the ClassBoxConnectors
    private void eventHandling() {
      final Delta dragDelta = new Delta();
      
      setOnMousePressed(new EventHandler<MouseEvent>() {
        @Override public void handle(MouseEvent mouseEvent) {
          // record a delta distance for the drag and drop operation.
          dragDelta.x = getCenterX() - mouseEvent.getX();
          dragDelta.y = getCenterY() - mouseEvent.getY();
          getScene().setCursor(Cursor.MOVE);
        }
      });
      
      setOnMouseReleased(new EventHandler<MouseEvent>() {
        @Override public void handle(MouseEvent mouseEvent) {
          getScene().setCursor(Cursor.HAND);
        }
      });
      
      setOnMouseEntered(new EventHandler<MouseEvent>() {
        @Override public void handle(MouseEvent mouseEvent) {
          if (!mouseEvent.isPrimaryButtonDown()) {
            getScene().setCursor(Cursor.HAND);
          }
        }
      });
      
      setOnMouseExited(new EventHandler<MouseEvent>() {
        @Override public void handle(MouseEvent mouseEvent) {
          if (!mouseEvent.isPrimaryButtonDown()) {
            getScene().setCursor(Cursor.DEFAULT);
          }
        }
      });
      
    }

    // records relative x and y co-ordinates.
    private class Delta { double x, y; }
}
