package eu.oberon.oss.tools.binaryreader.retriever.text;

import eu.oberon.oss.tools.binaryreader.BinaryDataReader;
import eu.oberon.oss.tools.binaryreader.BinaryDataViewer;

import java.nio.ByteOrder;
import java.nio.charset.Charset;

/**
 * @author TigerLilly64
 * @since 1.0.0
 */
public interface TextValueRetriever<T> {

    /**
     * Retrieves an object of type T from a binary data viewer.
     * <p>
     * The character set used is {@link Charset#defaultCharset()} and the byte order used will be {@link ByteOrder#nativeOrder()}.
     *
     * @param viewer The binary data viewer.
     * @param offset The offset of the bytes to convert in the viewer.
     * @param length The number of bytes to retrieve.
     *
     * @return The retrieved object.
     *
     * @since 1.0.0
     */
    T getValue(BinaryDataViewer viewer, int offset, int length);

    /**
     * Retrieves an object of type T from a binary data viewer, using the specified byte order.
     * <p>
     * The character set used is {@link Charset#defaultCharset()}.
     *
     * @param viewer    The binary data viewer.
     * @param offset    The offset of the bytes to convert in the viewer.
     * @param length    The number of bytes to retrieve.
     * @param byteOrder The byte order to use when retrieving the value.
     *
     * @return The retrieved object.
     *
     * @since 1.0.0
     */
    T getValue(BinaryDataViewer viewer, int offset, int length, ByteOrder byteOrder);

    /**
     * Retrieves an object of type T from a binary data viewer, using the specified charset.
     * <p>
     * The byte order used will be {@link ByteOrder#nativeOrder()}.
     *
     * @param viewer  The binary data viewer.
     * @param offset  The offset of the bytes to convert in the viewer.
     * @param length  The number of bytes to retrieve.
     * @param charset The charset to use when retrieving the value.
     *
     * @return The retrieved object.
     *
     * @since 1.0.0
     */
    T getValue(BinaryDataViewer viewer, int offset, int length, Charset charset);

    /**
     * Retrieves an object of type T from a binary data viewer, using the specified byte order and charset.
     *
     * @param viewer    The binary data viewer.
     * @param offset    The offset of the bytes to convert in the viewer.
     * @param length    The number of bytes to retrieve.
     * @param byteOrder The byte order to use when retrieving the value.
     * @param charset   The charset to use when retrieving the value.
     *
     * @return The retrieved object.
     *
     * @since 1.0.0
     */
    T getValue(BinaryDataViewer viewer, int offset, int length, ByteOrder byteOrder, Charset charset);

    T getValue(BinaryDataReader reader, int length);

    /**
     * Retrieves an object of type T from a binary data reader, using the specified charset; the byte order used will be {@link ByteOrder#nativeOrder()}.
     *
     * @param reader  The binary data viewer.
     * @param length  The number of bytes to retrieve.
     * @param charset The charset to use when retrieving the value.
     *
     * @since 1.0.0
     */
    T getValue(BinaryDataReader reader, int length, Charset charset);

    /**
     * Retrieves an object of type T from a binary data reader, updating the cursor maintained with in the reader itself.
     * <p>
     * The nyte order provided by the user will be used.
     *
     * @param reader    The binary data viewer.
     * @param length    The number of bytes to retrieve.
     * @param byteOrder The byte order to use when retrieving the value.
     * @param charset   The charset to use when retrieving the value.
     *
     * @since 1.0.0
     */
    T getValue(BinaryDataReader reader, int length, ByteOrder byteOrder, Charset charset);

}
