package lokalspots.global.models;

import javax.persistence.*; 
import java.util.Date;
@Entity
@Table(name = "l0kalsp0ts_FileUpload")
public class FileUpload {
	 public FileUpload(String filename, byte[] file, String mimeType, String tblName, String tblColumnRef, Long tblColumnRefUniqueID) {

         this.file = file;
         this.filename = filename;
         this.mimeType = mimeType; 
         this.tblName = tblName; 
         this.tblColumnRef = tblColumnRef;
         this.tblColumnRefUniqueID = tblColumnRefUniqueID; 
         
     }

     public FileUpload() {
         // Default Constructor
     }
    
     @Id
     @GeneratedValue(strategy = GenerationType.AUTO)
     private Long Id;
 
     private String filename;

     @Lob
     private byte[] file;

     private String mimeType;

     private String tblName; 
     
     private String tblColumnRef;
     
     private Long tblColumnRefUniqueID; 
    

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public String getTblName() {
		return tblName;
	}

	public void setTblName(String tblName) {
		this.tblName = tblName;
	}

	public String getTblColumnRef() {
		return tblColumnRef;
	}

	public void setTblColumnRef(String tblColumnRef) {
		this.tblColumnRef = tblColumnRef;
	}

	public Long getTblColumnRefUniqueID() {
		return tblColumnRefUniqueID;
	}

	public void setTblColumnRefUniqueID(Long tblColumnRefUniqueID) {
		this.tblColumnRefUniqueID = tblColumnRefUniqueID;
	}

	public String getFilename() {
         return filename;
     }

     public void setFilename(String filename) {
         this.filename = filename;
     }

     public byte[] getFile() {
         return file;
     }

     public void setFile(byte[] file) {
         this.file = file;
     }

     public String getMimeType() {
         return mimeType;
     }

     public void setMimeType(String mimeType) {
         this.mimeType = mimeType;
     } 
}
