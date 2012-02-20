/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.infine.thecube;

/**
 *
 * @author tony
 */
public class RandomGenerator implements Generator {

    int m_w = 0;
    int m_z = 0;

    public RandomGenerator(int m_w, int m_z) {
        this.m_w = m_w;
        this.m_z = m_z;
    }
  

    @Override
    public int getNext() {
        //System.out.println(m_z);
        //System.out.println(m_z & 65535);
        //System.out.println(m_z >> 16);

        m_z = 36969 * (m_z & 65535) + (m_z >> 16);
        m_w = 18000 * (m_w & 65535) + (m_w >> 16);
        return ((m_z << 16) + m_w) & 0x7FFFFFFF;  /*
         * 32-bit result
         */

    }
    // http://en.wikipedia.org/wiki/Random_number_generation
}
