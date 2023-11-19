/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.notimeforwaste.controller;
import com.notimeforwaste.model.Foto;
import com.notimeforwaste.service.FotoService;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static org.springframework.web.bind.annotation.RequestMethod.POST;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Arthur
 */
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/notimeforwaste/foto")
public class FotoController {

    private static final String uploadDirectory = "C:\\xampp\\htdocs\\img";
    private final FotoService fotoService;

    public FotoController(FotoService fotoService) {
        this.fotoService = fotoService;
    }

    @RequestMapping(path = "", method = POST, consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity<Object> postFoto(@RequestPart MultipartFile document) {
        try {

            java.util.UUID uuid = java.util.UUID.randomUUID();
            String fileName = uuid.toString();

            String originalFileName = document.getOriginalFilename();
            String fileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));

            java.nio.file.Path filePath = Paths.get(uploadDirectory, fileName);

            File file = new File(uploadDirectory, fileName + fileExtension);
            document.transferTo(file);

            String imageUrl = "http://localhost/img/"+ fileName + fileExtension;
            Foto foto = new Foto();
            foto.setFotoUrl(imageUrl);
            return ResponseEntity.status(HttpStatus.OK).body(fotoService.save(foto));
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao processar o arquivo.");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getFotoById(@PathVariable(value = "id") int id) {
        Foto foto = fotoService.findById(id);
        return foto != null ? ResponseEntity.status(HttpStatus.OK).body(foto)
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body("Foto não encontrada.");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteFoto(@PathVariable(value = "id") int id) {
        Foto foto = fotoService.findById(id);
        if (foto == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Foto não encontrada.");
        }
        int ret = fotoService.delete(foto.getIdFoto());
        return ret > 0 ? ResponseEntity.status(HttpStatus.OK).body(foto)
                : ResponseEntity.status(HttpStatus.CONFLICT).body("Erro ao deletar.");
    }

    @PutMapping(path = "/{id}", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity<Object> updateFoto(@PathVariable(value = "id") int id,
            @RequestParam MultipartFile newDocument) {
        try {
            Foto existingFoto = fotoService.findById(id);
            if (existingFoto == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Foto não encontrada.");
            }

            String existingImageUrl = existingFoto.getFotoUrl();

            File existingFile = new File(existingImageUrl);
            if (!existingFile.exists()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Arquivo de foto existente não encontrado.");
            }

            if (!existingFile.delete()) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body("Erro ao excluir o arquivo de foto existente.");
            }

            newDocument.transferTo(existingFile);
            return ResponseEntity.status(HttpStatus.OK).body(existingFoto);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao processar o arquivo.");
        }
    }

}
