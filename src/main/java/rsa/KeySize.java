package rsa;

public enum KeySize {
    SMALL(1024),
    DEFAULT(2048),
    PARANOID(4096);

    private final int bitSize;

    KeySize(int bitSize) {
        this.bitSize = bitSize;
    }

    int size() {
        return bitSize;
    }
}
