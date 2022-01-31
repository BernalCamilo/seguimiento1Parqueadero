package ui;

import java.text.SimpleDateFormat;
import java.util.Date;
import model.*;
import java.util.Scanner;
import Exception.CuposCarrosLlenoException;
import Exception.CuposMotosLlenoException;
import Exception.NoEsSuHorarioException;
import Exception.NoSeEncuentraPlacaException;
import Exception.PicoYPlacaCedulaException;

public class Main {
	//reglas
	//1: carros de la "a" - "m" solo ingresan desde las 7am-2pm(los demas desde las 2pm-10pm)
	//2: motos con la placa numero 00-49 desde las 7am-2pm(las demas desde las 2pm-10pm)
	//3: lunes(0,1), martes(2,3), miercoles(4,5), jueves(6,7), viernes(8,9)
	//4: arreglo de 100 carros
	//5: arreglo de 200 motos
	
	public static Scanner lec; 
	public static Parqueadero parqueadero;

	public static void main(String[] args)  {
		lec = new Scanner(System.in);
		parqueadero= new Parqueadero();
		boolean continuar=true;
		while(continuar) {
			System.out.println("\n"+"--------------------MENU-PRINCIPAL------------------"+"\n");
			System.out.println(parqueadero.getCarros());
			System.out.println(parqueadero.getMotos());
			System.out.println("1: ingreso de vehiculo");
			System.out.println("2: salida de vehiculo");
			System.out.println("3: cierre del dia");
			String menu = lec.nextLine();
			switch (menu) {
			case "1": 
				ingresoVehiculo();
				break;
			case "2":
				eliminarPlaca();
				break;
			case "3":
				continuar=false;
				break;
			default:
				System.out.println("ingreso una opcion invalida");
				System.out.println("ingrese enter para continuar");
				lec.nextLine();
			}
		}
	}
	
	public static void ingresoVehiculo()  {
		System.out.println("\n"+"------------------INGRESO-USUARIO----------------"+"\n");
		System.out.println("ingrese la cedula: ");
		String cedula = lec.nextLine();
		try {
			if (parqueadero.verificarCedula(cedula)==true) {
				System.out.println("Ingrese la placa del vehiculo");
				String placa = lec.nextLine();
				placa=placa.toLowerCase();
				try {
					parqueadero.agregarV(placa);
				} catch (CuposCarrosLlenoException ex) {
					System.out.println(ex.getMessage());
				} catch(CuposMotosLlenoException ex) {
					System.out.println(ex.getMessage());
				} catch(NoEsSuHorarioException ex) {
					System.out.println(ex.getMessage());
				} catch (Exception ex) {
					System.out.println(ex.getMessage());
				}
			}
		} catch (PicoYPlacaCedulaException ex) {
			System.out.println(ex.getMessage());
		} catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
		System.out.println("presione enter para continuar");
		lec.nextLine();
	}
	
	public static void eliminarPlaca() {
		System.out.println("\n"+"-----------------SALIDA-USUARIO----------------"+"\n");
		System.out.println("ingrese la placa del vehiculo que desea eliminar");
		String placa = lec.nextLine();
		placa=placa.toLowerCase();
		try {
			parqueadero.registrarSalida(placa);
		} catch (NoSeEncuentraPlacaException ex) {
			System.out.println(ex.getMessage());
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}

}
