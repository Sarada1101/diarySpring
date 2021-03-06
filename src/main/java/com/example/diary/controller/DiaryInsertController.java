package com.example.diary.controller;

import com.example.diary.domain.model.Diary;
import com.example.diary.domain.model.GroupOrder;
import com.example.diary.domain.model.DiaryForm;
import com.example.diary.domain.service.DiaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
public class DiaryInsertController {

    @Autowired
    DiaryService diaryService;

    @GetMapping("/diaryInsertInput")
    public String getDiaryInsertInput(@ModelAttribute DiaryForm diaryForm, Model model, HttpSession session) {
        if (!diaryService.checkLogin("student")) return "sessionError";
        diaryService.addContentsAndTitle(model, "student", "diaryInsertInput", "日誌登録入力");

        diaryForm.setInsertDate((String) session.getAttribute("today"));

        return "student/main";
    }

    @PostMapping("/diaryInsertCheck")
    public String postDiaryInsertCheck(@ModelAttribute @Validated(GroupOrder.class) DiaryForm diaryForm, BindingResult bindingResult, Model model, HttpSession session) {
        if (!diaryService.checkLogin("student")) return "sessionError";
        if (bindingResult.hasErrors()) {
            return getDiaryInsertInput(diaryForm, model, session);
        }
        diaryService.addContentsAndTitle(model, "student", "diaryInsertCheck", "日誌登録確認");

        session.setAttribute("diary", diaryService.setDiaryClass(diaryForm, session));

        return "student/main";
    }

    @PostMapping("/diaryInsertComplete")
    public String postDiaryInsertComplete(HttpSession session, Model model) {
        if (!diaryService.checkLogin("student")) return "sessionError";
        diaryService.addContentsAndTitle(model, "student", "diaryInsertComplete", "日誌登録完了");

        diaryService.insertDiary((Diary) session.getAttribute("diary"));

        return "student/main";
    }
}
