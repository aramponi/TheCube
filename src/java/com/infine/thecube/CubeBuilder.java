/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.infine.thecube;

/**
 *
 * @author tony
 */
class CubeBuilder {


    static Cube create(int size, int m_w, int m_z) {

        if (m_w == 0 || m_z == 0) {
            return null;
        }

        RandomGenerator rgen  = new RandomGenerator(m_w, m_z);
        Cube cube = new Cube(size);
        cube.fill(rgen);
        return cube;
        //throw new UnsupportedOperationException("Not yet implemented");
    }
}
