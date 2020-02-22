package org.example.demo.jpa.repository.base;

import java.util.List;

public interface BaseRepository<T> {
    <T> T one();
}
