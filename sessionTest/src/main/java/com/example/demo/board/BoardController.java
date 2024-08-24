package com.example.demo.board;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/board")
public class BoardController {
    @Autowired
    private BoardService service;

    @RequestMapping("/list")
    public void list(Model model) {
        model.addAttribute("list", service.getAll());
    }

    @GetMapping("/add")
    public void addForm() {
    }

    @PostMapping("/add")
    public String add(Board b) {
        service.addBoard(b);
        return "redirect:/board/list";
    }

    @ResponseBody
    @GetMapping("/getAjax")
    public Map getAjax(int num) {
        System.out.println(num);
        Board b = service.getBoard(num);
        Map map = new HashMap();
        map.put("num", b.getNum());
        map.put("writer", b.getWriter());
        map.put("wdate", b.getWdate() + "");
        map.put("title", b.getTitle());
        map.put("content", b.getContent());
        return map;
    }

    @GetMapping("/detail")
    public void detail(int num, Model model, HttpServletResponse res) {
        model.addAttribute("b", service.getBoard(num));
        Cookie c1 = new Cookie("num", num + "");
        res.addCookie(c1);
    }

    @PostMapping("/edit")
    public String edit(Board b) {
        service.editBoard(b);
        return "redirect:/board/list";
    }

    @GetMapping("/del")
    public String del(int num) {
        service.delBoard(num);
        return "redirect:/board/list";
    }
    @GetMapping("/todaylist")
    public String todayList(HttpServletRequest req, Model model) {
        // 쿠키에서 저장된 num 값을 가져와서 게시물 리스트에 추가
        List<Board> boardList = new ArrayList<>();
        Cookie[] cookies = req.getCookies();

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("num".equals(cookie.getName())) {
                    try {
                        int boardNum = Integer.parseInt(cookie.getValue());
                        Board board = service.getBoard(boardNum);
                        if (board != null) {
                            boardList.add(board);
                        }
                    } catch (NumberFormatException e) {
                        // 쿠키 값이 정수가 아니거나 오류가 발생한 경우 처리
                        e.printStackTrace();
                    }
                }
            }
        }
        model.addAttribute("boardList", boardList);

        return "board/todaylist";
    }

}







