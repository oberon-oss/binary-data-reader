package eu.oberon.oss.tools.binaryreader.retriever.text;

import eu.oberon.oss.tools.binaryreader.converters.AbstractConverterProvider;
import eu.oberon.oss.tools.binaryreader.converters.text.CharConverterProvider;

import java.util.Objects;

import static eu.oberon.oss.tools.binaryreader.converters.ValueTypeNames.CHARACTER;

/**
 * Provides a retriever for string values.
 *
 * @author TigerLilly64
 * @since 1.0.0
 */
public class CharacterValueRetriever extends AbstractTextValueRetriever<Character> {
    private CharacterValueRetriever() {
        CharConverterProvider provider = Objects.requireNonNull(AbstractConverterProvider.getConverterProvider(CHARACTER.name()));
        super(provider.getToObjectConverter());
    }

    private static final CharacterValueRetriever INSTANCE = new CharacterValueRetriever();

    /**
     * Returns an instance of the StringValueRetriever.
     *
     * @return a {@link CharacterValueRetriever} instance
     *
     * @since 1.0.0
     */
    public static CharacterValueRetriever getInstance() {
        return INSTANCE;
    }
}
