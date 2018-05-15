package lokalspots.global.services;
import java.util.List;
 
import org.springframework.stereotype.Service;

import lokalspots.global.models.FileUpload;

@Service
public interface FileUploadService {

    public FileUpload findById(Long Id);  

    
    public  List<FileUpload> findByTblColumnRefUniqueID(Long tblColumnRefUniqueID);
    
    //UPLOAD PHOTO INTO DATABASE
    public void uploadFile(FileUpload doc); 
    
    //DELETE PHOTO 
    void deleteById(Long id);
}
