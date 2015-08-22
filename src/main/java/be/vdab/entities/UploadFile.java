package be.vdab.entities;

import java.io.Serializable;
import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "files_upload")
@XmlRootElement
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
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "artikelid")
	private Artikel artikel;

	public UploadFile() {

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
		if (this.artikel != null
				&& this.artikel.getUploadFiles().contains(this)) {
			this.artikel.removeUploadFile(this);
		}
		this.artikel = artikel;
		if (artikel != null && !artikel.getUploadFiles().contains(this)) {
			artikel.addUploadFile(this);
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((artikel == null) ? 0 : artikel.hashCode());
		result = prime * result + Arrays.hashCode(data);
		result = prime * result
				+ ((fileName == null) ? 0 : fileName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UploadFile other = (UploadFile) obj;
		if (artikel == null) {
			if (other.artikel != null)
				return false;
		} else if (!artikel.equals(other.artikel))
			return false;
		if (!Arrays.equals(data, other.data))
			return false;
		if (fileName == null) {
			if (other.fileName != null)
				return false;
		} else if (!fileName.equals(other.fileName))
			return false;
		return true;
	}

}
