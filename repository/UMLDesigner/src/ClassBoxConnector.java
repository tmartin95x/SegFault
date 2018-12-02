import java.awt.geom.Point2D;

import javafx.beans.property.DoubleProperty;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.StrokeLineCap;

public class ClassBoxConnector extends Line{
	
	private DoubleProperty x;
	private DoubleProperty y;
	
	private Anchor start;
	private Anchor end;
	
	private Line selectorLine;
	private String type;
	
	private boolean isSelected = false;
	ClassBoxConnector(double startX, double startY, double endX, double endY, Pane pane) {
		setStartX(startX);
		setStartY(startY);
		setEndX(endX);
		setEndY(endY);
        pane.getChildren().add(this);
      }
	
	ClassBoxConnector(Anchor start, Anchor end, Pane pane, String type) {
		
		selectorLine = new Line();
		
		setStartX(start.getDoubleX());
		setStartY(start.getDoubleY());
		setEndX(end.getDoubleX());
		setEndY(end.getDoubleY());
				
		selectorLine.setStartX(start.getDoubleX());
		selectorLine.setStartY(start.getDoubleY());
		selectorLine.setEndX(end.getDoubleX());
		selectorLine.setEndY(end.getDoubleY());
		
		startXProperty().bind(start.centerXProperty());
		startYProperty().bind(start.centerYProperty());
		endXProperty().bind(end.centerXProperty());
		endYProperty().bind(end.centerYProperty());
		
		selectorLine.startXProperty().bind(start.centerXProperty());
		selectorLine.startYProperty().bind(start.centerYProperty());
		selectorLine.endXProperty().bind(end.centerXProperty());
		selectorLine.endYProperty().bind(end.centerYProperty());
		
		selectorLine.setStrokeWidth(30);
		selectorLine.setOpacity(0);
		
		this.type = type;
		this.start = start;
		this.end = end;
		
        pane.getChildren().add(this);
        pane.getChildren().add(selectorLine);
      }
	ClassBoxConnector(Anchor start, Anchor end, String type) {
		
		selectorLine = new Line();
		
		setStartX(start.getDoubleX());
		setStartY(start.getDoubleY());
		setEndX(end.getDoubleX());
		setEndY(end.getDoubleY());
				
		selectorLine.setStartX(start.getDoubleX());
		selectorLine.setStartY(start.getDoubleY());
		selectorLine.setEndX(end.getDoubleX());
		selectorLine.setEndY(end.getDoubleY());
		
		startXProperty().bind(start.centerXProperty());
		startYProperty().bind(start.centerYProperty());
		endXProperty().bind(end.centerXProperty());
		endYProperty().bind(end.centerYProperty());
		
		selectorLine.startXProperty().bind(start.centerXProperty());
		selectorLine.startYProperty().bind(start.centerYProperty());
		selectorLine.endXProperty().bind(end.centerXProperty());
		selectorLine.endYProperty().bind(end.centerYProperty());
		
		selectorLine.setStrokeWidth(30);
		selectorLine.setOpacity(0);
		
		this.type = type;
		this.start = start;
		this.end = end;

      }
	public void setLineEndX(double x) {
		setEndX(x);
	}
	public void setLineEndY(double y) {
		setEndY(y);
	}
	public void setLineStartX(double x) {
		setStartX(x);
	}
	public void setLineStartY(double y) {
		setStartY(y);
	}
	public Line getLine() {
		return this;
	}
	public Line getSelectorLine() {
		return selectorLine;
	}
	public void bindStart(Anchor anchor) {
		startXProperty().bind(anchor.centerXProperty());
		startYProperty().bind(anchor.centerYProperty());
		
		selectorLine.startXProperty().bind(anchor.centerXProperty());
		selectorLine.startYProperty().bind(anchor.centerYProperty());
	}
	public void bindEnd(Anchor anchor) {
		
		endXProperty().bind(anchor.centerXProperty());
		endYProperty().bind(anchor.centerYProperty());
		
		selectorLine.endXProperty().bind(anchor.centerXProperty());
		selectorLine.endYProperty().bind(anchor.centerYProperty());
	}
	public Anchor getStartAnchor() {
		return start;
	}
	public Anchor getEndAnchor() {
		return end;
	}
	public void setStartAnchor(Anchor anchor) {
		start = anchor;
	}
	public void setEndAnchor(Anchor anchor) {
		end = anchor;
	}
	public void selectOff() {
		isSelected = false;
		setStrokeColor(Color.BLACK);
	}
	public void toggleSelect() {
		isSelected = !isSelected;
		if (isSelected) {
			setStrokeColor(Color.RED);
		}else {
			setStrokeColor(Color.BLACK);
		}
	}
	private void setStrokeColor(Color color) {
		setStroke(color);
		selectorLine.setStroke(color);
	}
	public boolean isSelected() {
		return isSelected;
	}
	public void setType(String type) {
		if (type == "dotted") {
			getStrokeDashArray().addAll(5d);
		}else {
			getStrokeDashArray().removeAll(5d);
		}
		this.type = type;
			
	}
	public String getType() {
		return type;
	}
}