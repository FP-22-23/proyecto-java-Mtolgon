package fp.tipos;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;

import fp.utiles.Checkers;


public class recopilaciónEdits {
	private String autorReco;
	private LocalDate creacionReco;
private List<edits> editsList;
/**
 *- AutorReco, de tipo String, consultable. 
 *Representa el nombre del autor de la recopilacion
- creacionReco, de tipo LocalDate, consultable. 
Representa la fecha en la que se creo la recopilacion.
 - editsList, de tipo List, consultable.
 Representa una lista de objetos de tipo edits.
 * 

 */
public recopilaciónEdits(String autorReco, LocalDate creacionReco, List<fp.tipos.edits> edits) {
	super();
	this.autorReco = autorReco;
	this.creacionReco = creacionReco;
	this.editsList = edits;
}
public recopilaciónEdits(String autorReco, LocalDate creacionReco) {
	editsList=new ArrayList<>();
	this.autorReco = autorReco;
	this.creacionReco = creacionReco;
}

public Integer numelementos() {
	//devuelve el numero de elementos de editsList
	return editsList.size();
}
public void añadir(edits e) {
	//añade un elemento a editsList
	editsList.add(e);
}

public void añadirColeccion(List<edits> e) {
	//añade una lista de elementos a editsList
	editsList.addAll(e);
}

public void eliminarElemento(edits e) {
	//elimina un elemento de editsList, recive el objeto .
	Checkers.check("Elemento no existente en el registro", editsList.contains(e));
	editsList.remove(e);
}
public void eliminarElemento(Integer e) {
	////elimina un elemento de editsList,  recive su posicion.
	Checkers.check("Indice superior al número de elementos", e<editsList.size());
	editsList.remove(editsList.get(e));
}
public boolean ExisteUnEditsDeUsuario(String user) {
	//devuelve true si hay al menos un edits hecho por un usario dado.
	Boolean res=false;
	for(edits e : editsList) {
		if(e.getUser().equals(user)) {
			res=true;
			break;
		}}
	return res;
}

public Double mediaEditsPorUsuariosRegistrados() {
	//devuelve la media  de cuantos edits han sido hechos por usuarios registrados.
	Integer usuariosReg=0;
	for(edits e : editsList) {
		if(e.getIsAnonymous()) {
			usuariosReg++;
		}
	}
	Double res=usuariosReg.doubleValue()/editsList.size();
	return res;
}
public List<edits> editsPorPais(String pais){
	//devuelve una lista con todos los edits hechos en un pais dado.
	List<edits> res=new ArrayList<>();
	for(edits e:editsList) {
		if(e.getGeoIp()!=null && e.getGeoIp().countryName().equals(pais)) {
			res.add(e);
		}
	}
	
	return res;
}

public Map<Integer, List<edits>> editsPorAño(){
	//devuelve un Map que agrupa los edits por el año en que se hiceron
	Map<Integer,List<edits>> res=new HashMap<>();
	for(edits e: editsList) {
		if(res.containsKey(e.getFechaEdit().getYear())) {
			res.get(e.getFechaEdit().getYear()).add(e);
		}else {
			List<edits> n=new ArrayList<>();
			n.add(e);
			res.put(e.getFechaEdit().getYear(), n);
		}
		
	}
	return  res;
}
public Map<Integer, Integer> NumEditsPorCaracCambiados(){
	//devuelve un Map que relaciona en el número de caracteres cambiados con el número de edits en los que se ha cambiado ese número de caracteres.
	Map<Integer,Integer> res=new HashMap<Integer,Integer>();
	for(edits e: editsList) {
		if(res.containsKey(e.getChangeSize())) {
			res.put(e.getChangeSize(), res.get(e.getChangeSize())+1);
		}else {
			
			res.put(e.getChangeSize(), 1);
		}
		
	}
	return  res;
}

public String getAutorReco() {
	return autorReco;
}
public LocalDate getCreacionReco() {
	return creacionReco;
}
public List<edits> getEditsList() {
	return editsList;
}
@Override
public int hashCode() {
	return Objects.hash(autorReco, creacionReco);
}
@Override
public boolean equals(Object obj) {
	// Dos recopilaciones son iguales si lo son su autor y su fecha de creacion.
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	recopilaciónEdits other = (recopilaciónEdits) obj;
	return Objects.equals(autorReco, other.autorReco) && Objects.equals(creacionReco, other.creacionReco);
}
@Override
public String toString() {
	return "Autor de la recopilacion " + autorReco + ", fecha de creación de la recopilacion " + creacionReco + ", Primer elemento: " + editsList.get(0) + "]";
}



}
