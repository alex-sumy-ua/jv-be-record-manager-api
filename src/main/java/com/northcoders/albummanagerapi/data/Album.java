package com.northcoders.albummanagerapi.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Album {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column
    private String description;

    @Column
    private int released;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "artist_id", nullable = false)
    private Artist artist;

    // Remove check constraint and use integer type for genre
    @Column(columnDefinition = "integer")
    @Enumerated(EnumType.ORDINAL)
    private Genre genre;

    @Column
    private float price;

    @Column
    private int inStock;

    public Album(String title, String description, int released, Artist artist, Genre genre, float price, int inStock) {
        this.title = title;
        this.description = description;
        this.released = released;
        this.artist = artist;
        this.genre = genre;
        this.price = price;
        this.inStock = inStock;
    }

    @JsonProperty("artist_id")
    public Long getArtistId() {
        return artist != null ? artist.getId() : null;
    }

}
