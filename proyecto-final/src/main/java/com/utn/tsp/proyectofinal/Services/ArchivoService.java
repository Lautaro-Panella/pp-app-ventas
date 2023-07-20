package com.utn.tsp.proyectofinal.Services;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class ArchivoService {

    private final Path facturasPath = Paths.get(".//src//main//resources//facturas//");
    private final Path reportesPath = Paths.get(".//src//main//resources//reportes//");
    private final Path imagesPath = Paths.get(".//src//main//resources//images//");
    private final Path otrosPath = Paths.get(".//src//main//resources//otros//");

    /**
     *
     * @param file
     * @param fileType
     */
    public void save(MultipartFile file, String fileType) {
        try {
            switch (fileType) {
                case "PDF":
                    Files.write(this.facturasPath.resolve(file.getOriginalFilename()), file.getBytes());
                    break;
                case "XLS":
                    Files.write(this.reportesPath.resolve(file.getOriginalFilename()), file.getBytes());
                    break;
                case "IMG":
                    Files.write(this.imagesPath.resolve(file.getOriginalFilename()), file.getBytes());
                    break;
                default:
                    Files.write(this.otrosPath.resolve(file.getOriginalFilename()), file.getBytes());
            }
        } catch (Exception e) {
            throw new RuntimeException("No se pudo guardar el archivo. Error: " + e.getMessage());
        }
    }

    /**
     *
     * @param filename
     * @param fileType
     * @return
     */
    public Resource load(String filename, String fileType) {
        try {
            Path file;
            switch (fileType) {
                case "PDF":
                    file = facturasPath.resolve(filename);
                    break;
                case "XLS":
                    file = reportesPath.resolve(filename);
                    break;
                case "IMG":
                    file = imagesPath.resolve(filename);
                    break;
                default:
                    file = otrosPath.resolve(filename);
            }
            Resource resource = new UrlResource(file.toUri());

            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new RuntimeException("No se pudo leer el archivo.");
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("Error: " + e.getMessage());
        }
    }

}
