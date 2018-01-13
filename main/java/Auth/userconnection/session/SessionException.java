/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Auth.userconnection.session;

/**
 * Classe Exception pour les session.
 * Elle est lancé lorsqu'une session ne peut être arrètée ou démarrée.
 * @since 0.1.0
 * @author jordy
 */
public class SessionException extends Exception
{

    /**
     * Creates a new instance of <code>SessionException</code> without detail
     * message.
     */
    public SessionException()
    {
    }

    /**
     * Constructs an instance of <code>SessionException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public SessionException(String msg)
    {
        super(msg);
    }
}
