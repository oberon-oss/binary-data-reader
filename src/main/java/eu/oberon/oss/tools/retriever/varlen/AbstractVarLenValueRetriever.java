package eu.oberon.oss.tools.retriever.varlen;

import eu.oberon.oss.tools.binaryreader.BinaryDataReader;
import eu.oberon.oss.tools.binaryreader.BinaryDataViewer;
import eu.oberon.oss.tools.converters.varlen.VarLenToObjectConverter;

import java.nio.ByteOrder;

public abstract class AbstractVarLenValueRetriever<T> implements VarLenValueRetriever<T> {

    private final VarLenToObjectConverter<T> converter;

    protected AbstractVarLenValueRetriever(VarLenToObjectConverter<T> converter) {
        this.converter = converter;
    }

    protected VarLenToObjectConverter<T> getConverter() {
        return converter;
    }

    @Override
    public T getValue(BinaryDataViewer viewer, int offset, int length) {
        return getValue(viewer, offset, length, ByteOrder.nativeOrder());
    }

    @Override
    public T getValue(BinaryDataViewer viewer, int offset, int length, ByteOrder byteOrder) {
        return converter.convert(viewer.peekBytes(offset, length), byteOrder);
    }

    @Override
    public T getValue(BinaryDataReader reader, int length) {
        return getValue(reader, length, ByteOrder.nativeOrder());
    }

    @Override
    public T getValue(BinaryDataReader reader, int length, ByteOrder byteOrder) {
        return converter.convert(reader.readBytes(length), byteOrder);
    }
}
