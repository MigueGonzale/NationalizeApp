package com.example.testmenu.interfaces;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.testmenu.database.NationalizeTable;

import java.util.List;

/**
 * Create and query table
 */
@Dao
public interface NationalizeDao {
    /**
     * Get all list;
     * @nameTable nationalizeTable
     * @return
     */
    @Query("SELECT * FROM  nationalizeTable")
    List<NationalizeTable> getAllHistory();

    /**
     * Save data information
     * @param nationalizeTable
     * @Class NationalizeTable
     */
    @Insert
    void insertUri(NationalizeTable... nationalizeTable);
}
