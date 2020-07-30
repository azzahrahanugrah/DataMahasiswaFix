package android.jplas.datamahasiswa.Model;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

public class Mahasiswa {

    private String uid;
    private String NIM;
    private String Nama;
    private String Alamat;

    public Mahasiswa(){
    }

    public Mahasiswa(String uid, String NIM, String nama, String alamat) {
        this.uid = uid;
        this.NIM = NIM;
        Nama = nama;
        Alamat = alamat;
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("uid", uid);
        result.put("NIM", NIM);
        result.put("Nama", Nama);
        result.put("Alamat", Alamat);
        return result;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getNIM() {
        return NIM;
    }

    public void setNIM(String NIM) {
        this.NIM = NIM;
    }

    public String getNama() {
        return Nama;
    }

    public void setNama(String nama) {
        Nama = nama;
    }

    public String getAlamat() {
        return Alamat;
    }

    public void setAlamat(String alamat) {
        Alamat = alamat;
    }
}
