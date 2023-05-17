package fp.tipos;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import fp.utiles.Checkers;





public class FactoriaEdits {
	public static edits create(String lineaCSv) {
		//crea un objeto de tipo edits a partir de una cadena de texto.
		edits res=null;
		String[] campos=lineaCSv.split(",");
		Checkers.check("Error en longitud"+" "+campos[1]+" "+campos.length, campos.length==10 || campos.length==14);
		if(campos.length==10) {
	    Action action=Action.valueOf(campos[0].replace('"', ' ').trim().toUpperCase());
	    Integer changesize=Integer.valueOf(campos[1]);
	    GeoIp geoip=null;
	    Boolean isBot=Boolean.valueOf(campos[4]);
	    Boolean isMinor=Boolean.valueOf(campos[5]);
	    String pageTitle=campos[6].trim();
	    String[] subcampos=campos[7].split(";");
	    List<String> urls=new ArrayList<String>();
	    urls.add(subcampos[0].trim());
	    urls.add(subcampos[1].trim());
	    String user=campos[8].trim();
	    LocalDate fechaEdit=LocalDate.parse(campos[9].replace(";", " ").replace('"', ' ').trim(),DateTimeFormatter.ofPattern("d/M/yyyy") );
	    res=new edits(action, changesize, isBot, isMinor, pageTitle, geoip, user, urls, fechaEdit);
		}else {
			 Action action=Action.valueOf(campos[0].replace('"', ' ').trim().toUpperCase());
			    Integer changesize=Integer.valueOf(campos[1]);
			    GeoIp geoip=parseaGeoIp(campos[2].trim()+","+campos[3].trim()+","+campos[4].trim()+","+campos[5].trim()+","+campos[6].trim());
			    Boolean isBot=Boolean.valueOf(campos[8]);
			    Boolean isMinor=Boolean.valueOf(campos[9]);
			    String pageTitle=campos[10].trim();
			    String[] subcampos=campos[11].split(";");
			    List<String> urls=new ArrayList<String>();
			    urls.add(subcampos[0].trim());
			    urls.add(subcampos[1].trim());
			    String user=campos[12].trim();
			    LocalDate fechaEdit=LocalDate.parse(campos[13].replace(";", " ").replace('"', ' ').trim(),DateTimeFormatter.ofPattern("d/M/yyyy") );
			    res=new edits(action, changesize, isBot, isMinor, pageTitle, geoip, user, urls, fechaEdit);
		}
	    
		return res;
		}

	public static GeoIp parseaGeoIp(String cadena) {
		//crea un objeto de tipo GeoIp a partir de una cadena de texto.
		String[] campos=cadena.split(",|\\:");
		String city=campos[1].replace('"',' ').replace('{',' ').replace('}',' ').trim();
		String countryName=campos[5].replace('"',' ').replace('{',' ').replace('}',' ').trim();
		String regionName =campos[7].replace('"',' ').replace('{',' ').replace('}',' ').trim();
		Double latitud=Double.valueOf(campos[3].replace('"',' ').replace('{',' ').replace('}',' ').trim());
		Double longitud=Double.valueOf(campos[9].replace('"',' ').replace('{',' ').replace('}',' ').trim());
		return new GeoIp(city,countryName,regionName,latitud,longitud);
	}
	public static List<edits> leeredits(String rutaFichero) {
		//crea una lista de objetos de tipo edits a partir de un fichero.

		List<edits> res=new ArrayList<>();
	    try {
			res=Files.lines(Paths.get(rutaFichero))
					   .map(FactoriaEdits::create)
					   .collect(Collectors.toList());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}
	public static recopilaciónEdits creaRecopilacionEdits(String autorReco,LocalDate creacionReco,String rutaFichero) {
	
	
			recopilaciónEdits res=null;
			try {
				Stream<edits> sp = Files.lines(Paths.get(rutaFichero))
						.map(FactoriaEdits::create);
				res=new recopilaciónEdits(autorReco, creacionReco, sp);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		return res;
	}  
	
	
}
