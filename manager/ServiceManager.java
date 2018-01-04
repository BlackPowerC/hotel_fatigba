/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manager;

import bo.Service;
import java.util.List;

/**
 *
 * @author jordy
 */
public class ServiceManager extends Manager<Service>
{

    @Override
    public boolean insert(Service entity)
    {
    	if(entity == null || !entity.isValid())
    	{
    		return false ;
    	}  
    }

    @Override
    public void delete(int id)
    {
        
    }

    @Override
    public Service findById(int id)
    {
        
    }

    @Override
    public List<Service> findByCriteria(String criteria, boolean strict)
    {
        
    }

    @Override
    public List<Service> findAll()
    {
        
    }

    @Override
    public int update(Service entity)
    {
    	if(entity == null || !entity.isValid())
    	{
    		return -1 ;
    	}  
    }
    
}
