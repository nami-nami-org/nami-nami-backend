package com.nami.demo.integration.cloudinary;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.nami.demo.integration.cloudinary.dto.response.FileUploaderResponseDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@Service
public class FileUploaderService {

    private final Cloudinary cloudinary;
    private static final List<String> ALLOWED_EXTENSIONS = List.of("jpg", "jpeg", "png", "webp", "avif");

    public FileUploaderService(Cloudinary cloudinary) {
        this.cloudinary = cloudinary;
    }

    /**
     * Sube un archivo a Cloudinary con soporte para carpeta personalizada.
     *
     * @param file archivo recibido (Multipart)
     * @param folderId carpeta donde se guardará el archivo
     * @return información del archivo subido
     */
    @SuppressWarnings("unchecked")
    public FileUploaderResponseDto uploadFile(MultipartFile file, String folderId) {
        try {
            if (file == null || file.isEmpty()) {
                throw new RuntimeException("File cannot be empty.");
            }

            String originalName = file.getOriginalFilename();
            if (originalName == null || !originalName.contains(".")) {
                throw new RuntimeException("Invalid file name or missing extension.");
            }

            String extension = originalName.substring(originalName.lastIndexOf('.') + 1).toLowerCase();
            if (!ALLOWED_EXTENSIONS.contains(extension)) {
                throw new RuntimeException(
                        String.format("File extension '%s' not allowed. Allowed extensions: %s", extension, ALLOWED_EXTENSIONS)
                );
            }

            Map<String, Object> uploadResult = cloudinary.uploader().upload(
                    file.getBytes(),
                    ObjectUtils.asMap(
                            "folder", (folderId != null && !folderId.isBlank()) ? folderId : "default"
                    )
            );

            System.out.println("--------------------------------------------------");
            System.out.println("📤 Cloudinary upload result:");
            uploadResult.forEach((key, value) -> System.out.println(key + ": " + value));
            System.out.println("--------------------------------------------------");

            String fileUrl = (String) uploadResult.get("secure_url");
            String publicId = (String) uploadResult.get("public_id");

            return new FileUploaderResponseDto(publicId, fileUrl, extension);

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(String.format(
                    "Internal error while uploading file: %s", e.getMessage()
            ), e);
        }
    }
}
