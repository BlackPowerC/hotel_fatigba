/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.bo.validator;

/**
 *
 * @author jordy
 * @param <T>
 * @since 0.2.1
 * Interface à utiliser par des objets validateurs.
 */
public interface ValidatorInterface<T>
{
    public boolean isValid(T enity) ;
}
