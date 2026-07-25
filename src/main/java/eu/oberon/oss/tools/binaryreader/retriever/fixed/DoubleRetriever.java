package eu.oberon.oss.tools.binaryreader.retriever.fixed;

import eu.oberon.oss.tools.binaryreader.converters.AbstractConverterProvider;
import eu.oberon.oss.tools.binaryreader.converters.fixed.DoubleConverterProvider;

import java.util.Objects;

import static eu.oberon.oss.tools.binaryreader.converters.ValueTypeNames.DOUBLE;


/**
 * Retrieves a {@link Double} value from a binary data reader or viewer.
 *
 * @author TigerLilly64
 * @since 1.0.0
 */
public final class DoubleRetriever extends AbstractFixedLengthValueRetriever<Double> {

    private DoubleRetriever() {
        DoubleConverterProvider provider = Objects.requireNonNull(AbstractConverterProvider.getConverterProvider(DOUBLE.name()));
        super(provider.getToObjectConverter(), provider.getExpectedByteArraySize());
    }

    private static final DoubleRetriever INSTANCE = new DoubleRetriever();

    /**
     * Returns an instance of the {@code DoubleRetriever} class.
     *
     * @since 1.0.0
     */
    public static DoubleRetriever getInstance() {
        return INSTANCE;
    }
}
