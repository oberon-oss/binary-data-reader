package eu.oberon.oss.tools.binaryreader.converters.fixed;

import eu.oberon.oss.tools.binaryreader.converters.ConverterProvider;

/**
 * Provider for methods that convert a specific target class to bytes, or vice versa.
 *
 * @param <T> The type of class that will be either consumed or produced by the converters.
 *
 * @author TigerLilly64
 * @since 1.0.0
 */
public interface FixedConverterProvider<T> extends ConverterProvider {

    /**
     * Returns the expected size of the byte array that is consumed or produced.
     *
     * @return The expected byte size of the input/output byte array.
     *
     * @since 1.0.0
     */
    int getExpectedByteArraySize();

    /**
     * Returns the converter that converts an object of type {@code <T>} to bytes.
     *
     * @return A converter that takes a byte[] and produces an object of type {@code <T>}.
     *
     * @since 1.0.0
     */
    FixedToObjectConverter<T> getToObjectConverter();

    /**
     * Returns the converter that converts Type {@code <T> } into
     *
     * @return The converter that converts an object of type {@code <T>} to byte[].
     *
     * @since 1.0.0
     */
    FixedToByteConverter<T> getToByteConverter();
}
