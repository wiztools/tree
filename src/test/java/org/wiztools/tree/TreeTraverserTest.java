package org.wiztools.tree;

import java.io.File;
import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author subwiz
 */
public class TreeTraverserTest {
    
    public TreeTraverserTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testSomeMethod() {
        new TreeTraverser(new File("."), new OptionsBean());
        // fail("The test case is a prototype.");
    }
}
