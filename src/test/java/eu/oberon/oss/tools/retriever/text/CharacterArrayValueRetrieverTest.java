package eu.oberon.oss.tools.retriever.text;

import eu.oberon.oss.tools.binaryreader.BinaryDataReader;
import eu.oberon.oss.tools.binaryreader.BinaryDataViewer;
import eu.oberon.oss.tools.binaryreader.BinaryDataViewerImpl;
import eu.oberon.oss.tools.retriever.varlen.text.CharacterArrayValueRetriever;
import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class CharacterArrayValueRetrieverTest {

    @Test
    public void testGetValueFromViewer() {
        byte[] data = "Hello".getBytes(StandardCharsets.UTF_8);
        BinaryDataViewer viewer = new BinaryDataViewerImpl(data);
        CharacterArrayValueRetriever retriever = new CharacterArrayValueRetriever();

        Character[] expected = new Character[]{'H', 'e', 'l', 'l', 'o'};
        assertArrayEquals(expected, retriever.getValue(viewer, 0, 5));
    }

    @Test
    public void testGetValueFromReader() {
        byte[] data = "Hello".getBytes(StandardCharsets.UTF_8);
        BinaryDataViewer viewer = new BinaryDataViewerImpl(data);
        BinaryDataReader reader = viewer.getReader();
        CharacterArrayValueRetriever retriever = new CharacterArrayValueRetriever();

        Character[] expected = new Character[]{'H', 'e', 'l', 'l', 'o'};
        assertArrayEquals(expected, retriever.getValue(reader, 5));
    }
}
