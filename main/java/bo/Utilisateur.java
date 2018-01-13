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

    public Utilisateur(int id, String nom, String prenom, String email, Sexe sexe, String password, TypeUtilisateur type)
    {
        super(id, nom, prenom, email, sexe);
        this.password = password;
        this.type = type;
    }

    public Utilisateur(Utilisateur another)
    {
        super(another);
        this.password = another.password;
        this.type = another.type;
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
    
    public void setUtilisateur(Utilisateur another)
    {
        super.setPersonne(another);
        this.password = another.password;
        this.type = another.type;
    }
    
    @Override
    public boolean isValid()
    {
        return super.isValid() && !password.isEmpty() ; 
    }
}
