package com.example.diary.domain.service;

import com.example.diary.domain.model.Diary;
import com.example.diary.domain.model.Student;
import com.example.diary.domain.model.StudentDiaryForm;
import com.example.diary.domain.repository.DiaryDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class DiaryService {

    @Autowired
    DiaryDao diaryDao;

    public List<Diary> fetchSortDiaryList(String sortOptionCol, String sortOptionOrder, String fromWhere) {
        return diaryDao.fetchSortDiaryList(sortOptionCol, sortOptionOrder, fromWhere);
    }

    public List<Diary> fetchSearchDiaryList(String searchWord, String fromWhere) {
        return diaryDao.fetchSearchDiaryList(searchWord, fromWhere);
    }

    public int insertDiary(Diary diary) {
        return diaryDao.insertDiary(diary);
    }

    public int updateDiary(Diary diary) {
        return diaryDao.updateDiary(diary);
    }

    public int deleteDiary(Diary diary) {
        return diaryDao.deleteDiary(diary);
    }

    public Map<String, String> createSelectBoxOptionCol(String[] key, String[] value) {
        Map<String, String> selectColMap = new LinkedHashMap<>();

        for (int i = 0; i < key.length; i++) {
            selectColMap.put(key[i], value[i]);
        }

        return selectColMap;
    }

    public Map<String, String> createSelectBoxOptionOrder() {
        Map<String, String> selectOrderMap = new LinkedHashMap<>();
        selectOrderMap.put("asc", "昇順");
        selectOrderMap.put("desc", "降順");

        return selectOrderMap;
    }

    public Diary setDiaryClass(StudentDiaryForm studentDiaryForm, HttpSession session){
        Diary diary = new Diary();
        diary.setClassCode(((Student) session.getAttribute("student")).getClassCode());
        diary.setInsertDate(studentDiaryForm.getInsertDate());
        diary.setStudentId(((Student) session.getAttribute("student")).getStudentId());
        diary.setGoodPoint(studentDiaryForm.getGoodPoint());
        diary.setBadPoint(studentDiaryForm.getBadPoint());
        diary.setStudentComment(studentDiaryForm.getStudentComment());

        return diary;
    }
}
