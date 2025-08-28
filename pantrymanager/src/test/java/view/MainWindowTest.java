package view;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MainWindowTest {
	
    @BeforeEach
    void setup() {
        MainWindow.getInstance().initialize();
    }
	
    @Test
    void getInstanceTest() {
    	assertNotNull(MainWindow.getInstance());
    }
    
    @Test
	void getFormNameTest() {
		assertEquals("", MainWindow.getInstance().getFormName());
	}
    
    @Test
    void getFormQtyTest() {
    	assertEquals("", MainWindow.getInstance().getFormQty());
    }
    
    @Test
    void getFormWeightTest() {
    	assertEquals("", MainWindow.getInstance().getFormWeight());
    }
    
    @Test
    void getFormCalTest() {
    	assertEquals("", MainWindow.getInstance().getFormCal());
    }
	
    @Test
    void getExpDateTest() {
    	assertNull(MainWindow.getInstance().getExpDate());
    }
    
    @Test
    void getLastCampHoveredTest() {
    	assertNull(MainWindow.getInstance().getLastCompHovered());
    }
    
    @Test
    void toLocalDateTest() {
    	assertEquals(LocalDate.now(), MainWindow.getInstance().getExpLocalDate(new Date()));
    }
    
    
    
    
    
    
    
    
	


}
