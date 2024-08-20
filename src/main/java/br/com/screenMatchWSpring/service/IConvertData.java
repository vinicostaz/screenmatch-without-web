package br.com.screenMatchWSpring.service;

public interface IConvertData {
    <T> T getData(String json, Class<T> Class);
}
