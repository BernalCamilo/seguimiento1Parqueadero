package model;

import java.text.SimpleDateFormat;
import java.util.Date;

import Exception.CuposCarrosLlenoException;
import Exception.CuposMotosLlenoException;
import Exception.NoEsSuHorarioException;
import Exception.NoSeEncuentraPlacaException;
import Exception.PicoYPlacaCedulaException;

public class Parqueadero {
	//reglas
	//1: carros de la "a" - "m" solo ingresan desde las 7am-2pm(los demas desde las 2pm-10pm)
	//2: motos con la placa numero 00-49 desde las 7am-2pm(las demas desde las 2pm-10pm)
	//3: lunes(0,1), martes(2,3), miercoles(4,5), jueves(6,7), viernes(8,9)
	//4: arreglo de 100 carros
	//5: arreglo de 200 motos
	
	private Vehiculos vehiculo;
	
	public Parqueadero() {
		vehiculo=new Vehiculos();
	}
	
	public boolean verificarCedula(String cedula) throws PicoYPlacaCedulaException {
		Date d = new Date();
		SimpleDateFormat sdfDayWeek = new SimpleDateFormat("u");
		String dayWeek = sdfDayWeek.format(d);
		String ultimoNumeroCedulaString=""+cedula.charAt(cedula.length()-1);
		int ultimoNumeroCedula = Integer.parseInt(ultimoNumeroCedulaString);
		boolean ingreso=false;
		if (dayWeek.equals("1") && ultimoNumeroCedula!=0 && ultimoNumeroCedula!=1) {
			ingreso=true;
		} else if (dayWeek.equals("2") && ultimoNumeroCedula!=2 && ultimoNumeroCedula!=3) {
			ingreso=true;
		}else if (dayWeek.equals("3") && ultimoNumeroCedula!=4 && ultimoNumeroCedula!=5) {
			ingreso=true;
		}else if (dayWeek.equals("4") && ultimoNumeroCedula!=6 && ultimoNumeroCedula!=7) {
			ingreso=true;
		}else if (dayWeek.equals("5") && ultimoNumeroCedula!=8 && ultimoNumeroCedula!=9) {
			ingreso=true;
		}else if (dayWeek.equals("6") || dayWeek.equals("7")) {
			ingreso=true;
		}else {
			throw new PicoYPlacaCedulaException();
		}
		return ingreso;
	}
	
	public void agregarV (String placa) throws CuposCarrosLlenoException, CuposMotosLlenoException, NoEsSuHorarioException {
		vehiculo.agregarVehiculo(placa);
	}
	
	public void registrarSalida(String placa) throws NoSeEncuentraPlacaException {
		vehiculo.eliminarPlaca(placa);
	}
	
	public String getCarros() {
		String carros = vehiculo.carrosLibres();
		return carros;
	}
	
	public String getMotos() {
		String motos = vehiculo.motosLibres();
		return motos;
	}
	
	
}
