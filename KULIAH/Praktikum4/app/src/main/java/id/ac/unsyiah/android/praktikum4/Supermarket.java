package id.ac.unsyiah.android.praktikum4;

public class Supermarket {
    String kodeBarang;
    String namaBarang;
    int jumlah;

    public Supermarket(){
    }

    public Supermarket(String kodeBarang, String namaBarang, int jumlah){
        this.kodeBarang = kodeBarang;
        this.namaBarang = namaBarang;
        this.jumlah= jumlah;
    }

    //Getter Methode
    public String getKodeBarang() {
        return kodeBarang;
    }

    public String getNamaBarang() {
        return namaBarang;
    }

    public int getJumlah() {
        return jumlah;
    }

    // Setter Methode
    public void setKodeBarang(String kodeBarang) {
        this.kodeBarang = kodeBarang;
    }

    public void setNamaBarang(String namaBarang) {
        this.namaBarang = namaBarang;
    }

    public void setJumlah(int jumlah) {
        this.jumlah = jumlah;
    }
}
