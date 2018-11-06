import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import org.junit.jupiter.api.Test;

import javafx.scene.layout.Pane;

class UML_Test {

	@Test
	void initSize1() {
		ClassBox box = new ClassBox(1);
		assertEquals(box.size(),1);
	}
	
	@Test
	void initSize2() {
		ClassBox box = new ClassBox(2);
		assertEquals(box.size(),2);
	}
	
	@Test
	void initSize3() {
		ClassBox box = new ClassBox(3);
		assertEquals(box.size(),3);
	}
	
	@Test
	void initSize4() {
		ClassBox box = new ClassBox(4);
		assertEquals(box.size(),4);
	}
		
	@Test
	void toggleSelectOn() {
		ClassBox box = new ClassBox(1);
		box.toggleSelect();
		assertEquals(box.isSelected(), true);
	}
	
	@Test
	void toggleSelectOff() {
		ClassBox box = new ClassBox(1);
		box.toggleSelect();
		box.toggleSelect();
		assertEquals(box.isSelected(), false);
	}
	
	@Test
	void selectOff() {
		ClassBox box = new ClassBox(1);
		box.toggleSelect();
		box.selectOff();
		assertEquals(box.isSelected(), false);
	}
}
