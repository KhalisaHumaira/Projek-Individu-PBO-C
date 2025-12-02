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
