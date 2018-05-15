package lokalspots.global.repositories;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import lokalspots.global.models.FileUpload;

public interface FileUploadRepository extends JpaRepository<FileUpload, Long> {
//  FileUpload findById(Long Id);
//  List<FileUpload> findByTblColumnRefUniqueID(Long tblColumnRefUniqueID);
//	@Query("SELECT f FROM FileUpload f WHERE f.tblColumnRefUniqueID = :tblColumnRefUniqueID")
//	List<FileUpload> findByTblColumnRefUniqueID(@Param("tblColumnRefUniqueID") Long tblColumnRefUniqueID);
	
//	@Transactional
//	@Modifying(clearAutomatically = true)
//	@Query("UPDATE FileUpload f SET f.isMainImage=:isMainImage WHERE f.tblColumnRefUniqueID=:tblColumnRefUniqueID "
//			+ "and f.tblName = :tblName and f.Id = :Id")
//	void setMainImage(@Param("isMainImage") boolean isMainImage, @Param("tblColumnRefUniqueID") Long tblColumnRefUniqueID, 
//			@Param("tblName") String tblName, @Param("Id") Long Id);
}
