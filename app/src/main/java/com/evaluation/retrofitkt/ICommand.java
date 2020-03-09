package com.evaluation.retrofitkt;


public interface ICommand<T> {

    void execute(T param);

}
