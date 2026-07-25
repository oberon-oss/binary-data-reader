package eu.oberon.oss.tools.binaryreader.retriever.fixed;

import eu.oberon.oss.tools.binaryreader.converters.AbstractConverterProvider;
import eu.oberon.oss.tools.binaryreader.converters.fixed.UnsignedLongConverterProvider;

import java.math.BigInteger;
import java.util.Objects;

import static eu.oberon.oss.tools.binaryreader.converters.ValueTypeNames.UNSIGNED_LONG;

/**
 * Retrieves an unsigned long value from a binary data reader or viewer.
 *
 * @author TigerLilly64
 * @since 1.0.0
 */
public final class UnsignedLongRetriever extends AbstractFixedLengthValueRetriever<BigInteger> {

    private UnsignedLongRetriever() {
        UnsignedLongConverterProvider provider = Objects.requireNonNull(AbstractConverterProvider.getConverterProvider(UNSIGNED_LONG.name()));
        super(provider.getToObjectConverter(), provider.getExpectedByteArraySize());
    }

    private static final UnsignedLongRetriever INSTANCE = new UnsignedLongRetriever();

    /**
     * Returns an instance of the {@code UnsignedLongRetriever} class.
     *
     * @return an instance of the {@code UnsignedLongRetriever} class
     *
     * @since 1.0.0
     */
    public static UnsignedLongRetriever getInstance() {
        return INSTANCE;
    }
}
