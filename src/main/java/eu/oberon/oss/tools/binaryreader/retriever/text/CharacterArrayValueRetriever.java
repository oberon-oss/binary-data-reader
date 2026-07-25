package eu.oberon.oss.tools.binaryreader.retriever.text;

import eu.oberon.oss.tools.binaryreader.converters.AbstractConverterProvider;
import eu.oberon.oss.tools.binaryreader.converters.text.CharacterArrayConverterProvider;

import java.util.Objects;

import static eu.oberon.oss.tools.binaryreader.converters.ValueTypeNames.CHARACTER_ARRAY;

/**
 * Provides a retriever for string values.
 *
 * @author TigerLilly64
 * @since 1.0.0
 */
public class CharacterArrayValueRetriever extends AbstractTextValueRetriever<Character[]> {
    private CharacterArrayValueRetriever() {
        CharacterArrayConverterProvider provider = Objects.requireNonNull(AbstractConverterProvider.getConverterProvider(CHARACTER_ARRAY.name()));
        super(provider.getToObjectConverter());
    }

    private static final CharacterArrayValueRetriever INSTANCE = new CharacterArrayValueRetriever();

    /**
     * Returns an instance of the StringValueRetriever.
     *
     * @return a {@link CharacterArrayValueRetriever} instance
     *
     * @since 1.0.0
     */
    public static CharacterArrayValueRetriever getInstance() {
        return INSTANCE;
    }
}
