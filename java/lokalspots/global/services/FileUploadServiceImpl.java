package lokalspots.global.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import lokalspots.global.models.FileUpload;
import lokalspots.global.repositories.FileUploadRepository;

@Service
@Primary
public class FileUploadServiceImpl implements FileUploadService {

	@Autowired
    FileUploadRepository fileUploadRepository;
	
	// Upload the file
	@Override
    public void uploadFile(FileUpload doc) {
        fileUploadRepository.saveAndFlush(doc);
    }
       
    @Override
    public FileUpload findById(Long Id) {
	 return fileUploadRepository.findOne(Id);
	}
    
    @Override
	public List<FileUpload> findByTblColumnRefUniqueID(Long tblColumnRefUniqueID) {
    	return this.fileUploadRepository.findAll();
	}
    
    @Override
	public void deleteById(Long id) {
		this.fileUploadRepository.delete(id);
	}
}
