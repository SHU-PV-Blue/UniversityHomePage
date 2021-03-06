package com.wolfogre.uhp.domain;

import org.hibernate.annotations.Type;

import javax.persistence.*;

/**
 * Created by wolfogre on 16-7-25.
 */
@Entity
@Table(name = "homepage", schema = "universityhomepage", catalog = "")
public class HomePageEntity {
    private int id;
    private String name;
    private String url;
    private String imagePath;
    private String mobileImagePath;
    private Boolean ifLayoutImage;
    private String content;

    @Id
    @Column(name = "Id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "Name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "Url")
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Basic
    @Column(name = "ImagePath")
    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    @Basic
    @Column(name = "MobileImagePath")
    public String getMobileImagePath() {
        return mobileImagePath;
    }

    public void setMobileImagePath(String mobileImagePath) {
        this.mobileImagePath = mobileImagePath;
    }

    @Basic
    @Column(name = "IfLayoutImage")
    @Type(type = "org.hibernate.type.TrueFalseType")
    public Boolean getIfLayoutImage() {
        return ifLayoutImage;
    }

    public void setIfLayoutImage(Boolean ifLayoutImage) {
        this.ifLayoutImage = ifLayoutImage;
    }

    @Basic
    @Column(name = "Content")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        HomePageEntity that = (HomePageEntity) o;

        if (id != that.id) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (url != null ? !url.equals(that.url) : that.url != null) return false;
        if (imagePath != null ? !imagePath.equals(that.imagePath) : that.imagePath != null) return false;
        if (mobileImagePath != null ? !mobileImagePath.equals(that.mobileImagePath) : that.mobileImagePath != null)
            return false;
        if (ifLayoutImage != null ? !ifLayoutImage.equals(that.ifLayoutImage) : that.ifLayoutImage != null)
            return false;
        if (content != null ? !content.equals(that.content) : that.content != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (url != null ? url.hashCode() : 0);
        result = 31 * result + (imagePath != null ? imagePath.hashCode() : 0);
        result = 31 * result + (mobileImagePath != null ? mobileImagePath.hashCode() : 0);
        result = 31 * result + (ifLayoutImage != null ? ifLayoutImage.hashCode() : 0);
        result = 31 * result + (content != null ? content.hashCode() : 0);
        return result;
    }
}
