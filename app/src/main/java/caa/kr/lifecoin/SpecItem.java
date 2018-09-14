package caa.kr.lifecoin;

public class SpecItem {
    private String name;
    private String date;
    private byte[] hash;

    public SpecItem(String name, String date, byte[] hash) {
        this.name = name;
        this.date = date;
        this.hash = hash;
    }

    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }

    public byte[] getHash() {
        return hash;
    }
}
