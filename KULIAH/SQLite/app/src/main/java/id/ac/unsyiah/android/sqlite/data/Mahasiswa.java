package id.ac.unsyiah.android.sqlite.data;

public class Mahasiswa {

    int _id;
    String _nim;
    String _nama;

    //Empty constructor
    public Mahasiswa(){

    }

    //Constructor
    public Mahasiswa(String nim, String nama){
        this._nim = nim;
        this._nama = nama;
    }

    public int getId() {
        return this._id;
    }

    public void setId(int id) {
        this._id = id;
    }

    public String getNama(){
        return this._nama;
    }

    public void setNama(String nama){
        this._nama = nama;
    }

    public String getNim(){
        return this._nim;
    }

    public void setNim(String nim){
        this._nim = nim;
    }
}