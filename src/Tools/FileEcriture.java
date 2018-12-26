/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tools;

import Object.Article;
import Object.Rayon;
import Windows.MainWindow;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author moi
 */
public  class FileEcriture {
    public static void generateFile(ArrayList<Rayon> listRayon){
        
        for(Rayon rayon : listRayon){
            
            
                int compteur = 0;
                PrintWriter out = null;
                try {
                    File fichier = new File("fichierText\\" + rayon.getCodeRayon()+".txt");
                    if(!fichier.getParentFile().exists()){
                        fichier.getParentFile().mkdir();
                    }
                    out = new PrintWriter(fichier);
                    out.println("Emp    Code MP2    Code SAP    Unité    Qte");
                    
                    for(Article article : rayon.getListArticle()){
                        out.println((article.getEmplacement()==""?"    ":article.getEmplacement())+"    "+(article.getAncienCodeArticle()==""?"     ":article.getAncienCodeArticle())+"    "+article.getCodeArticle()+"    "+article.getUnite()+"    ");
                        compteur++;
                        out.println();
                        
                    }
                    
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(FileEcriture.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(null, "Une erreur est survenu, contactez moi à cette adresse : jerome.gerard02@outlook.fr", "Echec",JOptionPane.ERROR_MESSAGE);
                    
                } finally {
                    
                    out.close();
                    
                }
            }
        
                    JOptionPane.showMessageDialog(null, "Lecture du fichier terminé , fichier texte généré dans le dossier fichierText", "Succès ! ",JOptionPane.INFORMATION_MESSAGE);

    }

    public static void remplirFichierExcel(File selectedFile, ArrayList<Rayon> list) {
        try {
            Workbook wb = WorkbookFactory.create(selectedFile);
            Sheet sheet  =wb.getSheetAt(0);
             FormulaEvaluator eval = wb.getCreationHelper().createFormulaEvaluator();
             sheet.getRow(0).createCell(7).setCellValue("Stock trouvé");
             sheet.getRow(0).createCell(8).setCellValue("Ecart");
             int i = 1;
                for(Rayon rayon : list){
                    
                    for(Article article : rayon.getListArticle()){
                    int compteurs = 0;
                    
                    
                 sheet.getRow(i).createCell(7).setCellFormula(null);
                sheet.getRow(i).getCell(7).setCellType(CellType.NUMERIC);
                
                sheet.getRow(i).getCell(7).setCellValue(article.getStockTrouve());
               article.setEcart((int)sheet.getRow(i).getCell(7).getNumericCellValue()-(int)sheet.getRow(i).getCell(5).getNumericCellValue());
               sheet.getRow(i).createCell(8).setCellValue(article.getEcart());
                i++;
                    }
                }
            eval.evaluateAll();
            FileOutputStream fis = new FileOutputStream("bite.xlsx");
            wb.write(fis);
            
            fis.close();
            wb.close();
            new File("bite.xlsx").delete();
            JOptionPane.showMessageDialog(null, "Fichier excel modifier avec les écarts", "Succès ! ",JOptionPane.INFORMATION_MESSAGE);

        } catch (IOException ex) {
            Logger.getLogger(FileEcriture.class.getName()).log(Level.SEVERE, null, ex);
                                        JOptionPane.showMessageDialog(null, "Une erreur est survenu, contactez moi à cette adresse : jerome.gerard02@outlook.fr", "Echec",JOptionPane.ERROR_MESSAGE);

        } catch (InvalidFormatException ex) {
            Logger.getLogger(FileEcriture.class.getName()).log(Level.SEVERE, null, ex);
                                        JOptionPane.showMessageDialog(null, "Une erreur est survenu, contactez moi à cette adresse : jerome.gerard02@outlook.fr", "Echec",JOptionPane.ERROR_MESSAGE);

        } catch (EncryptedDocumentException ex) {
            Logger.getLogger(FileEcriture.class.getName()).log(Level.SEVERE, null, ex);
                                        JOptionPane.showMessageDialog(null, "Une erreur est survenu, contactez moi à cette adresse : jerome.gerard02@outlook.fr", "Echec",JOptionPane.ERROR_MESSAGE);

        }
        
    }

    public static void genererFichierBilan(MainWindow context,File selectedFile, ArrayList<Rayon> listRayon) {
        try {
            context.getjLabel2().setVisible(true);
            Workbook wb = WorkbookFactory.create(selectedFile);
            
            XSSFWorkbook wb2 = new XSSFWorkbook();
            Sheet sheet = null;
            generateLastFile(wb2,listRayon);

            if(wb.getNumberOfSheets()==2){
               sheet=wb.getSheet("Bilan");
            }else{
                 sheet = wb.createSheet("Bilan");
            }
            
            sheet.createRow(0).createCell(0).setCellValue("Ancien code Article");
            sheet.getRow(0).createCell(1).setCellValue("Code SAP");
            sheet.getRow(0).createCell(2).setCellValue("Désignation article");
            sheet.getRow(0).createCell(3).setCellValue("Emplacement");
            sheet.getRow(0).createCell(4).setCellValue("Ecart");
            int compteurLigne = 1;
            for(Rayon rayon : listRayon){
                for(Article article : rayon.getListArticle()){
                    sheet.createRow(compteurLigne).createCell(0).setCellValue(article.getAncienCodeArticle());
                    sheet.getRow(compteurLigne).createCell(1).setCellValue(article.getCodeArticle());
                    sheet.getRow(compteurLigne).createCell(2).setCellValue(article.getDesignationArticle());
                    sheet.getRow(compteurLigne).createCell(3).setCellValue(article.getEmplacement());
                    sheet.getRow(compteurLigne).createCell(4).setCellValue(article.getEcart());
                    compteurLigne++;
                }
            }
            
            FileOutputStream fis2 = new FileOutputStream("bilan.xlsx");
            FileOutputStream fis = new FileOutputStream("tmp.xlsx");
            wb.write(fis);
            wb2.write(fis2);
            fis2.close();
            fis.close();
            wb.close();
            wb2.close();
            new File("bite.xlsx").delete();
            JOptionPane.showMessageDialog(null, "Onglet bilan créé, fichier bilan également créé", "Succès ! ",JOptionPane.INFORMATION_MESSAGE);

            System.out.println("fini2");
        } catch (IOException ex) {
            Logger.getLogger(FileEcriture.class.getName()).log(Level.SEVERE, null, ex);
                                        JOptionPane.showMessageDialog(null, "Une erreur est survenu, contactez moi à cette adresse : jerome.gerard02@outlook.fr", "Echec",JOptionPane.ERROR_MESSAGE);

        } catch (InvalidFormatException ex) {
            Logger.getLogger(FileEcriture.class.getName()).log(Level.SEVERE, null, ex);
                                        JOptionPane.showMessageDialog(null, "Une erreur est survenu, contactez moi à cette adresse : jerome.gerard02@outlook.fr", "Echec",JOptionPane.ERROR_MESSAGE);

        } catch (EncryptedDocumentException ex) {
            Logger.getLogger(FileEcriture.class.getName()).log(Level.SEVERE, null, ex);
                                        JOptionPane.showMessageDialog(null, "Une erreur est survenu, contactez moi à cette adresse : jerome.gerard02@outlook.fr", "Echec",JOptionPane.ERROR_MESSAGE);

        }
    }

    private static void generateLastFile(XSSFWorkbook wb2, ArrayList<Rayon> listRayon) {
        XSSFSheet sheet = wb2.createSheet("Recap");
        XSSFRow row = sheet.createRow(0);
        row.createCell(0).setCellValue("Ancien code Article");
        row.createCell(1).setCellValue("Code SAP");
        row.createCell(2).setCellValue("Emplacement");
        row.createCell(3).setCellValue("Ecart");
        int compteurLigne=1;
        for(Rayon rayon : listRayon){
                for(Article article : rayon.getListArticle()){
                    if(article.getEcart()!=0){
                
                    sheet.createRow(compteurLigne).createCell(0).setCellValue(article.getAncienCodeArticle());
                    sheet.getRow(compteurLigne).createCell(1).setCellValue(article.getCodeArticle());
                    sheet.getRow(compteurLigne).createCell(2).setCellValue(article.getEmplacement());
                    sheet.getRow(compteurLigne).createCell(3).setCellValue(article.getEcart());
                    compteurLigne++;
                }
                }
                }
    }
}
