package eu.oberon.oss.tools.binaryreader.retriever.fixed;

import eu.oberon.oss.tools.binaryreader.BinaryDataReader;
import eu.oberon.oss.tools.binaryreader.BinaryDataViewer;

import java.nio.ByteOrder;

/**
 * Retrieves an object of type T from a binary data viewer or reader.
 * <p>
 * The main difference between the two methods specified is that viewer variant uses the {@link BinaryDataViewer}, which does not maintain a cursor. The reader
 * variant uses the {@link BinaryDataReader}, which maintains a cursor.
 *
 * @param <T> The type of object to retrieve.
 *
 * @author TigerLilly64
 * @since 1.0.0
 */
public interface FixedLengthValueRetriever<T> {

    /**
     * Retrieves an object of type T from a binary data viewer.
     *
     * @param viewer The binary data viewer.
     * @param offset The offset from which to retrieve the value.
     *
     * @return The retrieved value.
     *
     * @since 1.0.0
     */
    T getValue(BinaryDataViewer viewer, int offset);

    T getValue(BinaryDataViewer viewer, int offset, ByteOrder byteOrder);

    /**
     * Retrieves an object of type T from a binary data reader.
     * <p>
     * As a side effect, the cursor maintained with in the reader itself is updated.
     *
     * @param reader The binary data reader.
     *
     * @return The retrieved value.
     *
     * @since 1.0.0
     */
    T getValue(BinaryDataReader reader);

    T getValue(BinaryDataReader reader, ByteOrder byteOrder);
}
