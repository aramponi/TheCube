/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.infine.thecube;

import org.junit.AfterClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;

/**
 *
 * @author tony
 */
public class RandomGeneratorTest {
    
    public RandomGeneratorTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    /**
     * Test of getNext method, of class RandomGenerator.
     */
    @Test
    public void testGetNext() {
        RandomGenerator instance = new RandomGenerator(100, 77);
        assertEquals(1873835840, instance.getNext());
        assertEquals(889766939, instance.getNext());
        assertEquals(1180027696, instance.getNext());
     }
}
