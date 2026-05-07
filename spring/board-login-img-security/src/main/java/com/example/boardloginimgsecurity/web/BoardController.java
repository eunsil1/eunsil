package com.example.boardloginimgsecurity.web;

import com.example.boardloginimgsecurity.domain.Board;
import com.example.boardloginimgsecurity.domain.User;
import com.example.boardloginimgsecurity.security.SecurityUtils;
import com.example.boardloginimgsecurity.service.BoardService;
import com.example.boardloginimgsecurity.service.UserService;
import com.example.boardloginimgsecurity.web.dto.BoardForm;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/posts")
public class BoardController {
    private final BoardService boardService;
    private final UserService userService;

    public BoardController(BoardService boardService, UserService userService) {
        this.boardService = boardService;
        this.userService = userService;
    }

    @GetMapping
    public String list(Model model){
        model.addAttribute("posts",boardService.findAll());
        return "board/list";
    }

   @GetMapping("/write")
    public String writeForm(Model model){
        model.addAttribute("form", new BoardForm());
        return "board/write";
   }
   @GetMapping("/{id:\\d+}")
   public String detail(@PathVariable Long id,
                        Model model){
        Board board = boardService.findById(id);
        model.addAttribute("post", board);
        model.addAttribute("canEdit", canEdit( board));
         return "board/detail";
   }

    private static boolean canEdit(Board board) {

        String u = SecurityUtils.currentUsername();
        if (u == null) {
            return false;
        }
        return u.equals(board.getAuthor().getUsername());
        //로그인한 사용자와 == 글작성자 -> true  (수정가능)
    }


    @PostMapping("/write")
   public String write(@Valid @ModelAttribute("form") BoardForm form,
                       BindingResult bindingResult,
                       Model model){
        if(bindingResult.hasErrors()){
            return "board/write";
        }
      String username = SecurityUtils.currentUsername();
       User author = userService.findByUsername(username);
       if(author == null){
           return "redirect:/login";
       }
       boardService.create(form.getTitle(),form.getContent(),author,form.getFiles());
       return "redirect:/posts";

   }


   @GetMapping("/{id:\\d+}/edit")
    public String editForm(@PathVariable Long id, Model model){
       Board board = boardService.findById(id);
       if(!canEdit(board)){
           return "redirect:/posts/"+id;
       }
      BoardForm form = new BoardForm();
       form.setTitle(board.getTitle());
       form.setContent(board.getContent());
       model.addAttribute("form", form);
       model.addAttribute("postId", id);
       return "board/edit";

   }

    @PostMapping("/{id:\\d+}/edit")
    public String edit(@PathVariable Long id,
                       @Valid @ModelAttribute("form") BoardForm form,
                       BindingResult bindingResult,
                       Model model){
        if(bindingResult.hasErrors()){
            model.addAttribute("postId",id);
            return "board/list";
        }
      String username =  SecurityUtils.currentUsername();
      try{
        boardService.update(id,form.getTitle(),form.getContent(),username, form.getFiles());
      }catch (IllegalArgumentException e){
          model.addAttribute("error", e.getMessage());
          model.addAttribute("postId", id);
          return "board/edit";
      }
      return "redirect:/posts/" + id;
    }


    @PostMapping("/{id:\\d+}/delete")
    public String delete(@PathVariable Long id, Model model){
        String username= SecurityUtils.currentUsername();
        try{
            boardService.delete(id, username);
        }catch (IllegalArgumentException e){
            model.addAttribute("error", e.getMessage());
            Board board =boardService.findById(id);
            model.addAttribute("post", board);
            model.addAttribute("canEdit", canEdit(board));
            return "board/detail";
        }
        return "redirect:/posts";

    }




}
