package com.medicare.ProjectforMedical.exception;

public class ResourceNotFoundException extends RuntimeException{
    String name;
    String id;
    int id1;

    public ResourceNotFoundException(String name, String id, Integer id1){
        super(String.format("%s not found with %s: %s",name,id,id1));
        this.name = name;
        this.id = id;
        this.id1 = id1;
    }
}
