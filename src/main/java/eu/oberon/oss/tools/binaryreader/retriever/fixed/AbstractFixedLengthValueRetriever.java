package eu.oberon.oss.tools.binaryreader.retriever.fixed;

import eu.oberon.oss.tools.binaryreader.BinaryDataReader;
import eu.oberon.oss.tools.binaryreader.BinaryDataViewer;
import eu.oberon.oss.tools.binaryreader.converters.fixed.FixedToObjectConverter;

import java.nio.ByteOrder;

public abstract class AbstractFixedLengthValueRetriever<T> implements FixedLengthValueRetriever<T> {
    private final FixedToObjectConverter<T> converter;
    private final int expectedByteArraySize;

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