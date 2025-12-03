import java.util.Scanner;

public class TestBus {

    public static void main(String[] args) {
        
        Scanner input = new Scanner(System.in);
        Bus bus = new Bus();

        while (true) {

            System.out.println("\n===== BUS TRANS KOETARADJA =====");
            System.out.println("Pendapatan: Rp " + bus.getPendapatan());
            System.out.println("1. Naikkan Penumpang");
            System.out.println("2. Turunkan Penumpang");
            System.out.println("3. Lihat Data Bus");
            System.out.println("4. Top Up Saldo");
            System.out.println("5. Keluar");
            System.out.print("Pilih menu: ");

            int pilih = getInt(input);

            if (pilih == 1) {
                System.out.print("ID: ");
                int id = getInt(input);

                input.nextLine();
                System.out.print("Nama: ");
                String nama = input.nextLine();

                System.out.print("Umur: ");
                int umur = getInt(input);

                String hamil;
                do {
                    System.out.print("Hamil (y/n): ");
                    hamil = input.next().toLowerCase();
                } while (!hamil.equals("y") && !hamil.equals("n"));

                boolean statusHamil = hamil.equals("y");

                Penumpang p = new Penumpang(id, nama, umur, statusHamil);

                if (bus.naikPenumpang(p))
                    System.out.println("Penumpang berhasil naik!");
                else
                    System.out.println("❌ Gagal naik!");

            } else if (pilih == 2) {

                input.nextLine();
                System.out.print("Nama penumpang: ");
                String nama = input.nextLine();

                if (bus.turunkanPenumpang(nama))
                    System.out.println("Penumpang turun.");
                else
                    System.out.println("❌ Nama tidak ditemukan.");

            } else if (pilih == 3) {

                System.out.println(bus);

            } else if (pilih == 4) {

                input.nextLine();
                System.out.print("Nama penumpang: ");
                String nama = input.nextLine();

                System.out.print("Nominal topup: ");
                int nominal = getInt(input);

                if (bus.topUpSaldo(nama, nominal))
                    System.out.println("Topup berhasil.");
                else
                    System.out.println("❌ Nama tidak ditemukan.");

            } else if (pilih == 5) {
                System.out.println("Terima kasih!");
                break;
            }
        }
        input.close();
    }

    private static int getInt(Scanner sc) {
        while (!sc.hasNextInt()) {
            System.out.println("Masukkan angka!");
            sc.next();
        }
        return sc.nextInt();
    }
}

