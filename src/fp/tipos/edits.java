package fp.tipos;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

import fp.utiles.Checkers;

public class edits {
	private Action action;
	private Integer changeSize;

	private Boolean isBot;
	private Boolean isMinor;
	private String pageTitle;
    private GeoIp geoIp ;
	private String user;
	private List<String> urls;
	private LocalDate fechaEdit;
	
	public edits(Action action, Integer changeSize, Boolean isBot, Boolean isMinor, String pageTitle, GeoIp geoIp,
			String user, List<String> urls,LocalDate fechaEdit) {
		/**
		 * @param action Indica la accion realizada por el usuario 
		 * @param changeSize Indica el número de carácteres cambiados
		 * @param isBot Indica si la pagina fue editada por un bot.
		 * @param isMinor Indica si el usuario que edito la página era menor de edad 
		 * @param pageTitle Indica el titulo de la página editada.
		 * @param geoIp Indica El lugar desde el que se editó la página, es null si el usuario está registrado 
		 * @param user Indica el nombre de usuario del usuario que editó la página. Será una ip si el usuario no está registrado 
		 * @param urls Contiene la direccion de la página antes y después de que fuese editada
		 * @param fechaEdit Indica la fecha en la que se editó la página
		 *  @throws IllegalArgumentException si action no es EDITS
		 *   * @throws IllegalArgumentException si pageTitle es null 
		 *    * @throws IllegalArgumentException si fechaEdit no esta entre el 15/1/2001 y la fecha actual
		 */
		Checkers.checkNoNull(pageTitle);
	    Checkers.check("Error en accion", action==Action.EDIT);
	    Checkers.check("Error en fecha", fechaEdit.isAfter(LocalDate.of(2001, 1, 15))&&fechaEdit.isBefore(LocalDate.now()));
		this.action = action;
		this.changeSize = changeSize;
		this.isBot = isBot;
		this.isMinor = isMinor;
		this.pageTitle = pageTitle;
		this.geoIp = geoIp;
		this.user = user;
		this.urls = urls;
		this.fechaEdit=fechaEdit;
	}
  
public edits(Action action, Integer changeSize, Boolean isBot, Boolean isMinor, String pageTitle, String user,
			List<String> urls, LocalDate fechaEdit) {
	    Checkers.checkNoNull(pageTitle);
	    Checkers.check("Error en accion", action==Action.EDIT);
	    Checkers.check("Error en fecha", fechaEdit.isAfter(LocalDate.of(2001, 1, 15))&&fechaEdit.isBefore(LocalDate.now()));
		this.action = action;
		this.changeSize = changeSize;
		this.isBot = isBot;
		this.isMinor = isMinor;
		this.pageTitle = pageTitle;
		this.user = user;
		this.urls = urls;
		this.fechaEdit = fechaEdit;
		this.geoIp=null;
		 
		
	}
    public LocalDate getFechaEdit() {
		return fechaEdit;
	}
   public GeoIp getGeoIp() {
		return geoIp;
	}


	public Action getAction() {
		return action;
	}

	public Integer getChangeSize() {
		return changeSize;
	}
    //propiedad deriva
	public Boolean getIsAnonymous() {
	//indica si el usuario que edito la página de wikipedia era anonimno o no
				 
	
	 return geoIp!=null ;
	}




	public String getUser() {
		return user;
	}
    
    public List<String> getUrls() {
		return urls;
	}
    public void setUrls(List<String> urls) {
		this.urls = urls;
	}
    public Boolean getIsBot() {
		return isBot;
	}
	
	public Boolean getIsMinor() {
		return isMinor;
	}
	public void setIsMinor(Boolean isMinor) {
		this.isMinor = isMinor;
	}
	public String getPageTitle() {
		return pageTitle;
	}
	public void setPageTitle(String pageTitle) {
		Checkers.checkNoNull(pageTitle);
		this.pageTitle = pageTitle;
	}

   public int hashCode() {
		return Objects.hash(changeSize, pageTitle);
	}
   //Dos edits son iguales si tienen el mismo changeSize y el mismo pageTitle
    public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		edits other = (edits) obj;
		return Objects.equals(changeSize, other.changeSize) && Objects.equals(pageTitle, other.pageTitle);
	}
    //Se ordenan segun el changeSize y el pageTitle
    public Integer compareTo(edits e) {
    	Integer res=this.getChangeSize().compareTo(e.getChangeSize());
        if(res==0) {
        	res=this.getPageTitle().compareTo(e.getPageTitle());
        } 	
    	
    	return res;
    }

    public String toString() {
		return getPageTitle()+"("+getChangeSize()+")"+", editada por :"+getUser();
	}

	

}
