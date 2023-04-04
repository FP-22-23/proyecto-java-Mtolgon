package fp.tipos;


public record GeoIp(String city,String countryName,String regionName,Double latitud,Double longitud) {
//Contiene informacion sobre una localizacion desde la que se hizo un edit
	/**
	* @param city, string contiene la ciudad des la que se hizo el edit
	* @param cuntryName, string contiene el pais desde el que se hizo el edit
	* @param regionName, string contiene la region desde la que se hizo el edit
	* @param latitud,double contiene la latitud desde la que se hizo el edit
	* @param longitud, double contiene la longitud desde la que se hizo el edit
	
	*/
}
