package eu.oberon.oss.tools.retriever.varlen;

import eu.oberon.oss.tools.binaryreader.BinaryDataReader;
import eu.oberon.oss.tools.binaryreader.BinaryDataViewer;
import eu.oberon.oss.tools.binaryreader.BinaryDataViewerImpl;
import eu.oberon.oss.tools.converters.varlen.VarLenToObjectConverter;
import org.junit.jupiter.api.Test;

import java.nio.ByteOrder;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AbstractVarLenValueRetrieverTest {

    private static class TestVarLenValueRetriever extends AbstractVarLenValueRetriever<byte[]> {
        protected TestVarLenValueRetriever(VarLenToObjectConverter<byte[]> converter) {
            super(converter);
        }
    }

    private static class TestConverter implements VarLenToObjectConverter<byte[]> {
        @Override
        public byte[] convert(byte[] input) {
            return input;
        }

        @Override
        public byte[] convert(byte[] data, ByteOrder byteOrder) {
            return data;
        }
    }

    @Test
    public void testGetValueFromViewer() {
        byte[] data = {1, 2, 3, 4, 5};
        BinaryDataViewer viewer = new BinaryDataViewerImpl(data);
        TestVarLenValueRetriever retriever = new TestVarLenValueRetriever(new TestConverter());

        assertArrayEquals(new byte[]{1, 2, 3}, retriever.getValue(viewer, 0, 3));
        assertArrayEquals(new byte[]{4, 5}, retriever.getValue(viewer, 3, 2));
    }

    @Test
    public void testGetValueFromViewerWithByteOrder() {
        byte[] data = {1, 2, 3, 4, 5};
        BinaryDataViewer viewer = new BinaryDataViewerImpl(data);
        TestVarLenValueRetriever retriever = new TestVarLenValueRetriever(new TestConverter());

        assertArrayEquals(new byte[]{1, 2, 3}, retriever.getValue(viewer, 0, 3, ByteOrder.BIG_ENDIAN));
    }

    @Test
    public void testGetValueFromReader() {
        byte[] data = {1, 2, 3, 4, 5};
        BinaryDataViewer viewer = new BinaryDataViewerImpl(data);
        BinaryDataReader reader = viewer.getReader();
        TestVarLenValueRetriever retriever = new TestVarLenValueRetriever(new TestConverter());

        assertArrayEquals(new byte[]{1, 2, 3}, retriever.getValue(reader, 3));
        assertArrayEquals(new byte[]{4, 5}, retriever.getValue(reader, 2));
    }

    @Test
    public void testGetValueFromReaderWithByteOrder() {
        byte[] data = {1, 2, 3, 4, 5};
        BinaryDataViewer viewer = new BinaryDataViewerImpl(data);
        BinaryDataReader reader = viewer.getReader();
        TestVarLenValueRetriever retriever = new TestVarLenValueRetriever(new TestConverter());

        assertArrayEquals(new byte[]{1, 2, 3}, retriever.getValue(reader, 3, ByteOrder.BIG_ENDIAN));
    }

    @Test
    public void testGetConverter() {
        TestConverter converter = new TestConverter();
        TestVarLenValueRetriever retriever = new TestVarLenValueRetriever(converter);
        assertEquals(converter, retriever.getConverter());
    }
}
