package be.vdab.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "files_upload")
public class UploadFile implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	@Column(name = "upload_id")
	private long id;
	@Column(name = "file_name")
	private String fileName;
	@Lob
	@Column(name = "file_data")
	private byte[] data;
	@OneToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "artikelid")
	private Artikel artikel;
	
	public UploadFile(){
		
	}

	public UploadFile(String fileName, byte[] data, Artikel artikel) {
		this.fileName = fileName;
		this.data = data;
		this.artikel = artikel;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}

	public Artikel getArtikel() {
		return artikel;
	}

	public void setArtikel(Artikel artikel) {
		this.artikel = artikel;
	}

}
