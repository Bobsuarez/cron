package org.example;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalTime;

public class Main {
    public static final String POSTS_API_URL = "https://api.chucknorris.io/jokes/categories";

    public static void main(String[] args) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .header("accept", "application/json")
                .uri(URI.create(POSTS_API_URL))
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());


        System.out.println(response.body());
        CrearArchivoJava(response.body());
    }

    private static void CrearArchivoJava(String tesxt) {
        LocalTime nuevo = LocalTime.now();
        String nombreArchivo = "data-" + nuevo.getHour() + ":" + nuevo.getMinute() + ".txt";
        System.out.println(nombreArchivo);

        try {
            // Crea un objeto File para representar el archivo
            File archivo = new File(nombreArchivo);

            // Verifica si el archivo ya existe
            if (archivo.createNewFile()) {
                System.out.println("El archivo ha sido creado con éxito.");
            } else {
                System.out.println("El archivo ya existe.");
            }

            // Puedes usar FileWriter para escribir datos en el archivo
            FileWriter escritor = new FileWriter(archivo);

            // Escribe datos en el archivo
            escritor.write("Este es un ejemplo de cómo crear un archivo en Java.\n");
            escritor.write(tesxt);

            // Cierra el FileWriter
            escritor.close();

        } catch (IOException e) {
            System.out.println("Ocurrió un error al crear el archivo: " + e.getMessage());
        }
    }
}