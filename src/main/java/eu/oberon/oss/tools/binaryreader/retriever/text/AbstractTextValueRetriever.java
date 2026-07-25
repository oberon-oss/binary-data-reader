package eu.oberon.oss.tools.binaryreader.retriever.text;

import eu.oberon.oss.tools.binaryreader.BinaryDataReader;
import eu.oberon.oss.tools.binaryreader.BinaryDataViewer;
import eu.oberon.oss.tools.binaryreader.converters.text.TextToObjectConverter;

import java.nio.ByteOrder;
import java.nio.charset.Charset;

/**
 * Base class for text value retrievers.
 *
 * @param <T> The type of object to retrieve.
 *
 * @author TigerLilly64
 * @since 1.0.0
 */
public abstract class AbstractTextValueRetriever<T> implements TextValueRetriever<T> {
    private final TextToObjectConverter<T> converter;

    /**
     * Constructs a new instance of AbstractTextValueRetriever.
     *
     * @param converter The text-to-object converter to use.
     *
     * @since 1.0.0
     */
    protected AbstractTextValueRetriever(TextToObjectConverter<T> converter) {
        this.converter = converter;
    }

    @Override
    public T getValue(BinaryDataViewer viewer, int offset, int length) {
        return getValue(viewer, offset, length, ByteOrder.nativeOrder(), Charset.defaultCharset());
    }

    @Override
    public T getValue(BinaryDataViewer viewer, int offset, int length, ByteOrder byteOrder) {
        return getValue(viewer, offset, length, byteOrder, Charset.defaultCharset());
    }

    @Override
    public T getValue(BinaryDataViewer viewer, int offset, int length, Charset charset) {
        return getValue(viewer, offset, length, ByteOrder.nativeOrder(), charset);
    }

    @Override
    public T getValue(BinaryDataViewer viewer, int offset, int length, ByteOrder byteOrder, Charset charset) {
        return converter.convert(viewer.peekBytes(offset, length), charset, byteOrder);
    }


    @Override
    public T getValue(BinaryDataReader reader, int length) {
        return getValue(reader, length, ByteOrder.nativeOrder(), Charset.defaultCharset());
    }

    @Override
    public T getValue(BinaryDataReader reader, int length, Charset charset) {
        return getValue(reader, length, ByteOrder.nativeOrder(), charset);
    }

    @Override
    public T getValue(BinaryDataReader reader, int length, ByteOrder byteOrder, Charset charset) {
        return converter.convert(reader.readBytes(length), charset, byteOrder);
    }
}
