package be.ehb.eindproject.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Toestellen {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String category;
    private String uitleg;
    @Column(columnDefinition = "text")
    private String image;

    public Toestellen() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUitleg() {
        return uitleg;
    }

    public void setUitleg(String uitleg) {
        this.uitleg = uitleg;
    }


    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
