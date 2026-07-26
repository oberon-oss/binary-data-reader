package eu.oberon.oss.tools.retriever.fixed;

import eu.oberon.oss.tools.binaryreader.BinaryDataReader;
import eu.oberon.oss.tools.binaryreader.BinaryDataViewer;
import eu.oberon.oss.tools.converters.fixed.FixedToObjectConverter;

import java.nio.ByteOrder;

/**
 * Parent class for fixed-length value retrievers.
 *
 * @param <T> The type of object to retrieve.
 *
 * @author TigerLilly64
 * @since 1.0.0
 */
public abstract class AbstractFixedLengthValueRetriever<T> implements FixedLengthValueRetriever<T> {
    private final FixedToObjectConverter<T> converter;
    private final int expectedByteArraySize;

    /**
     * Constructs a new AbstractFixedLengthValueRetriever with the specified converter and expected byte array size.
     *
     * @param converter             The converter to use for converting byte arrays to objects.
     * @param expectedByteArraySize The expected size of the byte array to retrieve.
     *
     * @since 1.0.0
     */
    protected AbstractFixedLengthValueRetriever(FixedToObjectConverter<T> converter, int expectedByteArraySize) {
        this.converter = converter;
        this.expectedByteArraySize = expectedByteArraySize;
    }

    @Override
    public T getValue(BinaryDataViewer viewer, int offset) {
        return getValue(viewer, offset, ByteOrder.nativeOrder());
    }

    @Override
    public T getValue(BinaryDataViewer viewer, int offset, ByteOrder byteOrder) {
        return converter.convert(viewer.peekBytes(offset, expectedByteArraySize), byteOrder);
    }

    @Override
    public T getValue(BinaryDataReader reader) {
        return getValue(reader, ByteOrder.nativeOrder());
    }


    @Override
    public T getValue(BinaryDataReader reader, ByteOrder byteOrder) {
        return converter.convert(reader.readBytes(expectedByteArraySize), byteOrder);
    }
}