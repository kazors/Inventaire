/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tools;

import Object.Article;
import Object.Rayon;
import Windows.MainWindow;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

/**
 *
 * @author moi
 */
public class FileLecture {
    
    
    

    public static ArrayList<Rayon> extractData(MainWindow context, File selectedFile, ArrayList<Rayon> listRayon) {
        
        FileInputStream fis= null;
        try {
            fis = new FileInputStream(selectedFile);
            
            Workbook excelFile = WorkbookFactory.create(selectedFile);
            
            Sheet dataSheet = excelFile.getSheetAt(0);
            createAllObject(dataSheet, listRayon);
            excelFile.close();
            fis.close();
            return listRayon;
        } catch (FileNotFoundException ex) {
                            JOptionPane.showMessageDialog(null, "Une erreur est survenu, contactez moi à cette adresse : jerome.gerard02@outlook.fr", "Echec",JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        } catch (IOException ex) {
                            JOptionPane.showMessageDialog(null, "Une erreur est survenu, contactez moi à cette adresse : jerome.gerard02@outlook.fr", "Echec",JOptionPane.ERROR_MESSAGE);
                            ex.printStackTrace();
        } catch (InvalidFormatException ex) {
                            JOptionPane.showMessageDialog(null, "Une erreur est survenu, contactez moi à cette adresse : jerome.gerard02@outlook.fr", "Echec",JOptionPane.ERROR_MESSAGE);
                            ex.printStackTrace();
        } catch (EncryptedDocumentException ex) {
                            JOptionPane.showMessageDialog(null, "Une erreur est survenu, contactez moi à cette adresse : jerome.gerard02@outlook.fr", "Echec",JOptionPane.ERROR_MESSAGE);
                            ex.printStackTrace();
        } finally {
            try {
                
                if(fis!=null)
                fis.close();
            } catch (IOException ex) {
                                            JOptionPane.showMessageDialog(null, "Une erreur est survenu, contactez moi à cette adresse : jerome.gerard02@outlook.fr", "Echec",JOptionPane.ERROR_MESSAGE);

                Logger.getLogger(FileLecture.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return null;
    }

    private static void createAllObject(Sheet dataSheet, ArrayList<Rayon> listRayon) {
    	int lastRowIndex ;
    	try{
        lastRowIndex = Integer.valueOf(JOptionPane.showInputDialog("Combien il y a t'il d'article dans le fichier ?"))-1;
    	}catch (NumberFormatException  | NullPointerException e){
    		lastRowIndex=0;
    	}
        
       for(int i=1;i<=lastRowIndex;i++){
         
         Row currentLine = dataSheet.getRow(i);
         
         String currentRayon = getEmplacement(currentLine);
           //System.out.println(" Rayon : "+ currentRayon);
           currentLine.getCell(1).setCellType(CellType.STRING);
             
         Article currentArticle = new Article(currentLine.getCell(1).getStringCellValue(), currentLine.getCell(0).getStringCellValue(),currentLine.getCell(2).getStringCellValue() ,(int)(currentLine.getCell(5).getNumericCellValue()),currentLine.getCell(6).getStringCellValue(), currentLine.getCell(4).getStringCellValue());
             
         if(listRayon.isEmpty() || listRayon.get(listRayon.size()-1).getCodeRayon().compareTo(currentRayon)!=0){
           listRayon.add(new Rayon(currentRayon, new ArrayList<Article>()));
           
       }
       listRayon.get(listRayon.size()-1).getListArticle().add(currentArticle);
       
       }
        
    }
    
    private static void readCompleteTxtFile(){
        
    }

    
    
    

    
    public static String getEmplacement(Row currentLine){
        
        boolean stop=false;
        String nomRayon="";
         for(char c :  currentLine.getCell(4).getStringCellValue().toCharArray() ){
                
                    if(!stop){
                    nomRayon+=c;
                    if(Character.isDigit(c)){
                        stop=true;
                    }
                    }
            }
         
         return nomRayon;
    }

    
    private static boolean testLigne(Row currentLine){
        if(currentLine.getCell(1).getCellType()==CellType.NUMERIC.getCode() && currentLine.getCell(3)!= null && !"".equals(currentLine.getCell(3).getStringCellValue())){
            return true;
        }else{
            return false;
        }
    }
    public static void ReadCompleteFile (MainWindow context,List<File> selectedFiles , ArrayList<Rayon> listRayon) {
        
        ArrayList<Integer> listValeurSaisie = new ArrayList<Integer>();
        for(File currentFile : selectedFiles){
            
//           
           // System.out.println(currentFile.getName());
try {
                
                
              

                FileReader fileReader = new FileReader(currentFile);
                
                BufferedReader reader = new BufferedReader(fileReader);
                String line= reader.readLine() ;
                
                while((line = reader.readLine())!=null){  
                    reader.readLine();
                   if(line!=null || !line.isEmpty()){
                    String[] tab = line.split("  ");
                    try {
                    if(tab.length==9){
                        
                        listValeurSaisie.add( !"".equals(tab[tab.length-1]) && tab[tab.length-1]!=null ?Integer.parseInt(tab[tab.length-1]):0);
                        }else{
                            listValeurSaisie.add(0);
                            
                        }
                    }catch (NumberFormatException n){
                    	listValeurSaisie.add(0);
                    }
                    
                }
                }

            } catch (FileNotFoundException ex) {
                                            JOptionPane.showMessageDialog(null, "Une erreur est survenu, contactez moi à cette adresse : jerome.gerard02@outlook.fr", "Echec",JOptionPane.ERROR_MESSAGE);

                Logger.getLogger(FileLecture.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(FileLecture.class.getName()).log(Level.SEVERE, null, ex);
                                            JOptionPane.showMessageDialog(null, "Une erreur est survenu, contactez moi à cette adresse : jerome.gerard02@outlook.fr", "Echec",JOptionPane.ERROR_MESSAGE);

            }
            }
        int total =0;
        for(Rayon rayon: listRayon){
            total+=rayon.getListArticle().size();
        }
        int compteur =0;
        for(Rayon rayon: listRayon){
            for(Article article : rayon.getListArticle()){
                System.out.println("compteur : "+compteur);
                System.out.println("VALEUR : " + listValeurSaisie.get(compteur));
                article.setStockTrouve(listValeurSaisie.get(compteur));
                
                compteur++;
                
            }
        }
        
    }
    
}
