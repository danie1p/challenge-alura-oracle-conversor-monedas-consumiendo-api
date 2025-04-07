package utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import modelos.ConversorMonedas;

public class UI {
    public static void start () throws IOException, InterruptedException {
        final Scanner entradaUsuario = new Scanner(System.in);
        while (true) {
            String monedaBase = seleccionarMonedaBase(entradaUsuario, optenerMenuPrincipal());
            if (monedaBase.equals("salir"))
                return;
            String monedaSecundaria = seleccionarMonedaSecundaria(entradaUsuario, optenerMenuSecundario(monedaBase), monedaBase);
            if (!monedaSecundaria.equals("regresarMenuPrincipal")){
                double cantidadAConvertir = -1.0;
                while (cantidadAConvertir < 0) {
                    System.out.println("Ingrese la cantidad en " + monedaBase + " a convertir en " + monedaSecundaria);
                    cantidadAConvertir = entradaUsuario.nextDouble();
                    boolean esMenorACero = cantidadAConvertir < 0;
                    if (!esMenorACero) {
                        ConversorMonedas conversorDeMonedas = new ConversorMonedas(monedaBase, monedaSecundaria, cantidadAConvertir);
                        System.out.println(String.format("%.3f", conversorDeMonedas.convertirDivisas()) + " " + monedaSecundaria + ".");
                        System.out.println("Presione enter para continuar");
                    } else {
                        System.out.println("La cantidad en " + monedaBase + " debe ser mayor a 0.00");
                    }
                    entradaUsuario.nextLine();
                    entradaUsuario.nextLine();
                }
            }
        }
    }

    public static String optenerMenuPrincipal() {
        Monedas[] monedas = Monedas.values();
        final StringBuilder menuPrincipal = new StringBuilder();
        menuPrincipal.append("""
                           ************************************************************************
                                             Bienvenido al Conversor de Monedas
                           En este momento soportamos la conversion entre cualquiera de estas
                           monedas:
                   """);
        for (int i = 0; i < monedas.length; i++)  {
            menuPrincipal.append( "\t\t" + (i + 1) + ". "  + monedas[i].toString() + "\n");
        }
        menuPrincipal.append("""
                       0. Salir del conversor de monedas.
                       Ingresa una moneda base del 1 al 6 (Para salir seleccione 0).
                       ************************************************************************
               """);
 return menuPrincipal.toString();
}

public static String optenerMenuSecundario(String monedaBase) {
 Monedas[] monedas = Monedas.values();
 int contador = 0;
 StringBuilder menuSecundario = new StringBuilder();

 menuSecundario.append("""
                              *************************************************
                                 Ingresa la moneda secundaria:
                              """);

        for (int i = 0; i < monedas.length; i++) {
            if (!monedas[i].toString().equals(monedaBase)) {
                contador++;
                menuSecundario.append("\t\t" + contador + ". " + monedas[i].toString() + "\n");
            }
        }

        menuSecundario.append("\t\t0. Regresar a menu principal\n");
        menuSecundario.append("""
                                      Ingresa un numero del 0 - 5 para seleccionar
                                      moneda secundaria.
                                  *************************************************
                                  """);
        return menuSecundario.toString();
    }

    public static String seleccionarMonedaBase(Scanner entradaUsuario, String menuPrincipal) {
        int opcionSeleccionada = -1;
        Monedas moneda;
        String monedaBase = "";

        while (opcionSeleccionada != 0) {
            System.out.println(menuPrincipal.toString());
            opcionSeleccionada = entradaUsuario.nextInt();
            if (opcionSeleccionada > 0 && opcionSeleccionada <= Monedas.values().length) {
                moneda = Monedas.values()[opcionSeleccionada - 1];
                monedaBase = moneda.toString();
                return monedaBase;
            }
        }
        return "salir";
    }

    public static String seleccionarMonedaSecundaria(Scanner entradaUsuario, String menuSecundario, String monedaBase) {
        int opcionSeleccionada = -1;
        List<String> monedasSecundarias = new ArrayList<>();
        String monedaSecundaria = "";

        for (Monedas moneda : Monedas.values()) {
            if (!moneda.toString().equals(monedaBase))
                monedasSecundarias.add(moneda.toString());
        }

        while (opcionSeleccionada != 0) {
            System.out.println(menuSecundario);
            opcionSeleccionada = entradaUsuario.nextInt();

            if (opcionSeleccionada > 0 && opcionSeleccionada <= monedasSecundarias.size()) {
                monedaSecundaria = monedasSecundarias.get(opcionSeleccionada - 1);
                return monedaSecundaria;
            }
            if (opcionSeleccionada != 0) {
                System.out.println("La opcione seleccionada no se encuentra en el menu.\n" +
                                   "Selecciona una opcion del menu. Presione enter para continuar.");
                entradaUsuario.nextLine();
                entradaUsuario.nextLine();
            }
        }

        return  "regresarMenuPrincipal";
    }
}



















