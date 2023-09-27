/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.notimeforwaste.controller;

import com.google.common.io.Files;
import com.notimeforwaste.dto.ClienteDTO;
import com.notimeforwaste.dto.FotoDTO;
import com.notimeforwaste.model.Cliente;
import com.notimeforwaste.model.Employee;
import com.notimeforwaste.model.Foto;
import com.notimeforwaste.service.FotoService;
import jakarta.validation.Path;
import jakarta.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import lombok.Value;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Arthur
 */
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/notimeforwaste/foto")
public class FotoController {

    private static final String uploadDirectory = "C:/fotos";
    private final FotoService fotoService;

    public FotoController(FotoService fotoService) {
        this.fotoService = fotoService;
    }

    @RequestMapping(path = "", method = POST, consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity<Object> saveEmployee(@RequestPart MultipartFile document) {
        try {

            // Gere um nome de arquivo único com UUID
            java.util.UUID uuid = java.util.UUID.randomUUID();
            String fileName = uuid.toString();

            // Nome do arquivo
            // String fileName = new Date().toString();
            String originalFileName = document.getOriginalFilename();
            String fileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));

            // Construa o caminho completo para o arquivo
            java.nio.file.Path filePath = Paths.get(uploadDirectory, fileName);

            // Salve o arquivo no diretório
            File file = new File(uploadDirectory, fileName + fileExtension);
            document.transferTo(file);

            // Salve apenas o caminho no banco de dados...
            String imageUrl = uploadDirectory + "/" + fileName + fileExtension;
            Foto foto = new Foto();
            foto.setFotoUrl(imageUrl); // Salvar o URL no objeto Foto
            return ResponseEntity.status(HttpStatus.OK).body(fotoService.save(foto));
        } catch (IOException e) {
            // Tratar exceção de leitura de arquivo
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

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateFoto(@PathVariable(value = "id") int id,
            @RequestBody @Valid FotoDTO fotoDTO) {
        Foto fotoOptional = fotoService.findById(id);
        if (fotoOptional == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Foto não encontrada.");
        }
        var foto = new Foto();
        BeanUtils.copyProperties(fotoDTO, foto);
        foto.setIdFoto(fotoOptional.getIdFoto());
        int ret = fotoService.update(foto);
        return ret > 0 ? ResponseEntity.status(HttpStatus.OK).body(foto)
                : ResponseEntity.status(HttpStatus.CONFLICT).body("Erro ao alterar.");
    }
}
