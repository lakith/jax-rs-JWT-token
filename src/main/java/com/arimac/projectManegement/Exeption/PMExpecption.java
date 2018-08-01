package com.arimac.projectManegement.Exeption;

public class PMExpecption extends RuntimeException{

    private static final long serialVersionUID = 5698912669933828607L;

    public PMExpecption(String message) {
        super(message);
    }
}