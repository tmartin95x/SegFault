import java.awt.MouseInfo;
import java.util.ArrayList;
import java.util.List;

import javafx.application.*;
import javafx.scene.*;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.*;
import javafx.geometry.*;
import javafx.scene.control.*;
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
	@Override
	public void start(Stage arg) throws Exception
	{
		Pane pane = new Pane();
		Stage stage = new Stage();
		Scene scene = new Scene(pane);
		List<Rectangle> selectedRectangles = new ArrayList<>();
		List<Text> addedText = new ArrayList<>();
		
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
		
		ClassDiagram classDiagram = new ClassDiagram();
		
		// Button event handling
		create1.setOnAction((event) ->
		{
			List<Rectangle> addedRectangles = new ArrayList<>();
			Rectangle r1 = classDiagram.createClassDiagram(1);
			addedRectangles.add(r1);
			setRectangleEvents(selectedRectangles, addedRectangles, pane, editBar, addedText);
			pane.getChildren().add(r1);
		});
		
		create2.setOnAction((event) -> 
		{
			List<Rectangle> addedRectangles = new ArrayList<>();
			Rectangle r1 = classDiagram.createClassDiagram(1);
			addedRectangles.add(r1);
			pane.getChildren().add(r1);
			Rectangle r2 = classDiagram.createClassDiagram(2);
			addedRectangles.add(r2);
			setRectangleEvents(selectedRectangles, addedRectangles, pane, editBar, addedText);
			pane.getChildren().add(r2);
		});
		
		create3.setOnAction((event) -> 
		{
			List<Rectangle> addedRectangles = new ArrayList<>();
			Rectangle r1 = classDiagram.createClassDiagram(1);
			addedRectangles.add(r1);
			pane.getChildren().add(r1);
			Rectangle r2 = classDiagram.createClassDiagram(2);
			addedRectangles.add(r2);
			pane.getChildren().add(r2);
			Rectangle r3 = classDiagram.createClassDiagram(3);
			addedRectangles.add(r3);
			setRectangleEvents(selectedRectangles, addedRectangles, pane, editBar, addedText);
			pane.getChildren().add(r3);
		});
		
		create4.setOnAction((event) -> 
		{
			List<Rectangle> addedRectangles = new ArrayList<>();
			Rectangle r1 = classDiagram.createClassDiagram(1);
			addedRectangles.add(r1);
			pane.getChildren().add(r1);
			Rectangle r2 = classDiagram.createClassDiagram(2);
			addedRectangles.add(r2);
			pane.getChildren().add(r2);
			Rectangle r3 = classDiagram.createClassDiagram(3);
			addedRectangles.add(r3);
			pane.getChildren().add(r3);
			Rectangle r4 = classDiagram.createClassDiagram(4);
			addedRectangles.add(r4);
			setRectangleEvents(selectedRectangles, addedRectangles, pane, editBar, addedText);
			pane.getChildren().add(r4);
		});
		
		remove.setOnAction((event) -> 
		{
			boolean removed = false;
			if (pane.getChildren().contains(editBar))
			{
				pane.getChildren().remove(editBar);
			}
			for(Rectangle rectangle : selectedRectangles)
			{
					if(rectangle.getStroke() == Color.RED)
					{
						pane.getChildren().remove(rectangle);
						removed = true;
					}
			}
			for(Text txt : addedText)
			{
				pane.getChildren().remove(txt);
			}
			if (removed)
			{
				selectedRectangles.clear();
			}
			
		});
		
		// Add the buttons to the toolbar
		toolBar.getChildren().add(create1);
		toolBar.getChildren().add(create2);
		toolBar.getChildren().add(create3);
		toolBar.getChildren().add(create4);
		toolBar.getChildren().add(remove);
		
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
	 * This creates the vertical toolbox that stores all of the buttons for creating the class diagrams and removing them.
	 * @param primaryScreenBounds
	 * @return
	 */
	public VBox createToolbar(Rectangle2D primaryScreenBounds)
	{
		VBox toolbar = new VBox(8);
		toolbar.setPrefWidth(primaryScreenBounds.getWidth() / 12);
		toolbar.setPrefHeight(primaryScreenBounds.getHeight() / 2);
		toolbar.setLayoutX(20);
		toolbar.setLayoutY(50);
		toolbar.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, new CornerRadii(10), Insets.EMPTY)));
        toolbar.setBorder(new Border(new BorderStroke(Color.NAVY, BorderStrokeStyle.SOLID, new CornerRadii(10), new BorderWidths(1))));
        toolbar.setAlignment(Pos.TOP_CENTER);
        
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
		editBar.setLayoutX(175);
		editBar.setLayoutY(0);
		editBar.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, new CornerRadii(10), Insets.EMPTY)));
		editBar.setBorder(new Border(new BorderStroke(Color.NAVY, BorderStrokeStyle.SOLID, new CornerRadii(10), new BorderWidths(1))));
		editBar.setAlignment(Pos.CENTER_LEFT);
        
        return editBar;
	}
	
	/***
	 * This is the event handling of the class diagram; it handles the highlighting of the class diagram for it to interacted
	 * with. Class diagrams that are highlighted (only one may be) will be outlined in red, unhighlighted classes will be
	 * highlighted in black.
	 * @param selectedRectangles
	 * @param addedRectangles
	 * @param pane
	 * @param editBar
	 */
	public void setRectangleEvents(List<Rectangle> selectedRectangles, List<Rectangle> addedRectangles, Pane pane, HBox editBar, List<Text> addedText)
	{	
		for(Rectangle rect : addedRectangles)
		{
				// Handles highlighting and unhighlighting
				rect.setOnMouseClicked((event) -> 
				{
					
					if(rect.getStroke() == Color.RED)
					{
						pane.getChildren().remove(editBar);
						for(Rectangle selected : selectedRectangles)
						{
							selected.setStroke(Color.BLACK);
							selected.setStrokeWidth(4);
						}
						selectedRectangles.clear();
						for(Rectangle added : addedRectangles)
						{
							added.setStroke(Color.BLACK);
							added.setStrokeWidth(4);
						}
					}
					else
					{
						for(Rectangle selected : selectedRectangles)
						{
							selected.setStroke(Color.BLACK);
							selected.setStrokeWidth(4);
						}
						//Without clearing the list, it would stack multiple class rectangles onto the list, meaning
						//multiple class rectangles could be moved at once to the same position
						selectedRectangles.clear();
						//Added to stop an error caused by trying to add multiple edit bars if the original box wasn't deselected
						pane.getChildren().remove(editBar);
						
						for(Rectangle added : addedRectangles)
						{
							added.setStroke(Color.RED);
							added.setStrokeWidth(4);
							selectedRectangles.add(added);
						}
						pane.getChildren().add(editBar);
						setupEditBar(editBar, selectedRectangles, pane, addedRectangles, addedText);
						setupTextFields(editBar, selectedRectangles, pane, addedText);
					}
				});
				
				//Handles the dragging
				rect.setOnMouseDragged((event) ->
				{
					double prevHeight = 0;
					
					for(Rectangle selected : selectedRectangles)
					{
						int mouseX = MouseInfo.getPointerInfo().getLocation().x;
						int mouseY = MouseInfo.getPointerInfo().getLocation().y;
						
						selected.setX(mouseX-(selected.getWidth()/2));
						selected.setY(mouseY-65+prevHeight);
						
						prevHeight = prevHeight + selected.getHeight();
						
					}
				});
		}
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
	public void setupEditBar(HBox editBar, List<Rectangle> selectedRectangles, Pane pane, List<Rectangle> addedRectangles, List<Text> addedText)
	{
		if (editBar.getChildren().isEmpty())
		{
			Button remove = new Button("Remove Field");
			Button add = new Button("Add Field");
			
			if (selectedRectangles.size() > 3)
			{
				add.setDisable(true);
			}
		
			editBar.getChildren().add(remove);
			editBar.getChildren().add(add);
		
			remove.setOnAction((event) -> 
			{
				Rectangle last = selectedRectangles.get(selectedRectangles.size() - 1);
				for(Text txt : addedText)
				{
					if(txt.xProperty().get() > last.xProperty().get() && txt.xProperty().get() < last.xProperty().get() + 200)
					{
						pane.getChildren().remove(txt);
						addedText.remove(txt);
						pane.getChildren().remove(last);
						selectedRectangles.remove(last);
						
						if(selectedRectangles.size() == 0)
						{
							pane.getChildren().remove(editBar);
						}
						
						if (selectedRectangles.size() < 4)
						{
							add.setDisable(false);
						}
						
						setupTextFields(editBar, selectedRectangles, pane, addedText);
						return;
					}
				}
				pane.getChildren().remove(last);
				selectedRectangles.remove(last);
				if(selectedRectangles.size() == 0)
				{
					pane.getChildren().remove(editBar);
				}
				
				if (selectedRectangles.size() < 4)
				{
					add.setDisable(false);
				}
				
				setupTextFields(editBar, selectedRectangles, pane, addedText);
			});
		
			add.setOnAction((event) -> 
			{	
				if (selectedRectangles.size() == 1)
				{
					ClassDiagram classDiagram = new ClassDiagram();
					Rectangle r2 = classDiagram.createClassDiagram(2);
					
					//Get the last selected rectangle and set position accordingly;
					Rectangle lastR = selectedRectangles.get(selectedRectangles.size()-1);
					
					r2.setX(lastR.getX());
					r2.setY(lastR.getY()+lastR.getHeight());
					
					addedRectangles.add(r2);
					selectedRectangles.add(r2);
					setRectangleEvents(selectedRectangles, addedRectangles, pane, editBar, addedText);
					pane.getChildren().add(r2);
					r2.setStroke(Color.RED);
					r2.setStrokeWidth(4);
					setupTextFields(editBar, selectedRectangles, pane, addedText);
				}
				
				else if (selectedRectangles.size() == 2)
				{
					ClassDiagram classDiagram = new ClassDiagram();
					Rectangle r3 = classDiagram.createClassDiagram(3);
					
					//Get the last selected rectangle and set position accordingly;
					Rectangle lastR = selectedRectangles.get(selectedRectangles.size()-1);
					
					r3.setX(lastR.getX());
					r3.setY(lastR.getY()+lastR.getHeight());
					
					addedRectangles.add(r3);
					selectedRectangles.add(r3);
					setRectangleEvents(selectedRectangles, addedRectangles, pane, editBar, addedText);
					pane.getChildren().add(r3);
					r3.setStroke(Color.RED);
					r3.setStrokeWidth(4);
					setupTextFields(editBar, selectedRectangles, pane, addedText);
				}
				
				else if (selectedRectangles.size() == 3)
				{
					ClassDiagram classDiagram = new ClassDiagram();
					Rectangle r4 = classDiagram.createClassDiagram(4);
					
					//Get the last selected rectangle and set position accordingly;
					Rectangle lastR = selectedRectangles.get(selectedRectangles.size()-1);
					
					r4.setX(lastR.getX());
					r4.setY(lastR.getY()+lastR.getHeight());
					
					addedRectangles.add(r4);
					selectedRectangles.add(r4);
					setRectangleEvents(selectedRectangles, addedRectangles, pane, editBar, addedText);
					pane.getChildren().add(r4);
					r4.setStroke(Color.RED);
					r4.setStrokeWidth(4);
					setupTextFields(editBar, selectedRectangles, pane, addedText);
				}
				
				if (selectedRectangles.size() > 3)
				{
					add.setDisable(true);
					setupTextFields(editBar, selectedRectangles, pane, addedText);
				}
				
			});
		}
	}
	
	public void setupTextFields(HBox editBar, List<Rectangle> selectedRectangles, Pane pane, List<Text> addedText)
	{	
		editBar.getChildren().remove(2, editBar.getChildren().size());
		
		if (selectedRectangles.size() == 1)
		{	
			TextField tf1 = new TextField("Field 1");
			Button save1 = new Button("Add");
			setupTextSaveEvents(save1, selectedRectangles.get(0), tf1, pane, addedText);
			Label instr = new Label("Use a comma to add a new line.");
			editBar.getChildren().add(instr);
			editBar.getChildren().add(tf1);
			editBar.getChildren().add(save1);
		}
		if (selectedRectangles.size() == 2)
		{
			TextField tf1 = new TextField("Field 1");
			TextField tf2 = new TextField("Field 2");
			Button save1  = new Button("Add");
			setupTextSaveEvents(save1, selectedRectangles.get(0), tf1, pane, addedText);
			Button save2  = new Button("Add");
			setupTextSaveEvents(save2, selectedRectangles.get(1), tf2, pane, addedText);
			Label instr = new Label("Use a comma to add a new line.");
			editBar.getChildren().add(instr);
			editBar.getChildren().add(tf1);
			editBar.getChildren().add(save1);
			editBar.getChildren().add(tf2);
			editBar.getChildren().add(save2);
		}
		if (selectedRectangles.size() == 3)
		{
			TextField tf1 = new TextField("Field 1");
			TextField tf2 = new TextField("Field 2");
			TextField tf3 = new TextField("Field 3");
			Button save1  = new Button("Add");
			setupTextSaveEvents(save1, selectedRectangles.get(0), tf1, pane, addedText);
			Button save2  = new Button("Add");
			setupTextSaveEvents(save2, selectedRectangles.get(1), tf2, pane, addedText);
			Button save3  = new Button("Add");
			setupTextSaveEvents(save3, selectedRectangles.get(2), tf3, pane, addedText);
			Label instr = new Label("Use a comma to add a new line.");
			editBar.getChildren().add(instr);
			editBar.getChildren().add(tf1);
			editBar.getChildren().add(save1);
			editBar.getChildren().add(tf2);
			editBar.getChildren().add(save2);
			editBar.getChildren().add(tf3);
			editBar.getChildren().add(save3);
		}
		if (selectedRectangles.size() == 4)
		{
			TextField tf1 = new TextField("Field 1");
			TextField tf2 = new TextField("Field 2");
			TextField tf3 = new TextField("Field 3");
			TextField tf4 = new TextField("Field 4");
			Button save1  = new Button("Add");
			setupTextSaveEvents(save1, selectedRectangles.get(0), tf1, pane, addedText);
			Button save2  = new Button("Add");
			setupTextSaveEvents(save2, selectedRectangles.get(1), tf2, pane, addedText);
			Button save3  = new Button("Add");
			setupTextSaveEvents(save3, selectedRectangles.get(2), tf3, pane, addedText);
			Button save4  = new Button("Add");
			setupTextSaveEvents(save4, selectedRectangles.get(3), tf4, pane, addedText);
			Label instr = new Label("Use a comma to add a new line.");
			editBar.getChildren().add(instr);
			editBar.getChildren().add(tf1);
			editBar.getChildren().add(save1);
			editBar.getChildren().add(tf2);
			editBar.getChildren().add(save2);
			editBar.getChildren().add(tf3);
			editBar.getChildren().add(save3);
			editBar.getChildren().add(tf4);
			editBar.getChildren().add(save4);
		}
	}
	
	public void setupTextSaveEvents(Button b, Rectangle selectedRectangle, TextField tf, Pane pane, List<Text> addedText)
	{
		b.setOnAction((event) -> 
		{
			String text = tf.getText();
			text = text.replace(",", "\n");
			Text setText = new Text(text);
			addedText.add(setText);
			setText.setX(selectedRectangle.getX() + 5);
			setText.setY(selectedRectangle.getY() + 15);
			pane.getChildren().add(setText);
		});
	}
}
