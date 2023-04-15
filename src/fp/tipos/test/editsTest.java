package fp.tipos.test;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

import fp.tipos.Action;
import fp.tipos.FactoriaEdits;
import fp.tipos.GeoIp;
import fp.tipos.edits;
import fp.tipos.recopilaciónEdits;

public class editsTest {
	public static void main(String[] args) {
		LocalDate f1=LocalDate.of(2005,12,27);
		LocalDate f2=LocalDate.of(2011,6,27);
		List <String> urls= List.of("https://en.wikipedia.org/w/index.php?diff=795330615&amp","oldid=794447219");
		System.out.println("Test constructor");
		edits e1= new edits(Action.EDIT,-1,false,false,"Apollon Patras B.C.","Thanbla",urls,f1);
		System.out.println("edit1: "+e1);
        System.out.println("Es anonimo: "+e1.getIsAnonymous());
        System.out.println("Titulo de la pagina "+e1.getPageTitle());
        e1.setPageTitle("Nuevo titulo");
        System.out.println("Titulo cambiado de la pagina "+e1.getPageTitle());
        System.out.println("Test constructor 2");
        GeoIp g1=new GeoIp("Kasan","India","Haryana",28.3667,76.9);
    
        List <String> urls2= List.of("https://en.wikipedia.org/w/index.php?diff=795329324&amp","oldid=785009512");
        edits e2= new edits(Action.EDIT,-1,false,false,"Salim Chisti",g1,"122.176.254.6",urls2,f2);
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
        	edits e4= new edits(Action.EDIT,-1,false,false,"Apollon Patras B.C.","Thanbla",urls,f3);
        	
        }catch(IllegalArgumentException e) {
        	System.out.println("Fecha incorrecta");
       }
        try {
        	edits e4= new edits(Action.EDIT,-1,false,false,null,"Thanbla",urls,f1);
        	
        }catch(IllegalArgumentException e) {
        	System.out.println("Titulo incorrecto");
       }
        try {
        	edits e4= new edits(Action.TALK,-1,false,false,"titulo","Thanbla",urls,f1);
        	
        }catch(IllegalArgumentException e) {
        	System.out.println("Action incorrecta");
       }
        
        
        //Test factoria
        System.out.println("test factoria");
        List<edits> f=  FactoriaEdits.leeredits("data/edits.csv");
        System.out.println(f.get(0));
        System.out.println(f.get(18));
        
        //Test contenedor
        System.out.println("test contenedor");
        recopilaciónEdits re1=new recopilaciónEdits("Name", f3, f);
        System.out.println("Nombre creador recopilacion uno "+re1.getAutorReco());
        System.out.println("Primer elemento reco: "+re1.getEditsList().get(0));
        recopilaciónEdits re2=new recopilaciónEdits("nombre", f3 );
        System.out.println("Nombre creador recopilacion dos"+re2.getAutorReco());
        System.out.println("Num elemento reco1:"+ re1.numelementos());
        edits e5 =new edits(Action.EDIT,-1,false,false,"Yo",g1,"prueba",urls2,f2);
        re1.añadir(e5);
        System.out.println("comprobar que e1 se añadio al registro uno"+re1.getEditsList().contains(e5));
        re2.añadirColeccion(f);
        System.out.println("Comprobar que se añadio la lista de edits a re2:"+re2.getEditsList().get(10));
        System.out.println("Test eliminar");
        re1.eliminarElemento(e5);
        System.out.println("comprobar que e5 se elimino del registro uno"+re1.getEditsList().contains(e5));
        edits e6=re1.getEditsList().get(0);
        re1.eliminarElemento(0);
        System.out.println("comprobar que se elimino el primer elemento  del registro uno"+re1.getEditsList().contains(e6));
        System.out.println("test equals :"+re1.equals(re2));
        System.out.println(re1);
        
        //Test tratamientos secuenciales
        System.out.println("Test tratamientos secuenciales");
        System.out.println("Existe algun edit hecho por el usuairo MONGO: "+re1.ExisteUnEditsDeUsuario("MONGO"));
        System.out.println("Media  edits hechos por usuario registrados "+re1.mediaEditsPorUsuariosRegistrados());
        System.out.println("Lista de edits hechos en India "+re1.editsPorPais("India"));
        System.out.println("Lista de edits agrupados por año "+re1.editsPorAño());
        System.out.println("diccionario que agrupa el numero de edits que tienen un mismo numero de caracteres cambiados "+re1.NumEditsPorCaracCambiados());
	}}
        