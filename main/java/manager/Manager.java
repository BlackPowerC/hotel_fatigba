package main.java.manager ;

import java.util.List;

public abstract class Manager<E> extends Observable
{
    /* insertion */
    public abstract boolean insert(E entity) ;
    /* suppression */
    public abstract void delete(int id) ;
    /* selection suivant un id */
    public abstract E findById(int id) ;
    /* selection suivant un critère */
    public abstract List<E> findByCriteria(String criteria, boolean strict) ;
    /* selection de toute les données */
    public abstract List<E> findAll() ;
    /* mise à jour */
    public abstract int update(E entity) ;
}