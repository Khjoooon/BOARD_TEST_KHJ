package com.aloha.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.aloha.board.dto.Board;
import com.aloha.board.server.BoardService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;




@Slf4j
@Controller
@RequestMapping("/board")
public class BoardController {
    
    @Autowired
    private BoardService boardService;

    /**
     * 목록 화면    [GET]
     * 조회 화면    [GET]
     * 등록 화면    [GET]
     * 등록 처리    [POST]
     * 수정 화면    [GET]
     * 수정 처리    [POST]
     * 삭제 처리    [POST]
     */


  /**
   * 
   * @param model
   * @return
   * @throws Exception
   */
     @GetMapping("/list")
     public String list(Model model) throws Exception {
        log.info("목록 화면...");
        List<Board> boardList = boardService.list();     
        model.addAttribute("boardList", boardList);
      
        return "/board/list";
    }

    /**
     * 
     * @param no
     * @param model
     * @return
     * @throws Exception 
     */
    @GetMapping("/read")
    public String select(@RequestParam("no") int no, Model model) throws Exception {
        log.info("조회 화면...");
        Board board = boardService.select(no);     
        model.addAttribute("board", board);
     
        return "/board/read";
    }

    /**
     * 
     * @return
     */
    @GetMapping("/insert")
    public String insert() {
        log.info("등록 화면...");

        return "/board/insert";
    }

    /**
     * 
     * @param board
     * @return
     * @throws Exception 
     */
    @PostMapping("/insert")
    public String insertPro(Board board) throws Exception {     
        int result = boardService.insert(board);    

        if( result > 0 ) {
            return "redirect:/board/list";
        }    

        return "redirect:/board/insert?error";
    }
    
    /**
     * 
     * @param no
     * @param model
     * @return
     * @throws Exception
     */
    @GetMapping("/update")
    public String update(@RequestParam("no") int no, Model model) throws Exception {
        log.info("수정 화면...");
        Board board = boardService.select(no);
        model.addAttribute("board", board);

        return "/board/update";
    }

    /**
     * 
     * @param board
     * @return
     * @throws Exception 
     */
    @PostMapping("/update")
    public String updatetPro(Board board) throws Exception {
      
        int result = boardService.update(board);     

        if( result > 0 ) {
            return "redirect:/board/list";
        }    

        int no = board.getNo();

        return "redirect:/board/update?no=" + no + "&error";
    }

    /**
     * 
     * @param board
     * @return
     * @throws Exception 
     */
    @PostMapping("/delete")
    public String deletetPro(@RequestParam("no") int no) throws Exception {     
        int result = boardService.delete(no);
      
        if( result > 0 ) {
            return "redirect:/board/list";
        }    
      
        return "redirect:/board/delete?no=" + no + "&error";
    }
    
    
    
     
}
