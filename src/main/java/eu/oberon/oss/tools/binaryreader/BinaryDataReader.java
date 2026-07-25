package eu.oberon.oss.tools.binaryreader;

/**
 * The binary data reader combines the {@link BinaryDataViewer} class with an updateble cursor.
 * <p>
 * Where the Viewer can only be used for reading, the Reader will update the 'cursor' (offset) appropriately after performing the reading operations. It also
 * allows the user to adjust the offset.
 *
 * @author TigerLilly64
 * @since 1.0.0
 */
public interface BinaryDataReader {
    /**
     * Returns the current reader offset.
     *
     * @return the current reader offset
     *
     * @since 1.0.0
     */
    int offset();

    /**
     * Sets the current reader offset.
     *
     * @param offset the new reader offset
     *
     * @throws IndexOutOfBoundsException if the offset is outside the data bounds
     * @since 1.0.0
     */
    void offset(int offset);

    /**
     * Reads a byte from the current reader offset and advances the offset by one.
     *
     * @return the byte at the current reader offset
     *
     * @throws IndexOutOfBoundsException if the current offset is outside the data bounds
     * @since 1.0.0
     */
    byte readByte();

    /**
     * Reads a byte from the given absolute offset and advances the reader offset to the next byte.
     *
     * @param offset the absolute offset to read from
     *
     * @return the byte at the given offset
     *
     * @throws IndexOutOfBoundsException if the offset is outside the data bounds
     * @since 1.0.0
     */
    byte readByte(int offset);

    /**
     * Reads bytes from the current reader offset and advances the offset by the requested length.
     *
     * @param length the number of bytes to read
     *
     * @return a copy of the requested bytes
     *
     * @throws IndexOutOfBoundsException if the requested range is outside the data bounds
     * @throws IllegalArgumentException  if the length is negative
     * @since 1.0.0
     */
    byte[] readBytes(int length);

    /**
     * Reads bytes from the given absolute offset and advances the reader offset to the end of the read range.
     *
     * @param offset the absolute offset to read from
     * @param length the number of bytes to read
     *
     * @return a copy of the requested bytes
     *
     * @throws IndexOutOfBoundsException if the requested range is outside the data bounds
     * @throws IllegalArgumentException  if the length is negative
     * @since 1.0.0
     */
    byte[] readBytes(int offset, int length);

    /**
     * Reads n bytes from the current reader offset and advances the offset by the number of bytes read.
     * <p>
     * The byte array passed for the target cannot be {@code null}, or a NullPointerException will be thrown. Passing a zero-length array as target parameter is
     * allowed - albeit somewhat pointless.
     *
     * @param target the target array to read into
     * @param offset the zero-based offset to start reading into
     *
     * @throws IndexOutOfBoundsException if the requested range is outside the data bounds
     * @throws NullPointerException      if the target array is null
     * @since 1.0.0
     */
    void readBytes(byte[] target, int offset);
}
