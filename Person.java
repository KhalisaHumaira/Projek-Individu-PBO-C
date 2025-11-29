/**
 * Person (abstract) — blueprint untuk entitas yang punya id, nama, umur.
 */
public abstract class Person {
    protected int id;
    protected String nama;
    protected int umur;

    public Person(int id, String nama, int umur) {
        this.id = id;
        this.nama = nama;
        this.umur = umur;
    }
  
