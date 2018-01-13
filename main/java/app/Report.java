package main.java.app;

import main.java.core.Database;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

public class Report
{

    public static void Generer(String c)
    {
        try
        {
            // 	- Chargement et compilation du rapport
            JasperReport jasperReport = JasperCompileManager.compileReport(c);
            // 	- Paramètres à envoyer au rapport
            Map<String, Object> parameters = new HashMap<String, Object>();
            parameters.put("Titre", "Titre");
            // - Execution du rapport
            JasperPrint jasperPrint = null;
            try
            {
                jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, Database.getHinstance().getConnection());
                System.out.println("Rapport Généré !");
            } catch (SQLException e)
            {
                e.printStackTrace();
            }
            // - Création du rapport au format PDF
            JasperViewer.viewReport(jasperPrint, false);

        } catch (JRException e)
        {
            e.printStackTrace();
        }
    }
}
