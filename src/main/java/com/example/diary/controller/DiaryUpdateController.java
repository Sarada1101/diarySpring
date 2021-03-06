package com.example.diary.controller;

import com.example.diary.domain.model.Diary;
import com.example.diary.domain.model.DiaryForm;
import com.example.diary.domain.model.GroupOrder;
import com.example.diary.domain.service.DiaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
public class DiaryUpdateController {

    @Autowired
    DiaryService diaryService;

    @PostMapping("/diaryUpdateInput")
    public String postDiaryUpdateInput(@ModelAttribute DiaryForm diaryForm, Model model, HttpSession session) {
        if (!diaryService.checkLogin("student")) return "sessionError";
        diaryService.addContentsAndTitle(model, "student", "diaryUpdateInput", "日誌修正入力");

        return "student/main";
    }

    @PostMapping("/diaryUpdateCheck")
    public String postDiaryUpdateCheck(@ModelAttribute @Validated(GroupOrder.class) DiaryForm diaryForm, BindingResult bindingResult, Model model, HttpSession session) {
        if (!diaryService.checkLogin("student")) return "sessionError";
        if (bindingResult.hasErrors()) {
            return postDiaryUpdateInput(diaryForm, model, session);
        }
        diaryService.addContentsAndTitle(model, "student", "diaryUpdateCheck", "日誌修正確認");

        session.setAttribute("diary", diaryService.setDiaryClass(diaryForm, session));

        return "student/main";
    }

    @PostMapping("/diaryUpdateComplete")
    public String postDiaryUpdateComplete(HttpSession session, Model model) {
        if (!diaryService.checkLogin("student")) return "sessionError";
        diaryService.addContentsAndTitle(model, "student", "diaryUpdateComplete", "日誌修正完了");

        int row = diaryService.updateDiary((Diary) session.getAttribute("diary"));
        System.out.println(row);

        return "student/main";
    }
}
