/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import com.backendless.BackendlessUser;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;

import java.util.ArrayList;

import java.util.Date;
import java.util.List;

/**
 *
 * @author user
 */
public class Game extends RecursiveTreeObject<Game> {
    
  private String objectId; 
  private String typegame;
  private Date created;
  private String dateFin;
  private List<BackendlessUser> Users =new ArrayList<BackendlessUser>() ;
 private List<Round> rounds = new ArrayList<Round>();

    public Game() {
    }

  
    

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }
      public String getTypegame() {
        return typegame;
    }

    public void setTypegame(String typegame) {
        this.typegame = typegame;
    }
    

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getDateFin() {
        return dateFin;
    }

    public void setDateFin(String dateFin) {
        this.dateFin = dateFin;
    }

    public List<BackendlessUser> getUsers() {
        return Users;
    }

    public void setUsers(List<BackendlessUser> Users) {
        this.Users = Users;
    }

    public List<Round> getRounds() {
        return rounds;
    }

    public void setRounds(List<Round> rounds) {
        this.rounds = rounds;
    }
 
 
 
         
  
    
}
