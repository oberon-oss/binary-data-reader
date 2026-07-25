package eu.oberon.oss.tools.binaryreader.retriever.fixed;

import eu.oberon.oss.tools.binaryreader.converters.AbstractConverterProvider;
import eu.oberon.oss.tools.binaryreader.converters.fixed.SignedShortConverterProvider;

import java.util.Objects;

import static eu.oberon.oss.tools.binaryreader.converters.ValueTypeNames.SIGNED_SHORT;

/**
 * Retrieves a signed short value from a binary data reader or viewer.
 *
 * @author TigerLilly64
 * @since 1.0.0
 */
public final class SignedShortRetriever extends AbstractFixedLengthValueRetriever<Short> {

    private SignedShortRetriever() {
        SignedShortConverterProvider provider = Objects.requireNonNull(AbstractConverterProvider.getConverterProvider(SIGNED_SHORT.name()));
        super(provider.getToObjectConverter(), provider.getExpectedByteArraySize());
    }

    private static final SignedShortRetriever INSTANCE = new SignedShortRetriever();

    /**
     * Returns an instance of the {@code SignedShortRetriever} class.
     *
     * @return an instance of the {@code SignedShortRetriever} class
     *
     * @since 1.0.0
     */
    public static SignedShortRetriever getInstance() {
        return INSTANCE;
    }
}
