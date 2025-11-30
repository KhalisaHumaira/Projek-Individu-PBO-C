public class Penumpang {
    private int id;
    private String nama;
    private int umur;
    private boolean hamil;
    private int saldo;

    // saldo awal = 10.000
    public Penumpang(int id, String nama, int umur, boolean hamil) {
        this.id = id;
        this.nama = nama;
        this.umur = umur;
        this.hamil = hamil;
        this.saldo = 10000;
    }

    public int getID() { return id; }
    public String getNama() { return nama; }
    public int getUmur() { return umur; }
    public boolean getHamil() { return hamil; }
    public int getSaldo() { return saldo; }

    public void tambahSaldo(int nominal) {
        saldo += nominal;
    }

    public void bayarOngkos(int ongkos) {
        saldo -= ongkos;
    }

    public boolean isPrioritas() {
        return umur > 60 || umur < 10 || hamil;
    }

    @Override
    public String toString() {
        return nama + " (ID:" + id + ", Umur:" + umur + (hamil ? ", Hamil" : "") + ", Saldo: Rp " + saldo + ")";
    }
}
