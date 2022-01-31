package model;

import Exception.CuposMotosLlenoException;
import Exception.NoEsSuHorarioException;
import java.text.SimpleDateFormat;
import java.util.Date;
import Exception.CuposCarrosLlenoException;
import Exception.NoSeEncuentraPlacaException;

public class Vehiculos {
	public static final int CUPOSCARROS=100;
	public static final int CUPOSMOTOS=200;
	public static final int HORAENTRADA=7;
	public static final int HORACAMBIOENTRADA=14;
	public static final int HORASALIDA=22;
	String[] carros=new String[CUPOSCARROS];
	String[] motos=new String[CUPOSMOTOS];
	
	//atributos
	
	
	//metodos
	public Vehiculos() {
		
	}
	
	public void agregarVehiculo(String placa) throws CuposCarrosLlenoException, CuposMotosLlenoException, NoEsSuHorarioException {
		boolean esCarro=false;
		char ultima = placa.charAt(placa.length()-1);
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		Date d = new Date();
		String hora = sdf.format(d);
		String[] partes = hora.split(":");
		int horas=Integer.parseInt(partes[0]);
		
		if (ultima>='a' && ultima<='z') {
			String numerosPlacaMoto=""+placa.charAt(3)+placa.charAt(4);
			int placaMoto=Integer.parseInt(numerosPlacaMoto);
			if (placaMoto>=0 && placaMoto<=49 && horas>=HORAENTRADA && horas<HORACAMBIOENTRADA ) {
				agregarMoto(placa);
			} else if (placaMoto>=50 && placaMoto<=99 && horas>=HORACAMBIOENTRADA && horas<HORASALIDA) {
				agregarMoto(placa);
			} else {
				throw new NoEsSuHorarioException();
			}
		} else {
			if (placa.charAt(0)>='a' && placa.charAt(0)<='m' && horas>=HORAENTRADA && horas<HORACAMBIOENTRADA) {
				agregarCarro(placa);
			}else if (placa.charAt(0)>'m' && placa.charAt(0)<='z' && horas>=HORACAMBIOENTRADA && horas<HORASALIDA) {
				agregarCarro(placa);
			}else {
				throw new NoEsSuHorarioException();
			}
		}
		
	}
	
	public void agregarCarro(String placa) throws CuposCarrosLlenoException {
		boolean encontroHueco=false;
		for (int i=0; i>carros.length || encontroHueco==false; i++) {
			if (carros[i]==null) {
				carros[i]=placa;
				encontroHueco=true;
			}
			
		}
		if (encontroHueco==false) {
			throw new CuposCarrosLlenoException();
		}
		
	}
	
	public void agregarMoto(String placa) throws CuposMotosLlenoException {
		boolean encontroHueco=false;
		for (int i=0; i>motos.length || encontroHueco==false; i++) {
			if (motos[i]==null) {
				motos[i]=placa;
				encontroHueco=true;
			}
		}
		if (encontroHueco==false) {
			throw new CuposMotosLlenoException();
		}
	}
	
	public void eliminarPlaca(String placaEliminar) throws NoSeEncuentraPlacaException {
		boolean seElimino=false;
		for (int i=0; i<carros.length; i++) {
			if (carros[i]!=null) {
				if (carros[i].equalsIgnoreCase(placaEliminar)) {
					carros[i]=null;
					seElimino=true;
				}
			}
			if (i<motos.length) {
				if (motos[i]!=null) {
					if (motos[i].equalsIgnoreCase(placaEliminar)) {
						motos[i]=null;
						seElimino=true;
					}
				}
			}
		}
		if (seElimino==false) {
			throw new NoSeEncuentraPlacaException();
		}
	}
	
	public String carrosLibres() {
		int contador =0;
		for(int i=0; i<carros.length; i++) {
			if (carros[i]==null) {
				contador++;
			}
		}
		String cupos = "espacios disponible de carros: "+contador;
		return cupos;
	}
	
	public String motosLibres() {
		int contador =0;
		for(int i=0; i<motos.length; i++) {
			if (motos[i]==null) {
				contador++;
			}
		}
		String cupos = "espacios disponible de motos: "+contador;
		return cupos;
	}
}
