package main.java.core.loader;

/**
 * <p>
 * Cette classe permet de chager dynamiquement des classe ou des interfaces Java
 * à partir de leur binaire. Exemple: Voiture.class
 * </p>
 *
 * @author jordy
 * @since 0.0.0
 */
public class CustomClassLoader extends ClassLoader
{
    private static CustomClassLoader p_singleton = null;

    private CustomClassLoader()
    {
        super();
    }

    public static CustomClassLoader getInstance()
    {
        if (p_singleton == null)
        {
            p_singleton = new CustomClassLoader();
        }
        return p_singleton;
    }

    /**
     * Cette fonction permet de charger le byte code d'une classe java.
     * @param fullyQualifiedClassName Le nom de la classe sous la forme d'un binaire.
     * Exemple: user.vip.MaClasse
     * @return La classe Classe correspondant à <code>fullyQualifiedClassName</code>
     * @throws ClassNotFoundException
     * @since 0.0.0
     */
    @Override
    public Class loadClass(String fullyQualifiedClassName) throws ClassNotFoundException
    {
        return Class.forName(fullyQualifiedClassName) ;
    }
}
