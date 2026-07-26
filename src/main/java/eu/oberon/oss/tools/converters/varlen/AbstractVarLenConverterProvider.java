package eu.oberon.oss.tools.converters.varlen;

import eu.oberon.oss.tools.converters.ValueTypeNames;

public abstract class AbstractVarLenConverterProvider<T> implements VarLenConverterProvider<T> {

    private final ValueTypeNames valueType;
    private final VarLenToObjectConverter<T> toObjectConverter;
    private final VarLenToByteConverter<T> toByteConverter;

    protected AbstractVarLenConverterProvider(ValueTypeNames valueType) {
        this.valueType = valueType;
        this.toObjectConverter = createToObjectConverter();
        this.toByteConverter = createToByteConverter();
    }

    protected abstract VarLenToObjectConverter<T> createToObjectConverter();

    protected abstract VarLenToByteConverter<T> createToByteConverter();

    @Override
    public VarLenToObjectConverter<T> getToObjectConverter() {
        return toObjectConverter;
    }

    @Override
    public VarLenToByteConverter<T> getToByteConverter() {
        return toByteConverter;
    }

    @Override
    public String getValueTypeName() {
        return valueType.name();
    }
}
