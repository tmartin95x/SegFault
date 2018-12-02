import java.awt.MouseInfo;
import java.util.ArrayList;
import java.util.List;

import org.junit.platform.commons.util.StringUtils;

import javafx.application.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.*;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.transform.Scale;
import javafx.stage.*;
import javafx.geometry.*;
import javafx.print.PageLayout;
import javafx.print.PageOrientation;
import javafx.print.Paper;
import javafx.print.Printer;
import javafx.print.PrinterJob;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

/***
 * This program will have the capability of adding class diagrams with a different number of fields
 * and editing the different class diagrams that were created.
 * 
 * @author Tyler Martin, Zack Mixa, Eric Frey, Patrick Hack, Bill Shannon
 * @Version: Iteration 3
 * @Due: 12/4/2018
 * 
 */
public class UMLDesigner extends Application
{
	private List<ClassBox> addedClassBoxes = new ArrayList<>();
	public List<ClassBoxConnector> addedConnectors = new ArrayList();
	
	private HBox editBar;
	@Override
	public void start(Stage arg) throws Exception
	{
		Pane pane = new Pane();
		Stage stage = new Stage();
		Scene scene = new Scene(pane);

		setPaneMouseEvent(pane);
		
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
		this.editBar = editBar;
		
		TextField numBoxes = new TextField("Add Multuple: ");
		Button create1 = new Button("Create Class Diagram(1)");
		Button create2 = new Button("Create Class Diagram(2)");
		Button create3 = new Button("Create Class Diagram(3)");
		Button create4 = new Button("Create Class Diagram(4)");
		Button remove = new Button("Remove Class Diagram");
		Button print = new Button("Print UML Diagram");
		
		// Button event handling
		create1.setOnAction((event) ->
		{
			int num;
			try {
				num = Integer.parseInt(numBoxes.getText());
			}catch(NumberFormatException e) {
				num = 1;
			}
						
			for (int i = 0; i < num; i++) {
				ClassBox box = new ClassBox(1, pane, i);
				
				addedClassBoxes.add(box);
				
				//Set Mouse events for added rectangles
				for (Rectangle rect : box.getRects()) {
									
					setRectMouseEvent(rect, box, pane);
				}
			}
			
			numBoxes.setText("Add Multiple:");
		});
		
		create2.setOnAction((event) -> 
		{
			int num;
			try {
				num = Integer.parseInt(numBoxes.getText());
			}catch(NumberFormatException e) {
				num = 1;
			}
			
			for (int i = 0; i < num; i++) {
				ClassBox box = new ClassBox(2, pane, i);
				addedClassBoxes.add(box);
				
				//Set Mouse events for added rectangles
				for (Rectangle rect : box.getRects()) {
					
					setRectMouseEvent(rect, box, pane);
				}
			}
			numBoxes.setText("Add Multiple:");
		});
		
		create3.setOnAction((event) -> 
		{
			int num;
			try {
				num = Integer.parseInt(numBoxes.getText());
			}catch(NumberFormatException e) {
				num = 1;
			}
			
			for (int i = 0; i < num; i++) {
					ClassBox box = new ClassBox(3, pane, i);
				addedClassBoxes.add(box);
				
				//Set Mouse events for added rectangles
				for (Rectangle rect : box.getRects()) {
					
					setRectMouseEvent(rect, box, pane);
				}
			}
			numBoxes.setText("Add Multiple:");
		});
		
		create4.setOnAction((event) -> 
		{
			int num;
			try {
				num = Integer.parseInt(numBoxes.getText());
			}catch(NumberFormatException e) {
				num = 1;
			}
			
			for (int i = 0; i < num; i++) {
				ClassBox box = new ClassBox(4, pane, i);
				addedClassBoxes.add(box);
			
				//Set Mouse events for added rectangles
				for (Rectangle rect : box.getRects()) {
				
					setRectMouseEvent(rect, box, pane);
				}
			}
			numBoxes.setText("Add Multiple:");
		});
		remove.setOnAction((event) -> 
		{
		
			//deletes a ClassBox and all it's rectangles and textAreas
			for (int i = 0; i < addedClassBoxes.size(); i++) {
				
				ArrayList<ClassBoxConnector> removes = new ArrayList();
				
				if (addedClassBoxes.get(i).isSelected()) {
	
					for (Anchor anchor : addedClassBoxes.get(i).getAnchors()) {
						for (ClassBoxConnector connector : addedConnectors) {
							if ((connector.getStartAnchor().equals(anchor)) | (connector.getEndAnchor().equals(anchor))) {

								removes.add(connector);
							}
						}
					}
					for (ClassBoxConnector connectors : removes) {
						pane.getChildren().remove(connectors.getSelectorLine());
						pane.getChildren().remove(connectors.getLine());
					}
		
					addedConnectors.removeAll(removes);
					
					addedClassBoxes.get(i).anchorUpdate(pane, true, true, true, addedConnectors);
					addedClassBoxes.get(i).empty(pane);
					
					addedClassBoxes.remove(addedClassBoxes.get(i));

					pane.getChildren().remove(editBar);
					break;
				}
			}
			for (ClassBox box : addedClassBoxes) {
				for (Anchor anchor : box.getAnchors()) {
					anchor.hideAnchor();
				}
			}
		});
		
		print.setOnAction((event) -> 
		{	
			PrinterJob job = PrinterJob.createPrinterJob();
			job.showPrintDialog(stage);

			Printer printer = job.getPrinter();
			
			PageLayout pageLayout = printer.createPageLayout(Paper.A4, PageOrientation.LANDSCAPE, Printer.MarginType.HARDWARE_MINIMUM);
						
		    double scaleX = pageLayout.getPrintableWidth() / (primaryScreenBounds.getWidth()*2);
		    double scaleY = pageLayout.getPrintableHeight() / (primaryScreenBounds.getHeight()*2);

			Scale scale = new Scale(scaleX, scaleY);
			pane.getTransforms().add(scale);
			
			pane.getChildren().remove(this.editBar);
			
			pane.getChildren().remove(toolBar);
			
			for (ClassBox boxes : addedClassBoxes) {
				boxes.selectOff();
				for (Anchor anchor : boxes.getAnchors()) {
					anchor.hideAnchor();
				}
			}
			for (ClassBoxConnector connectors : addedConnectors) {
				connectors.selectOff();
			}
			
			if (job != null)	{
				job.printPage(pane);
				job.endJob();
			}
			pane.getTransforms().remove(scale);
			pane.getChildren().add(toolBar);
		});
		
		
		// Add the buttons to the toolbar
		toolBar.getChildren().add(numBoxes);
		toolBar.getChildren().add(create1);
		toolBar.getChildren().add(create2);
		toolBar.getChildren().add(create3);
		toolBar.getChildren().add(create4);
		toolBar.getChildren().add(remove);
		toolBar.getChildren().add(print);
		
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
	public void setPaneMouseEvent(Pane pane) {
		pane.setOnMouseDragged((event) -> {
			boolean dragging = false;
			for (ClassBox boxes : addedClassBoxes) {
				for (Anchor anchors : boxes.getAnchors()) {
					if (anchors.hasConnector()) {
						anchors.getConnector().setEndX(MouseInfo.getPointerInfo().getLocation().x-10);
						anchors.getConnector().setEndY(MouseInfo.getPointerInfo().getLocation().y-40);
						anchors.setEntered(false);
						dragging = true;
					}
				}
			}
		});
		pane.setOnMouseReleased((event) -> {
			Anchor first = new Anchor();
			boolean isConnecting = false;
			for (ClassBox boxes : addedClassBoxes) {
				for (Anchor anchors : boxes.getAnchors()) {
					if (anchors.hasConnector()) {
						first = anchors;
						isConnecting = true;
						anchors.setHasConnector(false);
					}
				}
			}
			if (isConnecting) {
				for (ClassBox boxes : addedClassBoxes) {
					for (Anchor anchors : boxes.getAnchors()) {
						
						double mouseX = MouseInfo.getPointerInfo().getLocation().x-10;
						double mouseY = MouseInfo.getPointerInfo().getLocation().y-40;
						
						if ((anchors.getDoubleX() > mouseX - 20) && (anchors.getDoubleX() < mouseX + 20)) {
							if ((anchors.getDoubleY() > mouseY - 20) && (anchors.getDoubleY() < mouseY + 20)) {
								ClassBoxConnector con = new ClassBoxConnector(first, anchors, pane, "solid");
								setConnectorMouseEvent(con.getSelectorLine(),con, pane);
								addedConnectors.add(con);
								anchors.toFront();
							}
						}
					}
				}
				
				pane.getChildren().remove(first.getConnector().getLine());
				pane.getChildren().remove(first.getConnector().getSelectorLine());
			}
			for (ClassBox boxes : addedClassBoxes) {
				for (Anchor anchors : boxes.getAnchors()) {
					anchors.toFront();
				}
					
			}
		});
	}
	public void setRectMouseEvent(Rectangle rect, ClassBox box, Pane pane) {
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
				for (ClassBoxConnector connectors : addedConnectors) {
					connectors.selectOff();
				}
				//select or unselect the box
				box.toggleSelect();
				
				//take focus away from any TextAreas currently being edited
				pane.requestFocus();
				
				if (box.isSelected()) { 
					//Create toolbox
					pane.getChildren().remove(editBar);
					
					for (ClassBox boxes : addedClassBoxes) {
					
						for (Anchor anchor : boxes.getAnchors()) {
							anchor.showAnchor();
						}
					}
					setupEditBar(box, pane);
					
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
					for (ClassBox boxes : addedClassBoxes) {
						for (Anchor anchor : boxes.getAnchors()) {
							anchor.hideAnchor();
						}
					}
					pane.getChildren().remove(editBar);
				}
			}
		});
	}
	
	public void setConnectorMouseEvent(Line line,ClassBoxConnector connector, Pane pane) {
		line.setOnMouseClicked((event) -> {
			
						
			//If a rectangle is double clicked, edit its TextArea
		
			//Handles the selecting of ClassBoxes
		
				//First deselect all ClassBoxes
				for (ClassBoxConnector connectors : addedConnectors) {
					if(connectors.equals(connector)) {
					}else {
						connectors.selectOff();
					}
				}
				for (ClassBox boxes : addedClassBoxes) {
					boxes.selectOff();
				}
				//select or unselect the box
				connector.toggleSelect();
				
				//take focus away from any TextAreas currently being edited
				pane.requestFocus();
				
				if (connector.isSelected()) { 
					//Create toolbox
					pane.getChildren().remove(editBar);
					
					setupConnectorEditBar(connector, pane);
					
					pane.getChildren().add(editBar);
					
					for (ClassBox boxes : addedClassBoxes) {
						for (Anchor anchors : boxes.getAnchors()) {
							anchors.showAnchor();
						}
					}
					
				}else {
					pane.getChildren().remove(editBar);
					
					for (ClassBox boxes : addedClassBoxes) {
						for (Anchor anchors : boxes.getAnchors()) {
							anchors.hideAnchor();
						}
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
	public void setupEditBar(ClassBox box, Pane pane)
	{
		Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
		HBox myEdit = createEditBar(primaryScreenBounds);
		
		if (myEdit.getChildren().isEmpty())
		{
		
			Button remove = new Button("Remove Field");
			Button add = new Button("Add Field");
			
			boolean removedAll = false;
			myEdit.getChildren().add(remove);
			myEdit.getChildren().add(add);
		
			//Set button clicked event for remove button
			remove.setOnAction((event) -> 
			{
				boolean updated = false;
				//remove field from ClassBox
				for (ClassBox boxes : addedClassBoxes) {
					if (boxes.isSelected()) {
						boxes.removeLast(pane);
						
						int boxSize = boxes.getRects().size();
						if(boxSize == 0) {
							pane.getChildren().remove(myEdit);
							
							ArrayList<ClassBoxConnector> removes = new ArrayList();
							
							if (boxes.isSelected()) {
				
								for (Anchor anchor : boxes.getAnchors()) {
									for (ClassBoxConnector connector : addedConnectors) {
										if ((connector.getStartAnchor().equals(anchor)) | (connector.getEndAnchor().equals(anchor))) {
											removes.add(connector);
										}
									}
								}
								for (ClassBoxConnector connectors : removes) {
									pane.getChildren().remove(connectors.getSelectorLine());
									pane.getChildren().remove(connectors.getLine());
								}
								addedConnectors.removeAll(removes);
							}
						}
						//Enable button
						add.setDisable(false);
						
						if (!updated)
						{
							boxes.anchorUpdate(pane, true, true, false, addedConnectors);
							updated = true;
							
						}
					}
				}
				updated = false;
			});
		
			//Set button clicked event for add button
			add.setOnAction((event) -> 
			{	
				boolean updated = false;
				//add field to ClassBox if there is room
				for (ClassBox boxes : addedClassBoxes) {
					if (boxes.isSelected()) {
						boxes.add(pane);
												
						int boxSize = boxes.getRects().size();
						
						setRectMouseEvent(boxes.getRects().get(boxSize-1), box, pane);
						
						//disable button if there are now 4 fields
						if (boxSize == 4) {
							add.setDisable(true);
						}
						if (!updated)
						{
							boxes.anchorUpdate(pane, true, false, false, addedConnectors);
							updated = true;
						}
					}
				}
				updated = false;
			});		
			editBar = myEdit;
		}
	}
	public void setupConnectorEditBar(ClassBoxConnector connector, Pane pane)
	{
		Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
		HBox myEdit = createEditBar(primaryScreenBounds);
		
		if (myEdit.getChildren().isEmpty())
		{
		
			Button remove = new Button("Remove Line");
			Button changeType = new Button("Make Dotted");

			if (connector.getType() == "dotted") {
				changeType.setText("Make Solid");
			}
			
			myEdit.getChildren().add(remove);
			myEdit.getChildren().add(changeType);
			//Set button clicked event for remove button
			remove.setOnAction((event) -> 
			{
				addedConnectors.remove(connector);
				pane.getChildren().remove(connector.getLine());
				pane.getChildren().remove(connector.getSelectorLine());
				pane.getChildren().remove(editBar);
			});
			changeType.setOnAction((event) -> 
			{
				System.out.print(connector.getType());
				if (connector.getType() == "solid") {
					connector.setType("dotted");
					changeType.setText("Make Solid");
				}else {
					connector.setType("solid");
					changeType.setText("Make Dotted");
				}
			});		
			editBar = myEdit;
		}
	}
}
