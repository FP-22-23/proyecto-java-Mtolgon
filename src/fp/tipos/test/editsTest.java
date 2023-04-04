package fp.tipos.test;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

import fp.tipos.Action;
import fp.tipos.GeoIp;
import fp.tipos.edits;

public class editsTest {
	public static void main(String[] args) {
		LocalDate f1=LocalDate.of(2005,12,27);
		LocalDate f2=LocalDate.of(2011,6,27);
		List <String> urls= List.of("https://en.wikipedia.org/w/index.php?diff=795330615&amp","oldid=794447219");
		System.out.println("Test constructor");
		edits e1= new edits(Action.EDITS,-1,false,false,"Apollon Patras B.C.","Thanbla",urls,f1);
		System.out.println("edit1: "+e1);
        System.out.println("Es anonimo: "+e1.getIsAnonymous());
        System.out.println("Titulo de la pagina "+e1.getPageTitle());
        e1.setPageTitle("Nuevo titulo");
        System.out.println("Titulo cambiado de la pagina "+e1.getPageTitle());
        System.out.println("Test constructor 2");
        GeoIp g1=new GeoIp("Kasan","India","Haryana",28.3667,76.9);
    
        List <String> urls2= List.of("https://en.wikipedia.org/w/index.php?diff=795329324&amp","oldid=785009512");
        edits e2= new edits(Action.EDITS,-1,false,false,"Salim Chisti",g1,"122.176.254.6",urls2,f2);
        System.out.println("Geoip del edit2"+e2.getGeoIp()); 
        System.out.println("Latitud del edit2"+e2.getGeoIp().latitud());
        System.out.println("Es anonimo: "+e2.getIsAnonymous());
        System.out.println("Test equals y compareto");
        System.out.println("compareTo e1 y e2");
        System.out.println(e1.compareTo(e2));
        System.out.println("equals");
        System.out.println("Es e1 igual que e2:"+e1.equals(e2));
        edits e3=e1;
        System.out.println("Es e1 igual que e3:"+e1.equals(e3));
        LocalDate f3=LocalDate.of(1999,6,27);
        System.out.println("Test checkers");
        //Test checkers
        try {
        	edits e4= new edits(Action.EDITS,-1,false,false,"Apollon Patras B.C.","Thanbla",urls,f3);
        	
        }catch(IllegalArgumentException e) {
        	System.out.println("Fecha incorrecta");
       }
        try {
        	edits e4= new edits(Action.EDITS,-1,false,false,null,"Thanbla",urls,f1);
        	
        }catch(IllegalArgumentException e) {
        	System.out.println("Titulo incorrecto");
       }
        try {
        	edits e4= new edits(Action.TALK,-1,false,false,"titulo","Thanbla",urls,f1);
        	
        }catch(IllegalArgumentException e) {
        	System.out.println("Action incorrecta");
       }
	}}
        