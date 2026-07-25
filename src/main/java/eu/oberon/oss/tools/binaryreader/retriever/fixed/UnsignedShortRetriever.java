package eu.oberon.oss.tools.binaryreader.retriever.fixed;

import eu.oberon.oss.tools.binaryreader.converters.AbstractConverterProvider;
import eu.oberon.oss.tools.binaryreader.converters.fixed.UnsignedShortConverterProvider;

import java.util.Objects;

import static eu.oberon.oss.tools.binaryreader.converters.ValueTypeNames.UNSIGNED_SHORT;

/**
 * Retrieves an unsigned short value from a binary data reader or viewer.
 *
 * @author TigerLilly64
 * @since 1.0.0
 */
public final class UnsignedShortRetriever extends AbstractFixedLengthValueRetriever<Integer> {

    private UnsignedShortRetriever() {
        UnsignedShortConverterProvider provider = Objects.requireNonNull(AbstractConverterProvider.getConverterProvider(UNSIGNED_SHORT.name()));
        super(provider.getToObjectConverter(), provider.getExpectedByteArraySize());
    }

    private static final UnsignedShortRetriever INSTANCE = new UnsignedShortRetriever();

    /**
     * Returns an instance of the {@code UnsignedShortRetriever} class.
     *
     * @since 1.0.0
     */
    public static UnsignedShortRetriever getInstance() {
        return INSTANCE;
    }
}
