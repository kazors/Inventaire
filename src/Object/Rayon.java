/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Object;

import java.util.ArrayList;

/**
 *
 * @author moi
 */
public class Rayon {
    private String codeRayon;
    private ArrayList<Article> listArticle ;

    public Rayon(String codeRayon, ArrayList<Article> listArticle) {
        this.codeRayon = codeRayon;
        this.listArticle = listArticle;
    }

    public String getCodeRayon() {
        return codeRayon;
    }

    public void setCodeRayon(String codeRayon) {
        this.codeRayon = codeRayon;
    }

    public ArrayList<Article> getListArticle() {
        return listArticle;
    }

    public void setListArticle(ArrayList<Article> listArticle) {
        this.listArticle = listArticle;
    }
       
    
}
