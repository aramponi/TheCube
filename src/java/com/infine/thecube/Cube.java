package com.infine.thecube;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor..
 */
/**
 *
 * @author tony
 */
class Cube {

    ByteBuffer byteBuffer;
    ByteBuffer[] byteBuffers;
    int isize;
    int isize2;
    long lsize;
    long lsize2;
    long lsize3;
    private final int nbOfMappeFiles;
    final int MAX_ITEMS = Integer.MAX_VALUE >> 2;

    Cube(int size) {
        this.lsize = this.isize = size;
        this.lsize2 = this.isize2 = size * size;
        this.lsize3 = lsize2 * lsize;
        System.out.println(Integer.MAX_VALUE / 4 + " = " + MAX_ITEMS);
        System.out.println("cube size=" + lsize3 + " items");
        System.out.println((lsize2 * size * 4L) + " " + lsize2 * size / MAX_ITEMS);
        nbOfMappeFiles = (int)Math.max(1, lsize2 * size / MAX_ITEMS + 1);
        System.out.println("nb of mapped files=" + nbOfMappeFiles);
        
        try {
            if (nbOfMappeFiles > 1) {
                byteBuffers = new ByteBuffer[size];
                for (int i = 0; i < nbOfMappeFiles; i++) {
                    RandomAccessFile file = new RandomAccessFile("cube" + i + ".dat", "rw");
                    byteBuffers[i] = file.getChannel().map(FileChannel.MapMode.READ_WRITE, 0, Integer.MAX_VALUE);
                }
            } else {
                RandomAccessFile file = new RandomAccessFile("cube0.dat", "rw");
                byteBuffer = file.getChannel().map(FileChannel.MapMode.READ_WRITE, 0, Integer.MAX_VALUE);

            }
        } catch (IOException ex) {
            Logger.getLogger(Cube.class.getName()).log(Level.SEVERE, null, ex);
        }



    }

    /*
     * get the value at the given i,j,k
     */
    int get(int i, int j, int k) {
        if (nbOfMappeFiles > 1) {
            long idx = 4 * (i * lsize2 + j * lsize + k);
            int chunck = (int) (idx / Integer.MAX_VALUE);
            idx = idx % Integer.MAX_VALUE;
            byteBuffers[chunck].position((int) idx);
            return byteBuffers[chunck].getInt();
        } else {
            int idx = 4 * (i * isize2 + j * isize + k);
            byteBuffer.position(idx);
            return byteBuffer.getInt();
        }

    }

    long get(int i, int j, int from_k, int to_k) {

        long sum = 0;
        if (nbOfMappeFiles > 1) {
            long idx = 4 * (i * lsize2 + j * lsize + from_k);
            int chunck = (int) (idx / Integer.MAX_VALUE);
            idx = idx % Integer.MAX_VALUE;
            byteBuffers[chunck].position((int) idx);
            for (int k = from_k; k < to_k; k++) {
                sum += byteBuffers[chunck].getInt();
            }
        } else {
            int idx = 4 * (i * isize2 + j * isize + from_k);
            byteBuffer.position(idx);
            for (int k = from_k; k < to_k; k++) {
                sum += byteBuffer.getInt();
            }
        }

        return sum;

    }

    void fill(Generator gen) {
        if (nbOfMappeFiles > 1) {
            ByteBuffer tmp ;
            for (int j = 0; j < nbOfMappeFiles - 1; j++) {
                tmp = byteBuffers[j];
                for (int i = 0; i < MAX_ITEMS; i++) {                    
                    tmp.putInt(gen.getNext());
                }
            }
            tmp = byteBuffers[nbOfMappeFiles - 1];
            for (long i = (nbOfMappeFiles - 1) * MAX_ITEMS; i < lsize3; i++) {
                tmp.putInt(gen.getNext());
            }
        } else {
            for (int i = 0; i < isize * isize2; i++) {
                byteBuffer.putInt(gen.getNext());
            }
        }
    }
}
