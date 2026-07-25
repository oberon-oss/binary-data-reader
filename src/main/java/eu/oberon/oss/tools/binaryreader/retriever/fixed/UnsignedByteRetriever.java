package eu.oberon.oss.tools.binaryreader.retriever.fixed;

import eu.oberon.oss.tools.binaryreader.converters.AbstractConverterProvider;
import eu.oberon.oss.tools.binaryreader.converters.fixed.UnsignedByteConverterProvider;

import java.util.Objects;

import static eu.oberon.oss.tools.binaryreader.converters.ValueTypeNames.UNSIGNED_BYTE;

/**
 * Retrieves an unsigned byte value from a binary data reader or viewer.
 *
 * @author TigerLilly64
 * @since 1.0.0
 */
public final class UnsignedByteRetriever extends AbstractFixedLengthValueRetriever<Integer> {

    private UnsignedByteRetriever() {
        UnsignedByteConverterProvider provider = Objects.requireNonNull(AbstractConverterProvider.getConverterProvider(UNSIGNED_BYTE.name()));
        super(provider.getToObjectConverter(), provider.getExpectedByteArraySize());
    }

    private static final UnsignedByteRetriever INSTANCE = new UnsignedByteRetriever();

    /**
     * Returns an instance of the {@code UnsignedByteRetriever} class.
     *
     * @return an instance of the {@code UnsignedByteRetriever} class
     *
     * @since 1.0.0
     */
    public static UnsignedByteRetriever getInstance() {
        return INSTANCE;
    }
}
