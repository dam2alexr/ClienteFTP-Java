package me.alejandro.ftp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TestFTP {

	public static void main(String[] args) {
		// Declaramos la ruta del archivo
		String archivoConfig = "res/ftpconfig.data";
		// Variables necesarias para el bucle
		String linea;
		String[] lectura = null;
		try {
			// Se almacena el archivo
			File archivo = new File(archivoConfig);
			// Leemos el archivo, lo almacena mediante un flujo de entrada de bytes 
			InputStream is = new FileInputStream(archivo);
			// Leemos el texto procedente del InputStream
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			//connectionname#address#puerto#usuarioftp#claveftp
			while(br.ready()) {
				// Vamos almacenando en las lineas
				linea = br.readLine();
				// Determinamos que cada palabra estará separada por #
				lectura = linea.split("#");
			}
			// Si todo lo anterior ha salido bien, mostramos un mensaje
			System.out.println("Los datos de conexión obtenidos de '" + archivo.toString() + "' son los siguientes:\n"
					+ "host=" + lectura[0] + ", usuario=" + lectura[1] + ", contrasena=" + lectura[2] + "\n");
			// Cerramos los flujos, importante, si no siempre están activos y
			// es memoria que estamos ocupando para nada.
			is.close();
			br.close();
			// Mostramos el mensaje de menú.
			System.out.println("Elige la opción que desees:\n"
					+ "1. Conectar al servidor\n"
					+ "2. Listar ficheros y directorios\n"
					+ "3. Buscar un fichero o directorio\n"
					+ "4. Descargar un fichero\n"
					+ "5. Subir un fichero\n"
					+ "6. Desconectar\n"
					+ "7. Salir del cliente FTP");
			// Excepciones
		} catch (IOException e) {
			// Mensaje
			System.out.println("Hay algún error en el archivo y su lectura.");
			e.printStackTrace(); // Mensaje detallado por parte del IDE
		}

		// Creamos un objeto de la clase
		ClienteFTP cliente1 = new ClienteFTP(lectura[0], lectura[1], lectura[2]);
		//ClienteFTP cliente2 = new ClienteFTP("ftp.servidor2.com", "usuario2", "password2");

		Thread hiloCliente1 = new Thread(cliente1);
		//Thread hiloCliente2 = new Thread(cliente2);

		hiloCliente1.start();
		//hiloCliente2.start();
		 
	}
}
