import java.awt.MouseInfo;
import java.util.ArrayList;
import java.util.List;

import javafx.application.*;
import javafx.event.EventHandler;
import javafx.scene.*;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.*;
import javafx.geometry.*;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

/***
 * This program will have the capability of adding class diagrams with a different number of fields
 * and editing the different class diagrams that were created.
 * 
 * @author Tyler Martin, Zack Mixa, Eric Frey, Patrick Hack, Bill Shannon
 * @Version: Iteration 1
 * @Due: 10/4/2018
 * 
 */
public class UMLDesigner extends Application
{
	private List<ClassBox> addedClassBoxes = new ArrayList<>();
	
	@Override
	public void start(Stage arg) throws Exception
	{
		Pane pane = new Pane();
		Stage stage = new Stage();
		Scene scene = new Scene(pane);

		Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();

		// Set Stage boundaries to visible bounds of the main screen
		stage.setX(primaryScreenBounds.getMinX());
		stage.setY(primaryScreenBounds.getMinY());
		stage.setWidth(primaryScreenBounds.getWidth());
		stage.setHeight(primaryScreenBounds.getHeight());
		stage.setTitle("UML Designer");
		stage.setScene(scene);
		
		// Create the vbox to hold all of the buttons
		VBox toolBar = createToolbar(primaryScreenBounds);
		HBox editBar = createEditBar(primaryScreenBounds);
		
		Button create1 = new Button("Create Class Diagram(1)");
		Button create2 = new Button("Create Class Diagram(2)");
		Button create3 = new Button("Create Class Diagram(3)");
		Button create4 = new Button("Create Class Diagram(4)");
		Button remove = new Button("Remove Class Diagram");
		
		// Button event handling
		create1.setOnAction((event) ->
		{
			ClassBox box = new ClassBox(1, pane);
			
			addedClassBoxes.add(box);
			
			//Set Mouse events for added rectangles
			for (Rectangle rect : box.getRects()) {
								
				setRectMouseEvent(rect, box, pane, editBar);
			}
		});
		
		create2.setOnAction((event) -> 
		{
			ClassBox box = new ClassBox(2, pane);
			addedClassBoxes.add(box);
			
			//Set Mouse events for added rectangles
			for (Rectangle rect : box.getRects()) {
				
				setRectMouseEvent(rect, box, pane, editBar);
			}
		});
		
		create3.setOnAction((event) -> 
		{
			ClassBox box = new ClassBox(3, pane);
			addedClassBoxes.add(box);
			
			//Set Mouse events for added rectangles
			for (Rectangle rect : box.getRects()) {
				
				setRectMouseEvent(rect, box, pane, editBar);
			}
		});
		
		create4.setOnAction((event) -> 
		{
			ClassBox box = new ClassBox(4, pane);
			addedClassBoxes.add(box);
			
			//Set Mouse events for added rectangles
			for (Rectangle rect : box.getRects()) {
				
				setRectMouseEvent(rect, box, pane, editBar);
			}
		});
		remove.setOnAction((event) -> 
		{
			//deletes a ClassBox and all it's rectangles and textAreas
			for (int i = 0; i < addedClassBoxes.size(); i++) {
				if (addedClassBoxes.get(i).isSelected()) {
					addedClassBoxes.get(i).empty(pane);
					addedClassBoxes.remove(addedClassBoxes.get(i));
					pane.getChildren().remove(editBar);
					break;
				}
			}
		});
		
		
		// Add the buttons to the toolbar
		toolBar.getChildren().add(create1);
		toolBar.getChildren().add(create2);
		toolBar.getChildren().add(create3);
		toolBar.getChildren().add(create4);
		toolBar.getChildren().add(remove);
		
		scene.getStylesheets().add("transparent-text-area.css");

		// Add the toolbars to the pane
		pane.getChildren().add(toolBar);
		//pane.getChildren().add(editBar);

		stage.show();
	}	
	
	/***
	 * Initial entry
	 * @param args
	 */
	public static void main(String[] args)
	{
		launch(args);
	}
	
