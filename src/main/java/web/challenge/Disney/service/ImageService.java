
package web.challenge.Disney.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ImageService {
    
    @Value("${my.property}")
    private String directory;
    
    public String copiar(MultipartFile img) throws IOException{
        
        try{
            String nameImage = img.getOriginalFilename();
        
            Path pathImage = Paths.get(directory, nameImage).toAbsolutePath();

            Files.copy(img.getInputStream(), pathImage, StandardCopyOption.REPLACE_EXISTING);

            return nameImage;
            
        }catch(IOException e){
            throw new IOException("Error al guardar la foto");
        }
        
    }
}
