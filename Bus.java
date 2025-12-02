public class Bus {

    private Penumpang[] penumpangPrioritas = new Penumpang[4];
    private Penumpang[] penumpangBiasa = new Penumpang[16];
    private Penumpang[] penumpangBerdiri = new Penumpang[20];

    public static final int ONGKOS = 2000;
    private int pendapatan = 0;

    public int getPendapatan() {
        return pendapatan;
    }

    private boolean isDuplicate(Penumpang p) {

        for (Penumpang x : penumpangPrioritas)
            if (x != null && (x.getNama().equalsIgnoreCase(p.getNama()) || x.getID() == p.getID()))
                return true;

        for (Penumpang x : penumpangBiasa)
            if (x != null && (x.getNama().equalsIgnoreCase(p.getNama()) || x.getID() == p.getID()))
                return true;

        for (Penumpang x : penumpangBerdiri)
            if (x != null && (x.getNama().equalsIgnoreCase(p.getNama()) || x.getID() == p.getID()))
                return true;

        return false;
    }

  public int totalPenumpang() {
        return getJumlah(penumpangPrioritas) +
               getJumlah(penumpangBiasa) +
               getJumlah(penumpangBerdiri);
    }

    public int getJumlah(Penumpang[] arr) {
        int count = 0;
        for (Penumpang p : arr) if (p != null) count++;
        return count;
    }

    public boolean naikPenumpang(Penumpang p) {

        if (isDuplicate(p)) {
            System.out.println("Penumpang dengan nama atau ID sudah ada!");
            return false;
        }

        if (p.getSaldo() < ONGKOS) {
            System.out.println("Saldo tidak cukup.");
            return false;
        }

        if (totalPenumpang() >= 40) {
            System.out.println("❌ Bus penuh! Tidak bisa naik.");
            return false;
        }

        // --- PRIORITAS ---
        if (p.isPrioritas()) {
            for (int i = 0; i < penumpangPrioritas.length; i++) {
                if (penumpangPrioritas[i] == null) {
                    penumpangPrioritas[i] = p;
                    p.kurangiSaldo(ONGKOS);
                    pendapatan += ONGKOS;
                    return true;
                }
            }
            System.out.println("Kursi prioritas penuh → dipindahkan ke kursi biasa.");
        }

        // --- KURSI BIASA ---
        for (int i = 0; i < penumpangBiasa.length; i++) {
            if (penumpangBiasa[i] == null) {
                penumpangBiasa[i] = p;
                p.kurangiSaldo(ONGKOS);
                pendapatan += ONGKOS;
                return true;
            }
        }

        // --- BERDIRI ---
        System.out.println("Kursi biasa penuh → penumpang berdiri.");

        for (int i = 0; i < penumpangBerdiri.length; i++) {
            if (penumpangBerdiri[i] == null) {
                penumpangBerdiri[i] = p;
                p.kurangiSaldo(ONGKOS);
                pendapatan += ONGKOS;
                return true;
            }
        }

        System.out.println("❌ Area berdiri juga penuh!");
        return false;
    }
 public boolean turunkanPenumpang(String nama) {

        for (int i = 0; i < penumpangPrioritas.length; i++)
            if (penumpangPrioritas[i] != null && penumpangPrioritas[i].getNama().equalsIgnoreCase(nama)) {
                penumpangPrioritas[i] = null;
                return true;
            }

        for (int i = 0; i < penumpangBiasa.length; i++)
            if (penumpangBiasa[i] != null && penumpangBiasa[i].getNama().equalsIgnoreCase(nama)) {
                penumpangBiasa[i] = null;
                return true;
            }

        for (int i = 0; i < penumpangBerdiri.length; i++)
            if (penumpangBerdiri[i] != null && penumpangBerdiri[i].getNama().equalsIgnoreCase(nama)) {
                penumpangBerdiri[i] = null;
                return true;
            }

        return false;
    }

    public boolean topUpSaldo(String nama, int jumlah) {

        for (Penumpang p : penumpangPrioritas)
            if (p != null && p.getNama().equalsIgnoreCase(nama)) { p.tambahSaldo(jumlah); return true; }

        for (Penumpang p : penumpangBiasa)
            if (p != null && p.getNama().equalsIgnoreCase(nama)) { p.tambahSaldo(jumlah); return true; }

        for (Penumpang p : penumpangBerdiri)
            if (p != null && p.getNama().equalsIgnoreCase(nama)) { p.tambahSaldo(jumlah); return true; }

        return false;
    }

    @Override
    public String toString() {
        return "\n===== DATA BUS =====\n" +
                "Prioritas : " + format(penumpangPrioritas) +
                "\nBiasa     : " + format(penumpangBiasa) +
                "\nBerdiri   : " + format(penumpangBerdiri) +
                "\nTotal Penumpang : " + totalPenumpang() +
                "\nPendapatan Bus  : Rp " + pendapatan +
                "\n====================\n";
    }

    private String format(Penumpang[] arr) {
        StringBuilder out = new StringBuilder();
        boolean kosong = true;

        for (Penumpang p : arr) {
            if (p != null) {
                out.append(p.getNama()).append(" (Rp ").append(p.getSaldo()).append(") | ");
                kosong = false;
            }
        }
        return kosong ? "<kosong>" : out.toString();
    }
}
