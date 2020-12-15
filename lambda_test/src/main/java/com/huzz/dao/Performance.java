package com.huzz.dao;

import com.huzz.model.Artist;

import java.util.stream.Stream;

import static java.util.stream.Stream.concat;

public interface Performance {
    public String getName();

    public Stream<Artist> getMusicians();

    public default Stream<Artist> getAllMusicians() {
        return getMusicians()
                .flatMap(artist -> concat(Stream.of(artist), artist.getMembers().stream()));
    }


}
