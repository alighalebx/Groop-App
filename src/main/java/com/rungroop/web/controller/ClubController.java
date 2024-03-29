package com.rungroop.web.controller;


import com.rungroop.web.dto.ClubDto;
import com.rungroop.web.models.Club;
import com.rungroop.web.service.ClubService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class ClubController {
    private ClubService clubService;

    public ClubController(ClubService clubService) {
        this.clubService = clubService;
    }
    @GetMapping("/clubs")
    public String listClubs(Model model ){
        List<ClubDto> clubs = clubService.findAllClubs();
        model.addAttribute("clubs",clubs);
        return "clubs-list";
    }

    @GetMapping("/clubs/new")
    public String createClubForm(Model model ){
        Club club = new Club();
        model.addAttribute("club", club);
        return "clubs-create";
    }

    @PostMapping("/clubs/new")
    public String saveClub(@ModelAttribute("club") Club club) {

        clubService.saveClub(club);
        return "redirect:/clubs";
    }

    @GetMapping("/clubs/{clubId}/edit")
    public String editClubForm(@PathVariable("clubId") Long clubId, Model model) {
        ClubDto club = clubService.findClubById(clubId);
        model.addAttribute("club", club);
        return "clubs-edit";
    }
    @PostMapping("/clubs/{clubId}/edit")
    public String updateClub(@PathVariable("clubId") long clubId,@ModelAttribute("club") ClubDto club, Model model) {

//            model.addAttribute("club", club);
//            return "clubs-edit";

         club.setId(clubId);
         clubService.updateClub(club);
         return "redirect:/clubs";
    }
}
