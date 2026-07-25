package eu.oberon.oss.tools.binaryreader.retriever.fixed;

import eu.oberon.oss.tools.binaryreader.converters.AbstractConverterProvider;
import eu.oberon.oss.tools.binaryreader.converters.fixed.UnsignedIntegerConverterProvider;

import java.util.Objects;

import static eu.oberon.oss.tools.binaryreader.converters.ValueTypeNames.UNSIGNED_INTEGER;

/**
 * Retrieves an unsigned integer value from a binary data reader or viewer.
 *
 * @author TigerLilly64
 * @since 1.0.0
 */
public final class UnsignedIntegerRetriever extends AbstractFixedLengthValueRetriever<Long> {

    private UnsignedIntegerRetriever() {
        UnsignedIntegerConverterProvider provider = Objects.requireNonNull(AbstractConverterProvider.getConverterProvider(UNSIGNED_INTEGER.name()));
        super(provider.getToObjectConverter(), provider.getExpectedByteArraySize());
    }

    private static final UnsignedIntegerRetriever INSTANCE = new UnsignedIntegerRetriever();

    /**
     * Returns an instance of the {@code UnsignedIntegerRetriever} class.
     *
     * @since 1.0.0
     */
    public static UnsignedIntegerRetriever getInstance() {
        return INSTANCE;
    }
}
