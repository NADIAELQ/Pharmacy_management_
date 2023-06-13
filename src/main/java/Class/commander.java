package Class;
import java.sql.Date;
public class commander {
	
	  int nbrCmd;
	  double prixCmd;
	  String etatcmd;
	  Date date;
	  public commander() {
		  super();
	  }
	  public commander(int nbrCmd, double prixCmd, String etatcmd, Date date ) {
	  this.nbrCmd=nbrCmd;
	  this.etatcmd=etatcmd;
	  this.date=date;

	  }
		
	public int getNbrCmd() {
		return nbrCmd;
	}
	public void setNbrCmd(int nbrCmd) {
		this.nbrCmd = nbrCmd;
	}
	public double getPrixCmd() {
		return prixCmd;
	}
	public void setPrixCmd(int prixCmd) {
		this.prixCmd = prixCmd;
	}
	public String getEtatcmd() {
		return etatcmd;
	}
	public void setEtatcmd(String etatcmd) {
		this.etatcmd=etatcmd;
}
}



