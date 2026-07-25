package eu.oberon.oss.tools.binaryreader.retriever.fixed;

import eu.oberon.oss.tools.binaryreader.converters.AbstractConverterProvider;
import eu.oberon.oss.tools.binaryreader.converters.fixed.FloatConvertProvider;

import java.util.Objects;

import static eu.oberon.oss.tools.binaryreader.converters.ValueTypeNames.FLOAT;

/**
 * Retrieves a {@link Float} value from a binary data reader or viewer.
 *
 * @author TigerLilly64
 * @since 1.0.0
 */
public final class FloatRetriever extends AbstractFixedLengthValueRetriever<Float> {

    private FloatRetriever() {
        FloatConvertProvider provider = Objects.requireNonNull(AbstractConverterProvider.getConverterProvider(FLOAT.name()));
        super(provider.getToObjectConverter(), provider.getExpectedByteArraySize());
    }

    private static final FloatRetriever INSTANCE = new FloatRetriever();

    /**
     * Returns an instance of the {@code FloatRetriever} class.
     *
     * @return an instance of the {@code FloatRetriever} class
     *
     * @since 1.0.0
     */
    public static FloatRetriever getInstance() {
        return INSTANCE;
    }
}
