/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bo;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author jordy
 */
public class Utilisateur extends Personne
{
    private String password ;
    private TypeUtilisateur type ;
    
    public Utilisateur()
    {
        
    }
    
    public Utilisateur(ResultSet rs, Sexe sexe, TypeUtilisateur type) throws SQLException
    {
        super(rs, sexe) ;
        this.password = rs.getString("password") ;
        this.type = type ;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public TypeUtilisateur getType()
    {
        return type;
    }

    public void setType(TypeUtilisateur type)
    {
        this.type = type;
    }

    @Override
    public boolean isValid()
    {
        return super.isValid() && !password.isEmpty() ; 
    }
}
