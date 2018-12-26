/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Object;

/**
 *
 * @author moi
 */
public class Article {
    private String ancienCodeArticle;
    private String codeArticle;
    private String designationArticle;
    private int stockDisponible;
    private String unite;
    private String emplacement;
    private int stockTrouve;
    private int ecart;
    
    public Article(String ancienCodeArticle ,String codeArticle, String designationArticle, int stockDisponible, String unite, String emplacement) {
        this.ancienCodeArticle=ancienCodeArticle;
        this.codeArticle = codeArticle;
        this.designationArticle = designationArticle;
        this.stockDisponible = stockDisponible;
        this.unite = unite;
        this.emplacement = emplacement;
        
    }

    
    
    

    public String getCodeArticle() {
        return codeArticle;
    }

    public void setCodeArticle(String codeArticle) {
        this.codeArticle = codeArticle;
    }

    public String getDesignationArticle() {
        return designationArticle;
    }

    public void setDesignationArticle(String designationArticle) {
        this.designationArticle = designationArticle;
    }

    public int getStockDisponible() {
        return stockDisponible;
    }

    public void setStockDisponible(int stockDisponible) {
        this.stockDisponible = stockDisponible;
    }

    public String getAncienCodeArticle() {
        return ancienCodeArticle;
    }

    public void setAncienCodeArticle(String ancienCodeArticle) {
        this.ancienCodeArticle = ancienCodeArticle;
    }

    public String getUnite() {
        return unite;
    }

    public void setUnite(String unite) {
        this.unite = unite;
    }

   

    public String getEmplacement() {
        return emplacement;
    }

    public void setEmplacement(String emplacement) {
        this.emplacement = emplacement;
    }

    public int getStockTrouve() {
        return stockTrouve;
    }

    public void setStockTrouve(int stockTrouve) {
        this.stockTrouve = stockTrouve;
    }

    public int getEcart() {
        return ecart;
    }

    public void setEcart(int ecart) {
        this.ecart = ecart;
    }
    
    
    
}
