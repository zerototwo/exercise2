package com;

import java.nio.ByteBuffer;

public class DirectBufferDemo {

    public static void main(String[] args) {

        ByteBuffer.allocateDirect(1000*1025*1025);
    }
}
