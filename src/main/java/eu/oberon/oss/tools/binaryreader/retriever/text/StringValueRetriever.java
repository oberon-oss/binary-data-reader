package eu.oberon.oss.tools.binaryreader.retriever.text;

import eu.oberon.oss.tools.binaryreader.converters.AbstractConverterProvider;
import eu.oberon.oss.tools.binaryreader.converters.text.StringConverterProvider;

import java.util.Objects;

import static eu.oberon.oss.tools.binaryreader.converters.ValueTypeNames.STRING;

/**
 * Provides a retriever for string values.
 *
 * @author TigerLilly64
 * @since 1.0.0
 */
public class StringValueRetriever extends AbstractTextValueRetriever<String> {
    private StringValueRetriever() {
        StringConverterProvider provider = Objects.requireNonNull(AbstractConverterProvider.getConverterProvider(STRING.name()));
        super(provider.getToObjectConverter());
    }

    private static final StringValueRetriever INSTANCE = new StringValueRetriever();

    /**
     * Returns an instance of the StringValueRetriever.
     *
     * @return a {@link StringValueRetriever} instance
     *
     * @since 1.0.0
     */
    public static StringValueRetriever getInstance() {
        return INSTANCE;
    }
}
