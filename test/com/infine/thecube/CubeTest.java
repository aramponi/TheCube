/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.infine.thecube;

import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author tony
 */
public class CubeTest {

    public CubeTest() {
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

    static class testGenerator implements Generator {

        int counter;

        public int getNext() {
            return counter++;
        }
    }

    /**
     * Test of get method, of class Cube.
     */
    @Test
    public void testGet_3args() {
        final int size = 256;
        Cube cube = new Cube(size);
        cube.fill(new testGenerator());

        int result = cube.get(11, 22, 33);
        assertEquals(11 * size * size + 22 * size + 33, result);
    }

    /**
     * Test of fill method, of class Cube.
     */
    @Test
    public void testFill() {
        final int size = 100;
        Cube cube = new Cube(size);
        testGenerator tgen = new testGenerator();
        cube.fill(tgen);
        assertEquals(tgen.counter, size*size*size);
    }

    /**
     * Test of get method, of class Cube.
     */
    @Test
    public void testGet_4args() {
        System.out.println("get");
        int i = 0;
        int i0 = 0;
        int i1 = 0;
        int i2 = 0;
        Cube instance = null;
        long expResult = 0L;
        long result = instance.get(i, i0, i1, i2);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
