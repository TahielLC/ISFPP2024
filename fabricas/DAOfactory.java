package fabricas;

import java.util.Hashtable;
import java.util.ResourceBundle;

public class DAOfactory {
    private static Hashtable<String, Object> instancias = new Hashtable<>();

    public static Object getInstance(String objName) {
        try {
            // verifico si existe un objeto relacionado a objName
            // en la hashtable
            Object obj = instancias.get(objName);
            // si no existe entonces lo instancio y lo agrego
            if (obj == null) {
                ResourceBundle rb = ResourceBundle.getBundle("factory");
                String sClassName = rb.getString(objName);
                /*
                 * getDeclaredConstructors()[] devuelve un array con los constructores
                 * de la clase que lo invoca. Estos no necesriamente estan en el
                 * orden que aparecen en la clase.
                 */
                obj = Class.forName(sClassName).getDeclaredConstructors()[0].newInstance();
                // agrego el objeto a la hashtable
                instancias.put(objName, obj);
            }
            return obj;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}