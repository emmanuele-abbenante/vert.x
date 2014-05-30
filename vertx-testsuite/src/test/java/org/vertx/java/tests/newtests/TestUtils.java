/*
 *
 *  * Copyright 2014 Red Hat, Inc.
 *  *
 *  * All rights reserved. This program and the accompanying materials
 *  * are made available under the terms of the Eclipse Public License v1.0
 *  * and Apache License v2.0 which accompanies this distribution.
 *  *
 *  *     The Eclipse Public License is available at
 *  *     http://www.eclipse.org/legal/epl-v10.html
 *  *
 *  *     The Apache License v2.0 is available at
 *  *     http://www.opensource.org/licenses/apache2.0.php
 *  *
 *  * You may elect to redistribute this code under either of these licenses.
 *  *
 *
 */

package org.vertx.java.tests.newtests;

import org.vertx.java.core.buffer.Buffer;

import java.util.Random;

/**
 * @author <a href="http://tfox.org">Tim Fox</a>
 */
public class TestUtils {

  private static Random random = new Random();

  /**
   * Creates a Buffer of random bytes.
   * @param length The length of the Buffer
   * @return the Buffer
   */
  public static Buffer randomBuffer(int length) {
    return randomBuffer(length, false, (byte) 0);
  }

  /**
   * Create an array of random bytes
   * @param length The length of the created array
   * @return the byte array
   */
  public static byte[] randomByteArray(int length) {
    return randomByteArray(length, false, (byte) 0);
  }

  /**
   * Create an array of random bytes
   * @param length The length of the created array
   * @param avoid If true, the resulting array will not contain avoidByte
   * @param avoidByte A byte that is not to be included in the resulting array
   * @return an array of random bytes
   */
  public static byte[] randomByteArray(int length, boolean avoid, byte avoidByte) {
    byte[] line = new byte[length];
    for (int i = 0; i < length; i++) {
      byte rand;
      do {
        rand = randomByte();
      } while (avoid && rand == avoidByte);

      line[i] = rand;
    }
    return line;
  }

  /**
   * Creates a Buffer containing random bytes
   * @param length the size of the Buffer to create
   * @param avoid if true, the resulting Buffer will not contain avoidByte
   * @param avoidByte A byte that is not to be included in the resulting array
   * @return a Buffer of random bytes
   */
  public static Buffer randomBuffer(int length, boolean avoid, byte avoidByte) {
    byte[] line = randomByteArray(length, avoid, avoidByte);
    return new Buffer(line);
  }

  /**
   * @return a random byte
   */
  public static byte randomByte() {
    return (byte) ((int) (Math.random() * 255) - 128);
  }

  /**
   * @return a random int
   */
  public static int randomInt() {
    return random.nextInt();
  }

  /**
   * @return a random long
   */
  public static long randomLong() {
    return random.nextLong();
  }

  /**
   * @return a random boolean
   */
  public static boolean randomBoolean() {
    return random.nextBoolean();
  }

  /**
   * @return a random char
   */
  public static char randomChar() {
    return (char)(random.nextInt(16));
  }

  /**
   * @return a random short
   */
  public static short randomShort() {
    return (short)(random.nextInt(16) - Short.MAX_VALUE);
  }

  /**
   * @return a random random float
   */
  public static float randomFloat() {
    return random.nextFloat();
  }

  /**
   * @return a random random double
   */
  public static double randomDouble() {
    return random.nextDouble();
  }

  /**
   * Creates a String containing random unicode characters
   * @param length The length of the string to create
   * @return a String of random unicode characters
   */
  public static String randomUnicodeString(int length) {
    StringBuilder builder = new StringBuilder(length);
    for (int i = 0; i < length; i++) {
      char c;
      do {
        c = (char) (0xFFFF * Math.random());
      } while ((c >= 0xFFFE && c <= 0xFFFF) || (c >= 0xD800 && c <= 0xDFFF)); //Illegal chars
      builder.append(c);
    }
    return builder.toString();
  }

  /**
   * Creates a random string of ascii alpha characters
   * @param length the length of the string to create
   * @return a String of random ascii alpha characters
   */
  public static String randomAlphaString(int length) {
    StringBuilder builder = new StringBuilder(length);
    for (int i = 0; i < length; i++) {
      char c = (char) (65 + 25 * Math.random());
      builder.append(c);
    }
    return builder.toString();
  }

  /**
   * Determine if two Buffer objects are equal
   * @param b1 The first buffer to compare
   * @param b2 The second buffer to compare
   * @return true if the Buffers are equal
   */
  public static boolean buffersEqual(Buffer b1, Buffer b2) {
    if (b1.length() != b2.length()) return false;
    for (int i = 0; i < b1.length(); i++) {
      if (b1.getByte(i) != b2.getByte(i)) return false;
    }
    return true;
  }

  /**
   * Determine if two byte arrays are equal
   * @param b1 The first byte array to compare
   * @param b2 The second byte array to compare
   * @return true if the byte arrays are equal
   */
  public static boolean byteArraysEqual(byte[] b1, byte[] b2) {
    if (b1.length != b2.length) return false;
    for (int i = 0; i < b1.length; i++) {
      if (b1[i] != b2[i]) return false;
    }
    return true;
  }
}