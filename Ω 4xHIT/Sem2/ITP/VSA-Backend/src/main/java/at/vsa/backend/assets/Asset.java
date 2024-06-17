package at.vsa.backend.assets;

import lombok.Getter;

@Getter
public class Asset {

    private String name;
    private String contact;
    private Long size;

    public Asset(String name, String contact, Long size) {
        this.name = name;
        this.contact = contact;
        this.size = size;
    }

    public String toString() {
        return name + ", " + contact + ", " + size;
    }
}
