package br.com.lucasmancan.converters;

public interface Converter<T, Z> {
    T toDomain(Z data);

    Z toDTO(T data);
}
