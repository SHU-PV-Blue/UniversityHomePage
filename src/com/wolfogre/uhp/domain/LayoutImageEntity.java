package com.wolfogre.uhp.domain;

import javax.persistence.*;
import java.util.Arrays;

/**
 * Created by wolfogre on 16-7-25.
 */
@Entity
@Table(name = "layoutimage", schema = "universityhomepage", catalog = "")
public class LayoutImageEntity {
    private int id;
    private byte[] image;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "image",columnDefinition="mediumblob")
    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LayoutImageEntity that = (LayoutImageEntity) o;

        if (id != that.id) return false;
        if (!Arrays.equals(image, that.image)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + Arrays.hashCode(image);
        return result;
    }
}
