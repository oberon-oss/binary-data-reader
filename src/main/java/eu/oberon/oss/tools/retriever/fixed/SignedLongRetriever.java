package eu.oberon.oss.tools.retriever.fixed;

import eu.oberon.oss.tools.converters.AbstractConverterProvider;
import eu.oberon.oss.tools.converters.fixed.SignedLongConverterProvider;

import java.util.Objects;

import static eu.oberon.oss.tools.converters.ValueTypeNames.SIGNED_LONG;

/**
 * Retrieves a signed long value from a binary data reader or viewer.
 *
 * @author TigerLilly64
 * @since 1.0.0
 */
public final class SignedLongRetriever extends AbstractFixedLengthValueRetriever<Long> {

    private SignedLongRetriever() {
        SignedLongConverterProvider provider = Objects.requireNonNull(AbstractConverterProvider.getConverterProvider(SIGNED_LONG.name()));
        super(provider.getToObjectConverter(), provider.getExpectedByteArraySize());
    }

    private static final SignedLongRetriever INSTANCE = new SignedLongRetriever();

    /**
     * Returns an instance of the {@code SignedLongRetriever} class.
     *
     * @return an instance of the {@code SignedLongRetriever} class
     *
     * @since 1.0.0
     */
    public static SignedLongRetriever getInstance() {
        return INSTANCE;
    }
}
