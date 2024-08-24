package com.example.guestbook.controller;

import com.example.guestbook.model.Guestbook;
import com.example.guestbook.service.GuestbookService;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping("/guestbook")
public class GuestbookController {

    private final GuestbookService guestbookService;

    public GuestbookController(GuestbookService guestbookService) {
        this.guestbookService = guestbookService;
    }

    @GetMapping
    public String listGuestbooks(Model model) {
        try {
            model.addAttribute("guestbooks", guestbookService.getAllGuestbooks());

            return "guestbook/list";
        } catch (DataAccessException e) {
            model.addAttribute("error", "방명록을 불러오는 중 오류가 발생했습니다.");
            return "error";
        }
    }

    @GetMapping("/write")
    public String showWriteForm(Model model) {
        model.addAttribute("guestbook", new Guestbook());
        return "guestbook/writeForm";
    }

    @PostMapping("/write")
    public String writeGuestbook(@ModelAttribute Guestbook guestbook, RedirectAttributes redirectAttributes) {
        try {
            guestbookService.addGuestbook(guestbook);
            redirectAttributes.addFlashAttribute("message", "방명록이 성공적으로 작성되었습니다.");
            return "redirect:/guestbook";
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/guestbook/write";
        } catch (DataAccessException e) {
            redirectAttributes.addFlashAttribute("error", "방명록 작성 중 오류가 발생했습니다.");
            return "redirect:/guestbook/write";
        }
    }

    @GetMapping("/{id}")
    public ModelAndView viewGuestbook(@PathVariable int id) {
        ModelAndView mav = new ModelAndView("guestbook/view");

        try {
            Optional<Guestbook> guestbookOptional = guestbookService.getGuestbook(id);

            if (guestbookOptional.isPresent()) mav.addObject("guestbook", guestbookOptional.get());
            else mav.addObject("error", "해당 방명록을 찾을 수 없습니다.");
        } catch (DataAccessException e) {
            mav.addObject("error", "방명록을 불러오는 중 오류가 발생했습니다.");
        }

        return mav;
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable int id, ModelMap modelMap) {
        try {
            guestbookService.getGuestbook(id).ifPresentOrElse(
                    guestbook -> modelMap.put("guestbook", guestbook),
                    () -> modelMap.put("error", "해당 방명록을 찾을 수 없습니다.")
            );
            return "guestbook/editForm";
        } catch (DataAccessException e) {
            modelMap.put("error", "방명록을 불러오는 중 오류가 발생했습니다.");
            return "error";
        }
    }

    @PostMapping("/{id}/edit")
    public String editGuestbook(@PathVariable int id, @ModelAttribute Guestbook guestbook, RedirectAttributes redirectAttributes) {
        try {
            guestbook.setId(id);
            guestbookService.updateGuestbook(guestbook);
            redirectAttributes.addFlashAttribute("message", "방명록이 성공적으로 수정되었습니다.");
            return "redirect:/guestbook/" + id;
        } catch (IllegalArgumentException | DataAccessException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/guestbook/" + id + "/edit";
        }
    }

    @PostMapping("/{id}/delete")
    public String deleteGuestbook(@PathVariable int id, @RequestParam String password, RedirectAttributes redirectAttributes) {
        try {
            guestbookService.deleteGuestbook(id, password);
            redirectAttributes.addFlashAttribute("message", "방명록이 성공적으로 삭제되었습니다.");
            return "redirect:/guestbook";
        } catch (IllegalArgumentException | DataAccessException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/guestbook/" + id;
        }
    }

}