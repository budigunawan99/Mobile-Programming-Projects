package id.ac.unsyiah.android.newactivity;

public class MataKuliah {
    private String kodeMtKul = "INF-308";
    private String namaMtkul = "Pemrograman Berbasis Mobile";
    private String deskripsi = "Perkuliahan ini berorientasi pada proyek, dimana mahasiswa bersama tim diharapkan untuk mampu mengembangkan dan merancang aplikasi berbasis mobile. Platform pemrograman mobile yang digunakan adalah Android dan/atau iOS. Topik pada perkuliahan ini meliputi: user interface design, memory management, input methods, network technique, URL loading, GPS and motion sensing. Luaran yang diharapkan pada perkuliahan ini adalah mahasiswa bersama tim dapat menghasilkan sebuah aplikasi berbasis mobile yang inovatif dan interaktif.";
    private double bobot = 3;

    public String getKodeMtKul() {
        return kodeMtKul;
    }

    public String getNamaMtkul() {
        return namaMtkul;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public double getBobot() {
        return bobot;
    }
}
