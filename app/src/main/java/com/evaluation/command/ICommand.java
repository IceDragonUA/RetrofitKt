package com.evaluation.command;


public interface ICommand<T> {

    void execute(T param);

}
