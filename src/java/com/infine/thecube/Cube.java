package com.infine.thecube;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
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
    private final long nbOfMappeFiles;

    Cube(int size) {
        this.lsize = this.isize = size;
        this.lsize2 = this.isize2 = size * size;
        nbOfMappeFiles = Math.max(1, lsize2 * size * 4L / Integer.MAX_VALUE);
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

    int get(int i, int j, int k) {
        if (nbOfMappeFiles > 1) {
            long idx = 4 * (i * lsize2 + j * lsize + k);
            int chunck = (int)(idx / Integer.MAX_VALUE);
            idx = idx % Integer.MAX_VALUE;
            byteBuffers[chunck].position((int)idx);
            return byteBuffers[chunck].getInt();
        } else {
            int idx = 4 * ( i * isize2 + j * isize + k );
            byteBuffer.position(idx);
            return byteBuffer.getInt();
        }

    }

    void fill(Generator gen) {
        if (nbOfMappeFiles > 1) {
            throw new UnsupportedOperationException("Not yet implemented");

        } else {
            for (int i = 0; i < isize*isize2; i++) {
                byteBuffer.putInt(gen.getNext());
            }
        }
    }

    long get(int i, int i0, int i1, int i2) {
        throw new UnsupportedOperationException("Not yet implemented");

    }
    

}