	/***
	 * Sets the on clicked events for rectangles. It handles selecting and unselecting the ClassBoxes,
	 * editing the TextAreas, and creating the editBar if a rectangle is selected.
	 * 
	 * @param Rectangle, ClassBox, Pane, HBox
	 */
	public void setRectMouseEvent(Rectangle rect, ClassBox box, Pane pane, HBox editBar) {
		rect.setOnMouseClicked((event) -> {
			
			int clicks = event.getClickCount();
			
			//If a rectangle is double clicked, edit its TextArea
			if (clicks > 1) {
					box.editText(rect);
					
			//Handles the selecting of ClassBoxes
			}else {
				//First deselect all ClassBoxes
				for (ClassBox boxes : addedClassBoxes) {
					if(boxes.equals(box)) {
					}else {
						boxes.selectOff();
					}
				}
				
				//select or unselect the box
				box.toggleSelect();
				
				//take focus away from any TextAreas currently being edited
				pane.requestFocus();
				
				if (box.isSelected()) { 
					//Create toolbox
					pane.getChildren().remove(editBar);
					
					setupEditBar(editBar, box, pane);
					
					//Disables and Enables the add button on the editBar based on the size of the ClassBox selected
					Node node = editBar.getChildren().get(1);
					int size = box.getRects().size();
					if (size == 4) {
						node.setDisable(true);
					}else {
						node.setDisable(false);
					}
					
					pane.getChildren().add(editBar);
					
					
				}else {
					pane.getChildren().remove(editBar);
				}
			}
		});
	}
	
	/***
	 * This creates the vertical toolbox that stores all of the buttons for creating the class diagrams and removing them.
	 * @param primaryScreenBounds
	 * @return
	 */
	public VBox createToolbar(Rectangle2D primaryScreenBounds)
	{
		VBox toolbar = new VBox(8);
		toolbar.setPrefWidth(primaryScreenBounds.getWidth() / 12);
		toolbar.setPrefHeight(primaryScreenBounds.getHeight());
		toolbar.setLayoutX(0);
		toolbar.setLayoutY(0);
		toolbar.setBackground(new Background(new BackgroundFill(Color.WHITE, new CornerRadii(10), Insets.EMPTY)));
        toolbar.setBorder(new Border(new BorderStroke(Color.GREY, BorderStrokeStyle.SOLID, new CornerRadii(0), new BorderWidths(1))));
        toolbar.setAlignment(Pos.TOP_CENTER);
        toolbar.setSpacing(20);
        toolbar.setPadding(new Insets (100,0,0,0));
        
        return toolbar;
	}
	
	/***
	 * This creates the horizontal box that stores all of the buttons for the inspector of the class diagram.
	 * @param primaryScreenBounds
	 * @return the editBar
	 */
	public HBox createEditBar(Rectangle2D primaryScreenBounds)
	{
		HBox editBar = new HBox(8);
		editBar.setPrefWidth(primaryScreenBounds.getWidth() * 0.8);
		editBar.setPrefHeight(primaryScreenBounds.getHeight() / 20);
		editBar.setLayoutX(primaryScreenBounds.getWidth() / 12);
		editBar.setLayoutY(0);
		editBar.setBackground(new Background(new BackgroundFill(Color.WHITE, new CornerRadii(10), Insets.EMPTY)));
		editBar.setBorder(new Border(new BorderStroke(Color.GREY, BorderStrokeStyle.SOLID, new CornerRadii(0), new BorderWidths(1))));
		editBar.setAlignment(Pos.CENTER_LEFT);
        editBar.setPadding(new Insets (0,0,0,50));

        return editBar;
	}
	
	/***
	 * This will create the 'edit' bar which will only display when a class diagram is highlighted in red.
	 * The edit bar will need to be re-created and added a child each time a diagram is selected because
	 * it will be removed upon un-selecting. The remove button will remove the lowest field of the diagram
	 * and the add button will add a new field to the bottom of the selected diagram (up to 4).
	 * @param editBar
	 * @param selectedRectangles
	 * @param pane
	 * @param addedRectangles
	 */
	public void setupEditBar(HBox editBar, ClassBox box, Pane pane)
	{
		
		if (editBar.getChildren().isEmpty())
		{
		
			Button remove = new Button("Remove Field");
			Button add = new Button("Add Field");

			editBar.getChildren().add(remove);
			editBar.getChildren().add(add);
		
			//Set button clicked event for remove button
			remove.setOnAction((event) -> 
			{
				//remove field from ClassBox
				for (ClassBox boxes : addedClassBoxes) {
					if (boxes.isSelected()) {
						boxes.removeLast(pane);
						
						int boxSize = boxes.getRects().size();
						if(boxSize == 0) {
							pane.getChildren().remove(editBar);
						}
						//Enable button
						add.setDisable(false);
					}
				}
			});
		
			//Set button clicked event for add button
			add.setOnAction((event) -> 
			{	
				//add field to ClassBox if there is room
				for (ClassBox boxes : addedClassBoxes) {
					if (boxes.isSelected()) {
						boxes.add(pane);
												
						int boxSize = boxes.getRects().size();
						
						setRectMouseEvent(boxes.getRects().get(boxSize-1), box, pane, editBar);
						
						//disable button if there are now 4 fields
						if (boxSize == 4) {
							add.setDisable(true);
						}
					}
				}
			});		
		}
	}
}
