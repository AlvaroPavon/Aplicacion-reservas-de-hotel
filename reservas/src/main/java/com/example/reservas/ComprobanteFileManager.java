package com.example.reservas;
/*Realizado por Alvaro Pavon Martinez */
import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ComprobanteFileManager {
    private static final String DIRECTORY_PATH = "comprobantes/";

    public static void guardarComprobante(Comprobante comprobante) throws IOException {
        File directory = new File(DIRECTORY_PATH);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(DIRECTORY_PATH + "comprobante_" + comprobante.getIdreserva() + ".dat"))) {
            oos.writeObject(comprobante);
        }
    }

    public static List<Comprobante> cargarComprobantes() throws IOException, ClassNotFoundException {
        List<Comprobante> comprobantes = new ArrayList<>();
        File directory = new File(DIRECTORY_PATH);

        if (directory.exists() && directory.isDirectory()) {
            File[] files = directory.listFiles((dir, name) -> name.endsWith(".dat"));
            if (files != null) {
                for (File file : files) {
                    try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                        Comprobante comprobante = (Comprobante) ois.readObject();
                        comprobantes.add(comprobante);
                    }
                }
            }
        }

        return comprobantes;
    }
}
