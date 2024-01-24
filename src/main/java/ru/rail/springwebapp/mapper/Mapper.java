package ru.rail.springwebapp.mapper;

public interface Mapper<F, T> {

    T mapFrom(F from);
    F mapTo(T to);


}
