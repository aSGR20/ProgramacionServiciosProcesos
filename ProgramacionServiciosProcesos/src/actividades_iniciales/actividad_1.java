package actividades_iniciales;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class actividad_1 {
	public static void main(String[] args) {
		ProcessBuilder pb = new ProcessBuilder();
		String ruta = "c:\\";
		do {
			ruta = leerRuta();
		}while(!checkRuta(ruta));
		
		try {
			pb.command("cmd.exe", "dir" + ruta);
			Process p = pb.start();
			StringBuilder buffer = new StringBuilder();
			BufferedReader reader = new BufferedReader(
					new InputStreamReader(p.getInputStream()));
			String line;
			while((line = reader.readLine()) != null) {
				buffer.append(line + "\n");
			}
			
			if(p.waitFor() == 0) {
				System.out.println(buffer);
			}else {
				System.out.println("nope");
			}
			
		}catch(IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private static boolean checkRuta(String ruta) {
		return new File(ruta).exists();
	}

	private static String leerRuta() {
		Scanner text = new Scanner(System.in);
		System.out.print("Introduce la ruta: ");
		return text.nextLine();
	}
}
