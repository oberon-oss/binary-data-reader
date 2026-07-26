package eu.oberon.oss.tools.retriever.text;

import eu.oberon.oss.tools.binaryreader.BinaryDataReader;
import eu.oberon.oss.tools.binaryreader.BinaryDataViewer;
import eu.oberon.oss.tools.binaryreader.BinaryDataViewerImpl;
import eu.oberon.oss.tools.retriever.varlen.text.CharacterValueRetriever;
import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CharacterValueRetrieverTest {

    @Test
    public void testGetInstance() {
        CharacterValueRetriever instance1 = CharacterValueRetriever.getInstance();
        CharacterValueRetriever instance2 = CharacterValueRetriever.getInstance();
        assertEquals(instance1, instance2);
    }

    @Test
    public void testGetValueFromViewer() {
        byte[] data = "ABC".getBytes(StandardCharsets.UTF_8);
        BinaryDataViewer viewer = new BinaryDataViewerImpl(data);
        CharacterValueRetriever retriever = CharacterValueRetriever.getInstance();

        assertEquals('A', retriever.getValue(viewer, 0, 1));
        assertEquals('B', retriever.getValue(viewer, 1, 1));
        assertEquals('C', retriever.getValue(viewer, 2, 1));
    }

    @Test
    public void testGetValueFromReader() {
        byte[] data = "ABC".getBytes(StandardCharsets.UTF_8);
        BinaryDataViewer viewer = new BinaryDataViewerImpl(data);
        BinaryDataReader reader = viewer.getReader();
        CharacterValueRetriever retriever = CharacterValueRetriever.getInstance();

        assertEquals('A', retriever.getValue(reader, 1));
        assertEquals('B', retriever.getValue(reader, 1));
        assertEquals('C', retriever.getValue(reader, 1));
    }
}
