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
