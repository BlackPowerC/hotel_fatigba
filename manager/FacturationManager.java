/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manager;

import bo.Facturation;
import java.util.List;

/**
 *
 * @author jordy
 */
public class FacturationManager extends Manager<Facturation>
{
    private static FacturationManager p_singleton = null ;
    
    private FacturationManager()
    {
        
    }
    
    public static FacturationManager getInstance()
    {
        if(p_singleton == null)
        {
            p_singleton = new FacturationManager() ;
        }
        return p_singleton ;
    }
   
    
    @Override
    public boolean insert(Facturation entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Facturation findById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Facturation> findByCriteria(String criteria, boolean strict) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Facturation> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int update(Facturation entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
