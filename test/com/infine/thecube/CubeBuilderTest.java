/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.infine.thecube;

import static org.junit.Assert.assertEquals;
import org.junit.*;

/**
 *
 * @author tony
 */
public class CubeBuilderTest  {
    
    public CubeBuilderTest() {
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
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test
    public void buildCube() {
        Cube cube = new CubeBuilder().create(100, 2, 99);
        long sum = cube.get(10,10,10,10);
        assertEquals(sum,1L);
    }
}
