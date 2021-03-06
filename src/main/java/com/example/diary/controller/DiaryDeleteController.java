package com.example.diary.controller;

import com.example.diary.domain.model.DiaryForm;
import com.example.diary.domain.service.DiaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
public class DiaryDeleteController {

    @Autowired
    DiaryService diaryService;

    @PostMapping("/diaryDeleteCheck")
    public String postDiaryDeleteCheck(@ModelAttribute DiaryForm diaryForm, Model model) {
        if (!diaryService.checkLogin("student")) return "sessionError";
        diaryService.addContentsAndTitle(model, "student", "diaryDeleteCheck", "日誌削除確認");

        return "student/main";
    }

    @PostMapping("/diaryDeleteComplete")
    public String postDiaryDeleteComplete(@ModelAttribute DiaryForm diaryForm, HttpSession session, Model model) {
        if (!diaryService.checkLogin("student")) return "sessionError";
        diaryService.addContentsAndTitle(model, "student", "diaryDeleteComplete", "日誌削除完了");

        int row = diaryService.deleteDiary(diaryService.setDiaryClass(diaryForm, session));
        System.out.println(row);

        return "student/main";
    }
}
