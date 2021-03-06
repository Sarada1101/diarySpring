package com.example.diary.domain.repository;

import com.example.diary.domain.model.Diary;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface DiaryDao {
    public List<Diary> fetchSortDiaryList(String sortOptionCol, String sortOptionOrder, String fromWhere) throws DataAccessException;

    public List<Diary> fetchSearchDiaryList(String searchWord, String fromWhere) throws DataAccessException;

    public boolean hasDiaryInsertedToday(String classCode, String today)throws DataAccessException;

    public int insertDiary(Diary diary) throws DataAccessException;

    public int updateDiary(Diary diary) throws DataAccessException;

    public int deleteDiary(Diary diary) throws DataAccessException;
}
