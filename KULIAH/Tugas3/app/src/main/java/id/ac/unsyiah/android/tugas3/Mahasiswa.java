package id.ac.unsyiah.android.tugas3;

public class Mahasiswa {
    String nama;
    String jurusan;
    int no;
    long npm;
    byte[] image;

    public Mahasiswa(){}

    public Mahasiswa(String nama, String jurusan, int no, long npm,  byte[] image) {
        this.nama = nama;
        this.jurusan = jurusan;
        this.no = no;
        this.npm = npm;
        this.image = image;
    }

    public String getNama() {
        return nama;
    }

    public String getJurusan() {
        return jurusan;
    }

    public int getNo() {
        return no;
    }

    public long getNpm() {
        return npm;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public void setJurusan(String jurusan) {
        this.jurusan = jurusan;
    }

    public void setNo(int nomor) {
        this.no = nomor;
    }

    public void setNpm(long npm) {
        this.npm = npm;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}
