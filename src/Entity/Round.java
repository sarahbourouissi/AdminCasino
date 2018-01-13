/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import com.backendless.BackendlessUser;
import java.util.Date;

/**
 *
 * @author user
 */
public class Round {
    private String Usermail;
    private String objectId; 
  
  private Date created;
  private String dates;
  private Double result;
  
  private Double resultfloat;
  
  private Game game;
  private BackendlessUser user;

    public String getDates() {
        return dates;
    }

    public void setDates(String dates) {
        this.dates = dates;
    }

    public String getUsermail() {
        return Usermail;
    }

    public void setUsermail(String Usermail) {
        this.Usermail = Usermail;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Double getResult() {
        return result;
    }

    public void setResult(Double result) {
        this.result = result;
    }

    public Double getResultfloat() {
        return resultfloat;
    }

    public void setResultfloat(Double resultfloat) {
        this.resultfloat = resultfloat;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public BackendlessUser getUser() {
        return user;
    }

    public void setUser(BackendlessUser user) {
        this.user = user;
    }
  
  
  
  
  
    
    
}
