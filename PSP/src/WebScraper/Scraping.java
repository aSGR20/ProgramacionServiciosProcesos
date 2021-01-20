package WebScraper;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class Scraping {

	public static final String url = "https://www.bolsamadrid.es/esp/aspx/Mercados/Precios.aspx?indice=ESI100000000&punto=indice";
	public static final Datos ibex = new Datos();
	public static final String urlPage = String.format(url);

	/* ASIGNACION DEL TIEMPO EN EL QUE EL PROGRAMA ESTÁ DORMIDO */
	public static final int tiempo = 60000;

	/* ASIGNACION DE LA RUTA EN LA QUE SE VA A GUARDAR EL FICHERO */
	public static final String rutaFichero = ".\\src\\WebScraper\\";

	/* ASIGNACION DEL NOMBRE DEL FICHERO QUE SE VA A GUARDAR */
	public static final String nombreFichero = "Ibex35.txt";

	public static void main(String args[]) throws InterruptedException {

		// Bucle infinito para ejecutar el programa infinitamente
		while (true) {
			// Comprobacion de que la conexión me dé sea un código 200
			if (getStatusConnection(urlPage) == 200) {

				// Obtengo el código HTML de la página
				Document document = getHTMLDocument(urlPage);

				// Selecciona la primera tabla con la clase TblPort
				Element table = document.select("table.TblPort").first();

				// Creo una ArrayList para meter el contenido
				ArrayList<String> contenido = new ArrayList<>();

				// Por cada <td> de la tabla seleccionado, lo meto en una ArrayList
				for (Element row : table.select("td")) {
					contenido.add(row.text());
				}

				// Meto el ArrayList obtenido del contenido de la tabla en el objeto Datos
				ibex.setNombre(contenido.get(0));
				ibex.setAnterior(contenido.get(1));
				ibex.setUltimo(contenido.get(2));
				ibex.setDifPorcentaje(contenido.get(3));
				ibex.setMaximo(contenido.get(4));
				ibex.setMinimo(contenido.get(5));
				ibex.setFecha(contenido.get(6));
				ibex.setHora(contenido.get(7));
				ibex.setDifPorcentaje2021(contenido.get(8));

				// Llamo al método para guardar el objeto Datos en un fichero
				guardarFichero();

			} else {
				System.out.println("Status de la página: " + getStatusConnection(urlPage));
			}
			// Duermo el programa durante un tiempo asignado anteriormente
			Thread.currentThread().sleep(tiempo);
			// Muestro por pantalla un aviso para saber cuando se actualiza el fichero
			System.out.println("Actualizado");
		}
	}

	/**
	 * Compruebo el status de la conexión y comprueba la respuesta que recibo <br>
	 * al hacer la petición: <br>
	 * - 200 OK <br>
	 * - 300 Multiple Choices <br>
	 * - 301 Moved Permanently <br>
	 * - 305 Use Proxy <br>
	 * - 400 Bad Request <br>
	 * - 403 Forbidden <br>
	 * - 404 Not Found <br>
	 * - 500 Internal Server Error <br>
	 * - 502 Bad Gateway <br>
	 * - 503 Service Unavailable <br>
	 * 
	 * @param url
	 * @return Status Code
	 */
	public static int getStatusConnection(String url) {
		Response response = null;

		try {
			response = Jsoup.connect(url).userAgent("Mozilla/5.0").timeout(100000).ignoreHttpErrors(true).execute();
		} catch (IOException ex) {
			System.out.println("Excepción al obtener el status de la página: " + ex.getMessage());
		}
		return response.statusCode();
	}

	/**
	 * Devuelvo el contenido del HTML de la web
	 * 
	 * @param url
	 * @return Documento con el HTML
	 */
	public static Document getHTMLDocument(String url) {

		Document doc = null;

		try {
			doc = Jsoup.connect(url).userAgent("Mozilla/5.0").timeout(100000).get();
		} catch (IOException ex) {
			System.out.println("Excepción al obtener el HTML de la página: " + ex.getMessage());
		}

		return doc;
	}

	/**
	 * Guardo el contenido del objeto Datos en un fichero con una ruta <br>
	 * determinada
	 */
	public static void guardarFichero() {
		File fichero = new File(rutaFichero + nombreFichero);
		try {
			FileWriter fw = new FileWriter(fichero, true);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter salida = new PrintWriter(bw);
			String linea = ibex.getFecha() + "," + ibex.getHora() + "," + ibex.getNombre() + "," + ibex.getAnterior()
					+ "," + ibex.getUltimo() + "," + ibex.getDifPorcentaje() + "," + ibex.getMaximo() + ","
					+ ibex.getMinimo() + "," + ibex.getDifPorcentaje2021();
			salida.println(linea);
			salida.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}